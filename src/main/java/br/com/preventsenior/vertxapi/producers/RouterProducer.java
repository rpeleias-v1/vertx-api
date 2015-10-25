package br.com.preventsenior.vertxapi.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

@ApplicationScoped
public class RouterProducer {

	@Inject
	private Vertx vertx;
	
	private HttpServer httpServer;

	@Produces
	@ApplicationScoped
	public Router createRouter() {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		httpServer = vertx.createHttpServer().requestHandler(router::accept).listen(8080);
		return router;
	}
	
	public void stop(@Disposes Router router) {
		router.clear();
		httpServer.close();
	}
}
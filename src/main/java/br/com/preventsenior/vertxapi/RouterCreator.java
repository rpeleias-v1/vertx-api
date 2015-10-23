package br.com.preventsenior.vertxapi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

@ApplicationScoped
public class RouterCreator {

	@Inject
	private Vertx vertx;

	@Produces	
	public Router createRouter() {
		Router router = Router.router(vertx);
		vertx.createHttpServer().requestHandler(router::accept).listen(8080, result -> {
			if (result.succeeded()) {
				
			} else {

			}
		});
		return router;
	}
	
	public void stop() {
		
	}
}

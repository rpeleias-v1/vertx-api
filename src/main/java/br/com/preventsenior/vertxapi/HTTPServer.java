package br.com.preventsenior.vertxapi;

import javax.inject.Inject;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class HTTPServer extends CDIVerticle {

	@Inject
	private Router router;

	@Override
	protected void onVertxStart() {				
		router.route("/rodrigo").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end(
					"<h1>Vertx application</h1>");
		});	
		
		router.route("/zaca").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end(
					"<h1>funciona</h1>");
		});	
	}

	@Override
	protected void onVertxStop() {

	}

}

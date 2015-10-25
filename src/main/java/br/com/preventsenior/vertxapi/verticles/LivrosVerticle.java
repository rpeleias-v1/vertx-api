package br.com.preventsenior.vertxapi.verticles;

import javax.inject.Inject;

import br.com.preventsenior.vertxapi.CDIVerticle;
import br.com.preventsenior.vertxapi.dao.LivroDAO;
import br.com.preventsenior.vertxapi.model.Livro;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class LivrosVerticle extends CDIVerticle {

	@Inject
	private LivroDAO dao;

	@Inject
	private Router router;

	@Override
	protected void onVertxStart() {
		router.get("/livros").handler(this::getAll);
		router.get("/livros/:id").handler(this::getOne);
		
	}

	@Override
	protected void onVertxStop() {
		// TODO Auto-generated method stub

	}

	private void getAll(RoutingContext context) {
		context.response().putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(dao.getAll()));
	}

	private void getOne(RoutingContext context) {
		String id = context.request().getParam("id");
		if (id == null) {
			context.response().setStatusCode(400).end();
		} else {
			Long idAsLong = Long.valueOf(id);
			Livro livro = dao.get(idAsLong);
			if (livro == null) {
				context.response().setStatusCode(400).end();
			} else {
				context.response().putHeader("content-type", "application/json; charset=utf-8")
						.end(Json.encodePrettily(livro));
			}
		}
	}

}

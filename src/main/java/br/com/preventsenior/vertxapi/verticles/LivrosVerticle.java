package br.com.preventsenior.vertxapi.verticles;

import javax.inject.Inject;

import br.com.preventsenior.vertxapi.CDIVerticle;
import br.com.preventsenior.vertxapi.dao.LivroDAO;
import br.com.preventsenior.vertxapi.model.Livro;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class LivrosVerticle extends CDIVerticle {

	@Inject
	private LivroDAO dao;

	@Inject
	private Router router;

	@Override
	protected void onVertxStart() {
		router.get("/api/livros").handler(this::getAll);
		router.get("/api/livros/:id").handler(this::getOne);
		router.delete("/api/livros/:id").handler(this::deleteBook);
		router.post("/api/livros").handler(this::addBook);		
		router.put("/api/livros/:id").handler(this::updateBook);
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

	private void addBook(RoutingContext context) {
		String stringContent = context.getBodyAsString();
		Livro livro = Json.decodeValue(stringContent, Livro.class);
		dao.insert(livro);
		context.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(livro));
	}

	private void updateBook(RoutingContext context) {
		String pathId = context.request().getParam("id");
		JsonObject jsonObject = context.getBodyAsJson();
		if (pathId == null || jsonObject == null) {
			context.response().setStatusCode(400).end();
		} else {
			Long id = Long.valueOf(pathId);
			Livro livro = dao.get(id);
			if (livro == null) {
				context.response().setStatusCode(400).end();
			} else {
				assignJsonValueToLivro(jsonObject, livro);
				context.response().putHeader("content-type", "application/json; charset=utf-8")
						.end(Json.encodePrettily(livro));
			}
		}
	}

	private void assignJsonValueToLivro(JsonObject jsonObject, Livro livro) {
		if (jsonObject.getString("nome") != null) {
			livro.setNome(jsonObject.getString("nome"));			
		}
		if (jsonObject.getString("isbn") != null) {
			livro.setIsbn(jsonObject.getString("isbn"));			
		}
		if (jsonObject.getString("quantidadePaginas") != null) {
			livro.setQuantidadePaginas(Integer.parseInt(jsonObject.getString("quantidadePaginas")));
		}
	}

	private void deleteBook(RoutingContext context) {
		String pathId = context.request().getParam("id");
		if (pathId == null) {
			context.response().setStatusCode(400).end();
		} else {
			Long id = Long.valueOf(pathId);
			dao.delete(id);
			context.response().setStatusCode(204).end();
		}

	}

}

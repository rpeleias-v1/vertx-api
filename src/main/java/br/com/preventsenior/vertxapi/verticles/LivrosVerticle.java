package br.com.preventsenior.vertxapi.verticles;

import javax.inject.Inject;

import br.com.preventsenior.vertxapi.CDIVerticle;
import br.com.preventsenior.vertxapi.dao.LivroDAO;
import io.vertx.ext.web.Router;

public class LivrosVerticle extends CDIVerticle{
	
	@Inject
	private LivroDAO dao;
	
	@Inject
	private Router router;

	@Override
	protected void onVertxStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onVertxStop() {
		// TODO Auto-generated method stub
		
	}

}

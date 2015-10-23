package br.com.preventsenior.vertxapi;

import javax.inject.Inject;

import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public abstract class CDIVerticle implements Verticle{

	@Inject
	private Vertx vertx;
	
	@Override
	public Vertx getVertx() {
		return vertx;
	}

	@Override
	public void init(Vertx vertx, Context context) {
		System.out.println("Vertx injetado = " + this.vertx);
		System.out.println("Vertx passado como parâmetro = " + vertx);
	}

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		onVertxStart();
		startFuture.complete();
	}

	protected abstract void onVertxStart();

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		onVertxStop();
		stopFuture.complete();
	}

	protected abstract void onVertxStop();

}

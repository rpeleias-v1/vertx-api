package br.com.preventsenior.vertxapi;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

@ApplicationScoped
public class VertxLauncher {

	private Vertx vertx;
	
	@Inject
	@Any
	private Instance<Verticle> allDiscoveredVerticles;;
	
	public void initVertx(@Observes @Initialized(ApplicationScoped.class) Object object) {
		this.vertx = Vertx.vertx();
		
		allDiscoveredVerticles.forEach(verticle -> {
			System.out.println("Encontrado o verticle = " + verticle);
			vertx.deployVerticle(verticle);
		});
	}
	
	@Produces
	@ApplicationScoped
	public Vertx getVertx() {
		return vertx;
	}
	
	@PreDestroy
	public void shutdown() {
		this.vertx.close();
	}
	
}

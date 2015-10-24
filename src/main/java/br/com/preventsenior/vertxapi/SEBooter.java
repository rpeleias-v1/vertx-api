package br.com.preventsenior.vertxapi;

import org.jboss.weld.environment.se.Weld;

public class SEBooter {

	public static void main(String[] args) {
		Weld weld = new Weld();
		weld.initialize();
	}
}

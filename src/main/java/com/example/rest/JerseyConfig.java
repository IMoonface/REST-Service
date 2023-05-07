package com.example.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Class that represents the Jersey configuration.
 * @extends ResourceConfig the resource configuration for configuring a web application.
 * @author Marc Uxa
 * 
 */
@Component
public class JerseyConfig extends ResourceConfig {

	/**
	 * Default constructor.
	 */
	public JerseyConfig() {
		/**
		 * Register a class of a custom JAX-RS component to be instantiated and 
		 * used in the scope of this configurable context.
		 */
		register(TodoResource.class);
	}
}
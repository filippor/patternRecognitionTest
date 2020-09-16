package it.filippor.test.pattern.recognition.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import it.filippor.straight.line.recognition.StraightLineRecognitionIncremental;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {
	@Inject
	StraightLineRecognitionIncremental service;
    
	@GET
    public String sayHello() {
    	
        return "Hello World " + service.getLines(2);
    }
}

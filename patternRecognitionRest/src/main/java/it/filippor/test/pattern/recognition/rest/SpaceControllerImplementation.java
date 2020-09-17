package it.filippor.test.pattern.recognition.rest;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.filippor.straight.line.recognition.StraightLineRecognitionIncremental;

/**
 *
 */
@Path("/")
public class SpaceControllerImplementation implements SpaceController {
	
	StraightLineRecognitionIncremental service;
	
	@Inject
	public SpaceControllerImplementation(StraightLineRecognitionIncremental service) {
		this.service = service;
	}
	
	@Override
	public Response addPoint(Point p) {
		var domainP = p.toDomainPoint();
		if (service.contains(domainP))
			return Response.status(Status.CONFLICT.getStatusCode(), "Point already added").build();
		service.addPoint(domainP);
		return Response.ok("added " + p).build();
	}

	@Override
	public Collection<Point> getSpace() {
		return service.getPoints().stream().map(Point::fromDomainPoint).collect(toUnmodifiableList());
	}

	@Override
	public Collection<Collection<Point>> getLines(
			 int n) {
		return service.getLines(n).stream()
				.map(line -> line.stream().map(Point::fromDomainPoint).collect(toUnmodifiableList()))
				.collect(toUnmodifiableList());
	}

	@Override
	public Response clearSpace() {
		service.clear();
		return Response.ok().build();
	}
}

package it.filippor.test.pattern.recognition.rest;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public interface SpaceController {
	@POST
	@Path("point")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Add new point to space", 
				description = "Add new point to space, Space cannot contain duplicated point")
	@APIResponses(value = {
			@APIResponse(responseCode = "409", description = "Point already added", content = @Content(mediaType = "text/plain")),
			@APIResponse(responseCode = "200", description = "Point added to space", content = @Content(mediaType = "text/plain")) 
			})
	Response addPoint(@NotNull @Valid Point p);

	@GET
	@Path("space")
	@Operation(summary = "Return all point in space")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Point in space", 
					content = @Content(mediaType = "application/json",example = "[{\"x\":12, \"y\":23},{\"x\":13, \"y\":15}]")) 
			})
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Point> getSpace();

	@GET
	@Path("line/{n}")
	@Operation(summary = "Return all straiight line with minmum {n} point ")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Line with minimum {n} lenght", 
					content = @Content(mediaType = "application/json",example = "[\n\t[{\"x\":12, \"y\":23},{\"x\":13, \"y\":15}],\n\t[{\"x\":12, \"y\":23},{\"x\":12, \"y\":12}]\n]")) 
			})
	@Produces(MediaType.APPLICATION_JSON)
	Collection<Collection<Point>> getLines(@Parameter(description = "Minimum lenght, in point, of the requested line", required = true, example = "2", schema = @Schema(type = SchemaType.INTEGER)) @Min(value = 2, message = "line with less than 2 point has no meaning") @PathParam("n")int n);

	@DELETE
	@Path("space")
	@Operation(summary = "Remove all point from space")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "all point removed")
			})
	Response clearSpace();

}
package com.github.sanchezih.jersey.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.sanchezih.jersey.entity.Usuario;

@Path("/users")
public class UsuarioService {

	private static List<Usuario> listaUsuarios = new ArrayList<Usuario>() {
		private static final long serialVersionUID = 1L;
		{
			add(new Usuario("mperez", "Mariela"));
			add(new Usuario("jlopez", "Juan"));
			add(new Usuario("savila", "Sol"));
		}
	};

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		return Response.ok(listaUsuarios).build();
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	@GET
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByUsername(@PathParam("username") String username) {
		Usuario found = null;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getUsername().equalsIgnoreCase(username)) {
				found = listaUsuarios.get(i);
			}
		}
		if (found != null) {
			return Response.ok(found).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Usuario no encontrado").build();
		}
	}

	/**
	 * NOTA: Debe existir el constructor por default en la entidad
	 * 
	 * @param userRequest
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(Usuario userRequest) {

		listaUsuarios.add(userRequest);
		return Response.ok(listaUsuarios).build();

	}

	/**
	 * NOTA: Debe existir el constructor por default en la entidad
	 * 
	 * @param usuario
	 * @return
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(Usuario usuario) {
		Usuario found = null;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getUsername().equalsIgnoreCase(usuario.getUsername())) {
				found = listaUsuarios.get(i);
				found.setName(usuario.getName());
				listaUsuarios.set(i, found);
			}
		}

		if (found != null) {
			return Response.ok(found).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Usuario no encontrado").build();
		}
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	@DELETE
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("username") String username) {
		Usuario found = null;
		for (int i = 0; i < listaUsuarios.size(); i++) {
			if (listaUsuarios.get(i).getUsername().equalsIgnoreCase(username)) {
				found = listaUsuarios.get(i);
				listaUsuarios.remove(found);
			}
		}

		if (found != null) {
			return Response.ok(listaUsuarios).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Usuario no encontrado").build();
		}
	}

}
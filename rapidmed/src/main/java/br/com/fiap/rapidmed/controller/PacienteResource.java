package br.com.fiap.rapidmed.controller;
import java.util.ArrayList;

import br.com.fiap.rapidmed.model.entity.Paciente;
import br.com.fiap.rapidmed.model.excecao.ErroInfraestruturaException;
import br.com.fiap.rapidmed.model.repository.PacienteRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path ("/paciente")

public class PacienteResource {



		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response findById(@PathParam("id") Long id) {
		    Paciente resposta = PacienteRepository.findById(id);
		    ResponseBuilder response = Response.ok();
		    if (resposta == null) {
		        response = Response.status(404);
		    }
		    response.entity(resposta);
		    return response.build();
		}

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response findAll() {
			ArrayList<Paciente> resposta = PacienteRepository.findAll();
			ResponseBuilder response = Response.ok();
			response.entity(resposta);
			return response.build();
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response save(@Valid Paciente paciente) throws ErroInfraestruturaException {
			
		 // Verificar se o CPF já existe
		    if (PacienteRepository.existsByCpf(paciente.getCpf())) {
		        ResponseBuilder response = Response.status(400); // 400 - Bad Request
		        throw new ErroInfraestruturaException("CPF já cadastrado", response);
		    }
			Paciente resposta = PacienteRepository.save(paciente);
			ResponseBuilder response = null;
			if (resposta != null) {
				response = Response.created(null); // 201 - Created
			} else {
				response = Response.status(400); // 400 - Bad Request
				throw new ErroInfraestruturaException("ERRO AO SALVAR PACIENTE", response);
			}
			response.entity(resposta);
			return response.build();
		}
		
		@DELETE
		@Path("/{id}")
		public Response delete(@PathParam("id") Long id) {
			if (PacienteRepository.delete(id)) {
				ResponseBuilder response = Response.noContent(); // 204 - No Content
				return response.build();
			} else {
				ResponseBuilder response = Response.status(404); // 404 - Not Found
				return response.build();
			}
		}
		
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response update(@Valid Paciente paciente)throws ErroInfraestruturaException {
			Paciente resposta = PacienteRepository.update(paciente);
			ResponseBuilder response = null;
			if (resposta != null) {
				response = Response.created(null); // 201 Created
			} else {
				response = Response.status(400); // 400 Bad Request
				throw new ErroInfraestruturaException("ERRO AO ALTERAR O PACIENTE", response);
			}
			response.entity(resposta);
			return response.build();
		}
		
	}

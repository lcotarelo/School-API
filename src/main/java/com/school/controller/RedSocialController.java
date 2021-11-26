package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.school.error.CustomError;
import com.school.model.RedSocial;
import com.school.service.RedSocialServiceImpl;

@RestController
@RequestMapping(value = "/v1/api/rrss")
public class RedSocialController {

	@Autowired
	private RedSocialServiceImpl redSocialServiceImpl;

	@GetMapping
	public ResponseEntity<List<RedSocial>> getRedesSociales(
			@RequestParam(value = "name", required = false) String name) {
		List<RedSocial> redes = new ArrayList<>();
		if (name == null) {
			redes = redSocialServiceImpl.getAll();
			if (redes.isEmpty()) {
				return new ResponseEntity(new CustomError("No existen redes sociales registradas"),
						HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<RedSocial>>(redes, HttpStatus.OK);
		} else {
			redes = redSocialServiceImpl.getByName(name);
			if (redes.size() >= 1) {
				return new ResponseEntity<List<RedSocial>>(redes, HttpStatus.OK);
			} else {
				return new ResponseEntity(new CustomError("No existe la red solicitada"), HttpStatus.OK);
			}
		}
	}

	@PostMapping
	public ResponseEntity<RedSocial> addRedSocial(@RequestBody RedSocial redSocial,
			UriComponentsBuilder uriComponentsBuilder) throws Exception {
		redSocialServiceImpl.create(redSocial);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				uriComponentsBuilder.path("/v1/api/rrss/{id}").buildAndExpand(redSocial.getId_RedSocial()).toUri());
		return new ResponseEntity<RedSocial>(redSocial, HttpStatus.CREATED);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<RedSocial> getById(@PathVariable(value = "id") Long id_RedSocial) throws Exception {
		RedSocial redSocial = redSocialServiceImpl.getById(id_RedSocial);
		if (id_RedSocial == null || id_RedSocial <= 0) {
			return new ResponseEntity(new CustomError("No se ingreso un id correcto"), HttpStatus.CONFLICT);
		}
		if (redSocial == null) {
			return new ResponseEntity(new CustomError("No se existe la red social con el id " + id_RedSocial),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RedSocial>(redSocial, HttpStatus.OK);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<RedSocial> updateRedSocial(@PathVariable(value = "id") Long id_RedSocial,
			@RequestBody RedSocial redSocial) throws Exception {
		RedSocial redAux = redSocialServiceImpl.getById(id_RedSocial);
		redAux = RedSocial.builder().id_RedSocial(id_RedSocial).icono(redSocial.getIcono())
				.nombre(redSocial.getNombre()).build();
		redSocialServiceImpl.update(redSocial);
		return new ResponseEntity<RedSocial>(redSocial, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity deleteRedSocial(@PathVariable(value = "id") Long id_RedSocial) throws Exception {
		RedSocial redSocial = redSocialServiceImpl.getById(id_RedSocial);
		if (id_RedSocial == null || id_RedSocial <= 0) {
			return new ResponseEntity(new CustomError("No se ingreso un id valido"), HttpStatus.CONFLICT);
		}
		if(redSocial == null) {
			return new ResponseEntity(new CustomError("No se encuentra la red social"), HttpStatus.NOT_FOUND);
		}
		redSocialServiceImpl.remove(id_RedSocial);
		return new ResponseEntity("Red social eliminada", HttpStatus.OK);
	}
}

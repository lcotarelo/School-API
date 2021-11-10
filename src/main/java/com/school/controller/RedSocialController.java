package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.error.CustomError;
import com.school.model.RedSocial;
import com.school.service.RedSocialService;

@RestController
@RequestMapping(value="/v1/api/redessociales")
public class RedSocialController {

	@Autowired
	private RedSocialService redSocialService;
	
	@GetMapping
	public ResponseEntity<List<RedSocial>> getRedesSociales(
			@RequestParam(value = "name", required = false) String name) {

		List<RedSocial> redes = new ArrayList<>();
		
				
		if (name == null) {
			redes = redSocialService.getAll();
			if (redes.isEmpty()) {
				return new ResponseEntity(new CustomError("No existen profesores"), HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<RedSocial>>(redes, HttpStatus.OK);
		} else {
			redes = redSocialService.getByName(name);
			if (redes.size() >= 1) {
				return new ResponseEntity<List<RedSocial>>(redes, HttpStatus.OK);
			} else {
				return new ResponseEntity(new CustomError("No existe la red solicitada"), HttpStatus.OK);
			}
		}
	}

}

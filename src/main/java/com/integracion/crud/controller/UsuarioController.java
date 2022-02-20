package com.integracion.crud.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integracion.crud.dto.MensajeResponse;
import com.integracion.crud.dto.MensajesResponse;
import com.integracion.crud.dto.UsuarioListaResponse;
import com.integracion.crud.dto.UsuarioRequest;
import com.integracion.crud.dto.UsuarioResponse;
import com.integracion.crud.mapper.UsuarioMapper;
import com.integracion.crud.model.entity.Usuario;
import com.integracion.crud.model.service.UsuarioService;
import com.integracion.crud.security.dto.JwtDto;
import com.integracion.crud.security.dto.LoginUsuarioDto;
import com.integracion.crud.security.jwt.JwtProvider;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin
@PropertySource("classpath:mensajes.properties")
public class UsuarioController {

	@Autowired
	Environment env;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody UsuarioRequest nuevoUsuario, BindingResult bindingResult) {

		ResponseEntity<?> result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		List<MensajeResponse> mensaje = new ArrayList<>();
		if (bindingResult.hasErrors()) {

			bindingResult.getAllErrors().forEach((error) -> {
				FieldError fieldError = (FieldError) error;
				mensaje.add(new MensajeResponse(env.getProperty("codigo.error.campo"), fieldError.getDefaultMessage(),
						fieldError.getField()));
			});
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajesResponse(mensaje));

		} else if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			mensaje.add(new MensajeResponse(env.getProperty("codigo.error.dato"), env.getProperty("mensaje.error.mail"),
					env.getProperty("mensaje.campo.mail")));
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajesResponse(mensaje));
		} else {
			try {

				UsuarioResponse usuarioResponse = usuarioService.registarUsuario(nuevoUsuario);

				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(nuevoUsuario.getEmail(), nuevoUsuario.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtProvider.generateToken(authentication);

				usuarioResponse.setToken(jwt);

				Usuario userUpdate = usuarioService.getById(usuarioResponse.getId());
				userUpdate.setToken(jwt);
				usuarioService.save(userUpdate);

				result = ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);

			} catch (NoSuchElementException | InvalidDataAccessApiUsageException c) {
				mensaje.add(new MensajeResponse(env.getProperty("codigo.error.interno"),
						env.getProperty("mensaje.error.interno"), env.getProperty("mensaje.campo.interno")));
				result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajesResponse(mensaje));
			}

		}

		return result;
	}

	@PostMapping("/actualizar")
	public ResponseEntity<?> actualizar(@Valid @RequestBody UsuarioRequest nuevoUsuario, BindingResult bindingResult) {

		ResponseEntity<?> result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		List<MensajeResponse> mensaje = new ArrayList<>();
		if (bindingResult.hasErrors()) {

			bindingResult.getAllErrors().forEach((error) -> {
				FieldError fieldError = (FieldError) error;
				mensaje.add(new MensajeResponse(env.getProperty("codigo.error.campo"), fieldError.getDefaultMessage(),
						fieldError.getField()));
			});
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajesResponse(mensaje));

		} else if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {

			try {

				UsuarioResponse usuarioResponse = usuarioService.actulizarUsuario(nuevoUsuario);

				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(nuevoUsuario.getEmail(), nuevoUsuario.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtProvider.generateToken(authentication);

				usuarioResponse.setToken(jwt);

				Usuario userUpdate = usuarioService.getById(usuarioResponse.getId());
				userUpdate.setToken(jwt);
				usuarioService.save(userUpdate);

				result = ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);

			} catch (NoSuchElementException | InvalidDataAccessApiUsageException | DataIntegrityViolationException c) {
				mensaje.add(new MensajeResponse(env.getProperty("codigo.error.interno"),
						env.getProperty("mensaje.error.interno"), env.getProperty("mensaje.campo.interno")));
				result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajesResponse(mensaje));
			}

		} else {

			mensaje.add(new MensajeResponse(env.getProperty("codigo.error.dato"), env.getProperty("mensaje.error.mail.no.exit"),
					env.getProperty("mensaje.campo.mail.no.exit")));
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajesResponse(mensaje));

		}

		return result;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuarioDto loginUsuario, BindingResult bindingResult) {

		ResponseEntity<?> result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		List<MensajeResponse> mensaje = new ArrayList<>();
		if (bindingResult.hasErrors()) {

			bindingResult.getAllErrors().forEach((error) -> {
				FieldError fieldError = (FieldError) error;
				mensaje.add(new MensajeResponse(env.getProperty("codigo.error.campo"), fieldError.getDefaultMessage(),
						fieldError.getField()));
			});
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensajesResponse(mensaje));

		} else {
			try {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword()));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				String jwt = jwtProvider.generateToken(authentication);

				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

				Usuario userUpdate = usuarioService.getByEmail(loginUsuario.getEmail()).get();
				userUpdate.setLast_login(Instant.now());
				usuarioService.save(userUpdate);

				result = ResponseEntity.status(HttpStatus.OK).body(jwtDto);

			} catch (AuthenticationException e) {
				mensaje.add(new MensajeResponse(env.getProperty("codigo.error.auth"),
						env.getProperty("mensaje.error.auth"), env.getProperty("mensaje.campo.auth")));
				result = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MensajesResponse(mensaje));
			} catch (NoSuchElementException | InvalidDataAccessApiUsageException c) {
				mensaje.add(new MensajeResponse(env.getProperty("codigo.error.interno"),
						env.getProperty("mensaje.error.interno"), env.getProperty("mensaje.campo.interno")));
				result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MensajesResponse(mensaje));
			}

		}

		return result;
	}
	
	

		@GetMapping("/lista")
	    public ResponseEntity<List<UsuarioListaResponse>> list(){
		 List<UsuarioListaResponse> usuarios = new ArrayList<>();
		 usuarioService.list().forEach(dato ->{ usuarios.add(UsuarioMapper.INSTANCE.mapListaToDto(dato));});
         return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	    }

}

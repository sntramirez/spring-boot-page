package com.integracion.crud.model.service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.integracion.crud.dto.UsuarioRequest;
import com.integracion.crud.dto.UsuarioResponse;
import com.integracion.crud.mapper.UsuarioMapper;
import com.integracion.crud.model.entity.Rol;
import com.integracion.crud.model.entity.Usuario;
import com.integracion.crud.model.repository.UsuarioRepository;
import com.integracion.crud.security.enums.RolNombre;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RolService rolService;

	public Optional<Usuario> getByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	public UsuarioResponse registarUsuario(UsuarioRequest nuevoUsuario) {
		Usuario usuario = UsuarioMapper.INSTANCE.mapToEntity(nuevoUsuario);
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		usuario.setCreated(Instant.now());
		usuario.setIsactive(true);
		usuario.setLast_login(Instant.now());
		usuario.setModified(Instant.now());
		usuario.setToken(null);

		if (usuario.getRoles().isEmpty()) {
			Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
			usuario.setRoles(roles);
		} else {
			Set<Rol> roles = new HashSet<>();
			usuario.getRoles().forEach(rolTmp -> {
				roles.add(rolService.getByRolNombre(rolTmp.getRolNombre()).get());
			});
			usuario.getRoles().clear();
			usuario.setRoles(roles);
		}

		return UsuarioMapper.INSTANCE.mapToDto(usuarioRepository.save(usuario));
	}
	
	
	public UsuarioResponse actulizarUsuario(UsuarioRequest nuevoUsuario) {
		Usuario usuario = UsuarioMapper.INSTANCE.mapToEntity(nuevoUsuario);
	
		
		Usuario usuarioUpdate = usuarioRepository.findByEmail(nuevoUsuario.getEmail()).get();
		
		
		usuarioUpdate.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		usuarioUpdate.setModified(Instant.now());
		usuarioUpdate.setName(usuario.getName());
		
		//usuarioUpdate  = UsuarioMapper.INSTANCE.mapUsuarioToUsuario(usuario);
		

		/*if (usuario.getRoles().isEmpty()) {
			Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
			usuario.setRoles(roles);
		} else {
			Set<Rol> roles = new HashSet<>();
			usuario.getRoles().forEach(rolTmp -> {
				roles.add(rolService.getByRolNombre(rolTmp.getRolNombre()).get());
			});
			usuario.getRoles().clear();
			usuario.setRoles(roles);
		}*/
		
		//if()

		return UsuarioMapper.INSTANCE.mapToDto(usuarioRepository.save(usuarioUpdate));
	}
	
	public void save (Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public Usuario getById(UUID id) {
		return usuarioRepository.getById(id);
	}
	
	   public List<Usuario> list(){
	        return usuarioRepository.findAll();
	    }
	
}

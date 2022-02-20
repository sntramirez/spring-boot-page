package com.integracion.crud.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.integracion.crud.model.entity.Usuario;
import com.integracion.crud.model.service.UsuarioService;
import com.integracion.crud.security.dto.UsuarioPrincipalDto;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByEmail(nombreUsuario).get();
        return UsuarioPrincipalDto.build(usuario);
    }
}

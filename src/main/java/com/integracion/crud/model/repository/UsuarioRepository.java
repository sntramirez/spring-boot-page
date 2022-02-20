package com.integracion.crud.model.repository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.integracion.crud.model.entity.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository <Usuario,UUID> {
	public Optional<Usuario> findByEmail(String email);
    public boolean existsByEmail(String email);
}

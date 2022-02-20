package com.integracion.crud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.integracion.crud.dto.TelefonoRequest;
import com.integracion.crud.dto.UsuarioListaResponse;
import com.integracion.crud.dto.UsuarioRequest;
import com.integracion.crud.dto.UsuarioResponse;
import com.integracion.crud.model.entity.Rol;
import com.integracion.crud.model.entity.Telefono;
import com.integracion.crud.model.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

	@Mapping(target = "id", source = "usuario.id")
	@Mapping(target = "created", source = "usuario.created", dateFormat = "dd-MM-yyyy HH:mm")
	@Mapping(target = "modified", source = "usuario.modified", dateFormat = "dd-MM-yyyy HH:mm")
	@Mapping(target = "last_login", source = "usuario.last_login")
	@Mapping(target = "token", source = "usuario.token")
	@Mapping(target = "isactive", source = "usuario.isactive")
	UsuarioResponse mapToDto(Usuario usuario);
	
	
	@Mapping(target = "email", source = "usuario.email")
	@Mapping(target = "created", source = "usuario.created", dateFormat = "dd-MM-yyyy HH:mm")
	@Mapping(target = "modified", source = "usuario.modified", dateFormat = "dd-MM-yyyy HH:mm")
	@Mapping(target = "last_login", source = "usuario.last_login")
	@Mapping(target = "isactive", source = "usuario.isactive")
	UsuarioListaResponse mapListaToDto(Usuario usuario);
	
	@Mapping(target = "id",  ignore = true)
	@Mapping(target = "created",  ignore = true)
	@Mapping(target = "last_login",  ignore = true)
	@Mapping(target = "token",  ignore = true)
	@Mapping(target = "isactive",  ignore = true)
	@Mapping(target = "email",  ignore = true)
	Usuario mapUsuarioToUsuario(Usuario usuario);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "number", source = "telefono.number")
	@Mapping(target = "citycode", source = "telefono.citycode")
	@Mapping(target = "contrycode", source = "telefono.contrycode")
	Telefono mapTelefonoToEntity(TelefonoRequest telefono);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", source = "usuario.name")
	@Mapping(target = "created", ignore = true)
	@Mapping(target = "modified", ignore = true)
	@Mapping(target = "last_login", ignore = true)
	@Mapping(target = "token", ignore = true)
	@Mapping(target = "isactive", ignore = true)
	@Mapping(target = "phones", expression = "java(usuario.getPhones().stream().map(dato -> mapTelefonoToEntity(dato)).collect(java.util.stream.Collectors.toList()))")
	@Mapping(target = "roles", expression = "java(usuario.getRoles().stream().map(dato ->  mapRolToEntity(dato)).collect(java.util.stream.Collectors.toList()))")
	Usuario mapToEntity(UsuarioRequest usuario);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "rolNombre", expression = "java(\"admin\".contains(roles)?com.integracion.crud.security.enums.RolNombre.ROLE_ADMIN:com.integracion.crud.security.enums.RolNombre.ROLE_USER)")
	Rol mapRolToEntity(String roles);


}
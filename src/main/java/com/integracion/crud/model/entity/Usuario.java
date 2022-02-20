package com.integracion.crud.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TB_USUARIOS")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "CODIGO", updatable = false, nullable = false)
	private UUID id;

	@Column(name = "NOMBRES")
	private String name;
	
	@Column(name = "MAIL", unique = true, nullable = false)
	private String email;
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FECHA_CREACION")
	private Instant created;
	
	@Column(name = "FECHA_MODIFICACION")
	private Instant modified;

	@Column(name = "ULTIMO_ACCESO")
	private Instant last_login;
	
	@Column(name = "TOKEN")
	private String token;

	@Column(name = "ACTIVO")
	private boolean isactive;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CODIGO_USUARIO")
	private Collection<Telefono> phones;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_USUARIO_ROL", joinColumns = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns = @JoinColumn(name = "ROL_ID"))
	private Collection<Rol> roles;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Telefono> getPhones() {
		return phones;
	}

	public void setPhones(Collection<Telefono> phones) {
		this.phones = phones;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getModified() {
		return modified;
	}

	public void setModified(Instant modified) {
		this.modified = modified;
	}

	public Instant getLast_login() {
		return last_login;
	}

	public void setLast_login(Instant last_login) {
		this.last_login = last_login;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

}

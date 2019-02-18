package com.example.mail.entity;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Role() {
	}

	public Role(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "role")
	private String rolename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + "]";
	}
	
	
}

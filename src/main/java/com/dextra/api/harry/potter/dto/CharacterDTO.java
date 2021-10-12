package com.dextra.api.harry.potter.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.dextra.api.harry.potter.entities.Character;

public class CharacterDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty(message = "Name is required")
	private String name;
	private String role;
	private String school;
	@NotEmpty(message = "House is required")
	private String house;
	private String patronus;

	public CharacterDTO() {
	}

	public CharacterDTO(Long id, String name, String role, String school, String house, String patronus) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.school = school;
		this.house = house;
		this.patronus = patronus;
	}

	public CharacterDTO(Character entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.role = entity.getRole();
		this.school = entity.getSchool();
		this.house = entity.getHouse();
		this.patronus = entity.getPatronus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getPatronus() {
		return patronus;
	}

	public void setPatronus(String patronus) {
		this.patronus = patronus;
	}
}

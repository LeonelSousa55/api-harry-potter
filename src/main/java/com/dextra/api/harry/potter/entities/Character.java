package com.dextra.api.harry.potter.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "character")
public class Character implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String role;
	private String school;
	private String house;
	private String patronus;

	public Character() {
	}

	public Character(Long id, String name, String role, String school, String house, String patronus) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.school = school;
		this.house = house;
		this.patronus = patronus;
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

	@Override
	public int hashCode() {
		return Objects.hash(house, id, name, patronus, role, school);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return Objects.equals(house, other.house) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(patronus, other.patronus) && Objects.equals(role, other.role)
				&& Objects.equals(school, other.school);
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", role=" + role + ", school=" + school + ", house=" + house
				+ ", patronus=" + patronus + "]";
	}
}

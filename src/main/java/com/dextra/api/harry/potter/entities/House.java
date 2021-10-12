package com.dextra.api.harry.potter.entities;

import java.io.Serializable;
import java.util.Objects;

public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	public House() {
	}

	public House(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		House other = (House) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Houses [id=" + id + "]";
	}
}

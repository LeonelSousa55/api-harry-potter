package com.dextra.api.harry.potter.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class ListHouse implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<House> houses = new ArrayList<>();

	public ListHouse() {
	}

	public ArrayList<House> getHouses() {
		return houses;
	}

	public void setHouses(ArrayList<House> houses) {
		this.houses = houses;
	}

	@Override
	public String toString() {
		return "ListHouse [houses=" + houses + "]";
	}
}

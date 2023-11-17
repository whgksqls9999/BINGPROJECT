package com.bing.community.model.dto;

public class Location {
	private int location_id;
	private String place_name;
	private String address_name;

	public Location() {
		// TODO Auto-generated constructor stub
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public Location(int location_id, String place_name, String address_name) {
		super();
		this.location_id = location_id;
		this.place_name = place_name;
		this.address_name = address_name;
	}

}

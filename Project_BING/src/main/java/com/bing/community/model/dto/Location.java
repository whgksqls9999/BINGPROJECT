package com.bing.community.model.dto;

public class Location {
	private int location_id;
	private String place_name;
	private String address_name;
	private int fav_cnt;

	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(int location_id, String place_name, String address_name, int fav_cnt) {
		super();
		this.location_id = location_id;
		this.place_name = place_name;
		this.address_name = address_name;
		this.fav_cnt = fav_cnt;
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

	public int getFav_cnt() {
		return fav_cnt;
	}

	public void setFav_cnt(int fav_cnt) {
		this.fav_cnt = fav_cnt;
	}

}

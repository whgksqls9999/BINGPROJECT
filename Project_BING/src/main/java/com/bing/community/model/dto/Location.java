package com.bing.community.model.dto;

public class Location {
	private int location_id;
	private String location_name;
	private String longitude;
	private String latitude;

	public Location(int location_id, String location_name, String longitude, String latitude) {
		super();
		this.location_id = location_id;
		this.location_name = location_name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

}

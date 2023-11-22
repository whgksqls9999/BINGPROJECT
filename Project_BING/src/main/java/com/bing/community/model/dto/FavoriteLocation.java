package com.bing.community.model.dto;

public class FavoriteLocation {
	private int favorite_locationId;
	private int location_id;
	private String user_email;
	private String place_name;
	private String address_name;

	public FavoriteLocation() {
	}

	public int getFavorite_locationId() {
		return favorite_locationId;
	}

	public void setFavorite_locationId(int favorite_locationId) {
		this.favorite_locationId = favorite_locationId;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
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

	public FavoriteLocation(int favorite_locationId, int location_id, String user_email, String place_name,
			String address_name) {
		super();
		this.favorite_locationId = favorite_locationId;
		this.location_id = location_id;
		this.user_email = user_email;
		this.place_name = place_name;
		this.address_name = address_name;
	}

	@Override
	public String toString() {
		return "FavoriteLocation [favorite_locationId=" + favorite_locationId + ", location_id=" + location_id
				+ ", user_email=" + user_email + ", place_name=" + place_name + ", address_name=" + address_name + "]";
	}

}

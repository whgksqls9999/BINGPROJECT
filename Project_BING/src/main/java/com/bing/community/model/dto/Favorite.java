package com.bing.community.model.dto;

public class Favorite {
	private String user_email;
	private int location_id;
	private int favorite_id;
	private String location_name;
	private String longitude;
	private String latitude;

	@Override
	public String toString() {
		return "Favorite [user_email=" + user_email + ", location_id=" + location_id + ", favorite_id=" + favorite_id
				+ ", location_name=" + location_name + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

	public Favorite() {
		// TODO Auto-generated constructor stub
	}

	public Favorite(String user_email, int location_id, int favorite_id) {
		super();
		this.user_email = user_email;
		this.location_id = location_id;
		this.favorite_id = favorite_id;
	}

	public Favorite(String user_email, int location_id, int favorite_id, String location_name, String longitude,
			String latitude) {
		super();
		this.user_email = user_email;
		this.location_id = location_id;
		this.favorite_id = favorite_id;
		this.location_name = location_name;
		this.longitude = longitude;
		this.latitude = latitude;
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

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getFavorite_id() {
		return favorite_id;
	}

	public void setFavorite_id(int favorite_id) {
		this.favorite_id = favorite_id;
	}

}

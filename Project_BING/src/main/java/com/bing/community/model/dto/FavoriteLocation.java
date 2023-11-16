package com.bing.community.model.dto;

public class FavoriteLocation{
	private int favorite_locationId;
	private int location_id;
	private String user_email;
	
	public FavoriteLocation() {
	}

	public FavoriteLocation(int favorite_locationId, int location_id, String user_email) {
		super();
		this.favorite_locationId = favorite_locationId;
		this.location_id = location_id;
		this.user_email = user_email;
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

	@Override
	public String toString() {
		return "FavoriteLocation [favorite_locationId=" + favorite_locationId + ", location_id=" + location_id
				+ ", user_email=" + user_email + "]";
	}
	
	

}

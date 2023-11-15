package com.bing.community.model.dto;

public class Community {
	private int community_id;
	private String community_name;
	private String admin_id;
	private String community_description;

	public Community() {
		// TODO Auto-generated constructor stub
	}

	public Community(int community_id, String community_name, String admin_id, String community_description) {
		super();
		this.community_id = community_id;
		this.community_name = community_name;
		this.admin_id = admin_id;
		this.community_description = community_description;
	}

	public int getCommunity_id() {
		return community_id;
	}

	public void setCommunity_id(int community_id) {
		this.community_id = community_id;
	}

	public String getCommunity_name() {
		return community_name;
	}

	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getCommunity_description() {
		return community_description;
	}

	public void setCommunity_description(String community_description) {
		this.community_description = community_description;
	}

}

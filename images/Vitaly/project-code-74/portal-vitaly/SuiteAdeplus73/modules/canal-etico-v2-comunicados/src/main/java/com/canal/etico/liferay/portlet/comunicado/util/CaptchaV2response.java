package com.canal.etico.liferay.portlet.comunicado.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class CaptchaV2response implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean success;	
	private Timestamp challenge_ts;	
	private String hostname;	
	@SerializedName("error-codes")
	private ArrayList<String> error_codes;
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Timestamp getChallenge_ts() {
		return challenge_ts;
	}
	
	public void setChallenge_ts(Timestamp challenge_ts) {
		this.challenge_ts = challenge_ts;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public ArrayList<String> getError_codes() {
		return error_codes;
	}

	public void setError_codes(ArrayList<String> error_codes) {
		this.error_codes = error_codes;
	}	
}
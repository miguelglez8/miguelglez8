package com.canal.etico.liferay.portlet.comunicado.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.naming.AuthenticationException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;


public class NetworkHelper {
	public static String postContent(String location, String postData) throws MalformedURLException, ProtocolException, IOException, Exception {
		return postContent(location, false, "", "", postData);
	}
	
	public static String postContent(String location, boolean authorization, String user, String password, String postData) throws MalformedURLException, ProtocolException, IOException, Exception {
		
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){

			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
							throws CertificateException {
				// TODO Auto-generated method stub

			}
			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
							throws CertificateException {
				// TODO Auto-generated method stub

			}
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
		}};

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			;
		}

		URL url;
		url = new URL(location);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//connection.setRequestProperty("Content-Type","application/x-www- form-urlencoded");
		connection.setRequestMethod("POST");
		
		if(authorization){
			Base64 b = new Base64();
			String authString = user + ":" + password;
			String authStringEnc = new String(b.encode(authString.getBytes()));
			connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
		}
		
		if(!postData.equals("")){
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();
		}
		
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
			
		} else if (responseCode == 401){
			throw new AuthenticationException("Response status code: " + responseCode + " URL: " + location);
			
		}

		throw new Exception("Response status code: " + responseCode + " URL: " + location);
	}	
		
}
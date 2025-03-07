package com.preving.liferay.portlet.commons.web.keycloak;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class Verifier implements HostnameVerifier{

	@Override
	public boolean verify(String arg0, SSLSession arg1) {

		return true;
	}

}

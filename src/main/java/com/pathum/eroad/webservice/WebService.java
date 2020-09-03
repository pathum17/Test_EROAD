package com.pathum.eroad.webservice;

import com.pathum.eroad.model.WebServiceResponse;
import com.pathum.eroad.util.PropertyUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

class WebService {

	 WebServiceResponse execute(String longitude, String latitude) {
		WebServiceResponse output = null;
		try {
			String url = buildWebServiceUrl(longitude, latitude);

			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

			WebResource webResource = client.resource(url);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(WebServiceResponse.class);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return output;
	}

	private String buildWebServiceUrl(String longitude, String latitude) {

		String baseUrl = PropertyUtil.getProperty("baseUrl");
		String APIKey = PropertyUtil.getProperty("APIKey");

		String url = baseUrl + APIKey + "&format=json&by=position&lat=" + latitude + "&lng=" + longitude;
		return url;

	}

}
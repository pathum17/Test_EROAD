package com.pathum.eroad.webservice;

import com.pathum.eroad.model.WebServiceResponse;
import com.pathum.eroad.webservice.WebService;

public class WebServiceCaller {
	WebService webService = new WebService();

	public WebServiceResponse getLocationDetails(String longitude, String latitude) {
		WebServiceResponse output = webService.execute(longitude, latitude);
		return output;
	}
}

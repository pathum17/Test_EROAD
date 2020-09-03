package com.pathum.eroad.test.integration.webservice;

import org.junit.Assert;
import org.junit.Test;

import com.pathum.eroad.model.WebServiceResponse;
import com.pathum.eroad.webservice.WebServiceCaller;

public class WebServiceCallerIT {
	
	@Test
    public void geoServiceResponse() {
		WebServiceCaller caller = new WebServiceCaller();
		WebServiceResponse response = caller.getLocationDetails("79.8612", "6.9271");

        Assert.assertEquals(response.getZoneName(),"Asia/Colombo");
    }

}

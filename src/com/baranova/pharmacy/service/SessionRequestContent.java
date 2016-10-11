package com.baranova.pharmacy.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for extract values from HttpServletRequest.
 */
public class SessionRequestContent {

    private Map<String, String> requestParameters=new HashMap<>();

    public SessionRequestContent(){

    }

    /**
     * Extract values from HttpServletRequest
     * @param request - HttpServletRequest to be analyzed
     */
    public void extractValues(HttpServletRequest request){
        Map<String, String[]> parameterMap=request.getParameterMap();
        for (Map.Entry<String,String[]> parameter:parameterMap.entrySet()){
            String[] values=parameter.getValue();
            requestParameters.put(parameter.getKey(),values[0]);
        }
    }


    /**
     * Return requestParameters extracted from the HttpServletRequest
     * @return Map<String,String> of request parameters
     */
    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }
}

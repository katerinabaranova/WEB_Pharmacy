package com.baranova.pharmacy.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ekaterina on 8/11/16.
 */
public class SessionRequestContent {

    private Map<String, Object> requestAttributes;
    private Map<String, String> requestParameters=new HashMap<>();

    public SessionRequestContent(){

    }
    public void extractValues(HttpServletRequest request){
        Map<String, String[]> parameterMap=request.getParameterMap();
        for (Map.Entry<String,String[]> parameter:parameterMap.entrySet()){
            String[] values=parameter.getValue();
            requestParameters.put(parameter.getKey(),values[0]);
        }
    }

    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }
}

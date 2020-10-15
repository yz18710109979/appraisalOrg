package com.jingyou.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class SoapUtils {


    public static Object[] sendToWsdl(String wdslUrl, String methodName, Object[] params) {
        Object[] resultObjs = {"-1"};
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client CXFClient = dcf.createClient(wdslUrl);
            resultObjs = CXFClient.invoke(methodName, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObjs;
    }
}

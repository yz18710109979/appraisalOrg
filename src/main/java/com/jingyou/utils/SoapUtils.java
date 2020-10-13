package com.jingyou.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

@Slf4j
public class SoapUtils {

    /**
     *
     * @param wdslUrl
     * @param methodName
     * @param params
     * @return
     */
    public static Object[] sendToWsdl(String wdslUrl, String methodName, Object[] params) {
        Object[] resultObjs = {"-1"};
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client CXFClient = dcf.createClient(wdslUrl);
            resultObjs = CXFClient.invoke(methodName, params);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Soap调用失败，调用methodName: {}, params: {}", methodName, params);
        }
        return resultObjs;
    }
}

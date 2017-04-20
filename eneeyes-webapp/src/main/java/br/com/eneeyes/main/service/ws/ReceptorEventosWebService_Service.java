package br.com.eneeyes.main.service.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.4.redhat-1
 * 2017-04-19T13:51:21.635-03:00
 * Generated source version: 3.1.4.redhat-1
 * OLD: wsdlLocation = "http://qa.segware.com.br:5050/SigmaWebServices/ReceptorEventosWebService?wsdl",
 */
@WebServiceClient(name = "ReceptorEventosWebService", 
                  wsdlLocation = "http://189.2.46.130:9900/SigmaWebServices/ReceptorEventosWebService?wsdl",
                  targetNamespace = "http://webServices.sigmaWebServices.segware.com.br/") 
public class ReceptorEventosWebService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webServices.sigmaWebServices.segware.com.br/", "ReceptorEventosWebService");
    public final static QName ReceptorEventosWebServicePort = new QName("http://webServices.sigmaWebServices.segware.com.br/", "ReceptorEventosWebServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://189.2.46.130:9900/SigmaWebServices/ReceptorEventosWebService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ReceptorEventosWebService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://189.2.46.130:9900/SigmaWebServices/ReceptorEventosWebService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ReceptorEventosWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ReceptorEventosWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReceptorEventosWebService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ReceptorEventosWebService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ReceptorEventosWebService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ReceptorEventosWebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ReceptorEventosWebService
     */
    @WebEndpoint(name = "ReceptorEventosWebServicePort")
    public ReceptorEventosWebService getReceptorEventosWebServicePort() {
        return super.getPort(ReceptorEventosWebServicePort, ReceptorEventosWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReceptorEventosWebService
     */
    @WebEndpoint(name = "ReceptorEventosWebServicePort")
    public ReceptorEventosWebService getReceptorEventosWebServicePort(WebServiceFeature... features) {
        return super.getPort(ReceptorEventosWebServicePort, ReceptorEventosWebService.class, features);
    }

}

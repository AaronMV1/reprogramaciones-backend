

package pe.edu.upsjb.reprogramaciones.util;


import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public SoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public Object llamarServicioSOAP(Object requestPayload) {
        String soapUrl = "http://172.102.4.131:8030/PSIGW/RESTListeningConnector/PSFT_CS/SJB_OBT_DISPONIBILIDAD_AMB.v1/DISPONIBILIDAD/2025-03-06/2025-03-06/18.35/20.05/L0034/LCRT////5///";
        return webServiceTemplate.marshalSendAndReceive(soapUrl, requestPayload);
    }

}


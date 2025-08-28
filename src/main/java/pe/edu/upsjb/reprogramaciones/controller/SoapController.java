

package pe.edu.upsjb.reprogramaciones.controller;


import org.springframework.web.bind.annotation.*;
import pe.edu.upsjb.reprogramaciones.util.SoapClient;


@RestController
@RequestMapping("/api")
public class SoapController {

    private final SoapClient soapClient;

    public SoapController(SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @GetMapping("/consulta")
    public Object consulta() {
        // Aquí armarías tu requestPayload con JAXB (clases generadas desde XSD/WSDL)
        Object requestPayload = new Object(); // 👈 Simulación

        return soapClient.llamarServicioSOAP(requestPayload);
    }

}


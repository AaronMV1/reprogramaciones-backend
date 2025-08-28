

package pe.edu.upsjb.reprogramaciones.service;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService (endpointInterface = "pe.edu.upsjb.reprogramaciones.service.ServicioSumarWS")
public class ServicioSumarImpl implements ServicioSumarWS {

    @Override
    public int sumar(int a, int b) {
        return a + b;
    }

}



package pe.edu.upsjb.reprogramaciones.service;


import pe.edu.upsjb.reprogramaciones.dto.*;


public interface AplicacionService {


    public ListaAplicacionResponse consultarConfiguracion();
    public MensajeResponse actualizarConfiguracion(AplicacionRequest request);


}




package pe.edu.upsjb.reprogramaciones.dao;


import pe.edu.upsjb.reprogramaciones.dto.*;


public interface AplicacionDao {


    public ListaAplicacionResponse consultarConfiguracion();
    public MensajeResponse actualizarConfiguracion(AplicacionRequest request);
    public ListaSemestreResponse consultarConfiguracionSemestre();
    public MensajeResponse actualizarConfiguracionSemestre(SemestreRequest request);


}


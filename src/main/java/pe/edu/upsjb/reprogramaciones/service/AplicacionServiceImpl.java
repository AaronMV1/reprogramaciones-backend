

package pe.edu.upsjb.reprogramaciones.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upsjb.reprogramaciones.dto.*;
import pe.edu.upsjb.reprogramaciones.dao.*;


@Service
@Transactional


public class AplicacionServiceImpl implements AplicacionService {


    @Autowired
    private AplicacionDao aplicacionDao;


    public ListaAplicacionResponse consultarConfiguracion() {
        return aplicacionDao.consultarConfiguracion();
    }
    public MensajeResponse actualizarConfiguracion(AplicacionRequest request) { return aplicacionDao.actualizarConfiguracion(request); }


}

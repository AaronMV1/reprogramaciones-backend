

package pe.edu.upsjb.reprogramaciones.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upsjb.reprogramaciones.dto.*;
import pe.edu.upsjb.reprogramaciones.service.*;


@RestController


@CrossOrigin(origins = {
    "http://localhost:4200",
})


public class AplicacionController {


    @Autowired
    AplicacionService aplicacionService;


    @GetMapping (value = "/consultar-configuracion")
    public @ResponseBody ListaAplicacionResponse consultarConfiguracion () {
        return aplicacionService.consultarConfiguracion();
    }

    @PostMapping (value = "/actualizar-configuracion")
    public @ResponseBody MensajeResponse actualizarConfiguracion (@RequestBody AplicacionRequest request) {
        return aplicacionService.actualizarConfiguracion(request);
    }

    @GetMapping (value = "/consultar-configuracion-semestre")
    public @ResponseBody ListaSemestreResponse consultarConfiguracionSemestre () {
        return aplicacionService.consultarConfiguracionSemestre();
    }

    @PostMapping (value = "/actualizar-configuracion-semestre")
    public @ResponseBody MensajeResponse actualizarConfiguracionSemestre (@RequestBody SemestreRequest request) {
        return aplicacionService.actualizarConfiguracionSemestre(request);
    }


}



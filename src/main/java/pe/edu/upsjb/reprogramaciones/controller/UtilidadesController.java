

package pe.edu.upsjb.reprogramaciones.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import pe.edu.upsjb.reprogramaciones.dto.*;
import pe.edu.upsjb.reprogramaciones.util.*;


@RestController


@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://localhost:8080",
        "https://app.upsjb.edu.pe",
        "https://frontdev-campus.upsjb.edu.pe",
        "https://frontdev-campus.upsjb.edu.pe/requerimientos",
        "https://frontdev-campus.upsjb.edu.pe/requerimientos/redireccion",
        "https://backdev-campus.upsjb.edu.pe",
        "https://backdev-campus.upsjb.edu.pe/api-requerimientos",
        "https://backdev-campus.upsjb.edu.pe/api-requerimiento",
        "https://app.upsjb.edu.pe/api-requerimiento"
})


public class UtilidadesController {


    @Autowired
    CorreoService correoService;


    @PostMapping(value = "/admin-enviar-email")
    public ResponseEntity<Map<String, Object>> enviarEmail(@RequestBody CorreoMensaje request) {

        Map<String, Object> response = new HashMap<>();

        try {

            /*
            System.out.println(request.getDestinatario());
            System.out.println(request.getCorreoCopia());
            System.out.println(request.getAsunto());
            System.out.println(request.getNombres());
            System.out.println(request.getCuerpoHTML());
            */

            correoService.enviarMensaje(request.getDestinatario(), request.getCorreoCopia(), request.getAsunto(), request.getCuerpoHTML());
            response.put("success", true);
            response.put("message", "Correo enviado correctamente");
            return ResponseEntity.ok(response);

        } catch (Exception e) {

            e.printStackTrace();
            response.put("Error", false);
            response.put("message", "Error al enviar el correo: " + e.getMessage());
            return ResponseEntity.status(500).body(response);

        }

    }

}



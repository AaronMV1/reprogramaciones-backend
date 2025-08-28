

package pe.edu.upsjb.reprogramaciones.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upsjb.reprogramaciones.dto.*;
import pe.edu.upsjb.reprogramaciones.service.*;


@RestController


@CrossOrigin(origins = {
        "http://localhost:4200",
})


public class CursoController {


    @Autowired
    CursoService cursoService;


    @PostMapping (value = "/consultar-curso")
    public @ResponseBody ListaCursoResponse consultarCurso(@RequestBody CursoRequest request) {
        return cursoService.consultarCurso(request);
    }


}

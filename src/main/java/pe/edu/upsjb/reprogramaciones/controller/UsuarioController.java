

package pe.edu.upsjb.reprogramaciones.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upsjb.reprogramaciones.dto.*;
import pe.edu.upsjb.reprogramaciones.service.*;


@RestController


@CrossOrigin(origins = {
     "http://localhost:4200",
})


public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;


    @PostMapping (value = "/consultar-usuario")
    public @ResponseBody ListaUsuarioResponse consultarUsuario(@RequestBody UsuarioRequest request) {
        return usuarioService.consultarUsuario(request);
    }

    @GetMapping  (value = "/consultar-lista-usuarios")
    public @ResponseBody ListaUsuarioResponse consultarListaUsuarios() {
        return usuarioService.consultarListaUsuarios();
    }

    @PostMapping (value = "/registrar-usuario")
    public @ResponseBody MensajeResponse registrarUsuario(@RequestBody UsuarioRequest request) {
        return usuarioService.registrarUsuario(request);
    }

    @PostMapping (value = "/actualizar-usuario")
    public @ResponseBody MensajeResponse actualizarUsuario(@RequestBody UsuarioRequest request) {
        return usuarioService.actualizarUsuario(request);
    }

    @PostMapping (value = "/eliminar-usuario")
    public @ResponseBody MensajeResponse eliminarUsuario(@RequestBody UsuarioRequest request) {
        return usuarioService.eliminarUsuario(request);
    }


    /*
    @GetMapping (value = "/obtener-usuario")
    public @ResponseBody MensajeResponse obtenerUsuario() {
        return usuarioService.obtenerUsuario();
    }
    @PostMapping (value = "/obtener-usuario-lista")
    public @ResponseBody MensajeResponse obtenerUsuarioLista(@RequestBody UsuarioRequest request) {
        return usuarioService.obtenerUsuarioLista(request);
    }
    */


}


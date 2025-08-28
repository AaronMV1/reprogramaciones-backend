

package pe.edu.upsjb.reprogramaciones.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upsjb.reprogramaciones.dto.*;
import pe.edu.upsjb.reprogramaciones.dao.*;


@Service
@Transactional


public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioDao usuarioDao;


    public ListaUsuarioResponse consultarUsuario(UsuarioRequest request) {
        return usuarioDao.consultarUsuario(request);
    }
    public ListaUsuarioResponse consultarListaUsuarios() {
        return usuarioDao.consultarListaUsuarios();
    }

    public MensajeResponse registrarUsuario(UsuarioRequest request) {
        return  usuarioDao.registrarUsuario(request);
    }
    public MensajeResponse actualizarUsuario(UsuarioRequest request) { return usuarioDao.actualizarUsuario(request); }
    public MensajeResponse eliminarUsuario(UsuarioRequest request) {
        return  usuarioDao.eliminarUsuario(request);
    }


    /*
    public MensajeResponse obtenerUsuario() {return usuarioDao.obtenerUsuario();}
    public MensajeResponse obtenerUsuarioLista(UsuarioRequest request) {return usuarioDao.obtenerUsuarioLista(request);}
    */


}


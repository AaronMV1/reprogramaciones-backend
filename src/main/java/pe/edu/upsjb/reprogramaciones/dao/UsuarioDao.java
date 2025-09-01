

package pe.edu.upsjb.reprogramaciones.dao;


import pe.edu.upsjb.reprogramaciones.dto.*;


public interface UsuarioDao {


    public ListaUsuarioResponse loginConsultarUsuario(UsuarioRequest request);
    public ListaUsuarioResponse consultarUsuario(UsuarioRequest request);
    public ListaUsuarioResponse consultarListaUsuarios();

    public MensajeResponse registrarUsuario(UsuarioRequest request);
    public MensajeResponse actualizarUsuario(UsuarioRequest request);
    public MensajeResponse eliminarUsuario(UsuarioRequest request);


    /*
    public MensajeResponse obtenerUsuario();
    public MensajeResponse obtenerUsuarioLista(UsuarioRequest request);
    */


}


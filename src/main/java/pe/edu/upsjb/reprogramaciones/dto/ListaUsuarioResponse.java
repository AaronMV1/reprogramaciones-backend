

package pe.edu.upsjb.reprogramaciones.dto;


import lombok.Data;
import java.util.List;

@Data


public class ListaUsuarioResponse {


    private List<UsuarioResponse> lista;

    public List<UsuarioResponse> getLista() {
        return lista;
    }
    public void setLista(List<UsuarioResponse> lista) {
        this.lista = lista;
    }


}

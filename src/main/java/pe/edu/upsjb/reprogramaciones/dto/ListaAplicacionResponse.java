

package pe.edu.upsjb.reprogramaciones.dto;


import lombok.Data;
import java.util.List;

@Data


public class ListaAplicacionResponse {


    private List<AplicacionResponse> lista;

    public List<AplicacionResponse> getLista() { return lista; }
    public void setLista(List<AplicacionResponse> lista) { this.lista = lista; }


}

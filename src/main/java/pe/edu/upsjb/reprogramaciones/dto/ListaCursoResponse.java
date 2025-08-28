

package pe.edu.upsjb.reprogramaciones.dto;


import lombok.Data;
import java.util.List;

@Data


public class ListaCursoResponse {


    private List<CursoResponse> lista;

    public List<CursoResponse> getLista() {
        return lista;
    }
    public void setLista(List<CursoResponse> lista) {
        this.lista = lista;
    }



}

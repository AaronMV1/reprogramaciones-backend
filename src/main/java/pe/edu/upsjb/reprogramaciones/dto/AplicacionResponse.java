

package pe.edu.upsjb.reprogramaciones.dto;


public class AplicacionResponse {


    private int limiteReprogramaciones;
    private int plazoMaximo;
    private Boolean correoDocente;
    private Boolean correoAlumno;
    private Boolean correoDOA;
    private Boolean correoCoordinadorPrograma;
    private Boolean correoCoordinadorAmbiente;


    public int getLimiteReprogramaciones() {
        return limiteReprogramaciones;
    }

    public void setLimiteReprogramaciones(int limiteReprogramaciones) {
        this.limiteReprogramaciones = limiteReprogramaciones;
    }

    public int getPlazoMaximo() {
        return plazoMaximo;
    }

    public void setPlazoMaximo(int plazoMaximo) {
        this.plazoMaximo = plazoMaximo;
    }

    public Boolean getCorreoDocente() {
        return correoDocente;
    }

    public void setCorreoDocente(Boolean correoDocente) {
        this.correoDocente = correoDocente;
    }

    public Boolean getCorreoAlumno() {
        return correoAlumno;
    }

    public void setCorreoAlumno(Boolean correoAlumno) {
        this.correoAlumno = correoAlumno;
    }

    public Boolean getCorreoDOA() {
        return correoDOA;
    }

    public void setCorreoDOA(Boolean correoDOA) {
        this.correoDOA = correoDOA;
    }

    public Boolean getCorreoCoordinadorPrograma() {
        return correoCoordinadorPrograma;
    }

    public void setCorreoCoordinadorPrograma(Boolean correoCoordinadorPrograma) {
        this.correoCoordinadorPrograma = correoCoordinadorPrograma;
    }

    public Boolean getCorreoCoordinadorAmbiente() {
        return correoCoordinadorAmbiente;
    }

    public void setCorreoCoordinadorAmbiente(Boolean correoCoordinadorAmbiente) {
        this.correoCoordinadorAmbiente = correoCoordinadorAmbiente;
    }


}



package pe.edu.upsjb.reprogramaciones.dto;


public class AplicacionRequest {


    private int limiteReprogramaciones;
    private int plazoMaximo;
    private Boolean correoDocente;
    private Boolean correoAlumno;
    private Boolean correoDOA;
    private Boolean correoCoordinador;


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

    public Boolean getCorreoCoordinador() {
        return correoCoordinador;
    }

    public void setCorreoCoordinador(Boolean correoCoordinador) {
        this.correoCoordinador = correoCoordinador;
    }


}

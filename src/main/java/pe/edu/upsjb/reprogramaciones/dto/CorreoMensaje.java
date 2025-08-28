

package pe.edu.upsjb.reprogramaciones.dto;


public class CorreoMensaje {


    private String destinatario;
    private String correoCopia;
    private String asunto;
    private String nombres;
    private String cuerpoHTML;


    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCorreoCopia() {
        return correoCopia;
    }

    public void setCorreoCopia(String correoCopia) {
        this.correoCopia = correoCopia;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCuerpoHTML() {
        return cuerpoHTML;
    }

    public void setCuerpoHTML(String cuerpoHTML) {
        this.cuerpoHTML = cuerpoHTML;
    }


}

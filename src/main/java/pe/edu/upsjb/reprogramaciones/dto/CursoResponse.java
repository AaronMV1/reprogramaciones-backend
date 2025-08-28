

package pe.edu.upsjb.reprogramaciones.dto;


public class CursoResponse {


    private String id;
    private String nombre;
    private String descripcion;
    private String fechaHoraIni;
    private String fechaHoraFin;
    private String docente;
    private String docenteDNI;
    private String tipoClase;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaHoraIni() {
        return fechaHoraIni;
    }

    public void setFechaHoraIni(String fechaHoraIni) {
        this.fechaHoraIni = fechaHoraIni;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getDocenteDNI() {
        return docenteDNI;
    }

    public void setDocenteDNI(String docenteDNI) {
        this.docenteDNI = docenteDNI;
    }

    public String getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(String tipoClase) {
        this.tipoClase = tipoClase;
    }


}

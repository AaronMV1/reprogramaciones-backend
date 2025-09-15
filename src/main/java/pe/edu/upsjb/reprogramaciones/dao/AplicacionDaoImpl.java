

package pe.edu.upsjb.reprogramaciones.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.edu.upsjb.reprogramaciones.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


@Repository


public class AplicacionDaoImpl extends Dao implements AplicacionDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public ListaAplicacionResponse consultarConfiguracion() {

        ListaAplicacionResponse response = new ListaAplicacionResponse();
        response.setLista(new ArrayList<>());

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
            "SELECT * FROM IDO_REPROGRAMACION_CLASES.CONFIGURACION "
            );

            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                AplicacionResponse dto = new AplicacionResponse();
                dto.setLimiteReprogramaciones(rs.getInt("limite"));
                dto.setPlazoMaximo(rs.getInt("plazo"));
                dto.setCorreoDocente("Y".equalsIgnoreCase(rs.getString("cdocente")));
                dto.setCorreoAlumno("Y".equalsIgnoreCase(rs.getString("calumno")));
                dto.setCorreoDOA("Y".equalsIgnoreCase(rs.getString("cdoa")));
                dto.setCorreoCoordinadorPrograma("Y".equalsIgnoreCase(rs.getString("ccoordinador_programa")));
                dto.setCorreoCoordinadorAmbiente("Y".equalsIgnoreCase(rs.getString("ccoordinador_ambiente")));
                response.getLista().add(dto);
            }

            psSelect.close();
            con.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }

    public MensajeResponse actualizarConfiguracion(AplicacionRequest request) {

        MensajeResponse response = new MensajeResponse();

        try {

            Connection con = getConnection();

            PreparedStatement psUpdate = con.prepareStatement(
                    "UPDATE IDO_REPROGRAMACION_CLASES.CONFIGURACION " +
                            " SET limite = ?, plazo = ?, cdocente = ?, calumno = ?, cdoa = ?, " +
                            " ccoordinador_programa = ?, ccoordinador_ambiente = ? "
            );

            psUpdate.setInt(1, request.getLimiteReprogramaciones());
            psUpdate.setInt(2, request.getPlazoMaximo());
            psUpdate.setString(3, request.getCorreoDocente() ? "Y" : "N");
            psUpdate.setString(4, request.getCorreoAlumno() ? "Y" : "N");
            psUpdate.setString(5, request.getCorreoDOA() ? "Y" : "N");
            psUpdate.setString(6, request.getCorreoCoordinadorPrograma() ? "Y" : "N");
            psUpdate.setString(7, request.getCorreoCoordinadorAmbiente() ? "Y" : "N");

            psUpdate.executeUpdate();

            response.setEstado("Success");
            response.setMensaje("La configuración se ha actualizado correctamente");

            psUpdate.close();
            con.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }

    public ListaSemestreResponse consultarConfiguracionSemestre() {

        ListaSemestreResponse response = new ListaSemestreResponse();
        response.setLista(new ArrayList<>());

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
                    "SELECT * FROM IDO_REPROGRAMACION_CLASES.SEMESTRE "
            );

            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                SemestreResponse dto = new SemestreResponse();
                dto.setGrado(rs.getString("grado"));
                dto.setCodigoGrado(rs.getString("cod_grado"));
                dto.setSemestre(rs.getString("semestre"));
                dto.setCodigoSemestre(rs.getString("cod_semestre"));
                dto.setFechaInicio(rs.getDate("fecha_inicio"));
                dto.setFechaCierre(rs.getDate("fecha_cierre"));
                dto.setFechaCreacion(rs.getDate("fecha_creacion"));
                dto.setUsuarioCreacion(rs.getString("usuario_creacion"));
                dto.setFechaModificacion(rs.getDate("fecha_modificacion"));
                dto.setUsuarioModificacion(rs.getString("usuario_modificacion"));
                response.getLista().add(dto);
            }

            psSelect.close();
            con.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }

    public MensajeResponse actualizarConfiguracionSemestre(SemestreRequest request) {

        MensajeResponse response = new MensajeResponse();

        try {

            Connection con = getConnection();

            PreparedStatement psUpdate = con.prepareStatement(
                    "UPDATE IDO_REPROGRAMACION_CLASES.SEMESTRE " +
                            " SET semestre = ?, cod_semestre = ?, fecha_inicio = ?, fecha_cierre = ?, " +
                            " fecha_modificacion = SYSDATE, usuario_modificacion = ? " +
                            " WHERE grado = ? "
            );

            psUpdate.setString(1, request.getSemestre());
            psUpdate.setString(2, request.getCodigoSemestre());

            System.out.println(request.getFechaInicio());
            System.out.println(request.getFechaCierre());

            if (request.getFechaInicio() != null) {
                psUpdate.setDate(3, new java.sql.Date(request.getFechaInicio().getTime()));
            } else {
                psUpdate.setNull(3, java.sql.Types.DATE);
            }

            if (request.getFechaCierre() != null) {
                psUpdate.setDate(4, new java.sql.Date(request.getFechaCierre().getTime()));
            } else {
                psUpdate.setNull(4, java.sql.Types.DATE);
            }

            psUpdate.setString(5, request.getUsuarioModificacion());

            psUpdate.setString(6, request.getGrado());

            psUpdate.executeUpdate();

            response.setEstado("Success");
            response.setMensaje("El semestre se ha actualizado correctamente");

            psUpdate.close();
            con.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }

}


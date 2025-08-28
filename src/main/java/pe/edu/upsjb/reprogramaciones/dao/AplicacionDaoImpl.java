

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
            "SELECT * FROM reprogramaciones.configuracion "
            );

            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                AplicacionResponse dto = new AplicacionResponse();
                dto.setLimiteReprogramaciones(rs.getInt("limite"));
                dto.setPlazoMaximo(rs.getInt("plazo"));
                dto.setCorreoDocente(rs.getBoolean("cdocente"));
                dto.setCorreoAlumno(rs.getBoolean("calumno"));
                dto.setCorreoDOA(rs.getBoolean("cdoa"));
                dto.setCorreoCoordinador(rs.getBoolean("ccoordinador"));
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
                    " UPDATE reprogramaciones.configuracion " +
                            " SET limite = ?, plazo = ?, cdocente = ?, calumno = ?, cdoa = ?, ccoordinador = ? "
            );

            psUpdate.setInt(1, request.getLimiteReprogramaciones());
            psUpdate.setInt(2, request.getPlazoMaximo());
            psUpdate.setBoolean(3, request.getCorreoDocente());
            psUpdate.setBoolean(4, request.getCorreoAlumno());
            psUpdate.setBoolean(5, request.getCorreoDOA());
            psUpdate.setBoolean(6, request.getCorreoCoordinador());
            psUpdate.executeUpdate();

            response.setEstado("Success");
            response.setMensaje("La configuracion se ha actualizada correctamente");

            psUpdate.close();
            con.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }


}


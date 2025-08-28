

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


public class CursoDaoImpl extends Dao implements CursoDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public ListaCursoResponse consultarCurso(CursoRequest request) {

        ListaCursoResponse response = new ListaCursoResponse();
        response.setLista(new ArrayList<>());

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
            " SELECT id, nombre, descripcion, fechaHoraIni, fechaHoraFin, docente, docenteDNI, tipoClase " +
                    " FROM reprogramaciones.curso " +
                    " WHERE LOWER(docenteDNI) = LOWER(?) "
            );

            psSelect.setString(1, request.getDocenteDNI());
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                CursoResponse dto = new CursoResponse();
                dto.setId(rs.getString("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setDescripcion(rs.getString("descripcion"));
                dto.setFechaHoraIni(rs.getString("fechaHoraIni"));
                dto.setFechaHoraFin(rs.getString("fechaHoraFin"));
                dto.setDocente(rs.getString("docente"));
                dto.setDocenteDNI(rs.getString("docenteDNI"));
                dto.setTipoClase(rs.getString("tipoClase"));
                response.getLista().add(dto);
            }

            psSelect.close();
            rs.close();

            con.close();


        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }


}


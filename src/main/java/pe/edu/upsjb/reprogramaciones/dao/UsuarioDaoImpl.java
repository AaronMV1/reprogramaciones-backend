

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


public class UsuarioDaoImpl extends Dao implements UsuarioDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public ListaUsuarioResponse loginConsultarUsuario(UsuarioRequest request) {

        ListaUsuarioResponse response = new ListaUsuarioResponse();
        response.setLista(new ArrayList<>());

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
                    " SELECT nombres, apellidos, correo, perfil, programa, activo, usuario_responsable " +
                            " FROM IDO_REPROGRAMACION_CLASES.USUARIO " +
                            " WHERE LOWER(correo) = LOWER(?) AND activo = 'Y'"
            );

            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                UsuarioResponse dto = new UsuarioResponse();
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
                dto.setCorreo(rs.getString("correo"));
                dto.setPerfil(rs.getString("perfil"));
                dto.setPrograma(rs.getString("programa"));
                dto.setActivo("Y".equalsIgnoreCase(rs.getString("activo"))); // conversión manual
                dto.setUsuarioResponsable(rs.getString("usuario_responsable"));
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
    public ListaUsuarioResponse consultarUsuario(UsuarioRequest request) {

        ListaUsuarioResponse response = new ListaUsuarioResponse();
        response.setLista(new ArrayList<>());

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
             " SELECT nombres, apellidos, correo, perfil, programa, activo, usuario_responsable " +
                     " FROM IDO_REPROGRAMACION_CLASES.USUARIO " +
                     " WHERE LOWER(correo) = LOWER(?) AND activo = 'Y'"
            );

            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                UsuarioResponse dto = new UsuarioResponse();
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
                dto.setCorreo(rs.getString("correo"));
                dto.setPerfil(rs.getString("perfil"));
                dto.setPrograma(rs.getString("programa"));
                dto.setActivo("Y".equalsIgnoreCase(rs.getString("activo")));
                dto.setUsuarioResponsable(rs.getString("usuario_responsable"));
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
    public ListaUsuarioResponse consultarListaUsuarios() {

        ListaUsuarioResponse response = new ListaUsuarioResponse();
        response.setLista(new ArrayList<>());

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
                    " SELECT nombres, apellidos, correo, perfil, programa, activo, usuario_responsable " +
                            " FROM IDO_REPROGRAMACION_CLASES.USUARIO " +
                            " WHERE activo = 'Y'"
            );

            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                UsuarioResponse dto = new UsuarioResponse();
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
                dto.setCorreo(rs.getString("correo"));
                dto.setPerfil(rs.getString("perfil"));
                dto.setPrograma(rs.getString("programa"));
                dto.setActivo("Y".equalsIgnoreCase(rs.getString("activo"))); // conversión manual
                dto.setUsuarioResponsable(rs.getString("usuario_responsable"));
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


    public MensajeResponse registrarUsuario(UsuarioRequest request) {

        MensajeResponse response = new MensajeResponse();

        try (Connection con = getConnection()) {

            // Verificar si ya existe

            PreparedStatement psSelect = con.prepareStatement(
                    "SELECT COUNT(*) FROM IDO_REPROGRAMACION_CLASES.USUARIO WHERE LOWER(correo) = LOWER(?)"
            );
            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            int count = 0;

            if (rs.next()) count = rs.getInt(1);

            psSelect.close();
            rs.close();

            if (count > 0) {

                // Verificar si está activo

                PreparedStatement psSelectActivo = con.prepareStatement(
                        "SELECT activo FROM IDO_REPROGRAMACION_CLASES.USUARIO WHERE LOWER(correo) = LOWER(?)"
                );
                psSelectActivo.setString(1, request.getCorreo());
                ResultSet rsActivo = psSelectActivo.executeQuery();

                if (rsActivo.next()) {
                    String activo = rsActivo.getString(1); // 'Y' o 'N'

                    if ("Y".equalsIgnoreCase(activo)) {
                        response.setEstado("Alert");
                        response.setMensaje("El usuario ya existe");
                    } else {
                        // Reactivar usuario
                        PreparedStatement psUpdate = con.prepareStatement(
                                "UPDATE IDO_REPROGRAMACION_CLASES.USUARIO " +
                                        "SET nombres = ?, apellidos = ?, perfil = ?, programa = ?, activo = 'Y' " +
                                        "WHERE LOWER(correo) = LOWER(?)"
                        );

                        psUpdate.setString(1, request.getNombres());
                        psUpdate.setString(2, request.getApellidos());
                        psUpdate.setString(3, request.getPerfil());
                        psUpdate.setString(4, request.getPrograma());
                        psUpdate.setString(5, request.getCorreo());
                        psUpdate.executeUpdate();
                        psUpdate.close();

                        response.setEstado("Success2");
                        response.setMensaje("El usuario fue reactivado");
                    }
                }

                psSelectActivo.close();
                rsActivo.close();

            } else {

                // Insertar nuevo

                PreparedStatement psInsert = con.prepareStatement(
                        "INSERT INTO IDO_REPROGRAMACION_CLASES.USUARIO " +
                                "(nombres, apellidos, correo, perfil, programa, activo, usuario_responsable) " +
                                "VALUES (?, ?, ?, ?, ?, 'Y', ?)"
                );

                psInsert.setString(1, request.getNombres());
                psInsert.setString(2, request.getApellidos());
                psInsert.setString(3, request.getCorreo());
                psInsert.setString(4, request.getPerfil());
                psInsert.setString(5, request.getPrograma());
                psInsert.setString(6, request.getUsuarioResponsable());
                psInsert.executeUpdate();
                psInsert.close();

                response.setEstado("Success");
                response.setMensaje("Usuario agregado!");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
    public MensajeResponse actualizarUsuario(UsuarioRequest request) {

        MensajeResponse response = new MensajeResponse();

        try (Connection con = getConnection()) {
            PreparedStatement psSelect = con.prepareStatement(
                    "SELECT COUNT(*) FROM IDO_REPROGRAMACION_CLASES.USUARIO " +
                            "WHERE LOWER(correo) = LOWER(?) AND activo = 'Y'"
            );
            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            int count = 0;
            if (rs.next()) count = rs.getInt(1);

            psSelect.close();
            rs.close();

            if (count > 0) {
                PreparedStatement psUpdate = con.prepareStatement(
                        "UPDATE IDO_REPROGRAMACION_CLASES.USUARIO " +
                                "SET nombres = ?, apellidos = ?, perfil = ?, programa = ? " +
                                "WHERE LOWER(correo) = LOWER(?)"
                );

                psUpdate.setString(1, request.getNombres());
                psUpdate.setString(2, request.getApellidos());
                psUpdate.setString(3, request.getPerfil());
                psUpdate.setString(4, request.getPrograma());
                psUpdate.setString(5, request.getCorreo());
                psUpdate.executeUpdate();
                psUpdate.close();

                response.setEstado("Success");
                response.setMensaje("Usuario actualizado!");
            } else {
                response.setEstado("Error");
                response.setMensaje("El usuario no existe o está inactivo");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
    public MensajeResponse eliminarUsuario(UsuarioRequest request) {


        MensajeResponse response = new MensajeResponse();


        try (

            Connection con = getConnection()) {

            PreparedStatement psSelect = con.prepareStatement(
                    "SELECT COUNT(*) FROM IDO_REPROGRAMACION_CLASES.USUARIO " +
                            "WHERE LOWER(correo) = LOWER(?) AND activo = 'Y'"
            );

            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();


            int count = 0;


            if (rs.next()) count = rs.getInt(1);


            psSelect.close();
            rs.close();


            if (count > 0) {

                PreparedStatement psUpdate = con.prepareStatement(
                        "UPDATE IDO_REPROGRAMACION_CLASES.USUARIO " +
                                "SET activo = 'N' WHERE LOWER(correo) = LOWER(?)"
                );
                psUpdate.setString(1, request.getCorreo());
                psUpdate.executeUpdate();
                psUpdate.close();

                response.setEstado("Success");
                response.setMensaje("Usuario eliminado!");

            } else {

                response.setEstado("Error");
                response.setMensaje("El usuario no existe o ya está inactivo");

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }


    /*
    public MensajeResponse obtenerUsuario() {

        MensajeResponse response = new MensajeResponse();

        try {

            Connection con = getConnection();

            response.setEstado("OK");
            response.setMensaje("Usuario Consultado");

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }
    public MensajeResponse obtenerUsuarioLista(UsuarioRequest request) {

        MensajeResponse response = new MensajeResponse();

        try {

            Connection con = getConnection();

            String correo = request.getCorreo();

            response.setEstado("OK");
            response.setMensaje("Correo Cosultado: " + correo);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }
    */


}


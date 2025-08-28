

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


    public ListaUsuarioResponse consultarUsuario(UsuarioRequest request) {

        ListaUsuarioResponse response = new ListaUsuarioResponse();
        response.setLista(new ArrayList<>());

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
             " SELECT nombres, apellidos, perfil, correo, activo, usuario_responsable " +
                 " FROM reprogramaciones.usuario " +
                 " WHERE LOWER(correo) = LOWER(?) AND activo = true"
            );

            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                UsuarioResponse dto = new UsuarioResponse();
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
                dto.setPerfil(rs.getString("perfil"));
                dto.setCorreo(rs.getString("correo"));
                dto.setActivo(rs.getBoolean("activo"));
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
            " SELECT nombres, apellidos, perfil, correo, activo, usuario_responsable " +
                " FROM reprogramaciones.usuario " +
                " WHERE activo = true "
            );

            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                UsuarioResponse dto = new UsuarioResponse();
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
                dto.setPerfil(rs.getString("perfil"));
                dto.setCorreo(rs.getString("correo"));
                dto.setActivo(rs.getBoolean("activo"));
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

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
                    "SELECT COUNT(*) FROM reprogramaciones.usuario WHERE LOWER(correo) = LOWER(?)"
            );

            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            int count = 0;

            if (rs.next()) {
                count = rs.getInt(1);
            }

            psSelect.close();
            rs.close();

            if (count > 0) {

                PreparedStatement psSelectActivo = con.prepareStatement(
                        "SELECT activo FROM reprogramaciones.usuario WHERE LOWER(correo) = LOWER(?)"
                );

                psSelectActivo.setString(1, request.getCorreo());
                ResultSet rsActivo = psSelectActivo.executeQuery();

                Boolean activo;

                if (rsActivo.next()) {

                    activo = rsActivo.getBoolean(1);

                    System.out.println("activo es: " + activo);

                    if (activo) {

                        response.setEstado("Alert");
                        response.setMensaje("El usuario ya existe");

                    } else {

                        PreparedStatement psInsert = con.prepareStatement(
                                " UPDATE reprogramaciones.usuario " +
                                        " SET nombres = ?, apellidos = ?, perfil = ?, activo = ? " +
                                        " WHERE LOWER(correo) = LOWER(?)"
                        );

                        psInsert.setString(1, request.getNombres());
                        psInsert.setString(2, request.getApellidos());
                        psInsert.setString(3, request.getPerfil());
                        psInsert.setBoolean(4, true);
                        psInsert.setString(5, request.getCorreo());
                        psInsert.executeUpdate();
                        psInsert.close();

                        response.setEstado("Success2");
                        response.setMensaje("El usuario nuevamente agregado");

                    }

                }

            } else {

                PreparedStatement psInsert = con.prepareStatement(
                    " INSERT INTO reprogramaciones.usuario " +
                        " (nombres, apellidos, perfil, correo, activo, usuario_responsable) " +
                        " VALUES (?, ?, ?, ?, ?, ?)"
                );

                psInsert.setString(1, request.getNombres());
                psInsert.setString(2, request.getApellidos());
                psInsert.setString(3, request.getPerfil());
                psInsert.setString(4, request.getCorreo());
                psInsert.setBoolean(5, true);
                psInsert.setString(6, request.getUsuarioResponsable());
                psInsert.executeUpdate();

                psInsert.close();

                response.setEstado("Success");
                response.setMensaje("Usuario Agregado!");

            }

            con.close();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }
    public MensajeResponse actualizarUsuario(UsuarioRequest request) {

        MensajeResponse response = new MensajeResponse();

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
                    "SELECT COUNT(*) FROM reprogramaciones.usuario WHERE LOWER(correo) = LOWER(?) AND activo = true"
            );

            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            int count = 0;

            if (rs.next()) {
                count = rs.getInt(1);
            }

            psSelect.close();
            rs.close();

            if (count > 0) {

                PreparedStatement psUpdate = con.prepareStatement(
                        " UPDATE reprogramaciones.usuario " +
                                " SET nombres = ?, apellidos = ?, perfil = ? " +
                                " WHERE LOWER(correo) = LOWER(?)"
                );

                psUpdate.setString(1, request.getNombres());
                psUpdate.setString(2, request.getApellidos());
                psUpdate.setString(3, request.getPerfil());
                psUpdate.setString(4, request.getCorreo());
                psUpdate.executeUpdate();

                psUpdate.close();

                response.setEstado("Success");
                response.setMensaje("Usuario Actualizado!");

            } else {

                response.setEstado("Error");
                response.setMensaje("El usuario no existe");

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

        return response;

    }
    public MensajeResponse eliminarUsuario(UsuarioRequest request) {

        MensajeResponse response = new MensajeResponse();

        try {

            Connection con = getConnection();

            PreparedStatement psSelect = con.prepareStatement(
                    "SELECT COUNT(*) FROM reprogramaciones.usuario WHERE LOWER(correo) = LOWER(?) AND activo = true"
            );

            psSelect.setString(1, request.getCorreo());
            ResultSet rs = psSelect.executeQuery();

            int count = 0;

            if (rs.next()) {
                count = rs.getInt(1);
            }

            psSelect.close();
            rs.close();

            if (count > 0) {

                PreparedStatement psInsert = con.prepareStatement(
                        " UPDATE reprogramaciones.usuario " +
                                " SET activo = false " +
                                " WHERE LOWER(correo) = LOWER(?)"
                );

                psInsert.setString(1, request.getCorreo());
                psInsert.executeUpdate();

                psInsert.close();

                response.setEstado("Success");
                response.setMensaje("Usuario Eliminado!");

            } else {

                response.setEstado("Error");
                response.setMensaje("El usuario no existe");

            }



            con.close();

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


package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Reservacion;
import connection.DBConnection;

public class ReservacionController implements IReservacionController {

    @Override
    public String listarReservaciones(String id_usuario) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select l.id_destino, l.titulo, l.genero, l.novedad, a.fecha from pelicula l "
                + "inner join alquiler a on l.id_destino = a.id_destino inner join usuarios u on a.id_usuario = u.id_usuario "
                + "where a.id_usuario = '" + id_usuario + "'";

        List<String> reservaciones = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id_reservacion = rs.getInt("id_reservacion");
              //  String id_usuario = rs.getString("id_usuario");
                int id_destino = rs.getInt("id_destino");
                //String titulo = rs.getString("titulo");
                int cant_personas = rs.getInt("cant_personas");
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date fecha_fin = rs.getDate("fecha_fin");
                String forma_pago = rs.getString("forma_pago");
                Double total_pago = rs.getDouble("total_pago");
                //String genero = rs.getString("genero");
               // boolean novedad = rs.getBoolean("novedad");
                //Date fechaAlquiler = rs.getDate("fecha");

                Reservacion reservacion = new Reservacion(id_reservacion, id_usuario, id_destino,  cant_personas, fecha_inicio, fecha_fin, forma_pago, total_pago );//titulo,,fechaAlquiler, novedad, genero

                reservaciones.add(gson.toJson(reservacion));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(reservaciones);
    }
}
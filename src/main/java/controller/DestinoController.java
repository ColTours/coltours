package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Destino;
import connection.DBConnection;

public class DestinoController implements IDestinoController {
    
    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM destino";

        if (ordenar == true) {
            sql += " order by ciudad " + orden;
        }

        List<String> destinos = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id_destino = rs.getInt("id_destino");
                String planes = rs.getString("planes");
                Double precio =rs.getDouble("precio");
                String ciudad = rs.getString("ciudad");
                //String autor = rs.getString("autor");
               // int copias = rs.getInt("copias");
               //boolean novedad = rs.getBoolean("novedad");

                Destino destino = new Destino(id_destino, planes, precio, ciudad); //copias, novedad);

                destinos.add(gson.toJson(destino));
                //System.out.println(destino.toString());

            }
            //st.executeQuery(sql);
            
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        finally {
            con.desconectar();
        }

        return gson.toJson(destinos);

    }
    
     @Override
    public String devolver(int id_destino, String id_usuario) {

        DBConnection con = new DBConnection();
        String sql = "Delete from reservacion where id_destino= " + id_destino + " and id_usuario = '" 
                + id_usuario + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id_destino);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }
     
       @Override
    public String sumarCantidad(int id_destino) {

        DBConnection con = new DBConnection();

        String sql = "Update destino set ciudad = (Select ciudad from destino where id_destino = " 
                + id_destino+ ") + 1 where id_destino = " + id_destino;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
   @Override
    public String reservar(int id_destino, String id_usuario) {

        Timestamp fecha_inicio = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();
        String sql = "Insert into reservacion values ('" + id_destino + "', '" + id_usuario + "', '" + fecha_inicio + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id_destino);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String modificar(int id_destino) {

        DBConnection con = new DBConnection();
        String sql = "Update destino set ciudad = (ciudad - 1) where id_destino = " + id_destino;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
}

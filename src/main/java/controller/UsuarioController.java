package controller;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import beans.Usuario;
import connection.DBConnection;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController implements IUsuarioController {

    @Override
    public String login(String id_usuario, String contrasena) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select * from usuario where id_usuario = '" + id_usuario
                + "' and contrasena = '" + contrasena + "'";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String telefono = rs.getString("telefono");
                //double saldo = rs.getDouble("saldo");
                //boolean premium = rs.getBoolean("premium");

                Usuario usuario = new Usuario(id_usuario, contrasena, nombre, apellidos, email, direccion, ciudad, telefono);
                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
   @Override
    public String register(String id_usuario, String contrasena, String nombre, String apellidos, String email, String direccion, String  ciudad, String  telefono) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Insert into usuario values('" + id_usuario + "', '" + contrasena + "', '" + nombre+ "', '" + apellidos + "', '" + email + "', ' " + direccion + " ',' " + ciudad + "',' " + telefono + "')";


        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            Usuario usuario = new Usuario(id_usuario, contrasena, nombre, apellidos, email, direccion, ciudad, telefono);

            st.close();

            return gson.toJson(usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }
    
      @Override
    public String pedir(String id_usuario) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from usuario where id_usuario = '" + id_usuario + "'";

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String telefono = rs.getString("telefono");
                //double saldo = rs.getDouble("saldo");
                //boolean premium = rs.getBoolean("premium");

                Usuario usuario = new Usuario(id_usuario, contrasena, nombre, apellidos, email, direccion, ciudad, telefono);

                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

   @Override
    public String modificar(String id_usuario, String nuevaContrasena, 
            String nuevoNombre, String nuevosApellidos,
            String nuevoEmail, String nuevaDireccion, String nuevaCiudad, String nuevoTelefono ) {   //double nuevoSaldo, boolean nuevoPremium

        DBConnection con = new DBConnection();

        String sql = "Update usuario set contrasena = '" + nuevaContrasena + "', nombre = '" + nuevoNombre + "', "+ "apellidos = '" + nuevosApellidos + "', email = '" + nuevoEmail + "', direccion = '" + nuevaDireccion + "', ciudad = '" + nuevaCiudad + "', telefono = '" + nuevoTelefono + "'";  
      
        /*if (nuevoPremium == true) {
            sql += " 1 ";
        } else {
            sql += " 0 ";
        }*/

        sql += " where id_usuario = '" + id_usuario + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    
    @Override
    public String verCopias(String id_usuario) {

        DBConnection con = new DBConnection();
        String sql = "Select id_destino, count(*) as num_ciudad from alquiler where id_usuario = '"
                + id_usuario + "' group by id_destino;";   //num_copias por num_ciudad

        Map<Integer, Integer> copias = new HashMap<Integer, Integer>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id_destino = rs.getInt("id_destino");
                int num_ciudad = rs.getInt("num_ciudad");

                copias.put(id_destino, num_ciudad);
            }

            devolverDestino(id_usuario, copias);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    
    @Override
    public String devolverDestino(String id_usuario, Map<Integer, Integer> ciudad) {

        DBConnection con = new DBConnection();

        try {
            for (Map.Entry<Integer, Integer> destino : ciudad.entrySet()) {
                int id_destino = destino.getKey();
                int num_ciudad = destino.getValue();

                String sql = "Update destino set ciudad = (Select copias + " + num_ciudad +
                        " from pelicula where id_destino = " + id_destino + ") where id_destino = " + id_destino;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);

            }

            this.eliminar(id_usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }
    
    
    
    public String eliminar(String id_usuario) {

        DBConnection con = new DBConnection();

        String sql1 = "Delete from destino where id_usuario = '" + id_usuario + "'";
        String sql2 = "Delete from usuario where id_usuario = '" + id_usuario + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
}

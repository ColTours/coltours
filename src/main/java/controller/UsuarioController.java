package controller;
import java.sql.ResultSet;
import java.sql.Statement;  
import com.google.gson.Gson;
import beans.Usuario;
import connection.DBConnection;


public class UsuarioController  implements IUsuarioController {
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
                

               Usuario usuario = new Usuario(id_usuario, contrasena, nombre, apellidos, email, direccion, ciudad,telefono);
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
    public String register(String id_usuario, String contrasena, String nombre, String apellidos, String email,
            String direccion, String  ciudad, String  telefono ) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Insert into usuario values('" + id_usuario + "', '" + contrasena + "', '" + nombre
                + "', '" + apellidos + "', '" + email + "', ' " + direccion + " ',' " + ciudad + "',' " + telefono + " )";

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
   
    
}

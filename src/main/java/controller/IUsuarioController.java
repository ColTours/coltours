
package controller;
import java.util.Map;

    public interface IUsuarioController {
    public String login(String id_usuario, String contrasena); 
    
    public String register(String id_usuario, String contrasena, 
    String nombre, String apellidos, String email, String direccion, String ciudad, String telefono);

    
    
}

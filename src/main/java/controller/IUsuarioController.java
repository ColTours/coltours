
package controller;
import java.util.Map;

    public interface IUsuarioController {
    public String login(String id_usuario, String contrasena); 
    
    public String register(String id_usuario, String contrasena, 
    String nombre, String apellidos, String email, String direccion, String ciudad, String telefono);

     public String pedir(String id_usuario);
    
     public String modificar(String id_usuario, String nuevaContrasena, 
            String nuevoNombre, String nuevosApellidos, String nuevoEmail, 
            String nuevaDireccion, String nuevaCiudad, String nuevoTelefono); // double nuevoSaldo, boolean nuevoPremium
     
     public String verCopias(String id_usuario);
     
     public String devolverDestino(String id_usuario, Map<Integer, Integer> copias);

    public String eliminar(String id_usuario);
    
}

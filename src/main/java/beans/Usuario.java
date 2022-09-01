
package beans;

public class Usuario {
    private String id_usuario;  
    private String contrasena;
    private String nombre;
    private String apellidos; 
    private String email;
    private String direccion;
    private String ciudad;
    private String telefono;

    public Usuario(String id_usuario, String contrasena, String nombre, String apellidos, String email, String direccion, String ciudad, String telefono) {
        this.id_usuario = id_usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", contrasena=" + contrasena + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", direccion=" + direccion + ", ciudad=" + ciudad + ", telefono=" + telefono + '}';
    }

    
        
    
}

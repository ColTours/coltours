
package controller;

public interface IDestinoController {
    
    public String listar(boolean ordenar, String orden);
    
    public String devolver(int id_destino, String id_usuario);
     
     public String sumarCantidad(int id_destino);
    
}
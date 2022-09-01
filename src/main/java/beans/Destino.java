
package beans;

public class Destino {
    private int id_destino;
    private String planes;
    private double precio;
    private String ciudad;

    public Destino(int id_destino, String planes, double precio, String ciudad) {
        this.id_destino = id_destino;
        this.planes = planes;
        this.precio = precio;
        this.ciudad = ciudad;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Destino{" + "id_destino=" + id_destino + ", planes=" + planes + ", precio=" + precio + ", ciudad=" + ciudad + '}';
    }

   
}

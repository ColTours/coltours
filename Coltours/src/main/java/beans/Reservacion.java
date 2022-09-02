
package beans;

import java.sql.Date;

public class Reservacion {
   
    private int id_reservacion;
    private String id_usuario;
    private int id_destino;
    private int cant_personas;
    private Date fecha_inicio; 
    private Date fecha_fin;
    private String forma_pago;
    private double total_pago;

    public Reservacion(int id_reservacion, String id_usuario, int id_destino, int cant_personas, Date fecha_inicio, Date fecha_fin, String forma_pago, double total_pago) {
        this.id_reservacion = id_reservacion;
        this.id_usuario = id_usuario;
        this.id_destino = id_destino;
        this.cant_personas = cant_personas;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.forma_pago = forma_pago;
        this.total_pago = total_pago;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public int getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(int cant_personas) {
        this.cant_personas = cant_personas;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public double getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(double total_pago) {
        this.total_pago = total_pago;
    }

    @Override
    public String toString() {
        return "Reservacion{" + "id_reservacion=" + id_reservacion + ", id_usuario=" + id_usuario + ", id_destino=" + id_destino + ", cant_personas=" + cant_personas + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", forma_pago=" + forma_pago + ", total_pago=" + total_pago + '}';
    }
    
    
}

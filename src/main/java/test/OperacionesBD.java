
package test;

import beans.Destino;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {
    public static void main(String[] args) {
    //actualizarDestino(2,"Mingueo"); 
     listarDestino();
        
    }
    
    public static void actualizarDestino(int id_destino, String ciudad){
        DBConnection con=new DBConnection();
        String sql="UPDATE destino SET ciudad='"+ciudad+"'WHERE id_destino="+ id_destino;
      
        try {
        Statement st=con.getConnection().createStatement();
        st.executeUpdate(sql);
                   
       } 
       catch(Exception ex){
           System.out.println(ex.getMessage());
       }
       finally {
           con.desconectar();
       }
        
    } 
    
    public static void listarDestino(){
      DBConnection con=new DBConnection();
      String sql="SELECT * FROM destino";
      
      try {
        Statement st=con.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
           int id_destino=rs.getInt("id_destino");
           String planes=rs.getString("planes");
           double precio=rs.getDouble("precio");
           String ciudad=rs.getString("ciudad");
                      
           Destino destino =new Destino(id_destino, planes,precio,ciudad);
           System.out.println(destino.toString());
        }
        
        st.executeQuery(sql);                
       } 
         catch(Exception ex){
           System.out.println(ex.getMessage());
       }
       finally {
           con.desconectar();
       }
    }
    
}

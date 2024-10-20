
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    public static boolean Conexion(){
       String url = "jdbc:mysql://localhost:3306/ordenesumg";
        String usuario = "Clairo";
        String clave = "ClaireCottrill07$";
        boolean respuesta = false;
    
        try{
            Connection cn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexion exitorsa");
            respuesta = true;
            cn.close();
        }
        catch(SQLException e){
            respuesta = false;
            System.out.println("Error al conectar");
            e.printStackTrace();
        }
        return respuesta;
    }     
}

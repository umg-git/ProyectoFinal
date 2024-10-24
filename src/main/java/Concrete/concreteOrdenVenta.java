package Concrete;

import Interfaces.IOrdenVenta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class concreteOrdenVenta implements IOrdenVenta {
    
    // Parámetros de la base de datos
    String url = "jdbc:mysql://localhost:3306/ordenesumg";
    String usuario = "Clairo";
    String clave = "ClaireCottrill07$";
    
    @Override
    public String Lista(JTable tabla) {
                String mensaje = "";
        String cadena = "select a.cliente,a.Apellido,a.FechaOrden,b.Nombre,c.Producto,a.total from orden_venta a\n" +
                        "inner join estado_orden_venta b on a.Estado=b.idEstadoOrdenVenta\n" +
                        "inner join producto c on a.idProducto=c.idProducto\n" +
                        "order by a.idOrdenVenta";

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        try (Connection conn = DriverManager.getConnection(url, usuario, clave);
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(cadena)) {

            int columnCount = rs.getMetaData().getColumnCount();

            model.setRowCount(0);
            
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje += "Error" + e;
        }
        return mensaje;
    }

    @Override
    public String Registrar(String cliente, String apellido, String fechaOrden, int estado, int idProducto, float total) {
                        String mensaje = "";
        // Consulta SQL de inserción
        String cadena = "INSERT INTO orden_venta(Cliente,Apellido,FechaOrden,Estado,idProducto,Total)" +
                        "values(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, clave);
             PreparedStatement pstmt = conn.prepareStatement(cadena)) {

            // Establecer los valores de los parámetros
            pstmt.setString(1, cliente);  
            pstmt.setString(2, apellido); 
            pstmt.setString(3, fechaOrden); 
            pstmt.setInt(4, estado);
            pstmt.setInt(5, idProducto);
            pstmt.setFloat(6, total);

            // Ejecutar el insert
            int rowsAffected = pstmt.executeUpdate();

            // Verificar el resultado
            if (rowsAffected > 0) {
                mensaje += "Operación realizada con exito";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje += "Error" + e;
        }
        return mensaje;
    }
    
}

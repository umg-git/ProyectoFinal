
package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrdenController {
    // Parámetros de la base de datos
        String url = "jdbc:mysql://localhost:3306/ordenesumg";
        String usuario = "Clairo";
        String clave = "ClaireCottrill07$";
        
    public String listaOrden(JTable tabla){
        String mensaje = "";
        String cadena = "select a.idOrden, a.proveedor, a.direccion, a.telefono, a.fechaOrden, a.fechaEntrega, b.Nombre from orden_compra a\n" +
                        "inner join estado_orden_compra b on a.idEstadoOrden=b.idEstado\n" +
                        "order by idOrden";

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
    
    public String registrarOrden(String cliente,String apellido,String telefon ){
        String mensaje = "";
        // Consulta SQL de inserción
        String cadena = "INSERT INTO orden_compra (cliente, idProducto, fecha) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, clave);
             PreparedStatement pstmt = conn.prepareStatement(cadena)) {

            // Establecer los valores de los parámetros
            pstmt.setString(1, cliente);  
            pstmt.setString(2, apellido); 
            pstmt.setString(3, telefon);     

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

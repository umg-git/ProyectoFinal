package Concrete;
import Interfaces.IOrdenCompra;
import Interfaces.IProducto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class concreteProducto implements IProducto {

    // Parámetros de la base de datos
    String url = "jdbc:mysql://localhost:3306/ordenesumg";
    String usuario = "Clairo";
    String clave = "ClaireCottrill07$";
    
    @Override
    public String Lista(JTable tabla) {
        String mensaje = "";
        String cadena = "select a.idProducto,a.producto,a.descripcion,a.precio,a.stock,a.tallas,b.descripcion from producto a\n" +
                        "inner join estadoproducto b on a.idEstado=b.idEstadoProducto\n" +
                        "order by idProducto"; 

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
    public String Registrar(String producto, String descri, float precio, int stock, String talla, int idE) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String Actualizar(int id, String producto, String descri, float precio, int stock, String talla, int idE) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}

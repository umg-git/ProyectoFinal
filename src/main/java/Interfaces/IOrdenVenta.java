package Interfaces;

import javax.swing.JTable;


public interface IOrdenVenta {
    String Lista(JTable tabla);
    String Registrar(String cliente,String apellido,String fechaOrden,int estado,int idProducto,float total);
}

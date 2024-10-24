package Interfaces;

import javax.swing.JTable;

public interface IProducto {
    String Lista(JTable tabla);
    String Registrar(String producto,String descri,float precio,int stock,String talla,int idE);
    String Actualizar(int id,String producto,String descri,float precio,int stock,String talla,int idE);
}

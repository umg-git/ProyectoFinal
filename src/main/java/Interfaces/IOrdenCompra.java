package Interfaces;

import javax.swing.JTable;

public interface IOrdenCompra {
    String Lista(JTable tabla);
    String Registrar(String p,String d,String t,String fo,String fe,int idE);
}

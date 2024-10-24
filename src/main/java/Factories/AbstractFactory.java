package Factories;

import Interfaces.IOrdenCompra;
import Interfaces.IProducto;
import javax.swing.JTable;

public abstract class AbstractFactory {
    public abstract IOrdenCompra Test();
    public abstract IProducto producto();
    //public abstract IOrdenCompra Registrar();

}

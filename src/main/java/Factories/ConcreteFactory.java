
package Factories;

import Concrete.concreteOrdenCompra;
import Concrete.concreteProducto;
import Interfaces.IOrdenCompra;
import Interfaces.IProducto;
import javax.swing.JTable;


public class ConcreteFactory extends AbstractFactory {

    @Override
    public IOrdenCompra Test() {
        return new concreteOrdenCompra(); 
    }
    
    @Override
    public IProducto producto() {
        return new concreteProducto(); 
    }

    
}

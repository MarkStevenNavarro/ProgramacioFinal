package daos;

import models.Producte;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProducteDAO {
    //CRUD
    public void createProducte(Producte p)throws SQLException;
    public void deleteProducte(Producte p)throws SQLException;
    public  void updateProducte(Producte p,String codiProducte)throws SQLException;
    public ArrayList<Producte> readProductes() throws SQLException;
    //public Map<String,Producte> readProductes();






}

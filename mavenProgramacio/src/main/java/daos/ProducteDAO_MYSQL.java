package daos;

import models.Producte;

import java.sql.*;
import java.util.ArrayList;

public class ProducteDAO_MYSQL implements ProducteDAO {
    private static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DB_ROUTE="jdbc:mysql://localhost:3306/expenedora";
    private static final String DB_USER="root";
    private static final String DB_PWD="1234";


    private Connection conn=null;

    public ProducteDAO_MYSQL(){
        try {
            Class.forName(DB_DRIVER);
            this.conn= DriverManager.getConnection(DB_ROUTE,DB_USER,DB_PWD);

        }catch (Exception e){
            System.out.println(e);
            System.out.println("error");
        }

    }
    @Override
    public void createProducte(Producte p) throws SQLException {
        PreparedStatement ps= conn.prepareStatement("insert into producte values (?,?,?,?,?)");
        ps.setString(1,p.getCodiProducte());
        ps.setString(2,p.getNom());
        ps.setString(3,p.getDescripcio());
        ps.setFloat(4,p.getPreuCompra());
        ps.setFloat(5,p.getPreuVenta());
        int rowCount=ps.executeUpdate();

    }

    @Override
    public void deleteProducte(Producte p) {

    }

    @Override
    public void updateProducte(Producte p, String codiProducte) {

    }

    @Override
    public ArrayList<Producte> readProductes() throws SQLException {
        ArrayList<Producte> llistaProductes=new ArrayList<>();
        PreparedStatement ps=conn.prepareStatement("select * from producte");
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            Producte p=new Producte();
            p.setCodiProducte(rs.getString(1));
            p.setNom(rs.getString(2));
            p.setDescripcio(rs.getString(3));
            p.setPreuCompra(rs.getFloat(4));
            p.setPreuVenta(rs.getFloat(5));
            llistaProductes.add(p);

        }


        return llistaProductes;
    }
}

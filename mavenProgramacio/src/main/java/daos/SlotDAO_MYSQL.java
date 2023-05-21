package daos;
import models.Slot;

import java.sql.*;
import java.util.ArrayList;

public class SlotDAO_MYSQL implements SlotDAO{

    private static final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DB_ROUTE="jdbc:mysql://localhost:3306/expenedora";
    private static final String DB_USER="root";
    private static final String DB_PWD="1234";

    private Connection conn =null;
    public SlotDAO_MYSQL(){
        try {
            Class.forName(DB_DRIVER);
            this.conn= DriverManager.getConnection(DB_ROUTE,DB_USER,DB_PWD);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("error");
        }
    }

    @Override
    public void createSlot(Slot s) throws SQLException {

    }

    @Override
    public void updateSlot(Slot s, int posicio) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("update slot set posicio=?,quantitat=?,codi_producte=? where posicio=?");
        ps.setInt(1,s.getPosicio());
        ps.setInt(2,s.getQuantitat());
        ps.setString(3,s.getCodiProducte());
        ps.setInt(4,posicio);
        int countRow=ps.executeUpdate();


    }

    @Override
    public void deleteSlot(Slot s) throws SQLException {

    }

    @Override
    public ArrayList<Slot> readSlot() throws SQLException {
        ArrayList<Slot> llistaSlots=new ArrayList<>();
        PreparedStatement ps=conn.prepareStatement("select * from slot ");
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            Slot s=new Slot();
            s.setPosicio(rs.getInt("posicio"));
            s.setQuantitat(rs.getInt("quantitat"));
            s.setCodiProducte(rs.getString("codi_producte"));
            llistaSlots.add(s);

        }
        return llistaSlots;




    }
}

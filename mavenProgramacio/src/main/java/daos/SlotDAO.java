package daos;

import models.Producte;
import models.Slot;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SlotDAO {
    public void createSlot(Slot s)throws SQLException;
    public void updateSlot(Slot s,int posicio)throws SQLException;
    public void deleteSlot(Slot s)throws SQLException;
    public ArrayList<Slot> readSlot()throws SQLException;


}

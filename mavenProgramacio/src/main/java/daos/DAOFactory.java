package daos;


public class DAOFactory {
    private static DAOFactory instance;
    private static ProducteDAO producteDAOInstance;
    private static SlotDAO slotDAOInstance;

    public DAOFactory() {
    }
    public static DAOFactory getInstance(){
        if (instance==null){
            instance=new DAOFactory();
        }
        return instance;
    }
    public ProducteDAO getProducteDAO(){
        if (producteDAOInstance==null){
            producteDAOInstance=new ProducteDAO_MYSQL();
        }
        return producteDAOInstance;
    }
    public SlotDAO getSlotDAO(){
        if (slotDAOInstance==null){
            slotDAOInstance=new SlotDAO_MYSQL();
        }
        return slotDAOInstance;

    }
}

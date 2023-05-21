import daos.DAOFactory;

import models.Producte;
import models.Slot;


import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    //Inicialitzem la nostre daoFactory i l'input helper
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final InputHelper inputHelper=InputHelper.getInput();

    public static void main(String[] args) {
        int opcio;

        do {
            mostrarMenu();
            opcio = inputHelper.demanarOpcioMenu();

            switch (opcio) {
                case 1 -> mostrarMaquina();
                case 2 -> comprarProducte();
                case 10 -> mostrarInventari();
                case 11 -> afegirProductes();
                case 12 -> modificarMaquina();
                case 13 -> mostrarBenefici();
                case -1 -> System.out.println("Bye...");
                default -> System.out.println("Opció no vàlida");
            }

        } while (opcio != -1);


    }

    private static void mostrarBenefici() {

    }

    /**
     * Aquest metode ens permet modificar la maquina ja sigui la seva posicio,quantitat o codi del producte
     */

    private static void modificarMaquina() {
        ArrayList<Slot> maquina;
        try {
            maquina=daoFactory.getSlotDAO().readSlot();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int posicio= inputHelper.demanarPosicio();
        Slot producte=obtenirProducte(maquina,posicio);
        System.out.print("(NOVA) ");
        producte.setPosicio(inputHelper.demanarPosicio());
        producte.setQuantitat(inputHelper.demanarQuantitat());
        producte.setCodiProducte(inputHelper.demanarCodiProducte());
        try {
            daoFactory.getSlotDAO().updateSlot(producte,posicio);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * En permet afegir el prodcute a la base de dades
     */

    private static void afegirProductes() {
        String cp=inputHelper.demanarCodiProducte();
        String nom=inputHelper.demanarNom();
        String descripcio=inputHelper.demanarDescripcio();
        float pc=inputHelper.demanarPreuCompra();
        float pv=inputHelper.demanarPreuVenda();
        Producte p = new Producte(cp, nom, descripcio, pc, pv);
        String pr = null;
        try {

            pr=comprovarCodi(p.getCodiProducte());
            daoFactory.getProducteDAO().createProducte(p);
        }catch (SQLException e) {
            System.out.println("Aquest codi ja existeix i el te "+pr);
            }
    }

    /**
     * Aquest metode comprova si el codi del producte que volem posar ja existeix
     * @param codi El codi del producte que volem posar
     * @return en el cas de que trobi un igual retornara el nom
     */

    private static String comprovarCodi(String codi) {
        ArrayList<Producte> llistaProductes;
        try {
            llistaProductes=daoFactory.getProducteDAO().readProductes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Producte pa:llistaProductes){
            if (pa.getCodiProducte().equals(codi)){
                return pa.getNom();
            }
        }
        return null;
    }

    /**
     * Mostra tot l'inventari que tenim
     */
    private static void mostrarInventari() {

        ArrayList<Producte> llistaProductes;
        try {
            llistaProductes = daoFactory.getProducteDAO().readProductes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        llistaProductes.forEach(System.out::println);
    }

    /**
     * permet fer la compra de un producte indicant la seva posicio
     */

    private static void comprarProducte() {
        ArrayList<Slot> maquina;
        try {
            maquina=daoFactory.getSlotDAO().readSlot();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mostrarMaquina();
        int posicio= inputHelper.demanarPosicio();
        Slot producte=obtenirProducte(maquina,posicio);
        if (producte.getQuantitat()!=0){
            producte.setQuantitat(producte.getQuantitat()-1);
            try {
                daoFactory.getSlotDAO().updateSlot(producte,posicio);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("El producte no te stock");
        }





    }

    /**
     * retorna el producte que hem indicaat
     * @param maquina maquina expenedora
     * @param posicio posicio que em triat
     * @return el producte
     */
    private static Slot obtenirProducte(ArrayList<Slot> maquina, int posicio) {
        for (Slot s:maquina){
            if (s.getPosicio()==posicio){
                return s;
            }
        }
        return null;
    }

    /**
     * Mostra la maquina expenedora
     */
    private static void mostrarMaquina() {
        ArrayList<Slot> llistaSlots;
    try {
            llistaSlots=daoFactory.getSlotDAO().readSlot();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("POSICIO     NOM         QUANTITAT ");
        System.out.println("=====================================");
        for (Slot s:llistaSlots){
            String nom=obtenirNom(s.getCodiProducte());
            System.out.println(s.getPosicio()+"     "+nom+"         "+s.getQuantitat());
        }
    }

    /**
     * Obtenim el nom de una producte mitjançant el codi
     * @param codiProducte
     * @return nom del producte
     */
    private static String obtenirNom(String codiProducte){
        ArrayList<Producte> llistaProductes;
        try {
            llistaProductes=daoFactory.getProducteDAO().readProductes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            for (Producte p:llistaProductes){
                if (p.getCodiProducte().equals(codiProducte)){
                   return  p.getNom();
                }

            }


        return null;
    }

    /**
     * mostra el menu
     */
    private static void mostrarMenu() {
        System.out.println("\nMenú de la màquina expenedora");
        System.out.println("=============================");
        System.out.println("Selecciona la operació a realitzar introduïnt el número corresponent: \n");


        //Opcions per client / usuari
        System.out.println("[1] Mostrar Posició / Nom producte / Stock de la màquina");
        System.out.println("[2] Comprar un producte");

        //Opcions per administrador / manteniment
        System.out.println();
        System.out.println("[10] Mostrar llistat productes disponibles (BD)");
        System.out.println("[11] Afegir productes disponibles");
        System.out.println("[12] Assignar productes / stock a la màquina");
        System.out.println("[13] Mostrar benefici");

        System.out.println();
        System.out.println("[-1] Sortir de l'aplicació");
    }
}


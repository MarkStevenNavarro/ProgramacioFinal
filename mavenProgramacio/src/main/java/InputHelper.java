import java.util.Scanner;

public class InputHelper {
    private  static InputHelper inputHelper=null;
    private static Scanner entrada =new Scanner(System.in);
    public InputHelper(){

    }


    public static InputHelper getInput(){
        if (inputHelper==null){
            inputHelper=new InputHelper();
        }
        return inputHelper;
    }
    public int demanarOpcioMenu(){
        System.out.println("Tria una opcio");
        return Integer.parseInt(entrada.nextLine());
    }

    public String demanarCodiProducte(){
        System.out.println("Introdueix el codi del producte");
        return entrada.nextLine();

    }
    public String demanarNom(){
        System.out.println("Introdueix el nom");
        return entrada.nextLine();
    }
    public String demanarDescripcio(){
        System.out.println("Introdueix la descripcio");
        return entrada.nextLine();
    }
    public float demanarPreuCompra(){
        System.out.println("Introdueix el preu compra");
        return Float.parseFloat(entrada.nextLine());
    }
    public float demanarPreuVenda(){
        System.out.println("Introdueix el preu venda");
        return Float.parseFloat(entrada.nextLine());
    }
    public int demanarPosicio(){
        System.out.println("Introdueix la posicio");
        return Integer.parseInt(entrada.nextLine());
    }
    public int demanarQuantitat(){
        System.out.println("Introdueix la quantitat");
        return Integer.parseInt(entrada.nextLine());
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.outils.Sealevel470U.API_Sealevel470U;
import com.sun.jna.ptr.IntByReference;
import java.util.Scanner;
import static test.TestJNA.BORNE;

/**
 *
 * @author Jacques
 */
public class TestInputJNA {
        /**
     * handler de la centrale 1
     */
    private IntByReference seaMAXHandle1;
    /**
     * handler de la centrale 2
     */
     private IntByReference seaMAXHandle2;
    
    /**
     * Interface de l'api de la central 470U
     */
    private API_Sealevel470U lib;
    
    /**
     * Retour d'erreur des fonctions de l'API
     */
    private int err;
    
    /*
     * port de communication
     */
    private static int PORT1 ;
    private static int PORT2 ;
    
    private static final int OUVRIR = 1;
    private static final int FERMER = 0;
    
    /**
     * intervals de mesure pour chaque entrée analogique
     */
    private  byte[] inputRanges1;
    private  byte[] inputRanges2;
    
    private static int HUMIDITE1234;
    private static int HUMIDITE5678;
    private static int HUMIDITE_SAS;
    private static int VALVE1;
    private static int VALVE2;
    private static int VALVE3;
    private static int VALVE4;
    private static int VALVE5;
    private static int VALVE6;
    private static int VALVE7;
    private static int VALVE8;
    private static int POSITIONEMENT_SAS;
    private static int REJET1;
    private static int REJET2;
    private static int ELECTROVANNE3VOIES;
    private static int INPUT_TEMPERATURE1;
    private static int INPUT_TEMPERATURE2;
    private static int INPUT_TEMPERATURE3;
    private static int INPUT_TEMPERATURE4;
    private static int INPUT_TEMPERATURE5;
    private static int INPUT_TEMPERATURE6;
    private static int INPUT_TEMPERATURE7;
    private static int INPUT_TEMPERATURE8;
    private static int INPUT_SAS;
    private static int INPUT_HUMIDITE1234;
    private static int INPUT_HUMIDITE5678;
    private static int INPUT_O3;
    private static int INPUT_CO2;

     
     public static void main(String[] args){
         double[] moyennes = new double[8];
         int nbPrise = 0;
         TestInputJNA test = new TestInputJNA();
         boolean b = true;
        Scanner sc = new Scanner(System.in);
        int channel;
        int value;
         for(int j=0;j<100;j++){
            //try{
                
            System.out.println("GO ? ");
            channel = sc.nextInt();//Integer.parseInt(sc.nextLine());
            if(channel == 0)
                break;
            
                for(int i = 1;i<9;i++){
                    Double d = test.getValue(i);
                    System.out.println("CENTRALE1 : getValue("+i+") : "+d);
                    moyennes[i-1]=moyennes[i-1]+d;
                }
                for(int i = 9;i<16;i++){
                    System.out.println("CENTRALE2 : getValue("+i+") : "+test.getValue(i));
                }
                
                nbPrise++;
                for(int k=0;k<8;k++)
                    System.out.println("moyenne["+k+"] : "+moyennes[k]/nbPrise);
              }
 
        }
     

     /**
      * initialise les ports de la centrale un et deux selon le fichier de config
      */
    public void initialisationConstantes(){
        System.out.println("INITIALISATION DES CONSTANTES");
        PORT1 = Integer.parseInt(Configuration.getConfiguration().getPropertie("PORT1"));
        PORT2 = Integer.parseInt(Configuration.getConfiguration().getPropertie("PORT2"));
        HUMIDITE1234 = Integer.parseInt(Configuration.getConfiguration().getPropertie("HUMIDITE1234"));
        HUMIDITE5678 = Integer.parseInt(Configuration.getConfiguration().getPropertie("HUMIDITE5678"));
        HUMIDITE_SAS= Integer.parseInt(Configuration.getConfiguration().getPropertie("HUMIDITE_SAS"));
        VALVE1= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE1"));
        VALVE2= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE2"));
        VALVE3= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE3"));
        VALVE4= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE4"));
        VALVE5= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE5"));
        VALVE6= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE6"));
        VALVE7= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE7"));
        VALVE8= Integer.parseInt(Configuration.getConfiguration().getPropertie("VALVE8"));
        POSITIONEMENT_SAS= Integer.parseInt(Configuration.getConfiguration().getPropertie("POSITIONEMENT_SAS"));
        REJET1= Integer.parseInt(Configuration.getConfiguration().getPropertie("REJET1"));
        REJET2= Integer.parseInt(Configuration.getConfiguration().getPropertie("REJET2"));
        ELECTROVANNE3VOIES= Integer.parseInt(Configuration.getConfiguration().getPropertie("ELECTROVANNE3VOIES"));
        INPUT_TEMPERATURE1 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE1"));
        INPUT_TEMPERATURE2 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE2"));
        INPUT_TEMPERATURE3 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE3"));
        INPUT_TEMPERATURE4 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE4"));
        INPUT_TEMPERATURE5 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE5"));
        INPUT_TEMPERATURE6 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE6"));
        INPUT_TEMPERATURE7 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE7"));
        INPUT_TEMPERATURE8 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_TEMPERATURE8"));
        INPUT_SAS= Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_SAS"));
        INPUT_HUMIDITE1234= Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_HUMIDITE1234"));
        INPUT_HUMIDITE5678 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_HUMIDITE5678"));
        INPUT_O3= Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_O3"));
        INPUT_CO2 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_CO2"));
        

    }
    
    public TestInputJNA(){
        initialisationConstantes();
        seaMAXHandle1 = new IntByReference();
        lib = API_Sealevel470U.INSTANCE;
        err = lib.SM_Open(seaMAXHandle1, "COM"+PORT1);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", "CommunicationSealevel570U","Impossible d'ouvrir de port "+PORT1, new Exception("Voir API , SM_Open erreur "+err));
        }
        err = lib.SM_SetAnalogInputConfig(seaMAXHandle1.getValue(), API_Sealevel470U.GROUND, API_Sealevel470U.SINGLE_ENDED);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "CommunicationSealevel570U","Operation problématique "+"COM"+PORT1, 
                    new Exception("Voir API , SM_SetAnalogInputConfig "+err));
        }
        // Get the current analog input ranges
        inputRanges1 = new byte[8];
        /* on configure les ranges d'input de +/-5V pour chaque analogue input */
        for(int i=0;i<8;i++){
            inputRanges1[i] =  API_Sealevel470U.PLUS_MINUS_FIFTEEN;
        }
        err =   lib.SM_SetAnalogInputRanges(seaMAXHandle1.getValue(), inputRanges1);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "CommunicationSealevel570U","Operation problématique "+"COM"+PORT1, 
                    new Exception("Voir API , SM_SetAnalogInputRanges "+err));
        }
        
        seaMAXHandle2 = new IntByReference();
        lib = API_Sealevel470U.INSTANCE;
        err = lib.SM_Open(seaMAXHandle2, "COM"+PORT2);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", "CommunicationSealevel570U","Impossible d'ouvrir de port "+PORT2, new Exception("Voir API , SM_Open erreur "+err));
        }
        /* configuration pour la reception de données analogiques */
        err = lib.SM_SetAnalogInputConfig(seaMAXHandle2.getValue(), API_Sealevel470U.GROUND, API_Sealevel470U.SINGLE_ENDED);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "CommunicationSealevel570U","Operation problématique "+"COM"+PORT2, 
                    new Exception("Voir API , SM_SetAnalogInputConfig "+err));
        }
        inputRanges2 = new byte[8];
        /* on configure les ranges d'input de +/-5V pour chaque analogue input */
        for(int i=0;i<8;i++){
            inputRanges2[i] =  API_Sealevel470U.PLUS_MINUS_FIFTEEN;
        }
        err =   lib.SM_SetAnalogInputRanges(seaMAXHandle2.getValue(), inputRanges2);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "CommunicationSealevel570U","Operation problématique "+"COM"+PORT2, 
                    new Exception("Voir API , SM_SetAnalogInputRanges "+err));
        }
        
    }

    
    public double getValue(int numCabine){
  /* si entre 1 et 8 retourner la temperature */
            /* si 15 CO2 */
            /* si 11 SAS */
            /* si 12 humidite1234 */
            /* si 13 humidite56789 */
            /* si 14 O3 */
            //numCabine = numCabine - 1;
            double[] inputs = new double[8];
            int valueHandle ;
            int channel = 9999 ;
            byte[] inputRanges ;
            switch(numCabine){
                case 1 : channel = INPUT_TEMPERATURE1;  break;
                case 2 : channel = INPUT_TEMPERATURE2;  break;
                case 3 : channel = INPUT_TEMPERATURE3;  break;
                case 4 : channel = INPUT_TEMPERATURE4;  break;
                case 5 : channel = INPUT_TEMPERATURE5;  break;
                case 6 : channel = INPUT_TEMPERATURE6;  break;
                case 7 : channel = INPUT_TEMPERATURE7;  break;
                case 8 : channel = INPUT_TEMPERATURE8;  break;
                case 11 : channel = INPUT_SAS;          break;
                case 12 : channel = INPUT_HUMIDITE1234; break;
                case 13 : channel = INPUT_HUMIDITE5678; break;
                case 14 : channel = INPUT_O3;           break;
                case 15 : channel = INPUT_CO2;           break;
                default :
                    chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", "send","Operation problématique : getValue("+numCabine+")", new Exception("Numero de cabine incorrect"));
                    break;
            }
            channel = channel -1;
            if(channel < 8){
                valueHandle = seaMAXHandle1.getValue();
                inputRanges = inputRanges1;
            }else{
                valueHandle = seaMAXHandle2.getValue();
                inputRanges = inputRanges2;
                channel = channel - 8;
            }
            err = lib.SM_ReadAnalogInputs(valueHandle, 0, 8, inputs, inputRanges, null);
            if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "getValue("+numCabine+")","Operation problématique", 
                    new Exception("Voir API , SM_ReadAnalogInputs "+err));
            }
            return inputs[channel];
	}

	
        
    }
    

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import chambresPhytotroniques.outils.Configuration;
import chambresPhytotroniques.outils.Sealevel470U.API_Sealevel470U;
//import chambresPhytotroniques.outils.Sealevel470U.API_Sealevel470U.DeviceConfig;
import com.sun.jna.Native;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author John
 */
public class TestJNA {

    final static int BORNE = 1000;
      /**
     * handler de la centrale 1
     */
    private static IntByReference seaMAXHandle1;
    /**
     * handler de la centrale 2
     */
     private static IntByReference seaMAXHandle2;
    
    /**
     * Interface de l'api de la central 470U
     */
    private static API_Sealevel470U lib;
    
    /**
     * Retour d'erreur des fonctions de l'API
     */
    private static int err;
    
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
    private static byte[] inputRanges1;
    private static byte[] inputRanges2;
    
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

     /**
      * initialise les constantes 
      */
    public static void initialisationConstantes(){
        System.out.println("INITIALISATION DES CONSTANTES");
        PORT1 = Integer.parseInt(Configuration.getConfiguration().getPropertie("PORT1"));
        PORT2 = Integer.parseInt(Configuration.getConfiguration().getPropertie("PORT2"));
        HUMIDITE1234 = Integer.parseInt(Configuration.getConfiguration().getPropertie("HUMIDITE1234"));
        HUMIDITE5678= Integer.parseInt(Configuration.getConfiguration().getPropertie("HUMIDITE5678"));
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
    public static void main(String[] args){
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
        
        boolean b = true;
        Scanner sc = new Scanner(System.in);
        int channel;
        int value;
        while(b){
            System.out.println("Output channel : ");
            channel = sc.nextInt()-1;//Integer.parseInt(sc.nextLine());
            System.out.println("Valeur : ");
            value = sc.nextInt(); //Integer.parseInt(sc.nextLine());
            if(value != -1)
                send(channel, value);
            else
                b=false;
            
        }
        
        
        
        /* test write */
        /*testWriteDigitalOutput();*/
        /*DeviceConfig d = new DeviceConfig();
        err = lib.SM_GetDeviceConfig(seaMAXHandle.getValue(), d);
        System.out.println("d : "+d);*/
        
        /* test read */
       /* testReadAnalogInputs();*/
        
        /* fermeture du port de communication */
        err = lib.SM_Close(seaMAXHandle1.getValue());
        System.out.println("SM_Close : "+err);
        
        err = lib.SM_Close(seaMAXHandle2.getValue());
        System.out.println("SM_Close : "+err);
        

    }
    
    public static void testReadAnalogInputs(){
        /*
         * SM_SetAnalogInputConfig() & SM_SetAnalogInputRanges()
            Control the analog-to-digital interface configuration
            Valid Analog to Digital Voltage References are Ground (1) and Floating (3).
            Valid Analog to Digital Channel Mode is Single Ended (0).
            Valid Channel Range Values are +-5 (1) and +-15 (3).
         */
       
        // Set the analog input mode
        // GROUND OU FLOATING ?
        err = lib.SM_SetAnalogInputConfig(seaMAXHandle1.getValue(), API_Sealevel470U.GROUND, API_Sealevel470U.SINGLE_ENDED);
        System.out.println("SM_SetAnalogInputConfig : "+err);
        
        // Get the current analog input ranges
        byte[] inputRanges = new byte[16];
        for(int i=0;i<16;i++)
            System.out.println("inputRanges["+i+"] = "+inputRanges[i]);
        err = lib.SM_GetAnalogInputRanges(seaMAXHandle1.getValue(), inputRanges);
        System.out.println("SM_GetAnalogInputRanges : "+err);
        for(int i=0;i<16;i++)
            System.out.println("next inputRanges["+i+"] = "+inputRanges[i]);
       
        /* on configure les ranges d'input de +/-5V pour chaque analogue input */
        for(int i=0;i<8;i++){
            inputRanges[i] =  API_Sealevel470U.PLUS_MINUS_FIFTEEN;
        }
        err =   lib.SM_SetAnalogInputRanges(seaMAXHandle1.getValue(), inputRanges);
        System.out.println("SM_SetAnalogInputRanges : "+err);
        // Read analog inputs 3 - 7
        double[] inputs = new double[8];

        //System.out.println("SM_ReadAnalogInputs : "+err);
        for(int j=0;j<8;j++){
            System.out.println("inputs["+j+"] : "+inputs[j]);
        }
        
        ArrayList<Double> moyennes = new ArrayList<Double>();
        for(int i=0;i<8;i++)
            moyennes.add(0.);
        
        for(int j=0;j<BORNE;j++){
            try{
                //do what you want to do before sleeping
                Thread.sleep(2000);//sleep for 1000 ms
                //do what you want to do after sleeptig
                
              }
            
              catch(InterruptedException ie){
              //If this thread was intrrupted by nother thread 
              }
            //err = lib.SM_ReadAnalogInputs(seaMAXHandle.getValue(), 0, 8, inputs, inputRanges, null);
            /*System.out.println("SM_ReadAnalogInputs ("+j+") : "+err);*/
            //DoubleByReference res;
            //for(int k=0;k<8;k++){
               // moyennes.set(k, moyennes.get(k)+inputs[k]);
                //err = lib.SM_AtoDConversion(seaMAXHandle.getValue(), res, inputs[k], err);
                /*System.out.println("next inputs["+k+"] : "+inputs[k]);*/
            //}
        }
        for(int i=0;i<8;i++)
            moyennes.set(i, moyennes.get(i)/BORNE);
        System.out.println("moyennes : "+moyennes);
    }
    
    /**
         * demande à la centrale de placer le bit numéro channel à la valeur choix (0 ou 1) de sa sortie digitale
         * @param channel
         * @param choix 
         */
       public static void send(int channel,int choix){
            channel = channel - 1;
            IntByReference handle;
            if(channel>7){
                handle = seaMAXHandle2;
                channel = channel - 8;
            }else{
                handle = seaMAXHandle1;
            }
            Integer b = 1;
            if(choix == 1){
                for(int i=1;i<(8-channel);i++)
                    b=b*2;
            }else{
                b=0;
            }
            byte[] datab = {b.byteValue()};
            err = lib.SM_WriteDigitalOutputs(handle.getValue(), channel, 1, datab);
            if(err<0){
                chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", "send","Operation problématique : send("+channel+", "+choix+")", new Exception("Voir API , SM_WriteDigitalOutputs "+err));
            }
        }



    public static void testWriteDigitalOutput(){
    // Write to digital outputs 2 to 7
           // 
           // Turn on outputs 2, 4, 5 and 7, turn off outputs 3 and 6 (0x2D = 0010 1101)
           byte[] datab = new byte[2]; //& 0xff
           byte[] datab2 = new byte[2];
           //datab[0] = 0x2D;
           datab[0] = 0x7F;
           //datab2[0] = (0x2D & 0xff);
           datab[1] = 0x00;
           /*String data = new String(datab);*/
            datab2[0] = 0x00;
           //datab2[0] = (0x2D & 0xff);
           datab2[1] = 0x7F;
           int err;
          for(int i=0;i<100;i++){
              System.out.println("i :"+ i);
               if ((err = lib.SM_WriteDigitalOutputs(seaMAXHandle1.getValue(), 0,8, datab) )< 0)
               {   
                   System.out.println("Error writing outputs 0 through 7. : "+err);
               }
               try{
                   //do what you want to do before sleeping
                   Thread.sleep(5000);//sleep for 1000 ms
                   //do what you want to do after sleeptig
                 }
                 catch(InterruptedException ie){
                 //If this thread was intrrupted by nother thread 
                 }
               lib.SM_WriteDigitalOutputs(seaMAXHandle1.getValue(), 0, 8, datab2);
               try{
                   //do what you want to do before sleeping
                   Thread.sleep(5000);//sleep for 1000 ms
                   //do what you want to do after sleeptig
                 }
                 catch(InterruptedException ie){
                 //If this thread was intrrupted by nother thread 
                 }

               //lib.SM_ReadAnalogInputs(seaMAXHandle.getValue(), test, res, null, datab2, datab2);
            }
          
          
    }
}
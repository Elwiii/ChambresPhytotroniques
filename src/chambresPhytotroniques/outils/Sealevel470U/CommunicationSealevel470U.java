/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.outils.Sealevel470U;

import chambresPhytotroniques.outils.Communication;
import chambresPhytotroniques.outils.Configuration;
import com.sun.jna.ptr.IntByReference;
import static test.TestJNAOUPUT.send;

/**
 *
 * @author CARRARA Nicolas
 */
public class CommunicationSealevel470U extends Communication{   
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
    private static Long DELAY_TO_SEND;

     /**
      * initialise les constantes 
      */
    public void initialisationConstantes(){
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
        INPUT_O3 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_O3"));
        INPUT_CO2 = Integer.parseInt(Configuration.getConfiguration().getPropertie("INPUT_CO2"));
        //DELAY_TO_SEND semble corriger un problème de gestion (plantage) de flux (à confirmer)
        DELAY_TO_SEND = Long.parseLong(Configuration.getConfiguration().getPropertie("DELAY_TO_SEND"));
    }
    
    /**
     * connexion aux centrales
     */
    public CommunicationSealevel470U(){
        initialisationConstantes();
        seaMAXHandle1 = new IntByReference();
        lib = API_Sealevel470U.INSTANCE;
        err = lib.SM_Open(seaMAXHandle1, "COM"+PORT1);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", "CommunicationSealevel570U","Impossible d'ouvrir de port "+PORT1, new Exception("Voir API , SM_Open erreur "+err));
        }
        err = lib.SM_SetAnalogInputConfig(seaMAXHandle1.getValue(), API_Sealevel470U.FLOATING, API_Sealevel470U.SINGLE_ENDED);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "CommunicationSealevel570U","Operation problématique "+"COM"+PORT1, 
                    new Exception("Voir API , SM_SetAnalogInputConfig "+err));
        }
        // Get the current analog input ranges
        inputRanges1 = new byte[8];
        /* on configure les ranges d'input de +/-5V pour chaque analogue input */
        for(int i=0;i<8;i++){
            inputRanges1[i] =  API_Sealevel470U.ZERO_TO_FIFTEEN ;
        }
        try {
                Thread.sleep(DELAY_TO_SEND);
            } catch (Exception e) {
                System.out.println(e);
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
        try {
              Thread.sleep(DELAY_TO_SEND);
            } catch (Exception e) {
                System.out.println(e);
              }
        //err = lib.SM_SetAnalogInputConfig(seaMAXHandle2.getValue(), API_Sealevel470U.ANALOG, API_Sealevel470U.SINGLE_ENDED);
        byte analog =1;
        err = lib.SM_SetAnalogInputConfig(seaMAXHandle2.getValue(), analog, API_Sealevel470U.SINGLE_ENDED);

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
        try {
            Thread.sleep(DELAY_TO_SEND);
            } catch (Exception e) {
                System.out.println(e);
              }        
        err =   lib.SM_SetAnalogInputRanges(seaMAXHandle2.getValue(), inputRanges2);
        if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "CommunicationSealevel570U","Operation problématique "+"COM"+PORT2, 
                    new Exception("Voir API , SM_SetAnalogInputRanges "+err));
        }
    
        
    }
    
        /**
         * demande à la centrale de placer le bit numéro channel à la valeur choix (0 ou 1) de sa sortie digitale
         * @param channel
         * @param choix 
         */
        public void send(int channel,int choix){
            System.out.println("==================================");         
            System.out.println("Channel [0-7]:" + (channel -1) );
            if(choix == OUVRIR){
                System.out.println("=========>Ouverture");
                for(int i=1;i<17;i++){
                    if(i!= channel){
                        System.out.println("=========>fermeture");
                        send(i, FERMER);
                    }
                }
            }
            /* les channels sont numérotés de 1 à 16 */
            channel = channel - 1;
            IntByReference handle;
            if(channel>7){
                handle = seaMAXHandle2;
                channel = channel - 8;
                System.out.println("[Boitier 2]");
            }else{
                handle = seaMAXHandle1;
                System.out.println("[Boitier 1]");
            }
            Integer b = 1;
            if(choix == 1){
                for(int i=1;i<(8-channel);i++)
                    b=b*2;
            }else{
                b=0;
            }
            byte[] datab = {b.byteValue()};
           /*dconfig = new IntByReference();
            err = lib.SM_GetDeviceConfig(handle.getValue(), dconfig);
            */
            //System.out.println("SM_WriteDigitalOutputs: channel :"+channel+" datab:"+datab[0]);
            try {
                Thread.sleep(DELAY_TO_SEND);
                } catch (Exception e) {
              System.out.println(e);
            }
            
            System.out.print(String.format("[Sm_WriteDigitalOutputs] : " ));
            System.out.print(String.format("%02x",channel));
            System.out.print(" 01");          
            for (byte by : datab){
                System.out.print(String.format(" %02x", by));
            }
            System.out.println();              
            err = lib.SM_WriteDigitalOutputs(handle.getValue(), channel, 1, datab);
            if(err<0){
                chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", "send","Operation problématique : send("+channel+", "+choix+")", new Exception("Voir API , SM_WriteDigitalOutputs "+err));
            }
        }
        
        /*private void closeOther(int channelNotClose){
            for(int i=1;i<17;i++){
                if(i!= channelNotClose){
                    send(i, FERMER);
                }
            }
        }*/
           
        
        @Override
	public void ouvrireHumidite1234() {
            send(HUMIDITE1234, OUVRIR);
	}

        @Override
	public void fermerHumidite1234() {
            send(HUMIDITE1234, FERMER);
	}

        @Override
	public void ouvrirHumidite5678() {
            send(HUMIDITE5678, OUVRIR);
	}

        @Override
	public void fermetureHumidite5678() {
            send(HUMIDITE5678, FERMER);
	}

        @Override
	public void fermetureHumiditeSas() {
            send(HUMIDITE_SAS, FERMER);
	}

        @Override
	public void ouvrirHumiditeSas() {
            send(HUMIDITE_SAS, OUVRIR);
	}

	/**
	 * Demande et retourne la valeur de l'humitité
	 * 
	 * @param numCabine
	 * 
	 * @return valeur de l'humidité
	 */
        @Override
	public double getValue(int numCabine) {
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
            try {
                Thread.sleep(DELAY_TO_SEND);
                } catch (Exception e) {
              System.out.println(e);
            }
            err = lib.SM_ReadAnalogInputs(valueHandle, 0, 8, inputs, inputRanges, null);
            if(err<0){
            chambresPhytotroniques.outils.Error.getError().error("CommunicationSealevel570U", 
                    "getValue("+numCabine+")","Operation problématique", 
                    new Exception("Voir API , SM_ReadAnalogInputs "+err));
            }
            return inputs[channel];
	}

	
        @Override
	public void positionnementValve(int x) {
		switch (x) {
		case 0:
		case 1:
			positionnementValve1();
			break;

		case 2:
			positionnementValve2();
			break;

		case 3:
			positionnementValve3();
			break;

		case 4:
			positionnementValve4();
			break;

		case 5:
			positionnementValve5();
			break;

		case 6:
			positionnementValve6();
			break;

		case 7:
			positionnementValve7();
			break;

		case 8:
			positionnementValve8();
			break;

		case 9:
			positionnementSas();
			break;

		case 10:
			positionnementRejet1();
			break;

		case 11:
			positionnementRejet2();
			break;

		default:
			chambresPhytotroniques.outils.Error.getError().error("CommunicationSeaLevel570U", "positionnementValve",
					"Valve " + x + " inexistante");
			break;
		}
	}

	/**
	 * Positionne à la valve fermé
	 */
        @Override
	public void fermetureTouteElectrovanne() {
            send(VALVE1, FERMER);
            send(VALVE2, FERMER);
            send(VALVE3, FERMER);
            send(VALVE4, FERMER);
            send(VALVE5, FERMER);
            send(VALVE6, FERMER);
            send(VALVE7, FERMER);
            send(VALVE8, FERMER);
            send(POSITIONEMENT_SAS, FERMER);
            send(REJET1, FERMER);
            send(REJET2, FERMER);
	}

	/**
	 * Positionne à la valve 1
	 */
        @Override
	public void positionnementValve1() {
            this.send(VALVE1, OUVRIR);
	}

	/**
	 * Positionne à la valve 2
	 */
        @Override
	public void positionnementValve2() {
            this.send(VALVE2, OUVRIR);
	}

	/**
	 * Positionne à la valve 3
	 */
        @Override
	public void positionnementValve3() {
            this.send(VALVE3, OUVRIR);
	}

	/**
	 * Positionne à la valve 4
	 */
        @Override
	public void positionnementValve4() {
            this.send(VALVE4, OUVRIR);
	}

	/**
	 * Positionne à la valve 5
	 */
        @Override
	public void positionnementValve5() {
            this.send(VALVE5, OUVRIR);
	}

	/**
	 * Positionne à la valve 6
	 */
        @Override
	public void positionnementValve6() {
            this.send(VALVE6, OUVRIR);
	}

	/**
	 * Positionne à la valve 7
	 */
        @Override
	public void positionnementValve7() {
            this.send(VALVE7, OUVRIR);
	}

	/**
	 * Positionne à la valve 8
	 */
        @Override
	public void positionnementValve8() {
            this.send(VALVE8, OUVRIR);
	}

	/**
	 * Positionne au sas
	 */
        @Override
	public void positionnementSas() {
            this.send(POSITIONEMENT_SAS, OUVRIR);
	}

	/**
	 * Positionne au rejet 1
	 */
        @Override
	public void positionnementRejet1() {
            this.send(REJET1, OUVRIR);
	}

	/**
	 * Positionne au rejet 2
	 */
        @Override
	public void positionnementRejet2() {
            this.send(REJET2, OUVRIR);
	}



	/**
	 * Ouvre l'electrovanne 3 voies
	 */
        @Override
	public void ouvertureElectrovanne3Voies() {
            this.send(ELECTROVANNE3VOIES, OUVRIR);
	}

	/**
	 * Ferme l'electrovanne 3 voies
	 */
        @Override
	public void fermetureElectrovanne3Voies() {
            this.send(ELECTROVANNE3VOIES, FERMER);
	}
    
}

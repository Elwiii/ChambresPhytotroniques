/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.sun.comm.Win32Driver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

/**
 *
 * @author John
 */
public class TestRelay {
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("test");

		new Win32Driver().initialize();
		// récupération de l'énumération
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		// affichage des noms des ports
		CommPortIdentifier portId = null;
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			System.out.println(portId.getName());
		}
		System.out.println("fin list");

		try {
			portId = CommPortIdentifier.getPortIdentifier("COM1");
		} catch (NoSuchPortException ex) {
			ex.printStackTrace();
		}
		SerialPort port = null;
		try {
			port = (SerialPort) portId.open("Mon_Appli", 10000);
		} catch (PortInUseException ex) {
			ex.printStackTrace();
		}
		System.out.println("fin connection");

		try {
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			port.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException ex) {
			ex.printStackTrace();
		}
		System.out.println("fin port param");

		PrintWriter writer;
		BufferedReader reader;
		try {
			// Écrire et lire ici
			writer = new PrintWriter(port.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(
					port.getInputStream()));

			// Commande
                        for(int i=0;i<100;i++){
              System.out.println("i :"+ i);
              writer.print("DO_LEVEL 7 0");
               try{
                   //do what you want to do before sleeping
                   Thread.sleep(500);//sleep for 1000 ms
                   //do what you want to do after sleeptig
                 }
                 catch(InterruptedException ie){
                 //If this thread was intrrupted by nother thread 
                 }
               writer.print("DO_LEVEL 7 1");
               try{
                   //do what you want to do before sleeping
                   Thread.sleep(500);//sleep for 1000 ms
                   //do what you want to do after sleeptig
                 }
                 catch(InterruptedException ie){
                 //If this thread was intrrupted by nother thread 
                 }

               //lib.SM_ReadAnalogInputs(seaMAXHandle.getValue(), test, res, null, datab2, datab2);
            }
			

			// Lecture de ce qui arrive
			/*String line;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());*/

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("fin config flux");

		port.close();

	}

}



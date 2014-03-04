package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.comm.*; 

import com.sun.comm.Win32Driver;

public class TestCommunicationPortSerie {

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
			writer.print("");

			// Lecture de ce qui arrive
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("fin config flux");

		port.close();

	}

}

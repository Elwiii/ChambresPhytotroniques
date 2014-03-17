/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.outils.Sealevel470U;

import chambresPhytotroniques.outils.Configuration;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;


/**
 * Interface JNA qui permet de faire le pont entre les fonctions de L'API (une dll) et les fonctions java utilisables.
 * @author CARRARA Nicolas
 */
public interface API_Sealevel470U extends Library{
    /* path de la dll SeaMAX.dll */
    //final static String LIB = "bibliotheque\\Dll_Sealevel\\x"+Integer.parseInt(Configuration.getConfiguration().getPropertie("SYSTEM"))+"\\SeaMAX";
    final static String LIB = "seamax";

    /* instance de la bibliothèque SeaMAX */
    API_Sealevel470U INSTANCE = (API_Sealevel470U) Native.loadLibrary(LIB, API_Sealevel470U.class);
    
    /* Channel range values */
    final static byte ZERO_TO_FIVE        = 0;
    final static byte PLUS_MINUS_FIVE     = 1;
    final static byte ZERO_TO_TEN         = 2;
    final static byte PLUS_MINUS_TEN      = 3;
    final static byte ZERO_TO_FIFTEEN     = 2;
    final static byte PLUS_MINUS_FIFTEEN  = 3;
    
    /* Voltage reference value */
    final static byte ANALOG          = 0; // errreur -14 (SeaMax 570u)
    final static byte GROUND          = 1; // mesures analogique erratiques (SeaMax 570u)
    final static byte AD_REFERENCE    = 2;
    final static byte FLOATING        = 3; // mesures analogiques plus stables (SeaMax 570u)
    final static byte DA_CHANNEL_1    = 4;
    final static byte DA_CHANNEL_2    = 8;
    
    /* Channel Modes */
    final static byte SINGLE_ENDED    = 0;
    final static byte DIFFERENTIAL    = 1;
    final static byte CURRENT_LOOP    = 2;
    

    
    /* quelques fonctions de l'API transposées pour le java */
   
   /**
    * Returns the SeaMAX library's version info as major.minor.revision.build.
    * @param major [out]
    * @param minor [out]
    * @param revision [out]
    * @param build [out]
    * @return 0	Success
    */
    int SM_Version(IntByReference major,IntByReference minor,IntByReference revision,IntByReference build )	;
    
    /**
     * Open the Module
     * @param handle [out] SeaMAX handle. This integer will be filled with a valid handle for future SeaMAX operations after a successfull open
     * @param connection [in] String representing the connection to open. For Modbus TCP, the ":502" does not have to be specified.
     * @return   0	Success.
                -1	Parameter 'connection' is null.
                -2	Could not determine connection type.
                -3	Invalid connection string.
                -10	Serial: Invalid or unavailable serial connection.
                -11	Serial: Unable to acquire a valid mutex.
                -12	Serial: Unable to set serial timeouts.
                -13	Serial: Unable to set serial parameters (e.g. baudrate, parity, etc.).
                -14	Serial: Invalid serial name parameter.
                -20	Ethernet: Could not resolve host address.
                -21	Ethernet: Host refused or unavailable.
                -22	Ethernet: Could not acquire free socket.
                -23	Ethernet: Could not acquire a valid mutex.
                -30	SeaDAC Lite: Invalid or unavailable port
                -31	SeaDAC Lite: Unable to aquire a mutex handle
                -32	SeaDAC Lite: Invalid device number (should be zero or greater). Object invalid.
                -33	SeaDAC Lite: Could not read Vendor ID
                -34	SeaDAC Lite: Could not read Product ID
                -40	Could not read USB device product or vendor ID.
                -41	Non-Sealevel USB device.
                -42	SeaMAX does not support this Sealevel USB device.

     */
    int SM_Open(IntByReference handle,String connection);
    
    
    
    /**
     * Closes the SeaMAX handle and releases all allocated memory.
     * @param handle [in] Valid handle returned by SM_Open().
     * @return   0	Successful closure and cleanup.
                -1	Invalid SeaMAX handle.
     */
    int SM_Close(int handle);
    
    /**
     * Writes the state of one or more digital outputs.
     * @param handle [in] Valid handle returned by SM_Open()
     * @param start [in] Starting output (zero-indexed).
     * @param number [in] Quantity of outputs to write.
     * @param values [in] Desired output state(s).
     * @return   >=0	Number of bytes successfully written.
                -1	Invalid SeaMAX handle.
                -2	Modbus: Connection is not established. Check the provided Connection object state.
                -3	Modbus: Read error waiting for response. Unkown Modbus exception.
                -4	Modbus: Illegal Modbus Function (Modbus Exception 0x01).
                -5	Modbus: Illegal Data Address (Modbus Exception 0x02).
                -6	Modbus: Illegal Data Value (Modbus Exception 0x03).
                -7	Modbus CRC was invalid. Possible communications problem.
                -20	SeaDAC Lite: Invalid model number.
                -21	SeaDAC Lite: Invalid addressing.
                -22	SeaDAC Lite: Error writing to the device. 
     */
    int SM_WriteDigitalOutputs(int handle,int start,int number, byte[] values );
    
    
    /**
     * Gets the device's analog inputs range configuraton.
     * @param handle [in] Valid handle returned by SM_Open().
     * @param ranges [in] Array of desired channel ranges.
     * @return   0	Successful completion.
                -1	Invalid SeaMAX handle.
                -2	Error retrieving the mode configuration.
                -3	Connection is not established. Check the provided Connection object state.
                -4	Modbus: Read error waiting for response. Unkown Modbus exception.
                -5	Modbus: Illegal Modbus Function (Modbus Exception 0x01).
                -6	Modbus: Illegal Data Address (Modbus Exception 0x02).
                -7	Modbus: Illegal Data Value (Modbus Exception 0x03).
                -8	Modbus: CRC was invalid. Possible communications problem.
                -9	Invalid device configuration.
     */
    int SM_SetAnalogInputRanges(int handle, byte[] ranges);
    
    /**
     * Reads one or more Sealevel I/O device analog input(s).
     * @param handle [in] Valid handle returned by SM_Open()
     * @param start [in] Starting input.
     * @param number [in] Quantity of analog inputs to read. 
     * @param analogValues [out] Input values as floating point values (voltages).
     * @param ranges [in] Array of channel ranges that correspond directly to each requested input.
     * @param byteValues [out] Register state values as 16-bit byte pairs.
     * @return >=0	Number of bytes successfully returned in result array.
                -1	Invalid SeaMAX handle.
                -2	Both 'values' or 'doubleValues' are null. Supply either one or both.
                -3	If an array of doubles is supplied, an similar array of analog input ranges must also be supplied.
                -4	One or more of the analog input ranges in the 'ranges' array is invalid.
                -5	Connection is not established. Check the provided Connection object state.
                -6	Read error waiting for response. Unkown Modbus exception.
                -7	Illegal Modbus Function (Modbus Exception 0x01).
                -8	Illegal Data Address (Modbus Exception 0x02).
                -9	Illegal Data Value (Modbus Exception 0x03).
                -10	Modbus CRC was invalid. Possible communications problem.
     */
    int SM_ReadAnalogInputs(int handle,int start,int number,double[] analogValues, byte[] ranges, byte[] byteValues );
    
    
    /**
     * Sets the device's analog configuraton.
     * @param handle [in] Valid handle returned by SM_Open().
     * @param reference [in] Analog to digital reference point.	
     * @param mode [in] Device input mode.
     * @return   0	Successful completion.
                -1	Invalid SeaMAX handle.
                -2	Error retrieving the mode configuration.
                -3	Connection is not established. Check the provided Connection object state.
                -4	Modbus: Read error waiting for response. Unkown Modbus exception.
                -5	Modbus: Illegal Modbus Function (Modbus Exception 0x01).
                -6	Modbus: Illegal Data Address (Modbus Exception 0x02).
                -7	Modbus: Illegal Data Value (Modbus Exception 0x03).
                -8	Modbus: CRC was invalid. Possible communications problem.
                -9	Invalid device configuration.
     */
    int SM_SetAnalogInputConfig(int handle, byte reference, byte mode);
    
    
    /**
     * Gets the device's analog inputs range configuraton.
     * @param seaMAXHandle [in] Valid handle returned by SM_Open().
     * @param inputRanges [in] Array of desired channel ranges.
     * @return   0	Successful completion.
                -1	Invalid SeaMAX handle.
                -2	Invalid parameter. Parameters may not be null.
                -3	Connection is not established. Check the provided Connection object state.
                -4	Modbus: Read error waiting for response. Unkown Modbus exception.
                -5	Modbus: Illegal Modbus Function (Modbus Exception 0x01).
                -6	Modbus: Illegal Data Address (Modbus Exception 0x02).
                -7	Modbus: Illegal Data Value (Modbus Exception 0x03).
                -8	Modbus: CRC was invalid. Possible communications problem.
     */
    int SM_GetAnalogInputRanges(int seaMAXHandle, byte[] inputRanges);
    
    
    /** 
     * Targets a new Modbus device. 
     * @param seaMAXHandle [in] Valid handle returned by SM_Open().
     * @param slaveID [in] New Modbus slave ID to be used in future SeaMAX operations. Valid values are 0 - 247.
     * @return   0	Success.
                -1	Invalid SeaMAX handle.
                -2	Invalid slave ID.
     */
    int SM_SelectDevice(int seaMAXHandle,int slaveID);
    
    
    /**
      * Converts a two-byte input register value to a double type voltage.
      * @param seaMAXHandle [in] Valid handle returned by SM_Open().
      * @param value After completion, contains a double floating-point voltage representation of 'data'.
      * @param data [in] Two bytes of data - usually returned by SM_ReadInputRegisters().
      * @param channelRange [in] A/D channel range of the Sealevel I/O module.
      * @return   */
    int SM_AtoDConversion(int seaMAXHandle,DoubleByReference value, byte[] data,int channelRange);
	
	   

}

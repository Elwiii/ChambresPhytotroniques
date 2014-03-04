/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author CARRARA Nicolas
 */
public class TestJNI {
    
    public native void SM_Open();

    static {
        System.loadLibrary("SeaMAX");
    }
    public static void main(String[] args) {
        new TestJNI().SM_Open();
    }
}

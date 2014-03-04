/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import test.Kernel32.SYSTEMTIME;

/**
 *
 * @author John
 */
public class Kernel32Impl {  
    public static void main(String[] argv) {  
        Kernel32 lib = Kernel32.INSTANCE;
        SYSTEMTIME time = new SYSTEMTIME();  
        lib.GetSystemTime(time);  
        System.out.println("Today's integer value is " + time.wDay);  
    }  
}  

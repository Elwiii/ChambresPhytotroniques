package test;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;
import java.util.ArrayList;
import java.util.List;

public interface Kernel32 extends StdCallLibrary {  
    Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class);  

    public static class SYSTEMTIME extends Structure {  
            public short wYear;  
            public short wMonth;  
            public short wDayOfWeek;  
            public short wDay;  
            public short wHour;    
            public short wMinute;  
            public short wSecond;  
            public short wMilliseconds;  
            @Override
            protected List getFieldOrder() {
                ArrayList<String> al = new ArrayList<String>();
                al.add("wYear");
                al.add("wMonth");
                al.add("wDayOfWeek");
                al.add("wDay");
                al.add("wHour");
                al.add("wMinute");
                al.add("wSecond");
                al.add("wMilliseconds");
                
                return al;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }  

    void GetSystemTime(SYSTEMTIME result);  
}  
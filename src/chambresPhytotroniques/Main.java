package chambresPhytotroniques;

import chambresPhytotroniques.vue.Fenetre;
import java.util.StringTokenizer;

public class Main {
        static {
        System.setProperty("jna.nosys", "true");
        }

	public static void main(String[] args) {
            
                String property = System.getProperty("java.library.path");           
                String fs = ":";
                StringTokenizer st = new StringTokenizer(property, fs);
         

                while (st.hasMoreTokens()) {
                System.err.println(st.nextToken());
                }           

		new Fenetre().start();
	}

}

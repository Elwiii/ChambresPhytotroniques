/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chambresPhytotroniques.vue.menu;

import chambresPhytotroniques.vue.Fenetre;
import javax.swing.JMenu;

/**
 *
 * @author Jacques
 */
public class MenuMails extends JMenu {
    
    private MenuParametresMails mpm;
    
    public MenuMails(Fenetre fenetre) {
        
        super("Mails");
        
        this.mpm = new MenuParametresMails(fenetre);
        
        this.add(mpm);
        
    }
    
    public MenuParametresMails getMpm() {
        return mpm;
    }
    
}

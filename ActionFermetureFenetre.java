import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Utilisation du patron Commande tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class ActionFermetureFenetre extends AbstractAction {
    private boolean sauvConfirm = true;
    private Fenetre f;
    public ActionFermetureFenetre(Fenetre f, boolean sauvConfirm) {this.f=f;this.sauvConfirm=sauvConfirm;}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(sauvConfirm==true) {
            ActionSauvegarder as = new ActionSauvegarder(f);
            as.actionPerformed(null);
        }
        System.exit(0);
    }
    public void setSauvConfirm(boolean sauvConfirm) {this.sauvConfirm = sauvConfirm;}
}

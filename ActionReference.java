import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Utilisation du patron Commande tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class ActionReference extends AbstractAction {
    private Fenetre f;
    ActionReference(Fenetre f) {this.f=f;}
    @Override
    public void actionPerformed(ActionEvent e) {
        InfosCreateur dial = new InfosCreateur(f, "À propos", false);
    }
}

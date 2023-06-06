import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Utilisation du patron Commande tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class ActionAfficherPageAide extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        PageAide.getInstancePageAide().afficherPage();
    }
}

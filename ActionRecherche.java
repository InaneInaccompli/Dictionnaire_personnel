import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Utilisation du patron Commande tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class ActionRecherche  extends AbstractAction {
    private RecherchePanel rP;
    ActionRecherche (RecherchePanel rP) {
        super();
        this.rP=rP;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(rP.getChoixRecherche().getSelectedIndex()) {
            case 0 :
                Fenetre.rT = new RechercheCommencePar();
                break;
            case 1 :
                Fenetre.rT = new RechercheContient();
                break;
            case 2 :
                Fenetre.rT = new RechercheNeContientPas();
                break;
            case 3 :
                Fenetre.rT = new RechercheTerminePar();
                break;
            /*case default :
                Fenetre.rT = new RechercheCommencePar();
                break;*/
        }
        if(Fenetre.rT!=null)
            rP.getF().updateToMain(Fenetre.rT.recherche(rP.getBande_recherche().getText(), rP.getF(), rP.getjCB().isSelected(), ((Integer)rP.getsNMM().getValue()), rP.getChoixMethode().getSelectedIndex()));
    }
}

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Utilisation du patron Commande tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class ActionChargerFichier extends AbstractAction {
    private Fenetre f;
    public ActionChargerFichier(Fenetre f) {this.f=f;}
    @Override
    public void actionPerformed(ActionEvent e) {
        // Ajouter option sauvegarder travail courant si pas sauvegardé
        JFileChooser jfc = new JFileChooser();
        //Référence : https://stackoverflow.com/questions/13516829/jfilechooser-change-default-directory-in-windows
        jfc.setCurrentDirectory(new File("./Dicos/"));
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Dictionnaires", "evd"));
        int result = jfc.showOpenDialog(null);
        if(result==JFileChooser.APPROVE_OPTION) {
            f.setFichSauv(jfc.getSelectedFile());
            f.getDico().setFile(f.getFichSauv());
            f.updateToMain(null);
            f.setTitle(jfc.getSelectedFile().getName());
            f.setVerif_Sauv(true);
        } else {
            System.out.println("Ça marche pas xD");
        }
    }
}

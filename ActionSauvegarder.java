import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Utilisation du patron Commande tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class ActionSauvegarder extends AbstractAction {
    Fenetre f;
    public ActionSauvegarder(Fenetre f) {this.f=f;}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(f.getFichSauv()!=null) {
            f.getDico().enregistrerDansFichier();
        } else {
            JFileChooser jfc = new JFileChooser();
            //Référence : https://stackoverflow.com/questions/13516829/jfilechooser-change-default-directory-in-windows
            jfc.setCurrentDirectory(new File("./Dicos/"));
            jfc.setSelectedFile(new File("./Dicos/sansnom.evd"));
            jfc.addChoosableFileFilter(new FileNameExtensionFilter("Dictionnaires", "evd"));
            int result = jfc.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                f.setFichSauv(jfc.getSelectedFile());
                f.getDico().setFile(f.getFichSauv());
                f.getDico().enregistrerDansFichier();
                f.updateToMain(null);
            } else {
                System.out.println("Ça marche pas xD");
            }
        }
        System.out.println("Sauvegarde faite dans " + f.getFichSauv().getAbsolutePath());
        f.setVerif_Sauv(true);
    }
}

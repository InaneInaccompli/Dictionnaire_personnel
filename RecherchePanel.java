import javax.swing.*;
import java.awt.*;

public class RecherchePanel extends JPanel {
    private JPanel choixLettres = new JPanel(), nombreLettres = new JPanel(), combinaison = new JPanel();
    private JTextField bande_recherche = new JTextField();
    private Choice choixRecherche = new Choice(), choixMethode = new Choice();
    private JCheckBox jCB = new JCheckBox("Utiliser");
    //Référence : https://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html
    private JSpinner sNMM = new JSpinner(new SpinnerNumberModel(2, 2, 50, 1));
    private JButton bouton = new JButton("Rechercher");
    private Fenetre f;
    RecherchePanel(Fenetre _f) {
        f=_f;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //Référence : https://www.javatpoint.com/java-awt-choice
        choixLettres.setLayout(new BoxLayout(choixLettres, BoxLayout.X_AXIS));
        nombreLettres.setLayout(new BoxLayout(nombreLettres, BoxLayout.X_AXIS));
        combinaison.setLayout(new BoxLayout(combinaison, BoxLayout.Y_AXIS));
        choixRecherche.add("Commence par ...");
        choixRecherche.add("Contient ...");
        choixRecherche.add("Ne contient pas ...");
        choixRecherche.add("Termine par ...");
        choixLettres.add(bande_recherche);
        choixLettres.add(choixRecherche);
        choixMethode.add("≤");
        choixMethode.add("=");
        choixMethode.add("≥");
        nombreLettres.add(jCB);
        nombreLettres.add(sNMM);
        nombreLettres.add(choixMethode);
        combinaison.add(choixLettres);
        combinaison.add(nombreLettres);
        add(combinaison);
        add(bouton);
        bouton.addActionListener(new ActionRecherche(this));
    }
    public Choice getChoixRecherche() {return choixRecherche;}
    public JButton getBouton() {return bouton;}
    public JTextField getBande_recherche() {return bande_recherche;}
    public JSpinner getsNMM() {return sNMM;}
    public Choice getChoixMethode() {return choixMethode;}
    public JCheckBox getjCB() {
        return jCB;
    }
    public Fenetre getF() {return f;}
}

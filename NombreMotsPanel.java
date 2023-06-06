import javax.swing.*;

public class NombreMotsPanel extends JPanel {
    private int taille_dico = 0, taille_recherche = 0;
    private JLabel labelTDico = new JLabel();
    private JLabel labelTRecherche = new JLabel();
    NombreMotsPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(labelTRecherche);
        this.add(labelTDico);
    }
    public void updateDico(int taille) {
        this.taille_dico = taille;
        this.taille_recherche = this.taille_dico;
        labelTRecherche.setText("");
        labelTDico.setText("Taille du dictionnaire : " + this.taille_dico + " mot(s)");
    }
    public void updateRecherche(int taille) {
        this.taille_recherche = taille;
        labelTRecherche.setText("Nombre de mot(s) correspondant : " + this.taille_recherche);
        labelTDico.setText("Taille du dictionnaire : " + this.taille_dico + " mot(s)");
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * Mot
 */
public class Mot extends JPanel implements ActionListener {
    /*
    * Je dois mettre Dictio en paramètre
    * afin de pouvoir ajouter des boutons permettant la suppression/modification du mot
    */
    private Fenetre f;
    private String m;
    private String definition;
    private static int jLabelMaxSize = 0, defMaxSWidth = 0;
    public Mot(String mot, String def, Fenetre _f) {
        super();
        f=_f;
        m=mot;
        definition=def;
        uiMot();
    }
    public void uiMot() {
        add(new JLabel(m));
        String def_split_tab[] = definition.split("~");
        String def_split = "";
        for(int i=0;i<def_split_tab.length;i++) {
            if(i!= def_split_tab.length-1)
                def_split += def_split_tab[i] + "\n";
            else
                def_split += def_split_tab[i];
        }
        JTextArea jta = new JTextArea(def_split);
        jta.setEditable(false);
        add(jta);
        JButton jb2 = new JButton("Modifier");
        jb2.setActionCommand("modif");
        jb2.addActionListener(this);
        add(jb2);
        JButton jb = new JButton("Supprimer");
        jb.setActionCommand("suppr");
        jb.addActionListener(this);
        add(jb);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("suppr")) {
            //Référence : https://stackoverflow.com/questions/15449022/show-prompt-before-closing-jframe
            String ObjButtons[] = {"Oui","Non"};
            int PromptResult = JOptionPane.showOptionDialog(null,
                    "Voulez supprimez le mot " + m + " ?", "Suppression du mot "+m,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                    ObjButtons,ObjButtons[1]);
            if(PromptResult==0){
                f.getDico().supprMot(m);
                f.updateToMain(null);
                f.setVerif_Sauv(false);
            }
        }
        if(e.getActionCommand().equals("modif")) {
            f.modifMot(this.m, this.definition);
        }
    }
}
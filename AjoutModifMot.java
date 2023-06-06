import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AjoutModifMot extends JFrame implements ActionListener {
    private Fenetre f;
    private JPanel contenantPrincipal = new JPanel(), champMot = new JPanel(), champDef = new JPanel();
    private JTextField jtf = new JTextField(), jtf2 = new JTextField();
    private JScrollPane jsp = new JScrollPane(jtf), jsp2 = new JScrollPane(jtf2);
    private JButton jb = new JButton(), jb2 = new JButton();
    private JLabel nom_mot_modif;
    private Vector<JTextField> listeDefs = new Vector<>();
    private boolean state=true;
    private String mot, def;
    AjoutModifMot(Fenetre _f) {
        super("Ajout d'un mot dans le dictionnaire");
        f=_f;
        listeDefs.add(new JTextField());
        listeDefs.get(0).setPreferredSize(new Dimension(300, 40));
        jb.addActionListener(this);
        jb2.addActionListener(this);
        dessine_fenetre(null);
    }
    AjoutModifMot(Fenetre _f, String _mot, String _def) {
        super("Modification du mot " + _mot);
        f=_f;
        mot=_mot;
        def=_def;
        state=false;
        String def_split_tab[] = def.split("~");
        for(String s : def_split_tab) {
            listeDefs.add(new JTextField(s));
            listeDefs.get(listeDefs.size()-1).setPreferredSize(new Dimension(300, 40));
        }
        jb.addActionListener(this);
        jb2.addActionListener(this);
        dessine_fenetre(null);
    }
    public void dessine_fenetre(String s) {
        contenantPrincipal = new JPanel();
        champDef = new JPanel();
        champMot = new JPanel();
        setContentPane(contenantPrincipal);
        contenantPrincipal.setLayout(new BoxLayout(contenantPrincipal, BoxLayout.PAGE_AXIS));
        champMot.add(new JLabel("Mot"));
        jsp.setPreferredSize(new Dimension(300, 40));
        champDef.add(new JLabel("Définition(s)"));
        for(JTextField jtf : listeDefs) {
            champDef.add(jtf);
        }
        jb2.setText("Ajouter définition");
        if(state==true) {
            champMot.add(jsp);
            jtf.setText(s);
            jtf2.setText(null);
            contenantPrincipal.add(champMot);
            contenantPrincipal.add(champDef);
            contenantPrincipal.add(jb);
            contenantPrincipal.add(jb2);
            jb.setText("Ajouter");
            jb.setActionCommand("Ajout mot");
            //Ajouter defs mots dans fenêtre modif
        } else {
            nom_mot_modif = new JLabel();
            nom_mot_modif.setText(mot);
            champMot.add(nom_mot_modif);
            contenantPrincipal.add(champMot);
            contenantPrincipal.add(champDef);
            jb.setText("Modifier");
            jb.setActionCommand("modif");
            contenantPrincipal.add(jb);
            contenantPrincipal.add(jb2);
        }
        pack();
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Ajout mot")) {
            boolean verif = listeDefs.get(0).getText().isEmpty() ? false : true;
            if(jtf.getText().isEmpty()==false&&verif==true){
                String def = "";
                for(JTextField jtf : listeDefs) {
                    if(jtf.getText().isEmpty()==false) {
                        if (def.equals("") == false && def.isEmpty() == false)
                            def += "~";
                        def += jtf.getText();
                    }
                }
                f.getDico().ajoutMot(jtf.getText(), def);
                f.updateToMain(null);
                f.setVerif_Sauv(false);
                System.out.println(jtf.getText() + " " + def);
                // Référence : https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
                setVisible(false);
                dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Il vous remplir le champ de mot et définition pour ajouter un mot dans le dictionnaire.","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getActionCommand().equals("modif")) {
            boolean verif = listeDefs.get(0).getText().isEmpty() ? false : true;
            if(verif==true){
                String def = "";
                for(JTextField jtf : listeDefs) {
                    if(jtf.getText().isEmpty()==false) {
                        if (def.equals("") == false && def.isEmpty() == false)
                            def += "~";
                        def += jtf.getText();
                    }
                }
                f.getDico().modif_mot(nom_mot_modif.getText(), def);
                f.updateToMain(null);
                f.setVerif_Sauv(false);
                System.out.println(nom_mot_modif.getText() + " " + def);
                setVisible(false);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null,"Il vous remplir le champ de définition pour modifier un mot du dictionnaire.","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource()==jb2) {
            System.out.println("Test");
            listeDefs.add(new JTextField());
            listeDefs.get(listeDefs.size()-1).setPreferredSize(new Dimension(300, 40));
            dessine_fenetre(jtf.getText());
        }
    }
}

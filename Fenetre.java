import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Fenetre extends JFrame implements ActionListener {
    private final int WIDTH = 750, HEIGHT = 450;
    private AjoutModifMot amm;
    private JPanel contenantPrincipal, j, affMots;
    private RecherchePanel recherchePanel = new RecherchePanel(this);
    private NombreMotsPanel nMP = new NombreMotsPanel();
    private JScrollPane jsp;
    private Dictio d;
    private JButton jb = new JButton("Ajouter un mot");
    private JDialog fermeture;
    private File fichSauv;
    private boolean verif_sauv=true;
    public static RechercheType rT;
    public Fenetre(Dictio _d) {
        super("");
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        d=_d;
        //Liste menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
            //Menu fichier
                JMenu fichier = new JMenu("Fichier");
                menuBar.add(fichier);
                JMenuItem ouvrir = new JMenuItem("Ouvrir/Charger");
                fichier.add(ouvrir);
                ouvrir.addActionListener(new ActionChargerFichier(this));
                JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
                fichier.add(sauvegarder);
                sauvegarder.addActionListener(new ActionSauvegarder(this));
            //Menu ?
                JMenu divers = new JMenu("?");
                menuBar.add(divers);
                JMenuItem ref = new JMenuItem("À propos");
                divers.add(ref);
                //!!!IMPORTAAAAANT!!! addActionListener, pas setAction CURIEUSEMENT
                ref.addActionListener(new ActionReference(this));
                JMenuItem aide = new JMenuItem("Aide");
                aide.addActionListener(new ActionAfficherPageAide());
                divers.add(aide);
        //Composants pour la partie principale
        jb.setActionCommand("Panel ajout");
        jb.addActionListener(this);
        updateToMain(null);
        //Fenêtre visible
        setVisible(true);
        //Méthode en fermeture de fenêtre
        Fenetre copie = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(verif_sauv==false){
                    fermeture = new JDialog(copie, "Fermeture du dictionnaire", true);
                    JPanel jpFerm = new JPanel();
                    jpFerm.setLayout(new BoxLayout(jpFerm, BoxLayout.Y_AXIS));
                    jpFerm.add(new JLabel("Êtes-vous sûr(e) de vouloir fermer le programme ? Si oui, voulez-vous sauvegarder les données ?"));
                    JPanel boutFerm = new JPanel();
                    JButton nonFerm = new JButton("Revenir sur le dictionnaire");
                    nonFerm.setActionCommand("nonFerm");
                    nonFerm.addActionListener(copie);
                    JButton noSaveFerm = new JButton("Ne pas sauvegarder");
                    noSaveFerm.addActionListener(new ActionFermetureFenetre(copie,false));
                    JButton ouiFerm = new JButton("Sauvegarder et quitter");
                    ouiFerm.addActionListener(new ActionFermetureFenetre(copie,true));
                    boutFerm.add(nonFerm);
                    boutFerm.add(noSaveFerm);
                    boutFerm.add(ouiFerm);
                    jpFerm.add(boutFerm);
                    fermeture.getContentPane().add(jpFerm);
                    fermeture.pack();
                    fermeture.setResizable(false);
                    fermeture.setVisible(true);
                } else {
                    System.exit(0);
                }
            }
        });
    }
    public void updateToMain(TreeMap<String, String> appel) {
        contenantPrincipal = new JPanel();
        j = new JPanel();
        affMots = new JPanel();
        affMots.setLayout(new BoxLayout(affMots, BoxLayout.Y_AXIS));
        j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
        j.setAlignmentX(Component.LEFT_ALIGNMENT);
        jsp = new JScrollPane(j);
        jsp.setPreferredSize(new Dimension(WIDTH-75, HEIGHT-50));
        jb.setPreferredSize(new Dimension(WIDTH-625, HEIGHT-50));
        if(getTitle().isEmpty())
            setTitle("Nouveau dictionnaire");
        setContentPane(contenantPrincipal);
        //Création des blocs de mot
        if(appel!=null){
            Set<String> cles = appel.keySet();
            for (String s : cles) {
                Mot m = new Mot(s, appel.get(s), this);
                j.add(m, BorderLayout.WEST);
            }
            if (appel.isEmpty() || appel.size() == 0)
                j.add(new JLabel("Aucun mot ne correspond à votre saisie, veuillez prendre en compte que la recherche respecte la casse."));
            nMP.updateRecherche(appel.size());
        } else {
            Set<String> cles = d.listeMot().keySet();
            for(String s : cles) {
                Mot m = new Mot(s, d.listeMot().get(s), this);
                //m.setAlignmentX(Component.RIGHT_ALIGNMENT);
                j.add(m, BorderLayout.WEST);
            }
            nMP.updateDico(getDico().getSize());
        }
        j.add(new JLabel("Pour afficher l'ensemble des mots, effacez le contenu de la barre et appuyer sur le bouton de recherche."), BorderLayout.WEST);
        affMots.add(recherchePanel);
        affMots.add(jsp);
        affMots.add(nMP);
        contenantPrincipal.setLayout(new FlowLayout());
        contenantPrincipal.add(affMots);
        contenantPrincipal.add(jb);
        affMots.setBackground(ThemeCouleur.getInstance().getColor_bg());
        contenantPrincipal.setBackground(ThemeCouleur.getInstance().getColor_bg());
        getContentPane().setBackground(ThemeCouleur.getInstance().getColor_bg());
        pack();
    }
    public void modifMot(String mot, String def) {amm = new AjoutModifMot(this, mot, def);}
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("Panel ajout"))
            amm = new AjoutModifMot(this);
        if(e.getActionCommand().equals("nonFerm"))
            fermeture.dispose();
    }
    public Dictio getDico() {return d;}
    public void setVerif_Sauv(boolean t) {
        verif_sauv=t;
        if(verif_sauv==false) {
            if(this.getTitle().charAt(this.getTitle().length()-1)!='*')
                this.setTitle(this.getTitle()+"*");
        } else {
            if(this.getTitle().charAt(this.getTitle().length()-1)=='*')
                this.setTitle(this.getTitle().substring(0, this.getTitle().length()-1));
        }
    }
    public File getFichSauv() {File f = fichSauv; return f;}
    public void setFichSauv(File fichSauv) {this.fichSauv = fichSauv;}
}
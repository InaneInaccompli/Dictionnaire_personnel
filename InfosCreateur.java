import javax.swing.*;

public class InfosCreateur extends JDialog {
    InfosCreateur(Fenetre f, String s, boolean b) {
        super(f, s, b);
        JPanel contenantDial = new JPanel();
        JTextArea jtaDial = new JTextArea("Ce programme a été conçu et développé par Evan VITALIS durant l'année 2023.\nJe me suis principalement basé sur les cours dispensés par M. Thon lors de ma deuxième année (2021-2022)\nSi vous avez des questions/requêtes/complaintes, veuillez me contacter à cette adresse : ecvitalis@gmail.com");
        jtaDial.setEditable(false);
        contenantDial.add(jtaDial);
        getContentPane().add(contenantDial);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
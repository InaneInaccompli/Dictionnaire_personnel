import java.util.*;
import java.io.*;
import javafx;

public class Main {
    private static Scanner s = new Scanner(System.in);
    private static File fichier;
    private static final String nomFich = "DictionnairEvan.txt";
    public static void main(String args[]) {
        Dictio d = new Dictio();
        Fenetre f = new Fenetre(d);
    }
}
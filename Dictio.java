import java.util.*;
import java.io.*;
public class Dictio {
    private TreeMap<String, String> dictio;
    private File f;
    private BufferedWriter bW;
    private static Scanner s = new Scanner(System.in);
    Dictio() {dictio = new TreeMap<String, String>();}
    //Référence pour la première lettre en majuscule : https://flexiple.com/javascript/javascript-capitalize-first-letter/
    public void ajoutMot(String mot, String def) {
        String preMaj = Character.toUpperCase(mot.charAt(0)) + mot.substring(1);
        if(dictio.get(mot)!=null) {
            if(dictio.get(mot).equals(def)==false&&def!=null&&def!=""&&def.equals(".")==false){
                String def_orig = dictio.get(mot);
                dictio.remove(mot);
                String nouv_def = def_orig + "~" + def;
                dictio.put(preMaj, nouv_def);
                return;
            }
        } else if(dictio.get(preMaj)!=null) {
            if(dictio.get(preMaj).equals(def)==false&&def!=null&&def!=""&&def.equals(".")==false){
                String def_orig = dictio.get(preMaj);
                dictio.remove(preMaj);
                String nouv_def = def_orig + "~" + def;
                dictio.put(preMaj, nouv_def);
                return;
            }
        }
        dictio.put(preMaj, def);
    }
    public void modif_mot(String mot, String def) {
        dictio.replace(mot, def);
        System.out.println("Test | " + dictio.get("mot"));
    }
    public Hashtable<String, String> getMot(String m) {
        Hashtable<String, String> ret = new Hashtable<>();
        ret.put(m, dictio.get(m));
        return ret;
    }
    public void supprMot(String mot) {
        dictio.remove(mot);
    }
    public TreeMap<String, String> listeMot() {TreeMap<String, String> tm = dictio; return tm;}
    public void setFile(File _f) {
        dictio = new TreeMap<String, String>();
        f=_f;
        System.out.println("Adresse de lecture de fichier : " + f);
        try {
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                if(!line.equals("")) {
                    String[] recup = line.split("&");
                    if(recup.length==2){
                        ajoutMot(recup[0], recup[1]);
                    }
                }
            }
            scan.close();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
    public void enregistrerDansFichier() {
        if(f!=null){
            try {
                bW = new BufferedWriter(new FileWriter(f));
                Set<String> cles = dictio.keySet();
                for (String s : cles) {
                    System.out.println(s);
                    bW.write(s, 0, s.length());
                    bW.write("&", 0, 1);
                    bW.write(dictio.get(s));
                    bW.newLine();
                }
                bW.close();
            } catch (IOException io) {
                System.out.println("Erreur" + io.getMessage());
            }
            System.out.println("Écriture faite dans " + f);
        } else {
            System.out.println("Pas de fichier");
        }
    }
    public int getSize() {return dictio.size();}
    public Set<String> keySet() {return dictio.keySet();}
}
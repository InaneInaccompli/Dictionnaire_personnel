import java.util.Set;
import java.util.TreeMap;

/**
 * Utilisation du patron MéthodeTemplate tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class RechercheContient extends RechercheType {
    @Override
    public TreeMap<String, String> rechercheLettres(String lettres, Fenetre f) {
        TreeMap<String, String> ret = new TreeMap<String, String>();
        String recherche = lettres.toLowerCase();
        if(lettres.isEmpty()==false){
            Set<String> cles = f.getDico().listeMot().keySet();
            for (String s : cles) {
                String minuscule = s.toLowerCase();
                //Référence : https://www.w3schools.com/java/ref_string_startswith.asp
                if (minuscule.contains(recherche) == true) {
                    ret.put(s, f.getDico().listeMot().get(s));
                }
            }
            return ret;
        }
        return null;
    }
}

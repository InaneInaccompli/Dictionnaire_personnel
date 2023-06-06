import java.util.Set;
import java.util.TreeMap;

/**
 * Utilisation du patron MéthodeTemplate tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class RechercheCommencePar extends RechercheType {
    @Override
    public TreeMap<String, String> rechercheLettres(String lettres, Fenetre f) {
        TreeMap<String, String> ret = new TreeMap<String, String>();
        if(lettres.isEmpty()==false){
            Set<String> cles = f.getDico().listeMot().keySet();
            for (String s : cles) {
                String preMaj = Character.toUpperCase(lettres.charAt(0)) + lettres.substring(1).toLowerCase();
                //Référence : https://www.w3schools.com/java/ref_string_startswith.asp
                if (s.startsWith(preMaj) == true) {
                    ret.put(s, f.getDico().listeMot().get(s));
                }
            }
            return ret;
        }
        return null;
    }
}

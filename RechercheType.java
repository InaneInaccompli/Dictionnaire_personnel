import java.util.Set;
import java.util.TreeMap;

/**
 * Utilisation du patron MéthodeTemplate tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public abstract class RechercheType {
    protected TreeMap<String, String> appel = new TreeMap<>();
    public TreeMap<String, String> recherche(String lettres, Fenetre f, boolean b, Integer i, int nbMethode) {
        TreeMap<String, String> sauv = rechercheLettres(lettres, f);
        if(b==true&&(lettres.isEmpty()==false||lettres.length()>0)){
            if(sauv!=null){
                Set<String> cles = sauv.keySet();
                for (String s : cles) {
                    NombreLettresMethode.getInstance().compare(appel, s, i, nbMethode, sauv.get(s));
                }
            } else {
                Set<String> cles = f.getDico().keySet();
                for (String s : cles) {
                    NombreLettresMethode.getInstance().compare(appel, s, i, nbMethode, sauv.get(s));
                }
            }
        } else {
            appel = sauv;
        }
        return appel;
    }
    public abstract TreeMap<String, String> rechercheLettres(String lettres, Fenetre f);
}

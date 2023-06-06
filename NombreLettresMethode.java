import java.util.TreeMap;

/**
 * Utilisation du patron Singleton tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class NombreLettresMethode {
    private static NombreLettresMethode instance = new NombreLettresMethode();
    private NombreLettresMethode() {}
    public static NombreLettresMethode getInstance() {
        return instance;
    }
    public void compare(TreeMap<String, String> map, String mot, int valeur, int methode, String def) {
        switch(methode){
            case 0 :
                if (mot.length() <= valeur) {
                    map.put(mot, def);
                }
                break;
            case 1 :
                if (mot.length() == valeur) {
                    map.put(mot, def);
                }
                break;
            case 2 :
                if (mot.length() >= valeur) {
                    map.put(mot, def);
                }
                break;
        }
    }
}
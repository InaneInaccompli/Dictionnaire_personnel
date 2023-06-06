import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Utilisation du patron Singleton tel que vu dans le cours LOG121 dispensé par le professeur Francis Cardinal à l'ÉTS
 */
public class PageAide {
    private static File page = new File("./pageAide/pageAide.html");
    private static PageAide pA = new PageAide();
    private PageAide() {
        System.out.println(page);
    }
    public static PageAide getInstancePageAide() {return pA;}
    public void afficherPage() {
        try {
            Desktop.getDesktop().browse(page.toURI());
        } catch (IOException e) {
            System.out.println("La page ne s'affiche pas.");
        }
    }
}

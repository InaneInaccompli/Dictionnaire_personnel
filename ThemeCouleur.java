import java.awt.*;

public class ThemeCouleur {
    private Color color_bg = new Color(215,215,215);
    private Color color_font = new Color(0,0,0);
    private static ThemeCouleur instance = new ThemeCouleur();
    private ThemeCouleur() {}
    public static ThemeCouleur getInstance() {return instance;}
    public Color getColor_bg() {return color_bg;}
    public Color getColor_font() {return color_font;}
    public void setTheme(int i) {
        switch(i) {
            case 0 :
                color_bg = new Color(215,215,215);
                color_font = new Color(0,0,0);
                break;
            case 1 :
                color_bg = new Color(50,50,50);
                color_font = new Color(255,255,255);
                break;
        }
    }
}

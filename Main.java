import java.awt.Color;
import src.main.java.aura.components.AuraMultiText;
import src.main.java.aura.components.AuraWindow;

public class Main {



    public static void main(String[] args) {
        
        new AuraWindow("Aura UI")
            .fullScreen()
            .noResizable()
            .background(new Color(5, 15, 23))
            .display()
            .content(window -> {

                AuraMultiText mt = new AuraMultiText("holaa").fillWidth();

                window.insert(mt);

            });
        
    }
}
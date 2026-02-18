import java.awt.Color;
import java.awt.Font;
import src.main.java.aura.components.AuraSelect;
import src.main.java.aura.components.AuraWindow;

public class Main {

    public static void main(String[] args) {
        
        new AuraWindow("Aura UI")
            .size(700, 500)
            .noResizable()
            .background(new Color(5, 15, 23))
            .display()
            .content(window -> {

                window.insert(
                    new AuraSelect("Button", "Text", "MultiText", "Input", "Select", "Image", "Spacer").font(new Font("Arial", Font.PLAIN, 20))
                );

            });
        
    }
}
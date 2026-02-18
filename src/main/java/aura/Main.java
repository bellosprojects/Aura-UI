package aura;

import java.awt.Color;

import aura.components.AuraSelect;
import aura.components.AuraText;
import aura.components.AuraWindow;
import aura.layouts.AuraRow;

public class Main {

    public static void main(String[] args) {
        
        new AuraWindow("Aura UI")
            .size(700, 500)
            .noResizable()
            .background(new Color(235, 235, 235))
            .display()
            .content(window -> {

                window.insert(

                    new AuraRow().fillWidth().margin(20).background(new Color(125,125,125)).radius(30).padding(12).gap(12).content(row -> {

                        row.insert(
                            new AuraSelect("Home", "Studio", "Projects", "Help")
                                .background(new Color(221,221,221))
                                .radius(16)
                                .padding(10, 20)
                                .shadow(new Color(121,121,121,250), 8)
                                .weight(0.25f)
                        );

                        row.insert(
                            new AuraText("My Projects")
                                .textColor(new Color(251,251,251))
                                .weight(0.25f)
                                .info(new AuraText("Proyectos personales").background(Color.white).radius(8).padding(2, 4), 0.5f, 0)
                        );

                        row.insert(
                            new AuraSelect("Multi-Leves", "Team", "Sales", "Another Link")
                                .background(new Color(221,221,221))
                                .radius(16)
                                .padding(10, 20)
                                .shadow(new Color(121,121,121,250), 8)
                                .weight(0.25f)
                        );

                        row.insert(
                            new AuraText("About")
                                .textColor(new Color(251,251,251))
                                .weight(0.25f)
                        );

                        row.insert(
                            new AuraText("Contact us")
                                .textColor(new Color(251,251,251))
                                .weight(0.25f)
                        );


                    })

                );

            });
        
    }
}
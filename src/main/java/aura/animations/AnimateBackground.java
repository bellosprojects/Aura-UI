package src.main.java.aura.animations;

import java.awt.Color;
import src.main.java.aura.core.Box;
import src.main.java.aura.core.Transition;
import src.main.java.aura.utils.MathUtils;

public class AnimateBackground extends Transition{

    public AnimateBackground(Box<?> component, Color to, int ms){
        Color initial = component.getBg();
        initialize(component, to, ms, initial);
    }

    private void initialize(Box<?> component, Color to, int ms, Color initial) {
        setup(0f, 1f, ms, value -> {

            Color c = new Color(
                (int) MathUtils.clamp((initial.getRed() + (to.getRed() - initial.getRed()) * value), 0, 255),
                (int) MathUtils.clamp((initial.getGreen() + (to.getGreen() - initial.getGreen()) * value), 0, 255),
                (int) MathUtils.clamp((initial.getBlue() + (to.getBlue() - initial.getBlue()) * value), 0, 255),
                (int) MathUtils.clamp((initial.getAlpha() + (to.getAlpha() - initial.getAlpha()) * value), 0, 255)
            );

            component.background(c);
        }, component,TransitionType.EASE_IN);
          
        animationType(AnimationType.BACKGROUND);
    }
    
}

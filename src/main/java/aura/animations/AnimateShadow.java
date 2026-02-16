package src.main.java.aura.animations;

import java.awt.Color;
import src.main.java.aura.core.Box;
import src.main.java.aura.core.Transition;
import src.main.java.aura.utils.MathUtils;

public class AnimateShadow extends Transition {
    
    public AnimateShadow(Box<?> component, Color toColor, int toSize, int ms){

        Color initial = component.getShadowColor();
        float initialSize = component.getShadowSize();

        initialize(component, initial, initialSize, toColor, toSize, ms);
    }

    private void initialize(Box<?> component, Color initialColor, float initialSize, Color toColor, int toSize, int ms){
        setup(0f, 1f, ms, value -> {

            Color c = new Color(
                (int) MathUtils.clamp((initialColor.getRed() + (toColor.getRed() - initialColor.getRed()) * value), 0, 255),
                (int) MathUtils.clamp((initialColor.getGreen() + (toColor.getGreen() - initialColor.getGreen()) * value), 0, 255),
                (int) MathUtils.clamp((initialColor.getBlue() + (toColor.getBlue() - initialColor.getBlue()) * value), 0, 255),
                (int) MathUtils.clamp((initialColor.getAlpha() + (toColor.getAlpha() - initialColor.getAlpha()) * value), 0, 255)
            );

            float newSize = initialSize + (toSize - initialSize) * value;

            component.shadow(c, newSize);

        }, component, TransitionType.EASE_OUT);

        animationType(AnimationType.SHADOW);
    }

}

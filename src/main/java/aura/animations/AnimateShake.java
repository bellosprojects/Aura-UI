package src.main.java.aura.animations;

import src.main.java.aura.core.AuraBox;
import src.main.java.aura.core.Transition;

public class AnimateShake extends Transition {

    public AnimateShake(AuraBox<?> component, int intensity, int ms){

        initialize(component, intensity, ms);
    }

    private void initialize(AuraBox<?> component, int intensity, int ms){
        setup(0f, 1f, ms, value -> { 
            component.offset((float) Math.sin( value * (ms / 10)) * intensity, 0);
         },component, TransitionType.LINEAR);

        animationType(AnimationType.MOVE);
    }
    
}

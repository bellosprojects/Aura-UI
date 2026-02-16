package src.main.java.aura.animations;

import src.main.java.aura.core.Box;
import src.main.java.aura.core.Transition;

public class AnimateShake extends Transition {

    public AnimateShake(Box<?> component, int intensity, int ms){

        initialize(component, intensity, ms);
    }

    private void initialize(Box<?> component, int intensity, int ms){
        setup(0f, 1f, ms, value -> { component.offset((float) Math.sin( value * (ms / 10)) * intensity, 0); },component, TransitionType.LINEAR);

        animationType(AnimationType.MOVE);
    }
    
}

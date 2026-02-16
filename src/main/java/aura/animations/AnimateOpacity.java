package src.main.java.aura.animations;

import src.main.java.aura.core.Box;
import src.main.java.aura.core.Transition;

public class AnimateOpacity extends Transition {
    
    public AnimateOpacity(Box<?> component, float to, int ms){

        float actualOp = component.getOpacity();

        initialize(component, actualOp, to, ms);
    }

    private void initialize(Box<?> component, float actualOp, float to, int ms){
        setup(actualOp, to, ms, value -> {

            component.setOpacity(value);

        }, component, TransitionType.LINEAR);

        animationType(AnimationType.OPACITY);
    }

}

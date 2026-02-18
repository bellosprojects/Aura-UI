package aura.animations;

import aura.core.Transition;
import aura.core.TransitionIntStep;

public class AnimateInteger extends Transition {

    public AnimateInteger(int initial, int end, int ms, TransitionIntStep step){

        setup(initial, end, ms, value -> {
            step.onUpdate((int) value);
        }, null, TransitionType.LINEAR);


    }
     
}

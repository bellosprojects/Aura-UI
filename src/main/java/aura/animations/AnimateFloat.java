package src.main.java.aura.animations;

import src.main.java.aura.core.Transition;
import src.main.java.aura.core.TransitionFloatStep;

public class AnimateFloat extends Transition {

    public AnimateFloat(float initial, float end, int ms, TransitionFloatStep step) {

        this.setup(initial, end, ms, value -> {step.onUpdate(value);}, null, TransitionType.LINEAR);

    }

}

package src.main.java.aura.animations;

import src.main.java.aura.core.Transition;
import src.main.java.aura.core.AuraBox;

public class AnimateWobble extends Transition {

    public AnimateWobble(AuraBox<?> component, int intensity, int ms){
        initialize(component, component.getAnchorX(), component.getAnchorY(), intensity, ms);
    }

    private void initialize(AuraBox<?> component, float initialX, float initialY, int intensity, int ms){

        setup(0, 0, 0, value -> {}, component, TransitionType.LINEAR);

        component.anchor(1f, 1f);

        Transition t1 = new AnimateRotation(component, intensity / 2, ms / 2).then(() ->
            component.anchor(0f, 1f)
        ).pingPong();

        Transition t2 = new AnimateRotation(component, -intensity / 3, ms / 3).then(() -> {
            component.anchor(1f, 1f);
        }).pingPong();

        t1.serie(t2);

        Transition t3 = new AnimateRotation(component, intensity / 4, ms / 4).then(() -> {
            component.anchor(initialX, initialY);
        }).pingPong();

        t2.serie(t3);

        animationType(AnimationType.ROTATE);

        serie(t1);

    }
    
}

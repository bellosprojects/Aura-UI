package src.main.java.aura.animations;

import src.main.java.aura.core.Transition;
import src.main.java.aura.core.Box;

public class AnimateRotation extends Transition {

    private final Box<?> component;
    private int ms = 0;
    private float to = 0;

    public AnimateRotation(Box<?> component, float to, int ms){
        this.component = component;
        this.ms = ms;
        this.to = to;
        initialize(component, component.getRotation(), to, ms);
    }

    private void initialize(Box<?> component, float initialAngle, float toAngle, int ms){

        setup(initialAngle, toAngle, ms, value -> {

            component.rotate(value);
            if(component.getParent() != null){
                component.getParent().repaint();
            }

        }, component, TransitionType.EASE_IN);


        animationType(AnimationType.ROTATE);
    }

    public AnimateRotation anchor(float x, float y){
        component.anchor(x, y);
        initialize(component, component.getRotation(), to, ms);
        return this;
    }
    
}

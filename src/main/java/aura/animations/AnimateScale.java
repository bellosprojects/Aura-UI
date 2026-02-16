package src.main.java.aura.animations;

import src.main.java.aura.core.Box;
import src.main.java.aura.core.Transition;

public class AnimateScale extends Transition {
    
    public AnimateScale(Box<?> component, float to, int ms){
        
        initialize(component, component.getScale(), to, ms);
    }

    private void initialize(Box<?> component,float initialScale, float to, int ms){
        setup(initialScale, to, ms, value -> { component.setScale(value); } ,component, TransitionType.LINEAR);

        animationType(AnimationType.SCALE);
    }

}

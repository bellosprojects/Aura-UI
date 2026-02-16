package src.main.java.aura.animations;

import src.main.java.aura.core.AuraBox;
import src.main.java.aura.core.Transition;

public class AnimateScale extends Transition {
    
    public AnimateScale(AuraBox<?> component, float to, int ms){
        
        initialize(component, component.getScale(), to, ms);
    }

    private void initialize(AuraBox<?> component,float initialScale, float to, int ms){
        setup(initialScale, to, ms, value -> { component.setScale(value); } ,component, TransitionType.LINEAR);

        animationType(AnimationType.SCALE);
    }

}

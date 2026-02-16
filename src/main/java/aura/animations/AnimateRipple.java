package src.main.java.aura.animations;

import src.main.java.aura.core.Box;
import src.main.java.aura.core.Transition;

public class AnimateRipple extends Transition {
    
    public AnimateRipple(Box<?> component, float to, int ms){

        float initialScale = component.getScale();

        initialize(component, initialScale, to, ms);
    }

    private void initialize(Box<?> component, float initialScale, float maximus, int ms){
        setup(initialScale, maximus, ms / 3, value -> {

            component.setScale(value);

        }, component, TransitionType.LINEAR);

        pingPong();

        type(TransitionType.EASE_IN);

        serie(
            
            new AnimateScale(component, initialScale + ((maximus - initialScale) / 2f), ms / 4)
            .pingPong()
            .cancelPrev(true)
            .serie(
                new AnimateScale(component, initialScale + ((maximus - initialScale) / 4f), ms / 6)
                    .pingPong()
                    .cancelPrev(true)
                    .serie(
                        new AnimateScale(component, initialScale + ((maximus - initialScale) / 8f), ms /  8)
                            .pingPong()
                            .cancelPrev(true)
                            .then(() -> {
                                component.setScale(initialScale);
                            })
                    )
            )
        );
    }

}

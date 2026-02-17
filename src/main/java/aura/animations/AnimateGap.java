package src.main.java.aura.animations;

import src.main.java.aura.core.Layout;
import src.main.java.aura.core.Transition;

public class AnimateGap extends Transition {

    public AnimateGap(Layout<?> layout, int to, int ms){
        initialize(layout, layout.getGap(), to, ms);
    }

    private void initialize(Layout<?> layout, int initialGap, int toGap, int ms){

        setup(initialGap, toGap, ms, value -> {

            layout.gap((int) value);

        }, layout, TransitionType.LINEAR);

        animationType(AnimationType.GAP);

        then(() -> layout.gap(initialGap));
    }
    
}

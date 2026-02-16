package src.main.java.aura.core;

import java.awt.Point;

@FunctionalInterface
public interface TransitionPointStep {
    void onUpdate(Point value);
}

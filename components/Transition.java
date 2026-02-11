package components;

import javax.swing.Timer;

public class Transition {

    private Timer timer;
    private long startTime;
    private int duration;
    private float startVal, endVal;

    public static enum TrasitionType {
        LINEAR,
        EASE_IN,
        EASE_OUT
    };

    public static enum AnimationType {
        MOVE,
        COLOR,
        SCALE
    };

    private TrasitionType type;

    private void setup(float initial, float target, int ms, TrasitionStep step, TrasitionType type){
        this.startVal = initial;
        this.endVal = target;
        this.duration = ms;
        this.type = type;

        timer = new Timer(15, e -> { // ~60 FPS
            long elapsed = System.currentTimeMillis() - startTime;
            float progress = Math.min(1f, (float) elapsed / duration);
            
            // Suavizado (Easing): Esto hace que no sea robótico
            float easedProgress = (float) (Math.sin(progress * Math.PI - Math.PI / 2) + 1) / 2f;
            
            float currentValue = startVal + (endVal - startVal) * calc(easedProgress);
            step.onUpdate(currentValue);

            if (progress >= 1f) {
                timer.stop();
            }
        });
    }

    private float calc(float value){
        switch (this.type) {
            case EASE_IN:
                return (float) Math.cbrt(value);
            case EASE_OUT:
                return (float) Math.pow(value, 3);
            default:
                return value;
        }
    }

    public Transition(float initial, float target, int ms, TrasitionStep step) {
        setup(initial, target, ms, step, TrasitionType.LINEAR);
    }

    public Transition(float initial, float target, int ms, TrasitionStep step, TrasitionType type){
        setup(initial, target, ms, step, type);
    }

    public void start() {
        startTime = System.currentTimeMillis();
        timer.start();
    }

    public void stop() {
        if (timer != null) timer.stop();
    }
}
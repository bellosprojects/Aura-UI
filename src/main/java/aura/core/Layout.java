package src.main.java.aura.core;

import java.util.function.Consumer;

@SuppressWarnings ("unchecked")
public class Layout<T extends Layout<T>> extends Box<T> {
    
    protected  int gap = 0;
    protected Alignment alignment = Alignment.CENTER;

    public static enum Alignment {
        CENTER,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public void setGap(int gap){
        this.gap = gap;
        this.revalidate();
        this.repaint();
        this.validate();
    }

    public int getGap(){
        return this.gap;
    }

    public T content(Consumer<Layout<T>> content){
        content.accept(this);
        return (T) this;
    }

}

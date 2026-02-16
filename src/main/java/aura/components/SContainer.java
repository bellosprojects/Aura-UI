package src.main.java.aura.components;

import java.awt.*;
import src.main.java.aura.core.Box;

public class SContainer extends Box<SContainer> {

    public SContainer(){
        setLayout(null);
        addMouseEvents();
    }

    public SContainer gradientType(Backgrounds type){
        this.setBgType(type == Backgrounds.RADIAL);
        return this;
    }

    public SContainer radius(float all){
        this.setRadius(all, all, all, all);
        return this;
    }

    public SContainer radius(float top, float bottom){
        this.setRadius(top, top, bottom, bottom);
        return this;
    }

    public SContainer radius(float tl, float tr, float bl, float br){
        this.setRadius(tl, tr, bl, br);
        return this;
    }

    public SContainer addBackground(Color color, float pos){
        this.addBg(color, pos);
        return this;
    }

    public SContainer backgroundAngle(float angle){
        this.setBackgroundAngle(angle);
        return this;
    }

    public SContainer size(int width, int height){

        int w = width + getMargin().left + getMargin().right;
        int h = height + getMargin().top + getMargin().bottom;

        this.setPreferredSize(new Dimension(w, h));
        this.setMinimumSize(new Dimension(w, h));
        this.setMaximumSize(new Dimension(w, h));
        return this;
    }

    public SContainer margin(int all){
        this.setMargin(all, all, all, all);
        return this;
    }

    public SContainer margin(int top_bottom, int left_right){
        this.setMargin(top_bottom, left_right, top_bottom, left_right);
        return this;
    }

    public SContainer margin(int top, int left, int bottom, int right){
        this.setMargin(top, left, bottom, right);
        return this;
    }

    public SContainer padding(int all){
        this.setPadding(all, all, all, all);
        return this;
    }

    public SContainer padding(int top_bottom, int left_right){
        this.setPadding(top_bottom, left_right, top_bottom, left_right);
        return this;
    }

    public SContainer padding(int top, int left, int bottom, int right){
        this.setPadding(top, left, bottom, right);
        return this;
    }

    //Flat Corners
    public SContainer flatCorners(boolean all){
        this.setFlatCorners(all, all, all, all);
        return this;
    }

    public SContainer flatCorners(boolean top, boolean bottom){
        this.setFlatCorners(top, top, bottom, bottom);
        return this;
    }

    public SContainer flatCorners(boolean tl, boolean  tr, boolean bl, boolean br){
        this.setFlatCorners(tl, tr, bl, br);
        return this;
    }



    public SContainer drawStrokes(boolean all){
        this.setPaintStrokes(all, all, all, all);
        return this;
    }

    public SContainer drawStrokes(boolean top_bottom, boolean left_right){
        this.setPaintStrokes(top_bottom, left_right, top_bottom, left_right);
        return this;
    }

    public SContainer drawStrokes(boolean top, boolean left, boolean bottom, boolean right){
        this.setPaintStrokes(top, left, bottom, right);
        return this;
    }


}

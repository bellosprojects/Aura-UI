package src.main.java.aura.components;

import java.awt.*;
import src.main.java.aura.core.AuraBox;

public class AuraContainer extends AuraBox<AuraContainer> {

    public AuraContainer(){
        setLayout(null);
        addMouseEvents();
    }

    public AuraContainer gradientType(Backgrounds type){
        this.setBgType(type == Backgrounds.RADIAL);
        return this;
    }

    public AuraContainer radius(float all){
        super.radius(all, all, all, all);
        return this;
    }

    public AuraContainer radius(float top, float bottom){
        super.radius(top, top, bottom, bottom);
        return this;
    }

    public AuraContainer addBackground(Color color, float pos){
        this.addBg(color, pos);
        return this;
    }

    public AuraContainer backgroundAngle(float angle){
        this.setBackgroundAngle(angle);
        return this;
    }

    public AuraContainer margin(int all){
        this.setMargin(all, all, all, all);
        return this;
    }

    public AuraContainer margin(int top_bottom, int left_right){
        this.setMargin(top_bottom, left_right, top_bottom, left_right);
        return this;
    }

    public AuraContainer margin(int top, int left, int bottom, int right){
        this.setMargin(top, left, bottom, right);
        return this;
    }

    public AuraContainer padding(int all){
        this.setPadding(all, all, all, all);
        return this;
    }

    public AuraContainer padding(int top_bottom, int left_right){
        this.setPadding(top_bottom, left_right, top_bottom, left_right);
        return this;
    }

    public AuraContainer padding(int top, int left, int bottom, int right){
        this.setPadding(top, left, bottom, right);
        return this;
    }

    //Flat Corners
    public AuraContainer flatCorners(boolean all){
        this.setFlatCorners(all, all, all, all);
        return this;
    }

    public AuraContainer flatCorners(boolean top, boolean bottom){
        this.setFlatCorners(top, top, bottom, bottom);
        return this;
    }

    public AuraContainer flatCorners(boolean tl, boolean  tr, boolean bl, boolean br){
        this.setFlatCorners(tl, tr, bl, br);
        return this;
    }



    public AuraContainer drawStrokes(boolean all){
        this.setPaintStrokes(all, all, all, all);
        return this;
    }

    public AuraContainer drawStrokes(boolean top_bottom, boolean left_right){
        this.setPaintStrokes(top_bottom, left_right, top_bottom, left_right);
        return this;
    }

    public AuraContainer drawStrokes(boolean top, boolean left, boolean bottom, boolean right){
        this.setPaintStrokes(top, left, bottom, right);
        return this;
    }


}

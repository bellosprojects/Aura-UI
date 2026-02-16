package src.main.java.aura.utils;

import java.awt.geom.*;

public class BoxUtils {
    
    public static Path2D createTriangle(float x, float y, float x2, float y2, float cx, float cy) {
        Path2D p = new Path2D.Float();
        p.moveTo(x, y);
        p.lineTo(x2, y2);
        p.lineTo(cx, cy);
        p.closePath();
        return p;
    }

}

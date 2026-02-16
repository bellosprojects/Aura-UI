package src.main.java.aura.utils;

import java.awt.geom.*;
import java.awt.*;

public class BoxUtils {
    
    public static Path2D createTriangle(float x, float y, float x2, float y2, float cx, float cy) {
        Path2D p = new Path2D.Float();
        p.moveTo(x, y);
        p.lineTo(x2, y2);
        p.lineTo(cx, cy);
        p.closePath();
        return p;
    }

    public static void setHighQuality(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

}

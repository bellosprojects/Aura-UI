package components;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import components.Transition.AnimationType;

public class SuperBox extends JPanel {

    //Esquinas planas o redondeadas
    private float[] radios = new float[4];
    private boolean[] flatCorners = new boolean[4];

    //Espaciado interno y externo
    private int[] padding = new int[4];
    private int[] margin = new int[4];

    //Fondo degradado, angulo y desplazamiento
    private final List<Color> backgroundColors = new ArrayList<>();
    private final List<Float> backgroundFractions = new ArrayList<>();
    private float backgroundAngle = 0.0f;
    private float backgroundOffsetX = 0f;
    private float backgroundOffsetY = 0f;

    //Figura de fondo y opacidad
    private Path2D shapePath;
    private float opacity = 1f;

    //offset general;
    private float offsetX = 0;
    private float offsetY = 0;

    //scala
    private float scale = 1.0f;

    //Bordes
    private Color StrokeColor = new Color(200, 200, 200);
    private float strokeWidth = 0f;
    
    //Comportamientos con Mouse
    private boolean isHovered = false;
    private final List<HoverAction> hoverActions = new ArrayList<>();
    private final List<ClickAction> clickActions = new ArrayList<>();

    //Comportamiento con hijos
    private boolean clipChildrens = false;
    private boolean detectChildrensHover = false;
    private boolean detectChildrensClick = false;

    //Animaciones
    private final List<Transition> timers = new ArrayList<>();
    private final List<AnimationType> timersTypes = new ArrayList<>();

    public void addTimer(Transition timer, AnimationType type, boolean cancel){
        
        if(cancel){
            for(int i = 0; i < timers.size(); i ++){
                if(timersTypes.get(i) == type){
                    timers.get(i).stop();
                    timers.remove(i);
                    timersTypes.remove(i);
                }
            }
        }

        timer.start();
        timers.add(timer);
        timersTypes.add(type);
    }

    public void addHoverAction(HoverAction action){
        this.hoverActions.add(action);
    }

    public void addClickAction(ClickAction action){
        this.clickActions.add(action);
    }

    public void setClipChildrens(boolean clipChildrens){
        this.clipChildrens = clipChildrens;
    }

    public void setDetectChildrensHover(boolean detect){
        this.detectChildrensHover = detect;
    }

    public void setDetectChildrensClick(boolean detect){
        this.detectChildrensClick = detect;
    }

    public void setScale(float scale){
        this.scale = scale;
        repaint();
    }

    public float getScale(){
        return this.scale;
    }

    public SuperBox(LayoutManager layout){

        layout = (layout != null)? layout : new FlowLayout(FlowLayout.CENTER, 0, 0);
        setLayout(layout);

        setOpaque(false);
        this.backgroundColors.add(new Color(255, 255, 255));
        this.backgroundFractions.add(0.0f);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public  void mouseMoved(MouseEvent e){
                checkHover(e.getPoint());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                updateHover(false);
            }
            @Override
            public void mouseReleased(MouseEvent e){
                checkClick(e.getPoint());
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    private void checkClick(Point p){

        Point adjustedPoint = new Point(
            (int)(p.x - offsetX),
            (int)(p.y - offsetY)
        );

        if(shapePath != null && shapePath.contains(adjustedPoint)){
            List<ClickAction> copy = new ArrayList<>(clickActions);
            for(ClickAction action : copy){
                action.onClick();
            }
        }
    }

    private void checkHover(Point p){

        Point adjustedPoint = new Point(
            (int)(p.x - offsetX),
            (int)(p.y - offsetY)
        );

        boolean contains = (shapePath != null && shapePath.contains(adjustedPoint));
        if (contains != isHovered){
            updateHover(contains);
        }
    }

    private void updateHover(boolean hover){
        this.isHovered = hover;
        
        List<HoverAction> copy = new ArrayList<>(hoverActions);
        for(HoverAction action: copy){
            action.onHover(hover);
        }

        repaint();
    }

    public void setStroke(Color color, float widht){
        this.StrokeColor = color;
        this.strokeWidth = widht;
        repaint();
    }

    public void setOffset(float x, float y){
        this.offsetX = x;
        this.offsetY = y;
        repaint();
    }

    public float[] getOffset(){
        return new float[]{offsetX, offsetY};
    }

    public void setRadius(float all){
        setRadius(all, all, all, all);
    }

    public void setRadius(float top, float bottom){
        setRadius(top, top, bottom, bottom);
    }

    public void setRadius(float tl, float tr, float bl, float br){
        this.radios = new float[]{tl, tr, bl, br};
        repaint();
    }

    public Insets getRadius(){
        return new Insets((int) this.radios[0], (int) this.radios[1], (int) this.radios[2], (int) this.radios[3]);
    }

    public Insets getPadding(){
        return new Insets((int) this.padding[0], (int) this.padding[1], (int) this.padding[2], (int) this.padding[3]);
    }

    public void setFlatCorners(boolean all){
        setFlatCorners(all, all, all, all);
    }

    public void setFlatCorners(boolean top, boolean bottom){
        setFlatCorners(top, top, bottom, bottom);
    }

    public void setFlatCorners(boolean tl, boolean tr, boolean bl, boolean br){
        this.flatCorners = new boolean[]{tl, tr, bl, br};
        repaint();
    }

    public boolean[] getFlatCorners(){
        return this.flatCorners;
    }

    public Insets getMargin(){
        return new Insets((int) this.margin[0], (int) this.margin[1], (int) this.margin[2], (int) this.margin[3]);
    }

    public void setBg(Color color){
        this.backgroundColors.clear();
        this.backgroundFractions.clear();
        this.backgroundColors.add(color);
        this.backgroundFractions.add(0.0f);
        repaint();
    }

    public void addBg(Color color, float position){
        this.backgroundColors.add(color);
        this.backgroundFractions.add(position);
        repaint();
    }

    public void setBackgroundColorAtIndex(Color color, int index){
        if(index < 0 || index >= this.backgroundColors.size()) return;
        this.backgroundColors.set(index, color);
        repaint();
    }

    public void setBackgroundFractionAtIndex(float fraction, int index){
        if(index < 0 || index >= this.backgroundColors.size()) return;
        this.backgroundFractions.set(index, fraction);
        repaint();
    }

    public void setBackgroundAtIndex(Color color, float fraction, int index){
        if(index < 0 || index >= this.backgroundColors.size()) return;
        this.backgroundFractions.set(index, fraction);
        this.backgroundColors.set(index, color);
        repaint();
    }

    public void setOpacity(float alpha){
        this.opacity = alpha;
        repaint();
    }

    public void setBackgroundAngle(float angle){
        this.backgroundAngle = angle;
        repaint();
    }

    public void setBackgroundOffsetX(float offsetX){
        this.backgroundOffsetX = offsetX;
        repaint();
    }

    public void setBackgroundOffsetY(float offsetY){
        this.backgroundOffsetY = offsetY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.translate(offsetX, offsetY);

        if (scale != 1.0f) {
            double centerX = getWidth() / 2.0;
            double centerY = getHeight() / 2.0;
            g2.translate(centerX, centerY);
            g2.scale(scale, scale);
            g2.translate(-centerX, -centerY);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        Insets insets = (getBorder() != null) ? getBorder().getBorderInsets(this) : new Insets(0,0,0,0);

        int width = getWidth() - insets.left - insets.right;
        int height = getHeight() - insets.top - insets.bottom;

        int x = insets.left;
        int y = insets.top;

        
        shapePath = new Path2D.Float();
        shapePath.moveTo(x + radios[0], y);

        shapePath.lineTo(x+ width - radios[1], y); // Top

        if (this.flatCorners[1]){
            shapePath.lineTo(x + width, y + radios[1]);
        }else {
            shapePath.quadTo(x + width, y, x + width, y + radios[1]); // TR
        }

        shapePath.lineTo(x + width, y + height - radios[3]); // Right

        if(this.flatCorners[3]){
            shapePath.lineTo(x + width- radios[3], y + height);
        } else {
            shapePath.quadTo(x + width, y + height, x + width - radios[3], y + height); // BR
        }

        shapePath.lineTo(x + radios[2], y + height); // Bottom

        if(this.flatCorners[2]){
            shapePath.lineTo(x, y + height - radios[2]);
        } else {
            shapePath.quadTo(x, y + height, x, y + height - radios[2]); // BL
        }

        shapePath.lineTo(x, y + radios[0]); // Left

        if(this.flatCorners[0]){
            shapePath.lineTo(x + radios[0], y);
        } else {
            shapePath.quadTo(x, y, x + radios[0], y); // TL
        }

        shapePath.closePath();
        
        Color[] colors = new Color[this.backgroundColors.size()];
        for(int i =0; i < this.backgroundColors.size(); i ++ ){
            colors[i] = this.backgroundColors.get(i);
        }

        float[] fractions = new float[this.backgroundFractions.size()];
        for(int i =0; i < this.backgroundFractions.size(); i ++ ){
            fractions[i] = this.backgroundFractions.get(i);
        }

        if (this.backgroundColors.size() > 1){
            float angleRad = (float) Math.toRadians(this.backgroundAngle);
            float cos = (float) Math.cos(angleRad);
            float sin = (float) Math.sin(angleRad);

            // 1. Calcular la longitud necesaria para que el gradiente cubra el área
            float length = Math.abs(width * cos) + Math.abs(height * sin);

            // 2. Centrar el degradado en el medio del componente
            float centerX = x + width / 2f;
            float centerY = y + height / 2f;

            // 3. Puntos de inicio y fin proyectados
            float startX = centerX - (length / 2f) * cos + backgroundOffsetX * width;
            float startY = centerY - (length / 2f) * sin + backgroundOffsetY * height;
            float endX = centerX + (length / 2f) * cos + backgroundOffsetX * width;
            float endY = centerY + (length / 2f) * sin + backgroundOffsetY * height;

            Paint background = new LinearGradientPaint(
                startX, startY,
                endX, endY,
                fractions,
                colors
            );

            g2.setPaint(background);
        } else {
            g2.setColor(this.backgroundColors.get(0));
        }
        
        g2.fill(shapePath);

        if (strokeWidth > 0){
            g2.setPaint(StrokeColor);
            g2.setStroke(new BasicStroke(strokeWidth));

            g2.draw(shapePath);
        }

        g2.dispose();
    }

    public void setPadding(int all){
        setPadding(all, all, all, all);
    }

    public void setPadding(int top_bottom, int left_right){
        setPadding(top_bottom, left_right, top_bottom, left_right);
    }

    public void setPadding(int top, int left, int bottom, int right){
        this.padding = new int[]{top, left, bottom, right};
        // No usamos setBorder aquí, solo refrescamos el layout
        revalidate();
        repaint();
    }

    @Override
    public Insets getInsets() {
        // Obtenemos los insets del margen (el borde)
        Insets borderInsets = super.getInsets();
        // Le sumamos el padding para que los componentes hijos se desplacen más
        return new Insets(
            borderInsets.top + padding[0],
            borderInsets.left + padding[1],
            borderInsets.bottom + padding[2],
            borderInsets.right + padding[3]
        );
    }

    public void setMargin(int all){
        setMargin(all, all, all, all);
    }   

    public void setMargin(int top_bottom, int left_right){
        setMargin(top_bottom, left_right, top_bottom, left_right);
    }

    public void setMargin(int top, int left, int bottom, int right){
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        this.margin = new int[]{top, left, bottom, right};
        revalidate();
        repaint();
    }

    @Override
    protected void paintChildren(Graphics g){

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.translate(offsetX, offsetY);

        if(shapePath != null && clipChildrens){
            g2.setClip(shapePath);
        }

        super.paintChildren(g2);
        g2.dispose();

    }

    @Override
    protected void addImpl(Component comp, Object constraints, int index){

        super.addImpl(comp, constraints, index);

        MouseAdapter bridgeListener = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e){
                if(detectChildrensHover) handleChildEvent(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (detectChildrensHover) handleChildEvent(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (detectChildrensHover) handleChildEvent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (detectChildrensClick) handleChildEvent(e);
            }

            private void handleChildEvent(MouseEvent e){

                Point parentPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), SuperBox.this);

                if(e.getID() == MouseEvent.MOUSE_RELEASED){
                    checkClick(parentPoint);
                } else {
                    checkHover(parentPoint);
                }
            }
        };

        comp.addMouseListener(bridgeListener);
        comp.addMouseMotionListener(bridgeListener);

    }

}
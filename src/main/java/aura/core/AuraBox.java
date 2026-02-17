package src.main.java.aura.core;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

import src.main.java.aura.core.Transition.AnimationType;
import src.main.java.aura.layouts.AuraColumn;
import src.main.java.aura.layouts.AuraRow;
import src.main.java.aura.utils.BoxUtils;
import src.main.java.aura.utils.MathUtils;

@SuppressWarnings ("unchecked")
public abstract class AuraBox<T extends AuraBox<T>> extends JPanel {

    private AuraColumn.Alignment colAlign = null;
    private AuraRow.Alignment rowAlign = null;

    public T alignSelf(AuraColumn.Alignment align){
        this.colAlign = align;
        return (T) this;
    }

    public T alignSelf(AuraRow.Alignment align){
        this.rowAlign = align;
        return (T) this;
    }

    public AuraRow.Alignment getAlignR(){
        return this.rowAlign;
    }

    public AuraColumn.Alignment getAlignC(){
        return this.colAlign;
    }

    protected Image backgroundImage = null;
    protected boolean scaleBackground = true;

    public T background(Image img){
        this.backgroundImage = img;
        repaint();
        return (T) this;
    }

    private float anchorX = 0.5f;
    private float anchorY = 0.5f;

    public T anchor(float x, float y){
        this.anchorX = x;
        this.anchorY = y;
        return (T) this;
    }

    public float getAnchorX(){
        return anchorX;
    }

    public float getAnchorY(){
        return anchorY;
    }

    private float weight = 0;

    public T weight(float w){
        this.weight = w;
        if(getParent() != null) getParent().revalidate();
        return (T) this;
    }

    public float getWeight(){
        return this.weight;
    }

    protected final List<HoverAction<T>> hoverActions = new ArrayList<>();
    protected final List<ClickAction<T>> clickActions = new ArrayList<>();

    protected  void addMouseEvents(){

        AuraBox<T> self = this;

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e){
                if(checkHover(e.getPoint())){
                    updateHover(!isHovered);
                    for(HoverAction<T> action : hoverActions){
                        action.onHover((T) self , isHovered);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                updateHover(false);
            }
            @Override
            public void mouseReleased(MouseEvent e){
                if(checkClick(e.getPoint())){
                    for(ClickAction<T> action : clickActions){
                        action.onClick((T) self);
                    }
                }
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public T onClick(ClickAction<T> action){
        this.clickActions.add(action);
        return (T) this;
    }

    public T onHover(HoverAction<T> action){
        this.hoverActions.add(action);
        return (T) this;
    }

    private boolean bgType = false; //false = lineal, true = radial

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

    //rotacion
    private float angle = 0;

    //Para SGrid
    private int colSpan = 1;
    private int rowSpan = 1;

    //scala
    private float scale = 1.0f;

    protected boolean isHovered = false;

    //shadow
    private Color shadowColor = new Color(0, 0, 0, 100);
    private float shadowSize = 0;
    private float shadowOffsetX = 0;
    private float shadowOffsetY = 0;

    //Bordes
    private Color StrokeColor = new Color(200, 200, 200);
    private float strokeWidth = 0f;
    private boolean[] paintedStrokes = new boolean[]{true, true, true, true};

    //Comportamiento con hijos
    private boolean clipChildrens = false;

    //Animaciones
    private final List<Transition> timers = new ArrayList<>();
    private final List<AnimationType> timersTypes = new ArrayList<>();

    private final Map<Component, Point> absoluteComponents = new HashMap<>();

    public static enum Backgrounds {

        RADIAL,
        LINEAR

    };


    public T colSpan(int s){
        this.colSpan = s;
        return (T) this;
    }

    public T rowSpan(int s){
        this.rowSpan = s;
        return (T) this;
    }

    public int getRowSpan(){
        return this.rowSpan;
    }

    public int getColSpan(){
        return this.colSpan;
    }

    protected void setBgType(boolean ty){
        this.bgType = ty;
    }

    public T rotate(float angle){
        this.angle = angle;
        revalidate();
        repaint();
        return (T) this;
    }

    public float getRotation(){
        return this.angle;
    }

    public void addAt(Component comp, int x, int y) {
        absoluteComponents.put(comp, new Point(x, y));
        super.add(comp); 
        revalidate();
        repaint();
    }

    @Override
    public void doLayout(){

        super.doLayout();

        for (Map.Entry<Component, Point> entry : absoluteComponents.entrySet()) {
            Component comp = entry.getKey();
            Point pos = entry.getValue();

            Insets insets = getInsets();
            
            comp.setBounds(
                insets.left + pos.x, 
                insets.top + pos.y, 
                comp.getPreferredSize().width, 
                comp.getPreferredSize().height
            );
        }

    }

    @Override
    public Dimension getPreferredSize(){
        Dimension d = super.getPreferredSize();

        if(angle == 0) return d;

        double rad = Math.toRadians(Math.abs(angle));
        int w = (int) (d.width * Math.cos(rad) + d.height * Math.sin(rad));
        int h = (int) (d.width * Math.sin(rad) + d.height * Math.cos(rad));

        return new Dimension(w, h);
    }

    public T clipChildrens(boolean clipChildrens){
        this.clipChildrens = clipChildrens;
        repaint();
        return (T) this;
    }

    public void setScale(float scale){
        this.scale = scale;
        repaint();
    }

    public float getScale(){
        return this.scale;
    }

    protected AuraBox(){

        setOpaque(false);
        this.backgroundColors.add(new Color(255, 255, 255));
        this.backgroundFractions.add(0.0f);

    }

    protected boolean checkClick(Point p){

        Point adjustedPoint = new Point(
            (int)(p.x - offsetX),
            (int)(p.y - offsetY)
        );

        return (shapePath != null && shapePath.contains(adjustedPoint));
    }

    protected boolean checkHover(Point p){

        Point adjustedPoint = new Point(
            (int)(p.x - offsetX),
            (int)(p.y - offsetY)
        );

        boolean contains = (shapePath != null && shapePath.contains(adjustedPoint));
        return contains != isHovered;
    }

    protected void updateHover(boolean hover){
        this.isHovered = hover;
        repaint();
    }

    public T stroke(Color color, float width){
        this.StrokeColor = color;
        this.strokeWidth = width;
        repaint();
        return (T) this;
    }

    public T offset(float x, float y){
        this.offsetX = x;
        this.offsetY = y;
        repaint();
        return (T) this;
    }

    protected float[] getOffset(){
        return new float[]{offsetX, offsetY};
    }

    public T radius(float all){
        return radius(all, all, all, all);
    }

    public T radius(float top, float bottom){
        return radius(top, top, bottom, bottom);
    }

    public T radius(float tl, float tr, float bl, float br){
        this.radios = new float[]{tl, tr, bl, br};
        repaint();
        return (T) this;
    }

    protected Insets getRadius(){
        return new Insets((int) this.radios[0], (int) this.radios[1], (int) this.radios[2], (int) this.radios[3]);
    }

    protected Insets getPadding(){
        return new Insets((int) this.padding[0], (int) this.padding[1], (int) this.padding[2], (int) this.padding[3]);
    }

    protected void setFlatCorners(boolean tl, boolean tr, boolean bl, boolean br){
        this.flatCorners = new boolean[]{tl, tr, bl, br};
        repaint();
    }

    protected boolean[] getFlatCorners(){
        return this.flatCorners;
    }

    protected void setPaintStrokes(boolean top, boolean left, boolean bottom, boolean right){
        this.paintedStrokes = new boolean[]{top, left, bottom, right};
        repaint();
    }

    protected boolean[] getPaintStrokes(){
        return this.paintedStrokes;
    }

    protected Insets getMargin(){
        return new Insets((int) this.margin[0], (int) this.margin[1], (int) this.margin[2], (int) this.margin[3]);
    }

    public T background(Color color){
        this.backgroundColors.clear();
        this.backgroundFractions.clear();
        this.backgroundColors.add(color);
        this.backgroundFractions.add(0.0f);
        repaint();
        return (T) this;
    }

    protected void addBg(Color color, float position){
        this.backgroundColors.add(color);
        this.backgroundFractions.add(position);
        repaint();
    }

    public Color getBg(){
        return this.backgroundColors.get(0);
    }

    protected void setBackgroundColorAtIndex(Color color, int index){
        if(index < 0 || index >= this.backgroundColors.size()) return;
        this.backgroundColors.set(index, color);
        repaint();
    }

    protected void setBackgroundFractionAtIndex(float fraction, int index){
        if(index < 0 || index >= this.backgroundColors.size()) return;
        this.backgroundFractions.set(index, fraction);
        repaint();
    }

    protected void setBackgroundAtIndex(Color color, float fraction, int index){
        if(index < 0 || index >= this.backgroundColors.size()) return;
        this.backgroundFractions.set(index, fraction);
        this.backgroundColors.set(index, color);
        repaint();
    }

    public void setOpacity(float alpha){
        if(alpha > 1f || alpha < 0f) return;
        this.opacity = alpha;
        repaint();
    }

    public float getOpacity(){
        return this.opacity;
    }

    protected void setBackgroundAngle(float angle){
        this.backgroundAngle = angle;
        repaint();
    }

    public float getBackgroundAngle(){
        return this.backgroundAngle;
    }

    protected void setBackgroundOffset(float offsetX, float offetY){
        this.backgroundOffsetX = offsetX;
        this.backgroundOffsetY = offsetY;
        repaint();
    }

    public T shadow(Color color, float size){
        this.shadowColor = color;
        this.shadowSize = size;
        repaint();
        return (T) this;
    }

    protected void setShadowOffset(float offsetX, float offsetY){
        this.shadowOffsetX = offsetX;
        this.shadowOffsetY = offsetY;
        repaint();
    }

    private void drawShadow(Graphics2D g2, Shape shape) {
        if (shadowSize <= 0) return;

        Graphics2D gShadow = (Graphics2D) g2.create();
        
        BoxUtils.setHighQuality(gShadow);
        gShadow.translate(shadowOffsetX, shadowOffsetY);

        double centerX = getWidth() / 2.0;
        double centerY = getHeight() / 2.0;
        gShadow.translate(centerX, centerY);
        gShadow.scale((shadowSize / getWidth()) + 1, (shadowSize / getHeight()) + 1);
        gShadow.translate(-centerX, -centerY);

        gShadow.setClip(shape);

        float change = shadowColor.getAlpha() / (shadowSize);

        // Dibujamos capas sucesivas para crear el efecto de suavizado (Blur)
        for (float i = shadowSize; i >= 1; i--) {
            // La opacidad disminuye conforme nos alejamos del centro
            float opacity_ = (float) (change * (i + 1));
            gShadow.setPaint(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), (int) MathUtils.clamp(opacity_, 0, 255)));
            
            // El grosor del trazo simula el desenfoque
            gShadow.setStroke(new BasicStroke(i));
            gShadow.draw(shape);
        }
        
        gShadow.dispose();
    }

    private void drawStrokes(Graphics2D g2, Shape shape, float x, float y, float width, float height) {
        if (strokeWidth <= 0) return;

        Graphics2D gStroke = (Graphics2D) g2.create();
        BoxUtils.setHighQuality(gStroke);
        gStroke.setPaint(StrokeColor);


        float cX = x + (width) / 2f;
        float cY = y + (height) / 2f;

        // El área de recorte sigue siendo la silueta original (shape)
        Area clipArea = new Area(new Rectangle2D.Float(x, y, width + 1f, height + 1f));

        // Restamos los cuadrantes que NO queremos ver
        if (!this.paintedStrokes[0]) clipArea.subtract(new Area(BoxUtils.createTriangle(x, y, x+width, y, cX, cY))); // Top
        if (!this.paintedStrokes[1]) clipArea.subtract(new Area(BoxUtils.createTriangle(x, y, x, y+height, cX, cY))); // Bottom
        if (!this.paintedStrokes[2]) clipArea.subtract(new Area(BoxUtils.createTriangle(x, y + height + 1, x + width + 1, y+height + 1, cX, cY))); // Left
        if (!this.paintedStrokes[3]) clipArea.subtract(new Area(BoxUtils.createTriangle(x+width + 1, y, x+width + 1, y+height + 1, cX, cY))); // Right

        gStroke.setClip(clipArea);
        gStroke.setStroke(new BasicStroke(strokeWidth));
        
        // Dibujamos el shape original
        gStroke.draw(shape);

        gStroke.dispose();
    }

    

    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g.create();
        BoxUtils.setHighQuality(g2);

        g2.translate(offsetX, offsetY);

        g2.setClip(null);

        if (scale != 1.0f || angle != 1.0f) {
            double pivotX = getWidth() * anchorX;
            double pivotY = getHeight() * anchorY;
            g2.translate(pivotX, pivotY);
            g2.scale(scale, scale);
            g2.rotate(Math.toRadians(this.angle));
            g2.translate(-pivotX, -pivotY);
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

            if(this.bgType){

                Paint background = new RadialGradientPaint(centerX, centerY, (float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)), fractions, colors);
                g2.setPaint(background);


            } else {

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
            }
            
        } else {
            g2.setColor(this.backgroundColors.get(0));
        }
        
        drawShadow(g2, shapePath);

        if (backgroundImage != null) {
            
            Graphics2D gImg = (Graphics2D) g2.create();
            BoxUtils.setHighQuality(gImg);


            gImg.setClip(shapePath);

            if(scaleBackground){
                gImg.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                gImg.drawImage(backgroundImage, 0, 0, this);
            }

        } else {
            g2.fill(shapePath);
        }
        
        drawStrokes(g2, shapePath, x, y, width, height);

        g2.dispose();
    }

    public T padding(int all){
        return padding(all, all, all, all);
    }

    public T padding(int top_bottom, int left_right){
        return padding(top_bottom, left_right, top_bottom, left_right);
    }

    public T padding(int top, int left, int bottom, int right){
        this.padding = new int[]{top, left, bottom, right};
        revalidate();
        repaint();
        return (T) this;
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

    public T margin(int all){
        return margin(all, all, all, all);
    }

    public T margin(int top_bottom, int left_right){
        return margin(top_bottom, left_right, top_bottom, left_right);
    }

    public T margin(int top, int left, int bottom, int right){
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        this.margin = new int[]{top, left, bottom, right};
        revalidate();
        repaint();
        return (T) this;
    }

    @Override
    protected void paintChildren(Graphics g){

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.translate(offsetX, offsetY);

        if(angle != 0.0f){
            double pivotX = getWidth() * anchorX;
            double pivotY = getHeight() * anchorY;
            g2.translate(pivotX, pivotY);
            g2.rotate(Math.toRadians(this.angle));
            g2.translate(-pivotX, -pivotY);
        }
        
        if (scale != 1.0f) {
            double pivotX = getWidth() / 2;
            double pivotY = getHeight() / 2;
            g2.translate(pivotX, pivotY);
            g2.scale(scale, scale);
            g2.translate(-pivotX, -pivotY);
        }

        if(!clipChildrens){
            g2.setClip(shapePath);
        }
        super.paintChildren(g2);

        g2.dispose();

    }

    public void animate(Transition transition, boolean cancel){

        if(cancel){
            for(int i = 0; i < timers.size(); i ++){
                if(timersTypes.get(i) == transition.getAnimationType()){
                    timers.get(i).stop(true);
                    timers.remove(i);
                    timersTypes.remove(i);
                }
            }
        }

        this.timers.add(transition);
        this.timersTypes.add(transition.getAnimationType());
    }

    public void cancelAnimations(AnimationType type){

        for(int i = 0; i < timers.size(); i ++){
            if(type == null || timersTypes.get(i) == type){
                timers.get(i).stop(true);
                timers.remove(i);
                timersTypes.remove(i);
            }
        }
        
    }

    public float getShadowSize(){
        return this.shadowSize;
    }

    public Color getShadowColor(){
        return this.shadowColor;
    }

    public Shape getShape(){
        return this.shapePath;
    }

    public float getShadowOffsetX(){
        return this.shadowOffsetX;
    }

    public float getShadowOffsetY(){
        return this.shadowOffsetY;
    }

    public T size(int width, int height){

        int w = width + getMargin().left + getMargin().right;
        int h = height + getMargin().top + getMargin().bottom;

        this.setPreferredSize(new Dimension(w, h));
        this.setMinimumSize(new Dimension(w, h));
        this.setMaximumSize(new Dimension(w, h));
        return (T) this;
    }
}
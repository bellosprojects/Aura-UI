package src.main.java.aura.layouts;

import java.awt.*;
import src.main.java.aura.core.Layout;
import src.main.java.aura.core.Box;

public class SRow extends Layout<SRow> {

    public SRow(){
        addMouseEvents();
        setLayout(null);
    }

    public SRow gap(int gap){
        this.gap = gap;
        revalidate();
        repaint();
        return this;
    }

    public SRow align(Alignment align){
        this.alignment = align;
        revalidate();
        return this;
    }


    public SRow padding(int all){
        this.setPadding(all, all, all, all);
        return this;
    }

    public SRow radius(float all){
        this.setRadius(all, all, all, all);
        return this;
    }

    public SRow margin(int all){
        this.setMargin(all, all, all, all);
        return this;
    }

    @Override
    public void doLayout() {
        Insets in = getInsets();
        Component[] children = getComponents();
        int totalWidth = getWidth() - in.left - in.right;
        int availableHeight = getHeight() - in.top - in.bottom;

        int fixedWidth = 0;
        float totalWeight = 0;
        int visibleCount = 0;

        for (Component c : children) {
            if (!c.isVisible()) continue;
            Box<?> box = (Box<?>) c;
            if (box.getWeight() > 0) {
                totalWeight += box.getWeight();
            } else {
                fixedWidth += c.getPreferredSize().width;
            }
            visibleCount++;
        }

        int gapSpace = (visibleCount > 1) ? (visibleCount - 1) * gap : 0;
        int remainingWidth = totalWidth - fixedWidth - gapSpace;

        int currentX = in.left;
        for (Component c : children) {
            if (!c.isVisible()) continue;
            Box<?> box = (Box<?>) c;
            Dimension d = c.getPreferredSize();
            
            int finalWidth = d.width;
            if (box.getWeight() > 0 && remainingWidth > 0) {
                finalWidth = (int) ((box.getWeight() / totalWeight) * remainingWidth);
            }

            int y = switch (alignment) {
                case CENTER -> in.top + (availableHeight - d.height) / 2;
                case BOTTOM -> in.top + (availableHeight - d.height);
                default -> in.top;
            };

            c.setBounds(currentX, y, finalWidth, d.height);
            currentX += finalWidth + gap;
        }
    }

    @Override
    public Dimension getPreferredSize(){

        Insets in = getInsets();
        int width = 0;
        int height = 0;
        int visibleChildren = 0;

        for(Component child : getComponents()){
            if(child.isVisible()){
                Dimension d = child.getPreferredSize();
                height = Math.max(height, d.height);
                width += d.width;
                visibleChildren ++;
            }
        }

        if(visibleChildren > 1){
            width += (visibleChildren - 1) * gap;
        }

        return new Dimension(
            width + in.left + in.right,
            height + in.top + in.bottom
        );
    }
}

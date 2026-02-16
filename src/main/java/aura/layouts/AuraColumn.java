package src.main.java.aura.layouts;

import java.awt.*;
import src.main.java.aura.core.Layout;
import src.main.java.aura.core.AuraBox;

public class AuraColumn extends Layout<AuraColumn> {

    public AuraColumn(){
        setLayout(null);
        addMouseEvents();
    }

    public AuraColumn gap(int gap){
        this.gap = gap;
        revalidate();
        repaint();
        return this;
    }

    public AuraColumn align(Alignment align){
        this.alignment = align;
        revalidate();
        return this;
    }

    public AuraColumn padding(int all){
        this.setPadding(all, all, all, all);
        revalidate();
        return this;
    }

    @Override
    public void doLayout() {
        Insets in = getInsets();
        Component[] children = getComponents();
        int totalHeight = getHeight() - in.top - in.bottom;
        int availableWidth = getWidth() - in.left - in.right;

        int fixedHeight = 0;
        float totalWeight = 0;
        int visibleCount = 0;

        for (Component c : children) {
            if (!c.isVisible()) continue;
            AuraBox<?> box = (AuraBox<?>) c;
            if (box.getWeight() > 0) {
                totalWeight += box.getWeight();
            } else {
                fixedHeight += c.getPreferredSize().height;
            }
            visibleCount++;
        }

        int gapSpace = (visibleCount > 1) ? (visibleCount - 1) * gap : 0;
        int remainingHeight = totalHeight - fixedHeight - gapSpace;

        int currentY = in.top;
        for (Component c : children) {
            if (!c.isVisible()) continue;
            AuraBox<?> box = (AuraBox<?>) c;
            Dimension d = c.getPreferredSize();
            
            int finalHeight = d.height;
            if (box.getWeight() > 0 && remainingHeight > 0) {
                finalHeight = (int) ((box.getWeight() / totalWeight) * remainingHeight);
            }

            int x = switch (alignment) {
                case CENTER -> in.left + (availableWidth - d.width) / 2;
                case RIGHT -> in.left + (availableWidth - d.width);
                default -> in.left;
            };

            c.setBounds(x, currentY, d.width, finalHeight);
            currentY += finalHeight + gap;
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
                width = Math.max(width, d.width);
                height += d.height;
                visibleChildren ++;
            }
        }

        if(visibleChildren > 1){
            height += (visibleChildren - 1) * gap;
        }

        return new Dimension(
            width + in.left + in.right,
            height + in.top + in.bottom
        );
    }

}

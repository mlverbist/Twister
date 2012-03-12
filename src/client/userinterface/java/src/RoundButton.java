import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RenderingHints;

public class RoundButton extends JButton {
    Shape shape;
    
    public RoundButton(String label) {
        super(label);
        setFocusPainted(false);
        setContentAreaFilled(false);}

    protected void paintComponent(Graphics g) { // Paint the round background and label.
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isRollover()){
            g2.setPaint(new GradientPaint(new Point(0, 10), Color.WHITE, new Point(0, getHeight()+30), new Color(119,133,255),true));}
        if(!getModel().isArmed()&&!getModel().isRollover()){
            g2.setPaint(new GradientPaint(new Point(0, 10), Color.WHITE, new Point(0, getHeight()+30), new Color(66,85,255),true));}
        g2.fillRoundRect(0, 0,getSize().width-1 ,getSize().height-1, 15, 15);
        super.paintComponent(g2);} // This call will paint the label and the focus rectangle.

    protected void paintBorder(Graphics g) {// Paint the border of the button using a simple stroke.
        g.setColor(new Color(150,150,150));
        if(!getModel().isArmed()){
            g.drawRoundRect(1, 1,getSize().width-2 ,getSize().height-2, 15, 15);}}

    public boolean contains(int x, int y) {// If the button has changed size, make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())){
            shape = new Ellipse2D.Float(0,0,getWidth(), getHeight());}
        return shape.contains(x, y);}}
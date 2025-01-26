import java.awt.Graphics;

public class BaseLimb {
    protected int x;
    protected int y;
    protected int length;
    protected Limb nextLimb;

    public BaseLimb(int x, int y, int length, Limb nextLimb) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.nextLimb = nextLimb;
    }

    public void draw(Graphics g) {
        // Draw the limb
        g.drawLine(x, y, x + length, y);
    }
}

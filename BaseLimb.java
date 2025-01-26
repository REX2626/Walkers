import java.awt.Graphics;

public class BaseLimb {
    protected int x;
    protected int y;
    protected int length;
    protected float angle;
    protected Limb nextLimb;

    public BaseLimb(int x, int y, int length, float angle, Limb nextLimb) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.angle = angle;
        this.nextLimb = nextLimb;
    }

    public void update() {
        // Gravity
        y += 1;

        // Update the limb
        if (nextLimb != null) {
            nextLimb.x = (int) (x + length * Math.sin(angle));
            nextLimb.y = (int) (y + length * Math.cos(angle));
            nextLimb.update();
        }
    }

    public void draw(Graphics g) {
        // Draw the limb
        g.drawLine(
            x,
            y,
            (int) (x + length * Math.sin(angle)),
            (int) (y + length * Math.cos(angle))
        );
    }
}

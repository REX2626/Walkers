import java.awt.Graphics;

public class BaseLimb {
    // (x, y) is the centre of the Limb

    protected int x;
    protected int y;
    protected int length;
    protected double angle;
    protected Limb nextLimb;

    public BaseLimb(int x, int y, int length, double angle, Limb nextLimb) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.angle = angle;
        this.nextLimb = nextLimb;
    }

    private int lx() {
        return (int) (x - length/2 * Math.sin(angle));
    }

    private int ly() {
        return (int) (y - length/2 * Math.cos(angle));
    }

    private int rx() {
        return (int) (x + length/2 * Math.sin(angle));
    }

    private int ry() {
        return (int) (y + length/2 * Math.cos(angle));
    }

    public void update() {
        // Gravity
        y += 1;

        // Check for collision with floor
        // If the limb is below the floor, set the touch part to the floor
        // If center is below floor, then set the center to the floor and set limb to flat
        // If one end is below floor, rotate limb so that the end is touching the floor
        if (y > Constants.HEIGHT-1) {
            y = Constants.HEIGHT-1;
            angle = (float) (Math.PI/2);
        } else if (ly() > Constants.HEIGHT-1) {
            int X;
            if (lx() < x) {
                X = (int) (x - Math.sqrt((length/2)*(length/2) - (Constants.HEIGHT-1 - y)*(Constants.HEIGHT-1 - y)));
            } else {
                X = (int) (x + Math.sqrt((length/2)*(length/2) - (Constants.HEIGHT-1 - y)*(Constants.HEIGHT-1 - y)));
            }
            double theta = 2 * Math.asin(Math.sqrt((X - lx())*(X - lx()) + (Constants.HEIGHT-1 - ly())*(Constants.HEIGHT-1 - ly())) / (2 * length));
            if (lx() < x) {
                angle -= theta;
            } else {
                angle += theta;
            }
            System.out.println("left");
            System.out.println("theta: " + Math.toDegrees(theta));
            System.out.println("X: " + X);
        } else if (ry() > Constants.HEIGHT-1) {
            int X;
            if (lx() < x) {
                System.out.println("lx");
                X = (int) (x + Math.sqrt((length/2)*(length/2) - (Constants.HEIGHT-1 - y)*(Constants.HEIGHT-1 - y)));
            } else {
                X = (int) (x - Math.sqrt((length/2)*(length/2) - (Constants.HEIGHT-1 - y)*(Constants.HEIGHT-1 - y)));
            }
            double theta = 2 * Math.asin(Math.sqrt((X - rx())*(X - rx()) + (Constants.HEIGHT-1 - ry())*(Constants.HEIGHT-1 - ry())) / (2 * length));
            if (rx() < x) {
                angle -= theta;
            } else {
                angle += theta;
            }
            System.out.println("right, " + rx());
            System.out.println("theta: " + Math.toDegrees(theta));
            System.out.println("X: " + X);
        }

        System.out.println("angle: " + Math.toDegrees(angle));

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
            lx(),
            ly(),
            rx(),
            ry()
        );

        // Draw the next limb
        if (nextLimb != null) {
            nextLimb.draw(g);
        }
    }
}

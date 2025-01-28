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

    protected int lx() {
        return (int) Math.round(x - length/2 * Math.sin(angle));
    }

    protected int ly() {
        return (int) Math.round(y - length/2 * Math.cos(angle));
    }

    protected int rx() {
        return (int) Math.round(x + length/2 * Math.sin(angle));
    }

    protected int ry() {
        return (int) Math.round(y + length/2 * Math.cos(angle));
    }

    protected void setLx(int lx) {
        x = (int) Math.round(lx + length/2 * Math.sin(angle));
    }

    protected void setLy(int ly) {
        y = (int) Math.round(ly + length/2 * Math.cos(angle));
    }

    protected void setRx(int rx) {
        x = (int) Math.round(rx - length/2 * Math.sin(angle));
    }

    protected void setRy(int ry) {
        y = (int) Math.round(ry - length/2 * Math.cos(angle));
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
            int left = lx();
            if (left < x) {
                angle -= theta;
            } else {
                angle += theta;
            }
            x += left - lx();
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
            int right = rx();
            if (right < x) {
                angle -= theta;
            } else {
                angle += theta;
            }
            x += right - rx();
            System.out.println("right, " + rx());
            System.out.println("theta: " + Math.toDegrees(theta));
            System.out.println("X: " + X);
        }

        System.out.println("angle: " + Math.toDegrees(angle));

        // Update the limb
        if (nextLimb != null) {
            nextLimb.update();
            int mx = (rx() + nextLimb.lx()) / 2;
            int my = (ry() + nextLimb.ly()) / 2;
            setRx(mx);
            setRy(my);
            nextLimb.setLx(mx);
            nextLimb.setLy(my);
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

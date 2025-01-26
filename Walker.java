import java.awt.Graphics;

public class Walker {
    private BaseLimb limb;

    public Walker(BaseLimb limb) {
        this.limb = limb;
    }

    public void update() {
        limb.update();
    }

    public void draw(Graphics g) {
        limb.draw(g);
    }
}

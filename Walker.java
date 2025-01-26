import java.awt.Graphics;

public class Walker {
    private BaseLimb limb;

    public Walker(BaseLimb limb) {
        this.limb = limb;
    }

    public void draw(Graphics g) {
        limb.draw(g);
    }
}

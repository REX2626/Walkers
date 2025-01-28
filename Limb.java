public class Limb extends BaseLimb {
    private Limb baseLimb;

    public Limb(int x, int y, int length, double angle, Limb nextLimb, Limb baseLimb) {
        super(x, y, length, angle, nextLimb);
        this.baseLimb = baseLimb;
    }
}

public class Limb extends BaseLimb {
    private Limb baseLimb;

    public Limb(int x, int y, int length, Limb nextLimb, Limb baseLimb) {
        super(x, y, length, nextLimb);
        this.baseLimb = baseLimb;
    }
}

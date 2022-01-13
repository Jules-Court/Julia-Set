package main;

public final class Complexe {

    protected double x;
    protected double y;

    public Complexe() {
        this.x = 0;
        this.y = 0;
    }

    public Complexe(double x) {
        this.x = x;
        this.y = 0;
    }

    public Complexe(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double mult(Complexe c) {
        return c.x * c.x - c.y * c.y;
    }

    public double abs() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public static void juliaCalcul(Complexe z, Complexe c) {
        double tmp = z.mult(z) + c.x;
        z.y = 2.0 * z.x * z.y + c.y;
        z.x = tmp;

    }

}

package exercise;

// BEGIN
public class Circle {

    private Point point;
    private int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getSquare() throws NegativeRadiusException {
        double square = Math.PI * this.radius * this.radius;
        if (radius < 0) {
            throw new NegativeRadiusException("");
        }
        return square;
    }
}
// END

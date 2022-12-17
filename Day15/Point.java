public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public boolean equals(Object obj) {
        Point Point = (Point) obj;
        return this.x == Point.x && this.y == Point.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
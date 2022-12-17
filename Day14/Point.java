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

    public boolean isAlign(Point p1, Point p2) {

        // System.out.print(this + " inside " + p1 + " and " + p2 + " : ");

        if (this.equals(p1) || this.equals(p2))
            return true;

        if (p1.x == p2.x) {
            if (this.x != p1.x)
                return false;
            if (this.y >= p1.y)
                return this.y <= p2.y;
            else
                return this.y >= p2.y;
        } else if (p1.y == p2.y) {
            if (this.y != p1.y)
                return false;
            if (this.x >= p1.x)
                return this.x <= p2.x;
            else
                return this.x >= p2.x;
        }

        return false;
    }

    public Point fall(int delta) {
        return new Point(this.x + delta, this.y + 1);
    }

    @Override
    public boolean equals(Object obj) {
        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
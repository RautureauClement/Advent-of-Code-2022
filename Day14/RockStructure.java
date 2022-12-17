import java.util.ArrayList;
import java.util.List;

public class RockStructure {
    private List<Point> path;

    public RockStructure() {
        this.path = new ArrayList<>();
    }

    public void addPath(Point point) {
        this.path.add(point);
    }

    public boolean contains(Point point) {
        for (int i = 0; i < this.path.size() - 1; i++) {
            boolean temp = point.isAlign(this.path.get(i), this.path.get(i + 1));
            // System.out.println(temp);
            if (temp)
                return true;
        }

        return false;
    }

    public List<Point> getPath() {
        return path;
    }

    public List<Point> getPathPoint() {
        List<Point> out = new ArrayList<>();

        for (int i = 0; i < this.path.size() - 1; i++) {
            Point from = this.path.get(i);
            Point to = this.path.get(i + 1);

            if (from.x == to.x)
                if (from.y <= to.y)
                    for (int j = from.y; j < to.y + 1; j++)
                        out.add(new Point(from.x, j));
                else
                    for (int j = to.y; j < from.y + 1; j++)
                        out.add(new Point(to.x, j));

            if (from.y == to.y)
                if (from.x <= to.x)
                    for (int j = from.x; j < to.x + 1; j++)
                        out.add(new Point(j, from.y));
                else
                    for (int j = to.x; j < from.x + 1; j++)
                        out.add(new Point(j, to.y));
        }

        return out;
    }

}
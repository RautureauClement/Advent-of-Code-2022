import java.util.ArrayList;
import java.util.List;

public class Cave {
    private Point sandSpawn;
    private List<Point> sands;
    private List<RockStructure> rocks;
    private int bottom = Integer.MIN_VALUE;
    private int top = 0;
    private int left = Integer.MAX_VALUE;
    private int right = Integer.MIN_VALUE;

    public Cave() {
        this.sandSpawn = new Point(500, 0);
        this.rocks = new ArrayList<>();
        this.sands = new ArrayList<>();
    }

    public void setBottom() {
        for (RockStructure rock : rocks)
            for (Point point : rock.getPath()) {
                this.bottom = Math.max(this.bottom, point.y);
                this.top = Math.min(this.top, point.y);
                this.right = Math.max(this.right, point.x);
                this.left = Math.min(this.left, point.x);
            }

    }

    public void addRocks(RockStructure rs) {
        this.rocks.add(rs);
    }

    public boolean newSandFall() {
        Point sand = new Point(this.sandSpawn);
        boolean[] delta = new boolean[3];

        while (true) {
            for (int i = 0; i < delta.length; i++) {
                Point temp = sand.fall(i - 1);

                delta[i] = temp.y > bottom + 1;

                delta[i] |= this.sands.contains(temp);

                for (RockStructure rock : rocks)
                    delta[i] |= rock.contains(temp);
            }

            if (!delta[1])
                sand = sand.fall(0);
            else if (!delta[0])
                sand = sand.fall(-1);
            else if (!delta[2])
                sand = sand.fall(1);
            else if (sand.equals(sandSpawn))
                return false;
            else
                break;
        }

        this.sands.add(sand);
        return true;
    }

    public int getFirtsSandFallInFloor() {
        for (int i = 0; i < this.sands.size(); i++) {
            if (sands.get(i).y > bottom)
                return i;
        }

        return 0;
    }

    public List<Point> getSands() {
        return sands;
    }

    @Override
    public String toString() {
        return this.toString(false, false) + "\n\n" + this.toString(true, false) + "\n\n" + this.toString(true, true);
    }

    public String toString(boolean sand, boolean allSand) {
        char[][] cs = new char[bottom - top + 2][right - left + 11];

        if (sand) {
            for (Point point : this.sands) {
                if (!allSand && point.y > bottom)
                    break;
                if (point.y - top > -1 && point.y - top < cs.length && point.x - left + 5 > -1
                        && point.x - left + 5 < cs[0].length)
                    cs[point.y - top][point.x - left + 5] = '.';
            }
        }

        for (RockStructure rock : this.rocks)
            for (Point point : rock.getPathPoint())
                cs[point.y - top][point.x - left + 5] = '#';

        cs[0 - top][500 - left + 5] = '+';

        String out = "";

        for (char[] cs2 : cs) {
            for (char c : cs2)
                out += c == 0 ? ' ' : c;
            out += "\n";
        }

        return out;
    }
}

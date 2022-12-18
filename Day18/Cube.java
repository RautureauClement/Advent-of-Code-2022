import java.util.ArrayList;
import java.util.List;

public class Cube {
    int x;
    int y;
    int z;

    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean adjacentTo(Cube cube) {
        return Math.abs(this.x - cube.x) + Math.abs(this.y - cube.y) + Math.abs(this.z - cube.z) == 1;
    }

    public List<Cube> neighbour() {
        List<Cube> neighbour = new ArrayList<>();

        neighbour.add(new Cube(x+1, y, z));
        neighbour.add(new Cube(x-1, y, z));
        neighbour.add(new Cube(x, y+1, z));
        neighbour.add(new Cube(x, y-1, z));
        neighbour.add(new Cube(x, y, z+1));
        neighbour.add(new Cube(x, y, z-1));

        return neighbour;
    }

    public boolean isAligne(Cube cube, char orientation) {
        switch (orientation) {
            case 'X' :
                return this.y == cube.y && this.z == cube.z && this.x < cube.x;
            case 'Y' :
                return this.x == cube.x && this.z == cube.z && this.y < cube.y;
            case 'Z' :
                return this.y == cube.y && this.x == cube.x && this.z < cube.z;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Cube cube = (Cube) obj;
        return this.x == cube.x && this.y == cube.y && this.z == cube.z;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}

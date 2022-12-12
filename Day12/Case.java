import java.util.ArrayList;
import java.util.List;

public class Case {
    private int nb;
    private int posX;
    private int posY;

    private int distance;

    public Case(int x, int y, int nb) {
        this.posX = x;
        this.posY = y;
        this.nb = nb;
        this.distance = -1;
    }

    public Case(int x, int y) {
        this(x, y, -1);
    }

    public List<Case> getNeighbour() {
        List<Case> out = new ArrayList<>();
        out.add(new Case(posX + 1, posY));
        out.add(new Case(posX - 1, posY));
        out.add(new Case(posX, posY + 1));
        out.add(new Case(posX, posY - 1));

        return out;
    }

    @Override
    public boolean equals(Object obj) {
        Case caze = (Case) obj;
        return this.posX == caze.posX && this.posY == caze.posY;
    }

    public int compareTo(Case caze) {
        return caze.nb - this.nb;
    }

    public int getNb() {
        return nb;
    }

    public int getDistance() {
        return distance;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "(" + posX + ", " + posY + "): nb = " + (char) (nb + 'a' - 1) + ", distance = " + distance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Case out = new Case(posX, posY, nb);
        return out;
    }
}

import java.util.HashSet;
import java.util.Set;

public class Sensor {
    private Point sensor;
    private Point beacon;
    int distance;

    public Sensor(int sx, int sy, int bx, int by) {
        this.sensor = new Point(sx, sy);
        this.beacon = new Point(bx, by);
        this.distance = Math.abs(sensor.x - beacon.x) + Math.abs(sensor.y - beacon.y);
    }

    // TODO return range for have juste two number

    public Set<Integer> inLine(int line) {
        Set<Integer> out = new HashSet<>();

        int distance = this.distance - Math.abs(sensor.y - line);

        for (int i = 0; i <= distance; i++) {
            out.add(this.sensor.x + i);
            out.add(this.sensor.x - i);
        }

        return out;
    }

    public int outRange(Point point) {
        int distanceY = Math.abs(sensor.y - point.y);

        if (this.distance < distanceY + Math.abs(sensor.x - point.x))
            return 0;

        else
            return this.sensor.x + this.distance - distanceY;
    }

    @Override
    public String toString() {
        return "sensor : " + sensor + ", beacon" + beacon;
    }
}

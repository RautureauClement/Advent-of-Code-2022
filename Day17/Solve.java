import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solve {

    static List<Point> deadPoint = new ArrayList<>();
    static int height = 0;

    static Point[] shape;
    static List<String> memory;
    static List<Integer> memoryHeight;
    static int precision = 0;
    static int indexFirst;

    static BigInteger indexFirstBig;
    static BigInteger loop;
    static BigInteger heightLoop;

    public static void main(String[] args) {

        FileReader fr;

        memory = new ArrayList<>();
        memoryHeight = new ArrayList<>();

        final Point[][] shapes = new Point[][] {
                { new Point(2, 3 + 1), new Point(3, 3 + 1), new Point(4, 3 + 1), new Point(5, 3 + 1) },
                { new Point(2, 4 + 1), new Point(3, 5 + 1), new Point(3, 3 + 1), new Point(4, 4 + 1) },
                { new Point(2, 3 + 1), new Point(4, 5 + 1), new Point(4, 3 + 1), new Point(4, 4 + 1),
                        new Point(3, 3 + 1) },
                { new Point(2, 3 + 1), new Point(2, 6 + 1), new Point(2, 5 + 1), new Point(2, 4 + 1) },
                { new Point(2, 3 + 1), new Point(3, 4 + 1), new Point(2, 4 + 1), new Point(3, 3 + 1) },
        };

        precision = 2 * shapes.length;

        int cptInput = 0;
        indexFirst = 0;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);
            char[] move = sc.nextLine().toCharArray();

            for (int i = 0; true; i++) {
                shape = cloneShape(shapes[(int) i % 5]);
                initShape();
                do {
                    push(move[cptInput++] == '>' ? 1 : -1);

                    if (cptInput >= move.length)
                        cptInput = 0;
                } while (fall());
                deadPoint.addAll(Arrays.asList(shape));
                height = Math.max(height, shape[1].y);

                String str = toMemory(cptInput);

                if (cycle()) {
                    indexFirst = memory.indexOf(str) - precision;
                    break;
                } else {
                    memory.add(str);
                    memoryHeight.add(height);
                }
            }

            int size = memory.size() - precision;

            // first loop index
            indexFirstBig = new BigInteger(indexFirst + "");
            // size of loop
            loop = new BigInteger(size - indexFirst + "");
            // heigth of loop
            heightLoop = new BigInteger(
                    memoryHeight.get(size - 1) - memoryHeight.get(indexFirst - 1) + "");

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(resultForNbShape("2022"));
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(resultForNbShape("1000000000000"));

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String resultForNbShape(String nb) {
        BigInteger input = new BigInteger(nb);
        BigInteger nbLoop = input.add(indexFirstBig.negate()).divide(loop);
        BigInteger restLoop = input.add(loop.multiply(nbLoop).negate()).add(indexFirstBig.negate());
        BigInteger addFinal = new BigInteger(memoryHeight.get(indexFirst + restLoop.intValue() - 1) + "");
        BigInteger result = heightLoop.multiply(nbLoop).add(addFinal);

        return "" + result;
    }

    public static void initShape() {
        for (Point point : shape) {
            point.y += height;
        }
    }

    public static void push(int delta) {
        Point[] temp = cloneShape(shape);

        for (Point point : temp) {
            point.x += delta;

            if (deadPoint.contains(point) || point.x < 0 || point.x > 6)
                return;
        }

        shape = temp;
    }

    public static boolean fall() {
        Point[] temp = cloneShape(shape);

        for (Point point : temp) {
            point.y--;

            if (deadPoint.contains(point) || point.y < 1)
                return false;
        }

        shape = temp;

        return true;
    }

    public static Point[] cloneShape(Point[] shape) {
        Point[] temp = new Point[shape.length];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Point(shape[i]);
        }

        return temp;
    }

    public static boolean cycle() {

        if (memory.size() < 2 * precision)
            return false;

        boolean out = true;

        List<String> temp = memory.subList(0, memory.size() - precision);
        for (int i = memory.size() - precision; i < memory.size(); i++) {
            out &= temp.contains(memory.get(i));
        }

        return out;
    }

    public static String toMemory(int i) {
        String out = "";

        for (Point point : shape) {
            out += point.x + ",";
        }

        out += i;

        return out;
    }
}

class Point {
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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solve {

    private static Map<Integer, List<String>> tailPassages;
    private static Point[] rope;

    public static void main(String[] args) {

        FileReader fr;

        tailPassages = new HashMap<>();
        tailPassages.put(2, new ArrayList<>());
        tailPassages.put(10, new ArrayList<>());

        rope = new Point[10];

        for (int i = 0; i < rope.length; i++) {
            rope[i] = new Point();
        }

        for (Integer integer : tailPassages.keySet()) {
            tailPassages.get(integer).add(new Point().toString());
        }

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                move(line[0].charAt(0), Integer.parseInt(line[1]), 0);
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(tailPassages.get(2).size());
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));

            pw.println(tailPassages.get(10).size());

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void move(char dir, int nb, int head) {
        if (nb < 1)
            return;

        if (head >= rope.length - 1) {
            move(dir, nb - 1, 0);
            return;
        }

        Point posHead = rope[head];
        Point posTail = rope[head + 1];

        Point newPosHead = new Point(posHead.x, posHead.y);

        if (head == 0) {
            switch (dir) {
                case 'R':
                    newPosHead.x += 1;
                    break;
                case 'L':
                    newPosHead.x -= 1;
                    break;
                case 'U':
                    newPosHead.y += 1;
                    break;
                case 'D':
                    newPosHead.y -= 1;
                    break;
            }
        }

        int deltaX = newPosHead.x - posTail.x;
        int deltaY = newPosHead.y - posTail.y;

        if (Math.abs(deltaX) > 1 && Math.abs(deltaY) > 1) {
            posTail.x = (posTail.x + newPosHead.x) / 2;
            posTail.y = (posTail.y + newPosHead.y) / 2;
        } else if (Math.abs(deltaX) > 1) {
            posTail.y = newPosHead.y;
            posTail.x = (posTail.x + newPosHead.x) / 2;
        } else if (Math.abs(deltaY) > 1) {
            posTail.x = newPosHead.x;
            posTail.y = (posTail.y + newPosHead.y) / 2;
        }

        for (Integer integer : tailPassages.keySet()) {
            if (head == integer - 2 && !tailPassages.get(integer).contains(posTail.toString()))
                tailPassages.get(integer).add(posTail.toString());
        }

        rope[head] = newPosHead;

        move(dir, nb, head + 1);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
}

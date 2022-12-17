import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);
            Cave cave = new Cave();

            while (sc.hasNextLine()) {
                RockStructure rocks = new RockStructure();
                for (String string : sc.nextLine().split(" -> ")) {
                    String[] coord = string.split(",");
                    rocks.addPath(new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])));
                }

                cave.addRocks(rocks);
            }

            cave.setBottom();

            while (cave.newSandFall())
                ;

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(cave.getFirtsSandFallInFloor());
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(cave.getSands().size() + 1);

            pw.close();

            pw = new PrintWriter(new FileOutputStream("map.txt"));
            pw.println(cave);

            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

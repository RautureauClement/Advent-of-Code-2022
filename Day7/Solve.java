import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            Dir slash = new Dir("home");
            slash.addDir(new Dir("/", slash));

            Dir currentDir = slash;

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");

                if (line[0].equals("$")) {
                    if (line[1].equals("cd")) {
                        currentDir = currentDir.getDir(line[2]);
                    }
                    continue;
                }

                if (line[0].equals("dir")) {
                    currentDir.addDir(new Dir(line[1], currentDir));
                    continue;
                }

                currentDir.addSize(Integer.parseInt(line[0]));
            }
            slash.updateSize();

            int cpt = 0;
            for (Dir dir : slash.getDir100k())
                cpt += dir.getSize();

            System.out.println(30000000 - (70000000 - slash.getSize()));

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(cpt);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(slash.part2(slash.getSize() - 40000000));

            fr.close();
            sc.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

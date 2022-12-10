import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solve {

    private static int x;
    private static int cycle;
    private static int sum;
    private static char[][] crt;

    public static void main(String[] args) {

        FileReader fr;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            x = 1;
            cycle = 0;
            sum = 0;
            crt = new char[6][40];

            while (sc.hasNextLine()) {
                addCycle();
                String line = sc.nextLine();

                if (line.equals("noop"))
                    continue;

                addCycle();
                x += Integer.parseInt(line.split(" ")[1]);
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(sum);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));

            for (char[] cs : crt)
                pw.println(new String(cs));

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addCycle() {
        cycle++;

        if ((cycle - 20) % 40 == 0)
            sum += x * cycle;

        if (x == (cycle - 1) % 40 || x - 1 == (cycle - 1) % 40 || x + 1 == (cycle - 1) % 40)
            crt[(cycle - 1) / 40][(cycle - 1) % 40] = 12;
        else
            crt[(cycle - 1) / 40][(cycle - 1) % 40] = ' ';
    }
}

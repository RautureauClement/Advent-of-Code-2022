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
            String line = sc.nextLine();

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(cptChardiff(line, 4));
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(cptChardiff(line, 14));

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int cptChardiff(String line, int nb) {
        for (int i = nb; i < line.length(); i++) {
            String presentChar = "";
            for (int j = i - nb; j < i; j++) {
                char c = line.charAt(j);
                if (presentChar.contains(c + ""))
                    break;
                else
                    presentChar += c;

                if (j == i - 1)
                    return i;
            }
        }

        return 0;
    }
}

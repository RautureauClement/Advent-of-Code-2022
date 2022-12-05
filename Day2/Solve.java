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
            int cptStrategy1 = 0;
            int cptStrategy2 = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                cptStrategy1 += battleStartegy1(line.charAt(0), line.charAt(2));
                cptStrategy2 += battleStartegy2(line.charAt(0), line.charAt(2));
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(cptStrategy1);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(cptStrategy2);

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int battleStartegy1(char advers, char player) {
        int sign = player - 'X' + 1;
        int win = 3;

        switch (advers) {
            case 'A':
                if (player == 'Y')
                    win = 6;
                if (player == 'Z')
                    win = 0;
                break;
            case 'B':
                if (player == 'Z')
                    win = 6;
                if (player == 'X')
                    win = 0;
                break;
            case 'C':
                if (player == 'X')
                    win = 6;
                if (player == 'Y')
                    win = 0;
                break;
        }

        return sign + win;
    }

    public static int battleStartegy2(char advers, char result) {

        int win = 3 * (result - 'X');
        int sign = 0;

        switch (result) {
            case 'X':
                if (advers == 'A')
                    sign = 3;
                if (advers == 'B')
                    sign = 1;
                if (advers == 'C')
                    sign = 2;
                break;
            case 'Y':
                sign = advers - 'A' + 1;
                break;
            case 'Z':
                if (advers == 'A')
                    sign = 2;
                if (advers == 'B')
                    sign = 3;
                if (advers == 'C')
                    sign = 1;
                break;
        }

        return sign + win;
    }
}

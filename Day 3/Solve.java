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
            int cpt = 0;
            int groupe = 0;

            while (sc.hasNextLine()) {
                String[] next3lines = new String[] { sc.nextLine(), sc.nextLine(), sc.nextLine() };
                for (String line : next3lines)
                    cpt += rucksack(line);
                groupe += groupe(next3lines);
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(cpt);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(groupe);

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int rucksack(String str) {
        String compartment1 = str.substring(0, str.length() / 2);
        String compartment2 = str.substring(str.length() / 2, str.length());

        for (char c : compartment1.toCharArray()) {
            if (compartment2.contains(c + "")) {
                return c - (Character.isUpperCase(c) ? 'A' - 26 : 'a') + 1;
            }
        }

        return 0;
    }

    public static int groupe(String[] bag) {

        for (char c : bag[0].toCharArray()) {
            if (bag[1].contains(c + "") && bag[2].contains(c + "")) {
                return c - (Character.isUpperCase(c) ? 'A' - 26 : 'a') + 1;
            }
        }
        return 0;
    }
}

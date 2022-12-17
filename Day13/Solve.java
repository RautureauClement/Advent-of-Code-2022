import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;
        List<Value> values = new ArrayList<>();
        Value two = stringToValue("[[2]]");
        Value six = stringToValue("[[6]]");
        values.add(two);
        values.add(six);

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);
            int cpt = 1;
            int out = 0;

            while (sc.hasNextLine()) {
                String line1 = sc.nextLine();
                if (line1.isEmpty())
                    continue;

                String line2 = sc.nextLine();

                Value v1 = stringToValue(line1);
                Value v2 = stringToValue(line2);

                out += (v1.compareTo(v2) < 0) ? cpt : 0;
                cpt++;

                values.add(v1);
                values.add(v2);
            }

            values.sort(null);

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(out);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println((values.indexOf(two) + 1) * (values.indexOf(six) + 1));

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Value stringToValue(String line) {
        Value out = new Value(new ArrayList<>());

        line = (String) line.subSequence(1, line.length() - 1);

        while (!line.isEmpty() && !line.isBlank()) {
            int comma = line.indexOf(',');
            int hook = line.indexOf('[');

            if (comma == 0) {
                line = line.substring(1);
                continue;
            }

            int substring = 0;

            if (comma == -1 && hook == -1) {
                out.addValue(new Value(Integer.parseInt(line)));
                break;
            } else if (hook != -1 && hook < comma || comma == -1) {
                substring = hook(line) + 1;
                out.addValue(stringToValue(line.substring(0, substring)));
            } else {
                substring = comma;
                out.addValue(new Value(Integer.parseInt(line.substring(0, substring))));
            }

            line = line.substring(substring);
        }

        return out;
    }

    public static int hook(String line) {
        int cpt = 0;
        char[] cs = line.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '[')
                cpt++;
            if (cs[i] == ']')
                cpt--;
            if (cpt == 0)
                return i;
        }

        return 0;
    }
}

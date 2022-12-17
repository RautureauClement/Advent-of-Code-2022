import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;
        final int LINE = 2000000;
        final int SIZE = 4000000;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            Set<Integer> points = new HashSet<>();
            Set<Integer> balisePresent = new HashSet<>();

            List<Sensor> sensors = new ArrayList<>();

            BigInteger outP2 = new BigInteger("4000000");

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().replaceAll("[^\\d\\s-]", "").replaceAll("  ", "").split(" ");
                Sensor sensor = new Sensor(
                        Integer.parseInt(line[0]),
                        Integer.parseInt(line[1]),
                        Integer.parseInt(line[2]),
                        Integer.parseInt(line[3]));

                points.addAll(sensor.inLine(LINE));
                sensors.add(sensor);
                if (Integer.parseInt(line[3]) == LINE)
                    balisePresent.add(Integer.parseInt(line[2]));
            }

            points.removeAll(balisePresent);

            y: for (int i = 0; i < SIZE + 1; i++) {
                x: for (int j = 0; j < SIZE + 1; j++) {
                    for (Sensor sensor : sensors) {
                        int out = sensor.outRange(new Point(j, i));
                        if (out > 0) {
                            j = out;
                            continue x;
                        }
                    }
                    outP2 = outP2.multiply(new BigInteger(j + "")).add(new BigInteger(i + ""));
                    break y;
                }
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(points.size());
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(outP2);

            fr.close();
            sc.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

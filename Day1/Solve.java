import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;
        int[] maxCaloriesX3 = new int[] { 0, 0, 0 };
        int maxCalories = 0;
        int sommeMaxCalories = 0;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);
            int cpt = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.isEmpty()) {
                    int minElfe = Math.min(maxCaloriesX3[0], Math.min(maxCaloriesX3[1], maxCaloriesX3[2]));
                    if (cpt > minElfe) {
                        for (int i = 0; i < maxCaloriesX3.length; i++) {
                            if (maxCaloriesX3[i] == minElfe) {
                                maxCaloriesX3[i] = cpt;
                                break;
                            }
                        }
                    }
                    cpt = 0;
                } else {
                    cpt += Integer.parseInt(line);
                }
            }

            for (int i : maxCaloriesX3) {
                sommeMaxCalories += i;
                maxCalories = Math.max(maxCalories, i);
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(maxCalories);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));

            pw.println(sommeMaxCalories);

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

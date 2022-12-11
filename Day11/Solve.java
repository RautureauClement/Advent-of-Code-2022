import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;
        List<Monkey> monkeys = new ArrayList<>();
        List<Monkey> monkeyPart2 = new ArrayList<>();
        Map<Integer, int[]> toMonkey = new HashMap<>();
        List<Integer> inspectes = new ArrayList<>();
        List<Integer> inspectesPart2 = new ArrayList<>();
        int modulo = 1;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                if (sc.nextLine().isEmpty())
                    sc.nextLine();

                Monkey monkey = new Monkey();
                Monkey monkey2 = new Monkey();
                for (String string : sc.nextLine().replaceAll("Starting items:", "").replaceAll(" ", "").split(",")) {
                    monkey.addObject(Long.parseLong(string));
                    monkey2.addObject(Long.parseLong(string));
                }
                String[] operate = sc.nextLine().split(" ");
                Long div = Long.parseLong(sc.nextLine().split(" ")[5]);

                monkey.setOperate(operate[6].charAt(0))
                        .setOperateBy(operate[7])
                        .setDivisileTest(div);

                monkey2.setOperate(operate[6].charAt(0))
                        .setOperateBy(operate[7])
                        .setDivisileTest(div);

                int[] afterTest = new int[] {
                        Integer.parseInt(sc.nextLine().split(" ")[9]),
                        Integer.parseInt(sc.nextLine().split(" ")[9])
                };

                toMonkey.put(toMonkey.size(), afterTest);

                monkeys.add(monkey);
                monkeyPart2.add(monkey2);
            }

            for (Integer integer : toMonkey.keySet()) {
                monkeys.get(integer)
                        .setIfTrue(monkeys.get(toMonkey.get(integer)[0]))
                        .setIfFalse(monkeys.get(toMonkey.get(integer)[1]));
                monkeyPart2.get(integer)
                        .setIfTrue(monkeyPart2.get(toMonkey.get(integer)[0]))
                        .setIfFalse(monkeyPart2.get(toMonkey.get(integer)[1]));
                modulo *= monkeys.get(integer).getDivisileTest();
            }

            for (int i = 0; i < 20; i++) {
                for (Monkey monkey : monkeys) {
                    monkey.round(modulo, 3);
                }
            }

            for (int i = 0; i < 10000; i++) {
                for (Monkey monkey : monkeyPart2) {
                    monkey.round(modulo, 1);
                }
            }

            for (Monkey monkey : monkeys) {
                inspectes.add(monkey.getInspected());
            }

            for (Monkey monkey : monkeyPart2) {
                inspectesPart2.add(monkey.getInspected());
            }

            inspectes.sort(null);
            Collections.reverse(inspectes);
            inspectesPart2.sort(null);
            Collections.reverse(inspectesPart2);

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(inspectes.get(0) * inspectes.get(1));
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(new BigInteger(inspectesPart2.get(0) + "").multiply(new BigInteger(inspectesPart2.get(1) + "")));

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

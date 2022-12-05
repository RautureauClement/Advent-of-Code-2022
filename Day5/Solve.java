import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            List<Stack<Character>> stacks1 = new ArrayList<>();
            List<Stack<Character>> stacks2 = new ArrayList<>();
            String out1 = "";
            String out2 = "";

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("1"))
                    break;

                line = line
                        .replaceAll("\\[", "")
                        .replaceAll("\\]", "")
                        .replaceAll("  ", " ");

                for (int i = 0; i < line.length(); i += 2) {
                    char c = line.charAt(i);
                    if (stacks1.size() <= i / 2) {
                        stacks1.add(new Stack<>());
                        stacks2.add(new Stack<>());
                    }

                    if (c != ' ') {
                        stacks1.get(i / 2).add(c);
                        stacks2.get(i / 2).add(c);
                    }
                }
            }

            for (int i = 0; i < stacks1.size(); i++) {
                Stack<Character> newStack = new Stack<>();
                Stack<Character> queue = stacks1.get(i);
                while (!queue.isEmpty()) {
                    newStack.push(queue.pop());
                }
                stacks1.set(i, newStack);
                stacks2.set(i, (Stack<Character>) newStack.clone());
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty())
                    continue;

                String[] words = line.split(" ");

                List<Character> chars = new ArrayList<>();

                for (int i = 0; i < Integer.parseInt(words[1]); i++) {
                    char c = stacks2.get(Integer.parseInt(words[3]) - 1).pop();
                    chars.add(c);
                }
                Collections.reverse(chars);
                stacks2.get(Integer.parseInt(words[5]) - 1).addAll(chars);

                for (int i = 0; i < Integer.parseInt(words[1]); i++) {
                    char c = stacks1.get(Integer.parseInt(words[3]) - 1).pop();
                    stacks1.get(Integer.parseInt(words[5]) - 1).add(c);
                }

            }

            for (Stack<Character> queue : stacks1) {
                out1 += queue.pop();
            }
            for (Stack<Character> queue : stacks2) {
                out2 += queue.pop();
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(out1);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(out2);

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

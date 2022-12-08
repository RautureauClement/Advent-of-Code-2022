import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            List<List<Tree>> vertical = new ArrayList<>();
            List<List<Tree>> horizontal = new ArrayList<>();

            char[] line;
            if (sc.hasNextLine()) {
                line = sc.nextLine().toCharArray();
                List<Tree> lineHorizontal = new ArrayList<>();
                for (char c : line) {
                    Tree tree = new Tree((short) (c - '0'));
                    lineHorizontal.add(tree);
                    List<Tree> lineVertical = new ArrayList<>();
                    lineVertical.add(tree);
                    vertical.add(lineVertical);
                }
                horizontal.add(lineHorizontal);
            }

            while (sc.hasNextLine()) {
                line = sc.nextLine().toCharArray();
                List<Tree> lineHorizontal = new ArrayList<>();
                for (int i = 0; i < line.length; i++) {
                    Tree tree = new Tree((short) (line[i] - '0'));
                    lineHorizontal.add(tree);
                    vertical.get(i).add(tree);
                }
                horizontal.add(lineHorizontal);
            }

            seeTree(horizontal);
            seeTree(vertical);

            scenicScore(horizontal);
            scenicScore(vertical);

            int treeVisible = 0;
            int bestScenicScore = 0;

            for (List<Tree> list : horizontal) {
                for (Tree tree : list) {
                    if (tree.isVisible())
                        treeVisible++;
                    bestScenicScore = Math.max(tree.getScenicScore(), bestScenicScore);
                }
            }

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            pw.println(treeVisible);
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));
            pw.println(bestScenicScore);

            fr.close();
            sc.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void seeTree(List<List<Tree>> list) {
        for (List<Tree> trees : list) {
            short cpt = -1;
            short cptReverse = -1;

            int size = trees.size();

            for (int i = 0; i < size; i++) {
                if (trees.get(i).getSize() > cpt) {
                    cpt = trees.get(i).getSize();
                    trees.get(i).setVisible();
                }

                if (trees.get(size - 1 - i).getSize() > cptReverse) {
                    cptReverse = trees.get(size - 1 - i).getSize();
                    trees.get(size - 1 - i).setVisible();
                }

                if (cpt == 9 && cptReverse == 9)
                    break;
            }
        }
    }

    public static void scenicScore(List<List<Tree>> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                Tree tree = list.get(i).get(j);

                int cpt = 0;

                for (int k = j + 1; k < list.get(i).size(); k++) {
                    cpt++;
                    if (list.get(i).get(k).getSize() >= tree.getSize())
                        break;
                }

                tree.multiplyingScenicScore(cpt);

                cpt = 0;

                for (int k = j - 1; k > -1; k--) {
                    cpt++;
                    if (list.get(i).get(k).getSize() >= tree.getSize())
                        break;
                }

                tree.multiplyingScenicScore(cpt);
            }
        }
    }
}

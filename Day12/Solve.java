import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solve {

    static List<Case> curentCase;
    static List<Case> cases;

    static List<Case> curentCaseP2;
    static List<Case> casesP2;

    public static void main(String[] args) {

        FileReader fr;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            cases = new ArrayList<>();
            casesP2 = new ArrayList<>();
            int cpt = 0;
            curentCase = new ArrayList<>();
            curentCaseP2 = new ArrayList<>();

            while (sc.hasNextLine()) {
                char[] line = sc.nextLine().toCharArray();

                for (int i = 0; i < line.length; i++) {
                    int nb;
                    if (line[i] == 'S')
                        nb = 0;
                    else if (line[i] == 'E')
                        nb = 27;
                    else
                        nb = line[i] - 'a' + 1;

                    cases.add(new Case(i, cpt, nb));
                }

                cpt++;
            }

            for (Case caze : cases) {
                casesP2.add((Case) caze.clone());
            }

            for (Case caze : casesP2) {
                if (caze.getNb() == 0) {
                    caze.setNb(1);
                    break;
                }
            }

            for (Case caze : cases) {
                if (caze.getNb() == 0) {
                    curentCase.add(caze);
                    break;
                }
            }

            for (Case caze : casesP2) {
                if (caze.getNb() == 27) {
                    curentCaseP2.add(caze);
                    break;
                }
            }

            search(0);
            searchP2(0);

            List<Integer> starting = new ArrayList<>();

            for (Case caze : casesP2) {
                if (caze.getNb() == 1)
                    starting.add(caze.getDistance());
            }

            starting.removeIf(n -> n == -1);
            starting.sort(null);

            PrintWriter pw = new PrintWriter(new FileOutputStream("output1.txt"));

            for (Case caze : cases) {
                if (caze.getNb() == 27) {
                    pw.println(caze.getDistance());
                    break;
                }
            }
            pw.close();

            pw = new PrintWriter(new FileOutputStream("output2.txt"));

            pw.println(starting.get(0));

            fr.close();
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int search(int steps) {
        if (curentCase.isEmpty())
            return steps;

        List<Case> newCurent = new ArrayList<>();
        for (Case curent : curentCase) {
            curent.setDistance(steps);
            for (Case neighbour : curent.getNeighbour()) {
                if (!cases.contains(neighbour))
                    continue;

                Case temp = cases.get(cases.indexOf(neighbour));
                if (curent.compareTo(temp) < 2 && temp.getDistance() == -1) {
                    newCurent.remove(temp);
                    newCurent.add(temp);
                }
            }
        }

        curentCase = newCurent;

        return search(steps + 1);
    }

    public static int searchP2(int steps) {
        if (curentCaseP2.isEmpty())
            return steps;

        List<Case> newCurent = new ArrayList<>();
        for (Case curent : curentCaseP2) {
            curent.setDistance(steps);
            for (Case neighbour : curent.getNeighbour()) {
                if (!casesP2.contains(neighbour))
                    continue;

                Case temp = casesP2.get(casesP2.indexOf(neighbour));
                if (curent.compareTo(temp) > -2 && temp.getDistance() == -1) {
                    newCurent.remove(temp);
                    newCurent.add(temp);
                }
            }
        }

        curentCaseP2 = newCurent;

        return searchP2(steps + 1);
    }
}

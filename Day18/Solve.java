import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) {

        FileReader fr;

        List<Cube> initCubes = new ArrayList<>();
        List<Cube> fullCubes = new ArrayList<>();
        int out1;
        int out2;

        try {
            fr = new FileReader("input.txt");
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                initCubes.add(new Cube(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
            }

            out1 = 6 * initCubes.size();

            for (int i = 0; i < initCubes.size()-1; i++) {
                for (int j = i+1; j < initCubes.size(); j++) {
                    if (initCubes.get(i).adjacentTo(initCubes.get(j))) 
                        out1 -= 2;
                }
            }

            fullCubes = fullObject(initCubes);

            out2 = 6 * fullCubes.size();

            for (int i = 0; i < fullCubes.size()-1; i++) {
                for (int j = i+1; j < fullCubes.size(); j++) {
                    if (fullCubes.get(i).adjacentTo(fullCubes.get(j))) 
                        out2 -= 2;
                }
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

    public static List<Cube> fullObject(List<Cube> cubes) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;
        int maxZ = 0;

        List<Cube> allCubes = new ArrayList<>();

        for (Cube cube : cubes) {
            minX = Math.min(minX, cube.x);
            minY = Math.min(minY, cube.y);
            minZ = Math.min(minZ, cube.z);

            maxX = Math.max(maxX, cube.x);
            maxY = Math.max(maxY, cube.y);
            maxZ = Math.max(maxZ, cube.z);
        }

        for (int x = minX-1; x <= maxX+1; x++) {
            for (int y = minY-1; y <= maxY+1; y++) {
                for (int z = minZ-1; z <= maxZ+1; z++) {
                    allCubes.add(new Cube(x, y, z));
                }
            }
        }

        outside(Arrays.asList(allCubes.get(0)), allCubes, cubes);

        return allCubes;
    }

    public static void outside(List<Cube> cubes, List<Cube> allCubes, List<Cube> object) {
        if (cubes.isEmpty())
            return;

        List<Cube> nextCubes = new ArrayList<>();
        List<Cube> removeCube = new ArrayList<>();

        for (Cube cube : cubes) {
            if (allCubes.contains(cube) && !object.contains(cube)) {
                nextCubes.removeAll(cube.neighbour());
                nextCubes.addAll(cube.neighbour());
                removeCube.add(cube);
            }
        }

        allCubes.removeAll(removeCube);
        outside(nextCubes, allCubes, object);
    }
}

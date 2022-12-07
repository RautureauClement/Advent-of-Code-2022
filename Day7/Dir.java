import java.util.ArrayList;
import java.util.List;

public class Dir {
    private String name;
    private List<Dir> sousDir;
    private int size;
    private Dir parent;

    public Dir(String name, Dir parent) {
        this.name = name;
        this.sousDir = new ArrayList<>();
        this.size = 0;
        this.parent = parent;
    }

    public Dir(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void addDir(Dir dir) {
        this.sousDir.add(dir);
    }

    public void addSize(int size) {
        this.size += size;
    }

    public int updateSize() {
        for (Dir dir : sousDir) {
            this.size += dir.updateSize();
        }

        return this.size;
    }

    public Dir getDir(String name) {
        if (name.equals(".."))
            return parent;

        for (Dir dir : sousDir) {
            if (dir.name.equals(name))
                return dir;
        }

        System.out.println("this: " + this);
        System.out.println("search:" + name);
        for (Dir dir : sousDir) {
            System.out.println(dir);
        }
        return null;
    }

    public int part2(int target) {
        int out = Integer.MAX_VALUE;

        for (Dir dir : sousDir) {
            int result = dir.part2(target);
            if (result > target)
                out = Math.min(out, result);
        }

        if (this.size > target && this.size < out)
            out = this.size;

        return out;
    }

    public List<Dir> getDir100k() {
        List<Dir> out = new ArrayList<>();
        for (Dir dir : sousDir) {
            out.addAll(dir.getDir100k());
        }

        if (this.size < 100000)
            out.add(this);

        return out;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

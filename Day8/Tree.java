public class Tree {
    private short size;
    private boolean visible;
    private int scenicScore;

    public Tree(short size) {
        this.size = size;
        this.visible = false;
        this.scenicScore = 1;
    }

    public short getSize() {
        return size;
    }

    public int getScenicScore() {
        return scenicScore;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible() {
        this.visible = true;
    }

    public void multiplyingScenicScore(int i) {
        this.scenicScore *= i;
    }
}

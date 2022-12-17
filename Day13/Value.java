import java.util.ArrayList;
import java.util.List;

public class Value implements Comparable {
    private Integer nb;
    private List<Value> list;

    public Value(int nb) {
        this.nb = nb;
    }

    public Value(List<Value> list) {
        this.list = list;
    }

    public Value addValue(Value v) {
        this.list.add(v);

        return this;
    }

    @Override
    public String toString() {
        if (nb == null)
            return list.toString();
        else
            return "" + nb;
    }

    public int compareTo(Object obj) {
        Value value = (Value) obj;

        if (this.nb != null && value.nb != null) {
            return this.nb - value.nb;

        } else if (this.list != null && value.list != null) {

            int out = -1;
            for (int i = 0; i < this.list.size(); i++) {
                if (i > value.list.size() - 1) {
                    return 1;
                }
                out = this.list.get(i).compareTo(value.list.get(i));
                if (out != 0)
                    return out;
            }

            return this.list.size() == value.list.size() ? 0 : -1;
        } else if (this.nb != null) {
            return new Value(new ArrayList<>()).addValue(new Value(this.nb)).compareTo(value);
        } else {
            return this.compareTo(new Value(new ArrayList<>()).addValue(new Value(value.nb)));
        }
    }
}

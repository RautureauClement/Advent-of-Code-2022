import java.util.ArrayList;
import java.util.List;

public class Monkey {
    private List<Long> objects;
    private char operate;
    private String operateBy;
    private Long divisileTest;
    private Monkey ifTrue;
    private Monkey ifFalse;
    private int inspected;

    public Monkey() {
        this.objects = new ArrayList<>();
        this.inspected = 0;
    }

    public void round(int mod, int div) {
        for (Long object : this.objects) {
            object = operate(object);
            object /= div;
            object %= mod;
            if (this.isDivisibleBy(object))
                ifTrue.addObject(object);
            else
                ifFalse.addObject(object);
        }
        objects.clear();
    }

    public boolean isDivisibleBy(Long object) {
        return object % this.divisileTest == 0;
    }

    public Long operate(Long object) {
        this.inspected++;
        Long by;
        if (this.operateBy.equals("old"))
            by = object;
        else
            by = Long.parseLong(operateBy);

        switch (this.operate) {
            case '+':
                return object + by;
            case '*':
                return object * by;
        }

        return object;
    }

    public Monkey addObject(Long i) {
        this.objects.add(i);
        return this;
    }

    public Monkey addObject(List<Long> list) {
        this.objects.addAll(list);
        return this;
    }

    public Monkey setOperate(char operate) {
        this.operate = operate;
        return this;
    }

    public Monkey setOperateBy(String operateBy) {
        this.operateBy = operateBy;
        return this;
    }

    public Monkey setDivisileTest(Long divisileTest) {
        this.divisileTest = divisileTest;
        return this;
    }

    public Monkey setIfFalse(Monkey ifFalse) {
        this.ifFalse = ifFalse;
        return this;
    }

    public Monkey setIfTrue(Monkey ifTrue) {
        this.ifTrue = ifTrue;
        return this;
    }

    public int getInspected() {
        return inspected;
    }

    public Long getDivisileTest() {
        return divisileTest;
    }
}

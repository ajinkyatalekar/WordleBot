import java.util.ArrayList;

public class Letter {
    public String letter;
    public ArrayList<Integer> indexes;

    public Letter(String letter) {
        this.letter = letter;
        this.indexes = new ArrayList<>();
    }

    public void addIndex(int i) {
        if (!indexes.contains(i)) {
            indexes.add(i);
        }
    }
}

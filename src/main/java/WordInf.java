import java.util.ArrayList;
import java.util.List;

public class WordInf {
    private String word;
    private int wordFreq;
    private List<PosVec> pos = new ArrayList<>();
    private String revWord;

    public WordInf(String word, int wordFreq) {
        this.word = word;
        this.wordFreq = wordFreq;
    }

    public void setWordFreq(int wordFreq) {
        this.wordFreq = wordFreq;
    }

    public int getWordFreq() {
        return wordFreq;
    }

    public WordInf(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != WordInf.class) {
            return false;
        }
        WordInf w = (WordInf) obj;
        StringBuilder sb = new StringBuilder(w.getWord());
        sb.reverse();
        return this.getWord().equals(w.getWord()) ||
                this.getWord().equals(sb.toString());
    }

    public List<PosVec> getPos() {
        return pos;
    }
}

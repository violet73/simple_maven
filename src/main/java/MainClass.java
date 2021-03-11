import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public static StringBuilder scanIn() {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            String s = in.nextLine();
            sb.append(s);
            sb.append("\n");
        }
        return sb;
    }

    public static List<Integer> getRange(StringBuilder sb) {
        List<Integer> rowRange = new ArrayList<>();
        rowRange.add(-1);
        Pattern p = Pattern.compile("\n");
        Matcher m = p.matcher(sb);
        while (m.find()) {
            rowRange.add(m.start());
        }
        return rowRange;
    }

    public static int getRow(List<Integer> li, int start) {
        int i = 1;
        int rnum = 1;
        while (li.get(i) < start) {
            rnum++;
            i++;
        }
        return rnum;
    }

    public static PosVec addPos(int rnum, int c) {
        PosVec pv = new PosVec();
        pv.setRi(rnum);
        pv.setCi(c);
        return pv;
    }

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00%");
        List<WordInf> ls = new ArrayList<>();
        StringBuilder sb = scanIn();
        List<Integer> li = getRange(sb);
        Pattern r = Pattern.compile("([a-zA-Z]+(-[a-zA-Z]+)?(-?-(\\n)+)?)+");
        Matcher m = r.matcher(sb);
        int sum = 0;
        int rnum;
        while (m.find()) {
            sum++;
            String s = sb.substring(m.start(), m.end());
            rnum = getRow(li, m.start());
            int c = m.start() - li.get(rnum - 1);
            s = s.replaceAll("-(\\n)+", "");
            s = s.toLowerCase();
            WordInf w = new WordInf(s);
            if (!(ls.contains(w))) {
                w = new WordInf(s, 1);
                w.getPos().add(addPos(rnum, c));
                StringBuilder st = new StringBuilder(s);
                st.reverse();
                ls.add(w);
                if (w.getWord().compareTo(st.toString()) > 0) {
                    w.setWord(st.toString());
                }
            } else {
                w = ls.get(ls.indexOf(w));
                w.setWordFreq(w.getWordFreq() + 1);
                w.getPos().add(addPos(rnum, c));
            }
        }
        ls.sort(Comparator.comparing(WordInf::getWord));
        for (WordInf w : ls) {
            System.out.println(w.getWord() + " " + w.getWordFreq() + " " +
                    df.format(w.getWordFreq() / (double) sum));
            for (PosVec pv : w.getPos()) {
                System.out.println("\t(" + pv.getRi() + ", " +
                        pv.getCi() + ")");
            }
        }
    }
}

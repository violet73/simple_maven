import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PreTest {

    /**
     * Unit test for simple App.
     */

    @Test
    public void shouldPassBasicCase() {
        final InputStream oldIn = System.in;
        final PrintStream oldOut = System.out;

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "ni" + System.lineSeparator() + "In";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        MainClass.main(null);

        assertEquals("in 2 100.00%" + System.lineSeparator() +
                "\t(1, 1)" + System.lineSeparator() +
                "\t(2, 1)" + System.lineSeparator(), outContent.toString());

        System.setIn(oldIn);
        System.setOut(oldOut);
    }

    @Test
    public void shouldPassMiddleCase() {
        final InputStream oldIn = System.in;
        final PrintStream oldOut = System.out;

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String input = "ni" + System.lineSeparator() + "put" + System.lineSeparator() +
                "In" + System.lineSeparator() + "Put" + System.lineSeparator() + "TUP";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        MainClass.main(null);

        assertEquals("in 2 40.00%" + System.lineSeparator() +
                "\t(1, 1)" + System.lineSeparator() +
                "\t(3, 1)" + System.lineSeparator() +
                "put 3 60.00%" + System.lineSeparator() +
                "\t(2, 1)" + System.lineSeparator() +
                "\t(4, 1)" + System.lineSeparator() +
                "\t(5, 1)" + System.lineSeparator(), outContent.toString());

        System.setIn(oldIn);
        System.setOut(oldOut);
    }

    @Test
    public void testWordInfEquals() {
        WordInf wordInf = new WordInf("test");
        assertNotEquals(wordInf, new PosVec());
    }
}

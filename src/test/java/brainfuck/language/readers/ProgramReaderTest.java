package brainfuck.language.readers;

import brainfuck.language.enumerations.Keywords;
import brainfuck.language.function.Function;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author jamatofu on 08/01/17.
 */
public class ProgramReaderTest {
    ProgramReader programReader;

    @Before
    public void setUp() {
        String program = "INCR\n" +
                "+ \n" +
                "\n" +
                "fonc\n" +
                "proc\n";
        Map<String, Function> functionMap = new HashMap<>();
        List<Keywords> keywordsList = new ArrayList<Keywords>();
        keywordsList.add(Keywords.INCR);

        functionMap.put("fonc", new Function(keywordsList, true, "fonc"));
        functionMap.put("proc", new Function(keywordsList, false, "proc"));

        this.programReader = new ProgramReader(program, functionMap);
    }

    @Test
    public void createBothMap() throws Exception {
        programReader.createBothMap();

        List<Keywords> keywordsList = new ArrayList<Keywords>();
        keywordsList.add(Keywords.INCR);

        Function function1 = new Function(keywordsList, true, "fonc");
        Function function2 = new Function(keywordsList, false, "proc");

        Map<Integer, Keywords> keywordsMap = programReader.getKeywordsToInterpreter();
        Map<Integer, Function> functionMap = programReader.getFunctionToInterpreter();

        assertEquals(Keywords.INCR, keywordsMap.get(0));
        assertEquals(Keywords.INCR, keywordsMap.get(1));

        assertEquals(function1.getCode(), functionMap.get(2).getCode());
        assertEquals(function1.getFunctionName(), functionMap.get(2).getFunctionName());
        assertEquals(function1.getParametre(), functionMap.get(2).getParametre());

        assertEquals(function2.getCode(), functionMap.get(3).getCode());
        assertEquals(function2.getParametre(), functionMap.get(3).getParametre());
        assertEquals(function2.getFunctionName(), functionMap.get(3).getFunctionName());
    }

}
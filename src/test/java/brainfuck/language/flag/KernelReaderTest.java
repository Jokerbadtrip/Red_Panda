package brainfuck.language.flag;

import brainfuck.language.exceptions.FilePathNotFoundException;
import brainfuck.language.exceptions.IncompatibleFlagsException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author jamatofu on 29/12/16.
 */

public class KernelReaderTest {
    KernelReader kernelReader;
    String path;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        path = temporaryFolder.getRoot().getAbsolutePath() + "/programme.bf";
        File programme = new File(path);
        try {
            programme.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void identifyFlag() throws Exception {
        List<String> flagList = new ArrayList<>();
        flagList.add("-p");
        flagList.add("-i");
        flagList.add("-o");

        this.kernelReader = new KernelReader(flagList);
        this.kernelReader.identifyFlag();

        Map<Flags, Boolean> result = new EnumMap(Flags.class);
        for(Flags flags : Flags.values()) {
            result.put(flags, false);
        }

        result.put(Flags.FILE_TO_READ, true);
        result.put(Flags.IN, true);
        result.put(Flags.OUT, true);

        assertEquals(result, this.kernelReader.getFlagMap());
    }

    @Test(expected = InvalidPathException.class)
    public void identifyFilePathForSpecificFlag() throws FilePathNotFoundException {
        List<String> flagList = new ArrayList<>();
        flagList.add("-p");
        flagList.add(path);
        flagList.add("-i");
        flagList.add(path);
        flagList.add("-o");
        flagList.add(path);

        this.kernelReader = new KernelReader(flagList);
        this.kernelReader.identifyFilePathForSpecificFlag();

        Map<Flags, String> result = new EnumMap(Flags.class);
        result.put(Flags.FILE_TO_READ, path);
        result.put(Flags.IN, path);
        result.put(Flags.OUT, path);

        assertEquals(result, this.kernelReader.getFilePathMap());

        flagList.remove(1);
        flagList.add(1, "aie");

        this.kernelReader = new KernelReader(flagList);
        this.kernelReader.identifyFilePathForSpecificFlag();
        result.put(Flags.FILE_TO_READ, path);
    }

    @Test(expected = FilePathNotFoundException.class)
    public void identifyFilePathForSpecificFlag_FilePathNotFoundException() throws FilePathNotFoundException {
        List<String> flagList = new ArrayList<>();
        flagList.add("-p");

        this.kernelReader = new KernelReader(flagList);
        this.kernelReader.identifyFilePathForSpecificFlag();
    }


    @Test
    public void goodFlag() {
        File resourcesDirectory = new File(temporaryFolder.getRoot().getAbsolutePath() + "prog.bf");
        resourcesDirectory.getParentFile().mkdirs();
        try {
            resourcesDirectory.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> flagList = new ArrayList<>();
        flagList.add("-p");
        flagList.add(resourcesDirectory.getAbsolutePath());
        flagList.add("-i");
        flagList.add(resourcesDirectory.getAbsolutePath());
        flagList.add("-o");
        flagList.add(resourcesDirectory.getAbsolutePath());

        this.kernelReader = new KernelReader(flagList);
        this.kernelReader.identifyFlag();
        this.kernelReader.identifyFilePathForSpecificFlag();

        try {
            this.kernelReader.goodFlag();
        } catch (Exception ex) {
            assertSame(FilePathNotFoundException.class, ex);
        }
    }

    @Test
    public void verifyIfAllFlagsAreValid() {
        List<String> flagList = new ArrayList<>();
        flagList.add("-p");
        flagList.add("-i");
        flagList.add("-o");

        this.kernelReader = new KernelReader(flagList);
        try {
            this.kernelReader.verifyIfAllFlagsAreValid();
        } catch (Exception ex) {
            fail();
        }

        flagList.add("azeazra");
        this.kernelReader = new KernelReader(flagList);

        try {
            this.kernelReader.verifyIfAllFlagsAreValid();
            fail();
        } catch (Exception ex) {
            assertSame(IncompatibleFlagsException.class, ex.getClass());
        }
    }

    @Test
    public void getFlag() {
        List<String> flagList = new ArrayList<>();
        flagList.add("-p");
        flagList.add("-i");
        flagList.add("-o");
        this.kernelReader = new KernelReader(flagList);
        this.kernelReader.identifyFlag();
        assertTrue(kernelReader.getFlag(Flags.FILE_TO_READ));
        assertTrue(kernelReader.getFlag(Flags.IN));
        assertTrue(kernelReader.getFlag(Flags.OUT));
    }

}
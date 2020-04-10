package myClasses;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;

class FileSplitterTest {

    String firstPart = "We had misunderstood. We were fools.\n"
            +"But, could anyone mock us for misunderstanding?\n"
            +"A drama that had its final episode determined wouldnt end until its final episode.\n"
            +"A movie that had a preview for its final instalment wouldnt end until its final instalment.\n"
            +"Everyone should have been living while believing that. They should have been taught as much.\n"
            +"I too had thought that.\n"
            +"I had had believed that a novel wouldnt end until its last page.\n"
            +"Perhaps she would laugh, saying that Id read too many novels.";
    String secondPart = "Even if I was laughed at, I didnt mind.\n"
            +"Even though I had wanted to read it till the very end. Even though I had meant to read it.\n"
            +"Her story had come to an end with the remaining pages still blank.\n"
            +"With all the build-up, foreshadowing, and red herrings neglected.\n"
            +"Id never be able to find out a single thing.\n"
            +"The result of the mischief with the rope she was setting up too.\n"
            +"What she really thought of me too.\n"
            +"Id never be able to find out.";

    @Test
    public void testN1() throws IOException {
        Main.main("split -d -n 2 -o story texts/end.txt".split(" +"));
        String path = FileSplitter.pathName;
        try {
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "story1"))), firstPart);
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "story2"))), secondPart);
            FileUtils.deleteDirectory(new File(path));
        } catch (AssertionError e) {
            FileUtils.deleteDirectory(new File(path));
            throw new AssertionError();
        }
    }

    @Test
    public void testN2() throws IOException {
        Main.main("split -d -l 8 -o story texts/end.txt".split(" +"));
        String path = FileSplitter.pathName;
        try {
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "story1"))), firstPart);
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "story2"))), secondPart);
            FileUtils.deleteDirectory(new File(path));
        } catch (AssertionError e) {
            FileUtils.deleteDirectory(new File(path));
            throw new AssertionError();
        }
    }

    @Test
    public void testN3() throws IOException {
        Main.main("split -l 8 -o story texts/end.txt".split(" +"));
        String path = FileSplitter.pathName;
        try {
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "storyaa"))), firstPart);
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "storyab"))), secondPart);
            FileUtils.deleteDirectory(new File(path));
        } catch (AssertionError e) {
            FileUtils.deleteDirectory(new File(path));
            throw new AssertionError();
        }
    }

    @Test
    public void testN4() throws IOException {
        Main.main("split -d -c 512 texts/end.txt".split(" +"));
        String path = FileSplitter.pathName;
        try {
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "x1"))), firstPart);
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "x2"))), secondPart);
            FileUtils.deleteDirectory(new File(path));
        } catch (AssertionError e) {
            FileUtils.deleteDirectory(new File(path));
            throw new AssertionError();
        }
    }

    @Test
    public void testN5() throws IOException {
        Main.main("split -o - -d -n 2 texts/end.txt".split(" +"));
        String path = FileSplitter.pathName;
        try {
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "end1"))), firstPart);
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "end2"))), secondPart);
            FileUtils.deleteDirectory(new File(path));
        } catch (AssertionError e) {
            FileUtils.deleteDirectory(new File(path));
            throw new AssertionError();
        }
    }

    @Test
    public void testN6() throws IOException {
        Main.main("split -d -n 3 -o - texts/gate.txt".split(" +"));
        String path = FileSplitter.pathName;
        try {
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "gate1"))), "e");
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "gate2"))), "n");
            assertEquals(joinToString(Files.readAllLines(Paths.get(path + "//" + "gate3"))), "d");
            FileUtils.deleteDirectory(new File(path));
        } catch (AssertionError e) {
            FileUtils.deleteDirectory(new File(path));
            throw new AssertionError();
        }
    }

    public String joinToString(List<String> text) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Object i: text) {
            joiner.add((String) i);
        }
        return joiner.toString();
    }

}
package myClasses;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class FileSplitterTest {

    String firstPart = "We had misunderstood. We were fools.\n"
            +"But, could anyone mock us for misunderstanding?\n"
            +"A drama that had its final episode determined wouldnt end until its final episode.\n"
            +"A movie that had a preview for its final instalment wouldnt end until its final instalment.\n"
            +"Everyone should have been living while believing that. They should have been taught as much.\n"
            +"I too had thought that.\n"
            +"I had had believed that a novel wouldnt end until its last page.\n"
            +"Perhaps she would laugh, saying that Id read too many novels.\n";
    String secondPart = "Even if I was laughed at, I didnt mind.\n"
            +"Even though I had wanted to read it till the very end. Even though I had meant to read it.\n"
            +"Her story had come to an end with the remaining pages still blank.\n"
            +"With all the build-up, foreshadowing, and red herrings neglected.\n"
            +"Id never be able to find out a single thing.\n"
            +"The result of the mischief with the rope she was setting up too.\n"
            +"What she really thought of me too.\n"
            +"Id never be able to find out.\n";

    @Test
    public void testN1() throws IOException {
        Main.main("split -d -n 2 -o story texts/end.txt".split(" +"));
        assertEquals(new String(Files.readAllBytes(Paths.get("story1"))), firstPart);
        assertEquals(new String(Files.readAllBytes(Paths.get("story2"))), secondPart);
        new File("story1").delete();
        new File("story2").delete();
    }

    @Test
    public void testN2() throws IOException {
        Main.main("split -d -l 8 -o story texts/end.txt".split(" +"));
        assertEquals(new String(Files.readAllBytes(Paths.get("story1"))), firstPart);
        assertEquals(new String(Files.readAllBytes(Paths.get("story2"))), secondPart);
        new File("story1").delete();
        new File("story2").delete();
    }

    @Test
    public void testN3() throws IOException {
        Main.main("split -l 8 -o story texts/end.txt".split(" +"));
        assertEquals(new String(Files.readAllBytes(Paths.get("storyaa"))), firstPart);
        assertEquals(new String(Files.readAllBytes(Paths.get("storyab"))), secondPart);
        new File("storyaa").delete();
        new File("storyab").delete();
    }

    String firstPart2 = "We had misunderstood. We were fools.\r"
            +"But, could anyone mock us for misunderstanding?\r"
            +"A drama that had its final episode determined wouldnt end until its final episode.\r"
            +"A movie that had a preview for its final instalment wouldnt end until its final instalment.\r"
            +"Everyone should have been living while believing that. They should have been taught as much.\r"
            +"I too had thought that.\r"
            +"I had had believed that a novel wouldnt end until its last page.\r"
            +"Perhaps she would laugh, saying that Id read too many novels.\r";
    String secondPart2 = "Even if I was laughed at, I didnt mind.\r"
            +"Even though I had wanted to read it till the very end. Even though I had meant to read it.\r"
            +"Her story had come to an end with the remaining pages still blank.\r"
            +"With all the build-up, foreshadowing, and red herrings neglected.\r"
            +"Id never be able to find out a single thing.\r"
            +"The result of the mischief with the rope she was setting up too.\r"
            +"What she really thought of me too.\r"
            +"Id never be able to find out.\r";

    @Test
    public void testN4() throws IOException {
        Main.main("split -d -c 511 texts/end.txt".split(" +"));
        assertEquals(new String(Files.readAllBytes(Paths.get("x1"))).replace("\n", ""), firstPart2);
        assertEquals(new String(Files.readAllBytes(Paths.get("x2"))).replace("\n", ""), secondPart2);
        new File("x1").delete();
        new File("x2").delete();
    }

    @Test
    public void testN5() throws IOException {
        Main.main("split -o - -d -n 2 texts/end.txt".split(" +"));
        assertEquals(new String(Files.readAllBytes(Paths.get("end1"))), firstPart);
        assertEquals(new String(Files.readAllBytes(Paths.get("end2"))), secondPart);
        new File("end1").delete();
        new File("end2").delete();
    }

}
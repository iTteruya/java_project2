package myClasses;

import java.io.*;
import java.util.ArrayList;


import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;


public class FileSplitter {
    @Argument private ArrayList<String> commandLine = new ArrayList<>();
    @Option(name = "-o") private String outputName = "x";
    @Option(name = "-d") private boolean numbering;
    @Option(name = "-l", forbids = {"-c", "-n"}) private int fileSizeInLines = 0;
    @Option(name = "-c", forbids = {"-l", "-n"}) private int fileSizeInChar = 0;
    @Option(name = "-n", forbids = {"-l", "-c"}) private int fileSizeInFileCount = 0;
    private BufferedReader br;
    private BufferedWriter bw;
    private String curFileName = outputName;
    private boolean createNewFile;
    private int curOutputFileNumber, availableCapacity;

    private void createFile(boolean sizeInLines, int capacity) throws IOException {
        availableCapacity = capacity;
        curOutputFileNumber = 1;
        curFileName = (numbering) ? outputName + curOutputFileNumber : (newNameWithChar());
        bw = new BufferedWriter(new FileWriter(curFileName));

        if (!sizeInLines) {
            int curChar;
            while ((curChar = br.read()) != -1) {
                write((char) curChar + "", capacity);
            }
        } else {
            String line;
            while ((line = br.readLine()) != null) {
                write(line + "\n", capacity);
            }
        }
        bw.close();
    }

    private void write(String text, int capacity) throws IOException {
        if (createNewFile) {
            bw = new BufferedWriter(new FileWriter(curFileName));
            createNewFile = false;
        }
        bw.write(text);
        availableCapacity--;
        if (availableCapacity == 0) {
            bw.close();
            curOutputFileNumber++;
            curFileName = (numbering) ? outputName + curOutputFileNumber: (newNameWithChar());
            createNewFile = true;
            availableCapacity = capacity;
        }
    }

    private String newNameWithChar() {
        String name = outputName;
        StringBuilder sb = new StringBuilder(outputName.length() + 2);
        for (char ch: name.toCharArray()) {
            sb.append(ch);
        }
        char firstLetter = (char) ('a' + ((curOutputFileNumber - 1) / 26));
        char secondLetter = (char) ('a' + ((curOutputFileNumber - 1) % 26));
        sb.append(firstLetter);
        sb.append(secondLetter);
        return sb.toString();
    }

    public void split(String[] cmd) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(cmd);
            if (commandLine.size() != 2 || !commandLine.get(0).equals("split"))
                throw new IllegalArgumentException("Incorrect Input");
            String fileName = commandLine.get(1);
            br = new BufferedReader(new FileReader(fileName));
            if (outputName.equals("-")) outputName =
                    fileName.replaceAll("(.+(?=/)/)|\\.(txt|doc|docx|rtf|odt|hlp)", "");
            if (fileSizeInLines > 0) {
                createFile(true, fileSizeInLines);
            } else if (fileSizeInChar > 0) {
                createFile(false, fileSizeInChar);
            } else if (fileSizeInFileCount > 0) {
                BufferedReader lineCount = new BufferedReader(new FileReader(fileName));
                int i = 0;
                while (lineCount.readLine() != null) {
                    i++;
                }
                int divider;
                if (i / fileSizeInFileCount != 0) {
                    if (i % fileSizeInFileCount == 0) divider = i / fileSizeInFileCount;
                    else divider = (i / fileSizeInFileCount) + 1;
                    createFile(true, divider);
                } else {
                    BufferedReader wordCount = new BufferedReader(new FileReader(fileName));
                    int j = 0;
                    while (wordCount.read() != -1) {
                        j++;
                    }
                    if (j % fileSizeInFileCount == 0) divider = j / fileSizeInFileCount;
                    else divider = (j / fileSizeInFileCount) + 1;
                    createFile(false, divider);
                }
            }else createFile(true, 100);
            br.close();

        } catch (CmdLineException e) {
            System.out.println("Incorrect input");
        }
    }

}
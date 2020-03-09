package myClasses;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileSplitter executor = new FileSplitter();
        try {
            executor.split(args);
        } catch (IOException e) {
            System.out.println("Incorrect input");
        }
    }
}

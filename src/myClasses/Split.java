package myClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Split {


    public void split(String inputLine) throws IOException {
        if (!inputLine.matches("split +( *\\[-([odlcn])\\s?([a-zA-Z/_0-9.] *)*])* *([a-zA-z/_0-9.])+"))
            throw new IllegalArgumentException("Line is incorrect");
        String searchIn = inputLine.replaceAll(" ", "");
    }

}
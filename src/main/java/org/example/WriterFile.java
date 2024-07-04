package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriterFile {

    private final String fileWriter;

    public WriterFile(String fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void setFileWriter(ArrayList<String> matches) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(fileWriter));
        for (String match : matches){
            fw.write(match+"\n");
        }
        fw.close();
    }
}

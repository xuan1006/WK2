package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class File {
    public String fileIntPut;

    public File(String fileIntPut){
        this.fileIntPut = fileIntPut;
    }
    public String bufferFile(){
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileIntPut));
            String text;
            while ((text = br.readLine())!=null){
                content.append(text).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}

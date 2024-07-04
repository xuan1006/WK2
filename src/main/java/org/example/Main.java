package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        File fileReader = new File("C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json");
        String fileContent = fileReader.bufferFile();
        String fileWriter = "queryAssetCodeWK2.txt";

        MatcherCode matcherCode = new MatcherCode(new ArrayList<>());
        matcherCode.matcherKindCode(fileContent);

        List<String> matches = matcherCode.getAll();
        Sort sort = new Sort(matches);
        sort.sortFile();
        List<String> sortedMatches = sort.sortFile();

        WriterFile writerFile = new WriterFile(fileWriter);
        writerFile.setFileWriter(new ArrayList<>(sortedMatches));

        for (String sortedMatch : sortedMatches) {
            System.out.println(sortedMatch);
        }
    }
}

package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = "C:\\Users\\bob92\\OneDrive\\文件\\queryAssetCode.json";
        String outputFile = "queryAssetCode.txt";

        MatcherCode matcher = new MatcherCode();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String text;
            while ((text = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\{\\\"kindCode(.*?)\\\"\\}");
                Matcher regexMatcher = pattern.matcher(text);

                while (regexMatcher.find()) {
                    matcher.addMatch(regexMatcher.group());
                }
            }
        }

        matcher.printMatches();
        matcher.writeMatches(outputFile);
    }
}
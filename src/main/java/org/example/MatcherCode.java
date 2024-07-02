package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

class MatcherCode {
    private final List<String> oddMatches = new ArrayList<>();
    private final List<String> evenMatches = new ArrayList<>();
    private final List<String> otherMatches = new ArrayList<>();

    public void addMatch(String matchGroup) {
        Pattern pattern1 = Pattern.compile(".*\\\"*物(.*?)物\\\".*");
        Matcher matcher1 = pattern1.matcher(matchGroup);

        while (matcher1.find()) {
            String match = matcher1.group();
            Pattern kindCodePattern = Pattern.compile("\\\"kindCode\\\":\\\"([0-9A-Za-z]+)\\\"");
            Matcher kindCodeMatcher = kindCodePattern.matcher(matchGroup);

            if (kindCodeMatcher.find()) {
                String kindCode = kindCodeMatcher.group(1);
                if (kindCode.matches("[0-9]+")) {
                    int kindCodeNum = Integer.parseInt(kindCode);
                    if (kindCodeNum % 2 == 0) {
                        evenMatches.add(match);
                    } else {
                        oddMatches.add(match);
                    }
                } else if (kindCode.matches("[AB][0-9]+")) {
                    int kindCodeNum = Integer.parseInt(kindCode.substring(1));
                    if (kindCodeNum % 2 == 0) {
                        evenMatches.add(match);
                    } else {
                        oddMatches.add(match);
                    }
                } else {
                    otherMatches.add(match);
                }
            }
        }
    }

    public List<String> getAllMatches() {
        List<String> allMatches = new ArrayList<>();
        allMatches.addAll(oddMatches);
        allMatches.addAll(evenMatches);
        allMatches.addAll(otherMatches);
        return allMatches;
    }

    public void printMatches() {
        List<String> matches = getAllMatches();
        System.out.println("Matches found:");
        for (String match : matches) {
            System.out.println(match);
        }
    }

    public void writeMatches(String filePath) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            List<String> matches = getAllMatches();
            for (String match : matches) {
                fw.write(match + "\n");
            }
        }
    }
}
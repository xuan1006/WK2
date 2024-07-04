package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sort {
    private final List<String> start;
    public Sort(List<String> all) {
        this.start = all;
    }

    public List<String> sortFile() {
        List<String> sorted = new ArrayList<>();

        for (String item : start) {
            Pattern pattern = Pattern.compile("\"kindCode\":\"([0-9AB][0-9]*|[a-zA-Z]+)\"");
            Matcher matcher = pattern.matcher(item);
            if (matcher.find()) {
                String kindCode = matcher.group(1);
                int kindCodeNum = extractKindCodeNum(kindCode);
                insertSorted(sorted, item, kindCodeNum, kindCode);
            } else {
                sorted.add(item);
            }
        }
        return sorted;
    }

    private int extractKindCodeNum(String kindCode) {
        if (kindCode.matches("[0-9]+")) {
            return Integer.parseInt(kindCode);
        } else if (kindCode.matches("[AB][0-9]+")) {
            return Integer.parseInt(kindCode.substring(1));
        } else {
            return Integer.MAX_VALUE;
        }
    }
    private void insertSorted(List<String> sorted, String item, int kindCodeNum, String kindCode) {
        int i = sorted.size() - 1;
        boolean isOdd = kindCodeNum % 2 != 0;
        boolean isPureEnglish = kindCode.matches("[a-zA-Z]+");

        if (isPureEnglish) {
            sorted.add(item);
            return;
        }
        while (i >= 0) {
            int currentKindCodeNum = extractKindCodeNum(extractKindCode(sorted.get(i)));
            boolean isEven = currentKindCodeNum % 2 != 0;

            if (isOdd && isEven && kindCodeNum >= currentKindCodeNum) {
                break;
            } else if (!isOdd && !isEven && kindCodeNum >= currentKindCodeNum) {
                break;
            } else if (!isOdd && isEven) {
                break;
            } else if (!isOdd && extractKindCodeNum(extractKindCode(sorted.get(i))) == Integer.MAX_VALUE) {
                break;
            }
            i--;
        }
        sorted.add(i + 1, item);
    }
    private String extractKindCode(String item) {
        Pattern pattern = Pattern.compile("\"kindCode\":\"([0-9AB][0-9]*|[a-zA-Z]+)\"");
        Matcher matcher = pattern.matcher(item);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}

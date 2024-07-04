package org.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherCode {
    private ArrayList all;
    public ArrayList getAll() {
        return all;
    }

    public MatcherCode(ArrayList all){
        this.all=all;
    }

    public void matcherKindCode(String text){
        Pattern pattern = Pattern.compile("\\{\"kindCode(.*?)\"}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            String matchGroup1 = matcher.group();
            Pattern pattern1 = Pattern.compile(".*\"*物(.*?)物\".*");
            Matcher matcher1 = pattern1.matcher(matchGroup1);

            while (matcher1.find()){
                all.add(matcher1.group());
            }
        }
    }

}

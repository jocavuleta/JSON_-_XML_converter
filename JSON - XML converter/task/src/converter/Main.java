//package converter;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public String converter() throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("/Users/jovanvuleta/IdeaProjects/JSON - XML converter/JSON - XML converter/task/src/converter/test.txt"));
//        StringBuilder readLines = new StringBuilder();
//        String currLine;
//        List<String> tags = new ArrayList<>();
//        while ((currLine = br.readLine()) != null) {
//            readLines.append(currLine);
//            tags.add(currLine);
//        }
//        String name = String.valueOf(readLines);
//        br.close();
//        String result;
//        char[] char_arr = name.toCharArray();
//        boolean hasAttributes = false;
//        //Convert to JSON
//        if(name.charAt(0) == '<'){
//            for(int i = 0; i < name.indexOf(' ') + 1; i++){
//                if(char_arr[i] == ' '){
//                    hasAttributes = true;
//                    break;
//                }
//            }
//            if(hasAttributes)
//                result = convertExtendedJSON(name, char_arr, tags);
//            else
//                result = convertToJSON(name);
//        //Convert to XML
//        }else if(name.charAt(0) == '{'){
//            result = convertToXML(name);
//        }else{
//            System.out.println("Bad input.");
//            throw new IllegalArgumentException();
//        }
//        return result;
//    }
//
//    private String convertExtendedJSON(String name, char[] charsPassed, List<String> tags) {
//        List<String> attributes = new ArrayList<>();
//        List<String> values = new ArrayList<>();
//
//        String openTag = "";
//        String key = "";
//        String insideBrackets = "";
//
//        for (String s : tags) {
//            char[] chars = s.toCharArray();
//            boolean[] checked = new boolean[s.length() - 1];
//            //Self closing
//            if (chars[s.length() - 2] == '/'){
//                insideBrackets = "null";
//            }else {
//                //Regular Tag
//                insideBrackets = s.substring(s.indexOf('>') + 1, s.indexOf('/') - 1);
//            }
//
//            openTag = s.substring(s.indexOf('<') + 1, s.indexOf('>'));
//            key = s.substring(s.indexOf('<') + 1, s.indexOf(' '));
//            int pos = s.indexOf(' ') + 1;
//            for (int i = s.indexOf(' ') + 1; i < openTag.length(); i++) {
//                if (chars[i] == ' ') {
//                    if(s.substring(pos, i).contains("=")) {
//                        pos++;
//                        continue;
//                    }else{
//                        attributes.add("@" + s.substring(pos, i));
//                        pos = i + 1;
//                        System.out.println(pos);
//                    }
//                } else if (chars[i] == '"' && !checked[i]) {
//                    int j = i + 1;
//                    while (chars[j] != '"') {
//                        j++;
//                    }
//                    checked[j] = true;
//                    values.add(s.substring(i + 1, j));
//                }
//            }
//
//        if (chars[s.length() - 2] == '/')
//            values.add("null");
//        else
//            values.add(insideBrackets);
//
//        attributes.add("#" + key);
//
//        }
//
//        System.out.println("Values: " +  Arrays.toString(new List[]{values}));
//        System.out.println("Keys: " +  Arrays.toString(new List[]{attributes}));
//        StringBuilder res = new StringBuilder();
//        res.append( "{\"" +key+"\" : {");
//
//        for(int i = 0; i < attributes.size(); i++){
//            res.append("\""+ attributes.get(i) +"\" : \""+ values.get(i) + "\", ");
//        }
//        if(values.get(values.size() - 1).contains("null"))
//            res.append("#"+ attributes.get(attributes.size() - 1) +"\" : "+ values.get(values.size() - 1));
//        res.deleteCharAt(res.length() - 2);
//        res.append("}}");
//        return String.valueOf(res);
//    }
//
//
//
//    public String convertToJSON(String name){
//        String result, key, value;
//        key = name.substring(name.indexOf("<") + 1, name.indexOf(">"));
//        try{
//            value = name.substring(name.indexOf(">") + 1, name.lastIndexOf("<"));}
//        catch (StringIndexOutOfBoundsException e){
//            key = key.substring(0, key.length() - 1);
//            return "{\"" + key + "\":"+ " null }";
//        };
//        result = "{"+ '"' + key + '"' + ":"+ '"' + value + '"' + "}";
//        return result;
//
//    }
//
//    public String convertToXML(String name){
//        String result, key, value;
//        key = name.substring(name.indexOf('"') + 1, name.indexOf(':') - 1);
//        value = name.substring(name.indexOf(":") + 2, name.indexOf("}") - 1);
//
//
//        if((key.contains("\"")))
//            key = key.substring(0, key.length() - 1);
//
//        if(value.contains("\""))
//            value = value.substring(1);
//
//        if(value.contains("ul"))
//            return "<"+key+"/>";
//
//        result = "<"+ key + ">" + value + "</"+ key + ">";
//
//        return result;
//    }
//
//    public static void main(String[] args) throws IOException {
//        String input1 = "<host>127.0.0.1</host>";
//        String input2 = "{\"jdk\" : \"1.8.9\"}";
//        String input3 = "<success/>";
//        String input4 = "{ \"storage\" : null }";
//        String test = "<qwerty/>";
//        String test1 = "{\"pizza\":\"slice\"}";
//        String test2 = "<jdk>1.8.9</jdk>";
//        String test3 = "{\"qwerty\":null}";
//
//        Scanner scanner = new Scanner(System.in);
//
//        //String input = scanner.next();
////
//        Main obj = new Main();
//        System.out.println(obj.converter());
////        System.out.println(obj.convertExtendedJSON());
//        //scanner.close();
//
//    }
//}

package converter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static String converter() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/jovanvuleta/IdeaProjects/JSON - XML converter/JSON - XML converter/task/test.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("/Users/jovanvuleta/IdeaProjects/JSON - XML converter/JSON - XML converter/task/src/converter/test.txt"));
        StringBuilder readLines = new StringBuilder();
        String currLine;
        String result;
        List<String[]> splitArr = new ArrayList<>();

        //Reading the .txt file and adding each line to a list of Strings
        while ((currLine = br.readLine()) != null) {
            readLines.append(currLine);
            //We split the read lines so we can only get the content without the spaces
            String[] curr = currLine.split(" ");
            splitArr.add(curr);
        }
        //Extracting a string from a line
        String fullReadLine = String.valueOf(readLines);
        //String fullReadLine = scanner.nextLine();
        br.close();


        //Conversion to JSON
        if(fullReadLine.charAt(0) == '<'){
            result = convertToJSON(splitArr, fullReadLine);
        }
        //Conversion to XML
        else{
            List<String> jsonTags = new ArrayList<>();
            jsonTags = inputBufferForXMLConvert(readLines, jsonTags);
            result = convertToXML(jsonTags,fullReadLine);
        }

        return result;
    }

    private static String convertToXML(List<String> jsonTags, String fullLine) throws IOException {
        String result = "";
        List<String> attributesAndValues = new ArrayList<>();
        String tagValue;
        String tagName;
        boolean hasAttributes = true;

        if(jsonTags.size() == 1)
            hasAttributes = false;


        //Only returning the tag name and value inside of an string array if
        //json has no attributes
        String[] tagNameAndValue;
        if(!hasAttributes){
            tagNameAndValue = checkForSpacesInsideNoAttributeJSON(fullLine);
            return "<"+tagNameAndValue[0]+">"+tagNameAndValue[1]+"</"+tagNameAndValue[0]+">";

        }else
            tagName = jsonTags.get(1).substring(jsonTags.get(1).indexOf("\"") + 1, jsonTags.get(1).indexOf(":") - 2);

        //Getting the appropriate JSON name and value(skipping the braces in the list
        //that's why the counter is incremented at beginning and decremented at the end
        int i;
        for( i = 2; i < jsonTags.size() - 3; i++){
            String currStr = jsonTags.get(i);
            attributesAndValues.add(jsonTags.get(i).substring(currStr.indexOf("\"") + 2, currStr.indexOf(":") - 2));
            attributesAndValues.add(jsonTags.get(i).substring(currStr.indexOf(":") + 2, currStr.length() - 1) );
        }


        //Checking for null values in a JSON and setting tagValue
        if(fullLine.contains("null"))
            tagValue = "null";
        else {
            String currStr = jsonTags.get(jsonTags.size() - i);
            tagValue = currStr.substring(currStr.indexOf(":") + 3, currStr.lastIndexOf("\""));
        }

        result += "<"+ tagName;

        //Dynamically returning the deleted first string
        //So we can always set the key : value pairs as they were inserted
        //in the list (key - odd, value - pair) number
        while(!attributesAndValues.isEmpty()){
            String att = attributesAndValues.remove(0);
            String val = attributesAndValues.remove(0);
            if(val.contains("\""))
                result += " " +att + " = "+val+"";
            else
                result += " " +att + " = \""+val+"\"";
        }

        if(tagValue.equalsIgnoreCase("null"))
            result += "/>";
        else{
            result += ">" + tagValue+"</"+tagName+">";
        }


        return result;
    }

    private static String tagWithNoAttributes(List<String> currentString) {
        String currentArrayString = currentString.get(0);
        String tagName = currentString.get(0).substring(currentArrayString.indexOf("<") + 1, currentArrayString.indexOf(">"));
        String tagValue = currentString.get(0).substring(currentArrayString.indexOf(">") + 1, currentArrayString.lastIndexOf("<"));
        return "{\""+ tagName +"\":\"" + tagValue + "\"}";
    }

    private static String selfClosingTag(List<String> currentString, List<String> tagAttributesAndValues, String tagName) {
        String result = "";
        String value = "null";

        for(int i = 1; i < currentString.size(); i++){
            //If we run into equal sign or just a blank space, we just ignore it and don't put it in our list
            if(currentString.get(i).equalsIgnoreCase("=") || currentString.get(i).equalsIgnoreCase("")){
                continue;
            }
            //Otherwise put everything else inside of our attributeValue list
            tagAttributesAndValues.add(currentString.get(i));
        }
        //Deletes the last element in the list which is "/>", since we don't need that
        //in our list containing only attributes and values of the XML file
        tagAttributesAndValues.remove(tagAttributesAndValues.get(tagAttributesAndValues.size() - 1));

        //Fixed first part of the print which is always the same
        result += "{ \"" + tagName + "\" : {";
        int i = 0;

        //Generic part of the print which is changing depending of the given XML
        while(!tagAttributesAndValues.isEmpty()){
            int j = 0;
            String att = "";
            String val = "";
            //Dynamically getting the first element and deleting after that
            //So we can always set the key : value pairs as they were inserted
            //in the list (key - odd, value - pair) number
            while(j < 2){
                att = tagAttributesAndValues.remove(j);
                val = tagAttributesAndValues.remove(j);
                j += 2;
            }
            result += " \"@"+ att +"\" : "+ val +", ";
        }
        //Appending the last fixed part of the string
        result += "\"#"+ tagName +"\" : "+ value +" } }";

        return result;
    }

    private static String normalClosingTag(List<String> currentString, List<String> tagAttributesAndValues, String tagName, String tagValue) {
        String result = "";

        for(int i = 1; i < currentString.size(); i++){
            if(currentString.get(i).equalsIgnoreCase("=") || currentString.get(i).equalsIgnoreCase("")){
                continue;
                //If the string contains ">" sign, that means that we don't have
                //more attributes and values, so we break.
            }else if(currentString.get(i).contains(">")){
                //Since the string can be glued to the value like this ("manager">Garry)
                //we need to separate the true attribute value for our list,
                // and to neglect the value of the tag
                tagAttributesAndValues.add(currentString.get(i).substring(0, currentString.get(i).indexOf(">")));
                break;
            }
            tagAttributesAndValues.add(currentString.get(i));
        }

        //tagAttributesAndValues.remove(tagAttributesAndValues.get(tagAttributesAndValues.size() - 1));
        result += "{ \"" + tagName + "\" : {";

        while(!tagAttributesAndValues.isEmpty()){
            String att = tagAttributesAndValues.remove(0);
            String val = tagAttributesAndValues.remove(0);
            result += " \"@"+ att +"\" : "+ val +", ";
        }

        result += "\"#"+ tagName +"\" : \""+ tagValue +"\" } }";

        return result;
    }

    private static String convertToJSON(List<String[]> splitArr, String fullLine) {
        List<String> tagAttributesAndValues = new ArrayList<>();
        List<String> currentString;
        String result,tagName;
        boolean hasAttributes = false;

        currentString = Arrays.asList(splitArr.get(0));

        tagName = currentString.get(0).substring(1);

        //Checking if the given XML has attributes
        if(fullLine.contains(" "))
            hasAttributes = true;

        //If a tag is self closing, return inside of this statement
        if(currentString.contains("/>")){
            result = selfClosingTag(currentString, tagAttributesAndValues, tagName);
        }
        //Tag has no attributes
        else if(!hasAttributes){
            result = tagWithNoAttributes(currentString);
        }
        //If not then tag is normal(not self closing), so we continue further and also provide the
        // tag value of the normal closing tag
        else{
            String tagValue = fullLine.substring(fullLine.indexOf(">") + 1, fullLine.lastIndexOf("<"));
            result = normalClosingTag(currentString, tagAttributesAndValues, tagName, tagValue);
        }
        return  result;
    }

    private static String[] checkForSpacesInsideNoAttributeJSON(String fullLine) {
        String[] tagNameAndValue = new String[2];
        String tagName;
        String tagValue;
        if(fullLine.contains(" ")){
            tagName = fullLine.substring(fullLine.indexOf("\"") + 1, fullLine.indexOf(":") - 2);
            tagValue = fullLine.substring(fullLine.indexOf(":") + 3, fullLine.lastIndexOf("\""));
        }else{
            tagName = fullLine.substring(fullLine.indexOf("\"") + 1, fullLine.indexOf(":") - 1);
            tagValue = fullLine.substring(fullLine.indexOf(":") + 2, fullLine.lastIndexOf("\""));
        }
        tagNameAndValue[0] = tagName;
        tagNameAndValue[1] = tagValue;
        return tagNameAndValue;
    }

    public static List<String> inputBufferForXMLConvert(StringBuilder readLines, List<String> jsonTags) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/jovanvuleta/IdeaProjects/JSON - XML converter/JSON - XML converter/task/src/converter/test.txt"));
        readLines = new StringBuilder();
        String currLine;
        List<String> tags = new ArrayList<>();

        while ((currLine = br.readLine()) != null) {
            readLines.append(currLine);
            tags.add(currLine);
        }
        br.close();
        return tags;
    }


    public static void main(String[] args) throws IOException {
        System.out.println(converter());
    }
}

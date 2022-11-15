package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;


public class FileReader {


    public static String returnValueFromMap(String str, Map<String, String> map) {

        Set<Map.Entry<String, String>> entrySet=map.entrySet();
        String toReturn = null;

        for (Map.Entry<String,String> pair : entrySet) {
            if (str.equals(pair.getKey())) {
                toReturn = pair.getValue();
            }
        }
        return toReturn;


//        Optional<String> result = map.entrySet()
//                .stream()
//                .filter(entry -> str.equals(entry.getKey()))
//                .map(Map.Entry::getValue)
//                .findFirst();
//
//            return result.get();
    }

    public static Profile getDataFromFile(File file) throws IOException {
//        file = new File("src\\main\\resources\\Profile.txt");
        Map<String, String> map = new HashMap<>();

        try (java.io.FileReader fileReader = new java.io.FileReader(file);){

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null){
                String[] pairs = line.split(": ");
                map.put(pairs[0], pairs[1]);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new Profile(returnValueFromMap("Name", map),
                Integer.parseInt(returnValueFromMap("Age", map)),
                returnValueFromMap("Email", map),
                (long) Integer.parseInt(returnValueFromMap("Phone", map)));
    }
}

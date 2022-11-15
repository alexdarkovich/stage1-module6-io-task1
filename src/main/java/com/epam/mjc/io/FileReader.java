package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class FileReader {


    public static String returnValueFromMap(String str, Map<String, String> map) {

        Optional<String> result = map.entrySet()
                .stream()
                .filter(entry -> str.equals(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst();

            return result.get();
    }

    public Profile getDataFromFile(File file) throws IOException {
//        file = new File("C:\\Users\\klubo\\IdeaProjects\\stage1-module6-io-task1\\src\\main\\resources\\Profile.txt");
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
            System.out.println("Problem reading the file " + file.getName());;
        }


        return new Profile(returnValueFromMap("Name", map),
                Integer.parseInt(returnValueFromMap("Age", map)),
                returnValueFromMap("Email", map),
                (long) Integer.parseInt(returnValueFromMap("Phone", map)));
    }
}

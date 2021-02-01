package com.nbascrape.rondo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataParser {

    public static void main(String[] args) {
        String d = new DataParser().fileToString("../nba2.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode json = mapper.readTree(d);
            JsonNode rowData = json.get("resultSets");
            JsonNode rowSet = rowData.get(0).get("rowSet");
            rowSet.forEach(DataParser::printRow);
            

            // Iterator<JsonNode> it = rowData.iterator();
            // while (it.hasNext()) {
            //     System.out.println(it.next().asText());
            // }
        } catch (JsonMappingException jme) {
            System.out.println("failed parsing");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void printRow(JsonNode row){
        // need 1, 2 4, 5, 6
        System.out.println(row.get(1));
    }

    String fileToString(String path) {

        InputStreamReader isr=  null;
        try {
            isr = new InputStreamReader(new FileInputStream(path));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        char[] buffer = new char[1024 * 5];

        int offset = 0;
        int read = 0;
        int totalRead = 0;
        StringBuilder totalBuffer = new StringBuilder();

        while (read != -1) {
            try {
                read = isr.read(buffer, offset, buffer.length - offset);
                offset += read;
                totalRead += read;
                if(offset == buffer.length){
                    offset = 0;
                    totalBuffer.append(buffer);
                    buffer = null;
                    buffer = new char[1024 * 5];
                }
                // System.out.println(totalRead);
                // System.out.println(read);
                // System.out.println();
                

            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        totalBuffer.append(buffer);
        return totalBuffer.toString();
        
        
        
    }
    
}

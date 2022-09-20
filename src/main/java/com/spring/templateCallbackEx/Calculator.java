package com.spring.templateCallbackEx;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class Calculator {
    public int calcSum(String path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(getFile(path)));

        int sum = 0;
        String line;
        while ((line = br.readLine()) != null){
            sum += Integer.parseInt(line);
        }
        br.close();
        return sum;
    }

    public File getFile(String path) throws IOException {
        return new ClassPathResource(path).getFile();
    }
}

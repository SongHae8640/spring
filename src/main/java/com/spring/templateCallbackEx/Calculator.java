package com.spring.templateCallbackEx;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

public class Calculator {

    public Integer lineReadTemplate(String path, LineCallback callback, int initValue){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new ClassPathResource(path).getFile()));
            String line;
            int result = initValue;
            while((line = br.readLine()) != null){
                result = callback.doWithLine(line, result);
            }
            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Integer calcSum2(String path) throws RuntimeException {
        LineCallback callback = new LineCallback() {
            @Override
            public Integer doWithLine(String line, Integer value) {
                return value + Integer.parseInt(line);
            }
        };

        return lineReadTemplate(path, callback ,0);
    }

    public Integer calcMultiply2(String path) {
        return lineReadTemplate(path, (line, value) -> value * Integer.parseInt(line), 1);
    }

    //---

    public Integer fileReadTemplate(String path, BufferedReaderCallback callback){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new ClassPathResource(path).getFile()));
            return callback.doWithReader(br);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Integer calcSum(String path) throws RuntimeException {
        BufferedReaderCallback sumCallBack = new BufferedReaderCallback() {
            @Override
            public Integer doWithReader(BufferedReader br) throws IOException {
                int sum = 0;
                String line;
                while ((line = br.readLine()) != null){
                    sum +=Integer.parseInt(line);
                }
                return sum;
            }
        };

        return fileReadTemplate(path, sumCallBack);
    }

    public Integer calcMultiply(String path) throws RuntimeException {
        return fileReadTemplate(path, reader -> {
            int result = 1;
            String line;
            while ((line = reader.readLine()) != null){
                result *=Integer.parseInt(line);
            }
            return result;
        });
    }


}

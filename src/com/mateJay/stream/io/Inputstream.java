package com.mateJay.stream.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author: mate_J
 * @Date: 2019/5/21 22:01
 * @Version 1.0
 */
public class Inputstream {

    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("C:\\Users\\mate_J\\Desktop\\aliCloud.txt");

        byte[] buff = new byte[1024];

        int count;

        StringBuilder sb = new StringBuilder();
        while((count = is.read(buff)) > 0){
            String s = new String(buff,0,count);
            sb.append(s);
        }
        String s1 = new String();
        s1 = sb.toString();
        String s2 = new String(s1.getBytes(),"UTF-8");
        System.out.println(s2);
    }
}

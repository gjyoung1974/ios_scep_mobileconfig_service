package com.gyoung.pki.ios.mobileconfig.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.Charset;

public class GetPage {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        // Construct data
        Authenticator.setDefault(new MyAuthenticator());
        URL url = new URL("http://pki.gordonjyoung.com/certsrv/mscep_admin/");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");


        conn.connect();
        InputStream in = conn.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));


        String decodedString = reader.readLine();

        Charset charSet = Charset.forName("UTF8");
        byte[] test = decodedString.getBytes(charSet);


        System.out.write(test);


        //String text = reader.readLine();

        //String text2 = toUTF8(text);

        //System.out.print(text);

        conn.disconnect();


    }


    private static String toUTF8(String str) {
        try {
            return new String(str.getBytes("ISO8859_1"), "UTF8");
        } catch (UnsupportedEncodingException ex) {
            return str;
        }
    }


    static class MyAuthenticator extends Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            //System.out.println("trying to authenticate");
            return new PasswordAuthentication("goyoung", "c3rnd8ggs!".toCharArray());
        }
    }
}



package com.polarising.PortalNet.TIBCOconnect;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class PostToTibco {

    private static String urlStr;

    public PostToTibco (String protocol, String host) {

        if(protocol.toLowerCase().equals( GlobalVariablesTibcoPost.PROTOCOL_HTTP )) {

            this.urlStr = "http://" + host + ":";

//            System.out.println( "PostToTibco(): " + this.urlStr );

        } else {

            System.out.println( "PostToTibco(): Protocol Invalid" );

        }
    }

    public void sendPost(int port, Map<String, String> headers, Map<String,String> params) {

        try {

            URL url = new URL(urlStr + port);
            System.out.println("PostToTibco/sendPost(): " + url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", GlobalVariablesTibcoPost.CONTENT_TYPE);

            setHeaders(connection, headers);

            byte[] message = setMessage(params);

            connection.setFixedLengthStreamingMode(message.length);

            connection.connect();

            OutputStream output = connection.getOutputStream();

            output.write(message);

            System.out.println("\n" + connection.getResponseCode());
            System.out.println(connection.getResponseMessage() + "\n");

            InputStream input = connection.getInputStream();
            BufferedReader inBuf = new BufferedReader(new InputStreamReader(input));

            ArrayList< Map< String, String>> list = getMessage(inBuf.readLine());

            for( int i = 0; i < list.size(); i++ ) {
                for ( Map.Entry<String, String> entry : list.get(i).entrySet() ) {

                    System.out.println(entry.getKey() + "=" + entry.getValue());

                }
            }

            output.flush();
            output.close();
            input.close();

        } catch (IOException e) {
            System.out.println("PostToTibco/sendPost(): ERROR - IOException");
        }
    }

    private byte[] setMessage( Map<String, String> params) {

        StringJoiner message = new StringJoiner( GlobalVariablesTibcoPost.DELIMITER_PRIMARY);

        for ( Map.Entry<String, String> entry : params.entrySet() ) {

            message.add(URLEncoder.encode(entry.getKey(), GlobalVariablesTibcoPost.CHARSET_PARAM) + "=" + URLEncoder.encode(entry.getValue(), GlobalVariablesTibcoPost.CHARSET_PARAM));

        }

        return  message.toString().getBytes( GlobalVariablesTibcoPost.CHARSET_OBJECT );

    }

    private void setHeaders ( HttpURLConnection connection, Map<String, String> headers ) {

        for ( Map.Entry<String, String> entry : headers.entrySet() ) {

            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

    }

    private ArrayList< Map< String, String >> getMessage(String content) {

        ArrayList<Map<String,String>> objectlist = new ArrayList<>();

        String[] objects = content.split(GlobalVariablesTibcoPost.DELIMITER_PRIMARY);

        for( int i = 0; i < objects.length; i++ ){

            Map<String,String> map = new HashMap<>();

            String[] params = objects[i].split(";");

            for ( String param : params ) {

                String[] token = param.split("=");

                map.put(token[0], token[1]);
            }

            objectlist.
                    add(map);

        }
        return objectlist;
    }

}


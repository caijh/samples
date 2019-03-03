package com.coding.sample.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class BioClient {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream outputStream;
        try (Socket socket = new Socket("127.0.0.1", 8080)) {
            outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            while (true) {
                String data = bufferedReader.readLine();
                if ("q".equals(data)) {
                    break;
                }
                System.out.println(data);
                bufferedWriter.write(data);
                bufferedWriter.flush();
            }
        }

    }

}

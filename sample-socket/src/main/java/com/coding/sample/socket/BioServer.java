package com.coding.sample.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("服务器启动");
        while (true) {
            Socket socket = server.accept();
            System.out.println("客户端:" + socket.getInetAddress().getLocalHost() + "已连接到服务器");
            executorService.execute(() -> {
                try {
                    handle(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void handle(Socket socket) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if ("q".equals(line)) {
                    break;
                }
                System.out.println("客户端消息：" + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

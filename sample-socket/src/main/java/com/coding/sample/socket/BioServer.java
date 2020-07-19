package com.coding.sample.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BioServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BioServer.class);

    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(8080)) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            LOGGER.info("服务器启动");
            do {
                Socket socket = server.accept();
                executorService.execute(() -> {
                    try {
                        handle(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } while (true);
        }
    }

    private static void handle(Socket socket) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if ("q".equals(line)) {
                    break;
                }
                LOGGER.info("客户端消息 {}", line);
            }
        } catch (Exception e) {
            throw new IOException();
        }
    }

}

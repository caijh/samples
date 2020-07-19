package com.coding.sample.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioClient {

    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("hello".getBytes(StandardCharsets.UTF_8));
            socketChannel.write(byteBuffer);
        }
    }

}

package com.coding.sample.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioServer {

    private Selector selector;

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.init(8080);
        server.listen();
    }

    private void init(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    private void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                handle(key);
            }
        }
    }

    private void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            handleAccept(key);
        } else if (key.isConnectable()) {

        } else if (key.isReadable()) {
            handleRead(key);
        } else if (key.isWritable()) {

        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        // 获得和客户端连接的通道
        SocketChannel channel = server.accept();
        // 设置成非阻塞
        channel.configureBlocking(false);

        // 在这里可以给客户端发送信息哦
        System.out.println("新的客户端连接");
        // 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
        channel.register(this.selector, SelectionKey.OP_READ);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        if (read > 0) {
            System.out.println("收到客户端消息：" + new String(byteBuffer.array(), StandardCharsets.UTF_8));
        } else {
            System.out.println("客户端关闭");
            key.cancel();
        }
    }

}

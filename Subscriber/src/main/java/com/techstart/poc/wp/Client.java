package com.techstart.poc.wp;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Client {

    public static void mainx(String[] args) throws Exception {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        // In Future form
        Future<?> future = client.connect(new InetSocketAddress("localhost",8080));
        // Block it, wait for the connection to succeed
        future.get();

        Attachment att = new Attachment();
        att.setClient(client);
        att.setReadMode(false);
        att.setBuffer(ByteBuffer.allocate(2048));
        byte[] data = "I am a subscriber obot!".getBytes();
        att.getBuffer().put(data);
        att.getBuffer().flip();

        // Asynchronous sending of data to server
        client.write(att.getBuffer(), att, new ClientChannelHandler());
        // To prevent main threads from exiting
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
        }
    }
}
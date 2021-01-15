package com.techstart.poc;


import com.techstart.poc.wp.Attachment;
import com.techstart.poc.wp.ClientChannelHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class Subscriber {

    public static void connect(String clientName) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        // In Future form
        Future<?> future = client.connect(new InetSocketAddress("localhost", 8080));
        // Block it, wait for the connection to succeed
        future.get();

        com.techstart.poc.wp.Attachment att = new Attachment();
        att.setClient(client);
        att.setReadMode(false);
        att.setBuffer(ByteBuffer.allocate(2048));
        byte[] data = ("I am a subscriber ! "+clientName).getBytes();
        att.getBuffer().put(data);
        att.getBuffer().flip();

        // Asynchronous sending of data to server
        client.write(att.getBuffer(), att, new ClientChannelHandler(clientName));

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        Subscriber.connect("one");
//        Subscriber.connect("two");
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
        }
    }
}
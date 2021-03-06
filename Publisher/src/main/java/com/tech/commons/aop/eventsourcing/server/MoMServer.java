package com.tech.commons.aop.eventsourcing.server;

import com.tech.commons.aop.eventsourcing.Event;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

//https://programming.vip/docs/java-non-blocking-io-and-asynchronous-io.html
//https://www.programmersought.com/article/9109184461/
public class MoMServer {

    static final String localhost = "localhost";
    static final int port = 8080;

    public static void createServer() throws IOException {
        // Instantiate and listen on ports

        AsynchronousServerSocketChannel server =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(localhost, port));

        // Define an Attachment class for passing some information
        Attachment att = new Attachment();
        att.setServer(server);

        server.accept(att, new CompletionHandler<AsynchronousSocketChannel, Attachment>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Attachment att) {
                try {
                    SocketAddress clientAddr = client.getRemoteAddress();
                    System.out.println("Received a new connection:" + clientAddr);

                    // After receiving a new connection, the server should call the accept method again and wait for the new connection to come in.

                    // get the subscriber detail
                    // what is the last index of the event it recieved
                    //load the list of events for last index
                    //publish to subscriber
                    att.getServer().accept(att, this);

                    Attachment newAtt = new Attachment();
                    newAtt.setServer(server);
                    newAtt.setClient(client);
                    newAtt.setReadMode(true);
                    newAtt.setBuffer(ByteBuffer.allocate(2048));

                    // Anonymous implementation classes can also continue to be used here, but the code is ugly, so a class is specifically defined here.
                    client.read(newAtt.getBuffer(), newAtt, new ChannelHandler());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable t, Attachment att) {
                System.out.println("accept failed");
            }
        });

    }

    public static void main(String[] args) {
        try {
            MoMServer.createServer();
            Event event = new Event();
            event.setId("one");
            event.setTimestamp(new Date());


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
        }
    }
}
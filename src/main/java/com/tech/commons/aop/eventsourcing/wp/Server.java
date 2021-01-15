package com.tech.commons.aop.eventsourcing.wp;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
//https://programming.vip/docs/java-non-blocking-io-and-asynchronous-io.html
public class Server {

    public static void main(String[] args) throws IOException {

        // Instantiate and listen on ports
        AsynchronousServerSocketChannel server =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));

        // Define an Attachment class for passing some information
        Attachment att = new Attachment();
        att.setServer(server);

        server.accept(att, new CompletionHandler<AsynchronousSocketChannel, Attachment>() {
            @Override
            public void completed(AsynchronousSocketChannel client, Attachment att) {
                try {
                    SocketAddress clientAddr = client.getRemoteAddress();
                    System.out.println("Receive a new connection:" + clientAddr);

                    // After receiving a new connection, the server should call the accept method again and wait for the new connection to come in.
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
        // To prevent main threads from exiting
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
        }
    }
}
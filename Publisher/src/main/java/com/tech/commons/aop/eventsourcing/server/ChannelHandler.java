package com.tech.commons.aop.eventsourcing.server;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class ChannelHandler implements CompletionHandler<Integer, Attachment> {

    private String subscriber;


    public ChannelHandler() {
//        this.subscriber = subscriber;
    }

    public ChannelHandler(String subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void completed(Integer result, Attachment att) {
        if (att.isReadMode()) {
            // Read data from the client
            ByteBuffer buffer = att.getBuffer();
            buffer.flip();
            byte bytes[] = new byte[buffer.limit()];
            buffer.get(bytes);
            String msg = new String(buffer.array()).toString().trim();
            System.out.println("Received data from the client: " + msg);

            // Respond to client request and return data
            buffer.clear();
            buffer.put("Response from server!".getBytes(Charset.forName("UTF-8")));
            att.setReadMode(false);
            buffer.flip();
            // Writing data to the client is also asynchronous
            att.getClient().write(buffer, att, this);
        } else {
            // Here, it means that writing data to the client is over. There are two choices:
            // 1. Continue to wait for the client to send new data.
            att.setReadMode(true);
            att.getBuffer().clear();
            att.getClient().read(att.getBuffer(), att, this);
            // 2. Since the server has returned data to the client, disconnect this connection.
//            try {
//                att.getClient().close();
//            } catch (IOException e) {
//            }
        }
    }

    @Override
    public void failed(Throwable t, Attachment att) {
        System.out.println("Disconnection");
    }
}
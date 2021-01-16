package com.techstart.poc.wp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ClientChannelHandler implements CompletionHandler<Integer, Attachment> {

    private String client;

    public ClientChannelHandler() {
    }

    public ClientChannelHandler(String client) {
        this.client = client;
    }

    @Override
    public void completed(Integer result, Attachment att) {
        ByteBuffer buffer = att.getBuffer();
        if (att.isReadMode()) {
            // Read data from the server
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            String msg = new String(bytes, Charset.forName("UTF-8"));
            System.out.println("Receive response data from the server:  "+
                    client + msg);

            // Next, there are two options:
            // 1. Send new data to the server
            att.setReadMode(false);

            System.out.println("enter a message to server "+client);
            Scanner sc = new Scanner(System.in);
            String user = sc.nextLine();
            if("close".equalsIgnoreCase(user)){

                try {
                    att.getClient().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            byte[] data = user.getBytes(Charset.forName("UTF-8"));
            buffer.clear();
            buffer.put(data);
            buffer.flip();
            att.getClient().write(buffer, att, this);
            buffer.clear();


        } else {
            // When the write operation is complete, it will come in here.
            att.setReadMode(true);
            buffer.clear();
            att.getClient().read(buffer, att, this);
        }
    }

    @Override
    public void failed(Throwable t, Attachment att) {
        System.out.println("Server no response");
    }
}
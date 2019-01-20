package com.godme.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TimeClientMain {
    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Channel channel = bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new TimeClientInitializer()).connect("localhost",8989).sync().channel();
            while(true){
                channel.writeAndFlush(reader.readLine());
            }
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

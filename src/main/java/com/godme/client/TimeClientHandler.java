package com.godme.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class TimeClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            switch (event.state()){
                case READER_IDLE:
                    ctx.writeAndFlush("读超时触发");
                    break;
                case WRITER_IDLE:
                    ctx.writeAndFlush("写超时触发");
                    break;
                case ALL_IDLE:
                    ctx.writeAndFlush("读写超时触发");
                    break;
                    default:break;
            }
        }
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}

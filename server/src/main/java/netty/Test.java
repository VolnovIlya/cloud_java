package netty;

import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class Test {
    public static void main(String[] args) {
        ChannelInitializer<SocketChannel> chi;
        ChannelInboundHandler ci;
        ChannelOutboundHandler co;

        ChannelInboundHandlerAdapter cia;
        ChannelOutboundHandlerAdapter coa;
    }
}

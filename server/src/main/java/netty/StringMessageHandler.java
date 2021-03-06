package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StringMessageHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("client accepted.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                String s) throws Exception {
        log.debug("received: {}", s);
        ctx.writeAndFlush(s);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("client disconnected.");
    }
}

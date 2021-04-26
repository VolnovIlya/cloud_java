import handler.CallBack;
import handler.SerialHandlerWithCallBack;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;
import model.Message;

@Slf4j
public class NettyNetwork {

    private SocketChannel clientChannel;
    private final CallBack callBack;
    public static NettyNetwork INSTANCE;

    public static NettyNetwork getInstance(CallBack callBack) {
        if (INSTANCE == null) {
            INSTANCE = new NettyNetwork(callBack);
        }
        return INSTANCE;
    }

    private NettyNetwork(CallBack c) {
        this.callBack = c;
        new Thread(() -> {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel channel) throws Exception {
                                clientChannel = channel;
//                                channel.pipeline().addLast(
//                                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
//                                        new ObjectEncoder(),
//                                        new SerialHandlerWithCallBack(callBack)
//                                );
                            }
                        });

                ChannelFuture future = bootstrap.connect("localhost", 8189).sync();
                log.debug("client network ctarted");
                future.channel().closeFuture().sync(); //block
            } catch (Exception e) {
                log.error("e=", e);
            } finally {
                group.shutdownGracefully();
            }
        }).start();
    }

    public void write(Message message) {
        clientChannel.writeAndFlush(message);
    }

}

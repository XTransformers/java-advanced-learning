package com.xtransformers.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
        bind();
    }

    private void bind() {
        // 创建 BossGroup 和 WorkerGroup
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建 ServerBootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置 Channel 和 Option
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    // 设置Channel的类型为NIO
                    .channel(NioServerSocketChannel.class)
                    // 设置BACKLOG的大小为1024
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 启用心跳检测机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 设置数据包无延迟
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            // 配置解码器为MessageDecoder类
                            nioSocketChannel.pipeline().addLast("decoder", new MessageDecoder());
                        }
                    });
            // 设置绑定端口号并启动
            ChannelFuture channelFuture = serverBootstrap
                    .bind(port).sync();
            if (channelFuture.isSuccess()) {
                log.info("NettyServer start success, port : " + port);
            }
            // 设置异步关闭连接
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("NettyServer start failed, exception : " + e.getMessage());
        } finally {
            // 优雅退出函数设置
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyServer(9000);
    }

}

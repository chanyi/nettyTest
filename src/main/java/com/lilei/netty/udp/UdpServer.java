package com.lilei.netty.udp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.bootstrap.Bootstrap;

public class UdpServer {

	public static void main(String[] args ) throws  Exception {
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			EventLoopGroup group = new NioEventLoopGroup();
			b.group(group)
					.channel(NioDatagramChannel.class)
					.option(ChannelOption.SO_BROADCAST, true)
					.handler(new UdpServerHandler());

			b.bind(2555).sync().channel().closeFuture().await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
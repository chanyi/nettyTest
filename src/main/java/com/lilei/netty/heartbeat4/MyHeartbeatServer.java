package com.lilei.netty.heartbeat4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MyHeartbeatServer {

	public static void main(String[] args ) throws  Exception{
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosGroup,workGroup).channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))//对boosGroup起作用
					.childHandler(new MyHeartBeatServerInitializer());//对workerGroup起作用
			ChannelFuture channelFuture = serverBootstrap.bind(9900).sync();
			channelFuture.channel().closeFuture().sync();
		}finally {
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}

	}
}
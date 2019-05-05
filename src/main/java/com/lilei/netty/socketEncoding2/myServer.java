package com.lilei.netty.socketEncoding2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class myServer {

	public static void  main(String[] args) throws Exception{

		EventLoopGroup boosLoopGroup = new NioEventLoopGroup();
		EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosLoopGroup,workerLoopGroup).channel(NioServerSocketChannel.class)
			.childHandler(new MyServerInitializer());

			ChannelFuture channelFuture = serverBootstrap.bind(7700).sync();
			channelFuture.channel().closeFuture().sync();

		}finally {
			boosLoopGroup.shutdownGracefully();
			workerLoopGroup.shutdownGracefully();
		}
	}
}

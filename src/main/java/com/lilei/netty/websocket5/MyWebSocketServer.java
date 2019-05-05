package com.lilei.netty.websocket5;

import com.lilei.netty.socketEncoding2.MyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class MyWebSocketServer  {

	public static void  main(String[] args) throws Exception{

		EventLoopGroup boosLoopGroup = new NioEventLoopGroup();
		EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
		try{
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosLoopGroup,workerLoopGroup).channel(NioServerSocketChannel.class)
					.childHandler(new MyWebSocketServerInitializer());

			ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8800)).sync();
			channelFuture.channel().closeFuture().sync();

		}finally {
			boosLoopGroup.shutdownGracefully();
			workerLoopGroup.shutdownGracefully();
		}
	}
}

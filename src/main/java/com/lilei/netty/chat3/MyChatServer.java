package com.lilei.netty.chat3;

import com.lilei.netty.socketEncoding2.MyServerInitializer;
import com.lilei.netty.socketEncoding2.myClientInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyChatServer {

	public static void main(String[] args ) throws  Exception{

		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try{
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosGroup,workGroup).channel(NioServerSocketChannel.class)
					.childHandler(new MyChatServerInitializer());
			ChannelFuture channelFuture = serverBootstrap.bind(9900).sync();
			channelFuture.channel().closeFuture().sync();
		}finally {
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}

	}
}

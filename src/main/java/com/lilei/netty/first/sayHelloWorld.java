package com.lilei.netty.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class sayHelloWorld {

	public static void main(String[] args) throws Exception{


		//两个死循环
		//是用来获取链接
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		//用来完成任务
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try{
			//服务启动类
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(boosGroup,workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ServerInitializer());

			ChannelFuture channelFuture = serverBootstrap.bind(7777).sync();
			channelFuture.channel().closeFuture().sync();
			//此问题和上面的有区别，上面的死循环，下面的不会
			//channelFuture.channel().close().sync();
		}finally {
			boosGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}

}

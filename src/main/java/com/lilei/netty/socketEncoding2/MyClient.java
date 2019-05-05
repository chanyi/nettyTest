package com.lilei.netty.socketEncoding2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {

	public static void main(String[] args) throws Exception{

		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

		try{
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new myClientInitializer());

			//连接服务端  输入服务端的地址
			ChannelFuture channelFuture = bootstrap.connect("localhost",9900).sync();

			channelFuture.channel().closeFuture().sync();

		}finally {
			eventLoopGroup.shutdownGracefully();
		}


	}
}

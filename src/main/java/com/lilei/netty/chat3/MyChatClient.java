package com.lilei.netty.chat3;

import com.lilei.netty.socketEncoding2.myClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyChatClient  {

	public static  void main(String[] args) throws Exception{
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

		try{
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());

			//连接服务端  输入服务端的地址
			Channel channel = bootstrap.connect("localhost",9900).sync().channel();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for(;;){
				channel.writeAndFlush(br.readLine() + "\r\n");
			}
		}finally {
			eventLoopGroup.shutdownGracefully();
		}
	}


}

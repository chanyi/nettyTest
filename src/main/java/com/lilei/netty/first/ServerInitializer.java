package com.lilei.netty.first;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {


	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline =  ch.pipeline();
		//netty自带的
		channelPipeline.addLast("httpServerCodec",new HttpServerCodec());
		//自己生成的
		channelPipeline.addLast("myselfHttpHandel",new myselfHttpServerHandler());

	}
}

package com.lilei.netty.heartbeat4;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyHeartBeatServerInitializer extends ChannelInitializer<SocketChannel> {


	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		ChannelPipeline channelPipeline = ch.pipeline();

		//空闲检测处理器，如果在一段时间内没有向服务器读或着写，则触发这个handler
		//5，5s内没有读
		//7，7s内没有写
		//10，10s内没有读也没有写
		channelPipeline.addLast(new IdleStateHandler(5,7,10, TimeUnit.SECONDS));

		channelPipeline.addLast(new MyHeartBeatServerHandler());
	}
}

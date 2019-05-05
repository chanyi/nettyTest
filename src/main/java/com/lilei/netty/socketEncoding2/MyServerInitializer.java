package com.lilei.netty.socketEncoding2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {


	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		ChannelPipeline channelPipeline = ch.pipeline();
		//解码器
		channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
		//编码器
		channelPipeline.addLast(new LengthFieldPrepender(4));

		channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

		//myself handler
		channelPipeline.addLast(new MyserverHandler());




	}
}

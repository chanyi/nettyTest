package com.lilei.netty.websocket5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyWebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {

		ChannelPipeline channelPipeline = ch.pipeline();
		channelPipeline.addLast(new HttpServerCodec());
		channelPipeline.addLast(new ChunkedWriteHandler());
		//把http请求聚合成一个fullhttprequest
		channelPipeline.addLast(new HttpObjectAggregator(8192));
		channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
		channelPipeline.addLast(new TextWebSocketFrameHandler());
	}
}

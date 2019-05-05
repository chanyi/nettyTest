package com.lilei.netty.chat3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("服务器端返回的消息是："+ msg+"\r\n");
	}
}
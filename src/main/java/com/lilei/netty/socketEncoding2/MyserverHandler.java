package com.lilei.netty.socketEncoding2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class MyserverHandler extends SimpleChannelInboundHandler<String> {


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

		//ChannelHandlerContext  里面存储的是 上下文相关的信息，比如远程的地址啊 连接对象是什么。
		//msg：就是客户端发送过来的对象

		System.out.print(ctx.channel().remoteAddress()+","+msg);

		//返回给客户端
		ctx.channel().writeAndFlush("server send msg to client:"+ UUID.randomUUID());


	}

	//用来捕获异常,如果捕获到异常，就关掉连接
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx ,Throwable cause) throws  Exception{
		cause.printStackTrace();;
		ctx.close();

	}
}

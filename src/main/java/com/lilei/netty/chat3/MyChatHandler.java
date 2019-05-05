package com.lilei.netty.chat3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatHandler extends SimpleChannelInboundHandler<String> {

	//用来存放channel 所有与服务端建立连接的客户端channel
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	//服务器端收到客户端的任何一个消息，read0方法都会被调用
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		//获取到发送消息本身的channel，就是发消息过来的channel
		Channel channel = ctx.channel();
		channelGroup.forEach(ch->{
			if(channel != ch){
				ch.writeAndFlush(channel.remoteAddress()+ " 发过来的消息："+msg +"\r\n");
			}else{
				ch.writeAndFlush("【自己】发的消息："+msg +"\r\n");
			}
		});
	}

	//客户端连接过来
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();

		channelGroup.writeAndFlush("客户端："+channel.remoteAddress()+"加入服务器"+"\r\n");
		channelGroup.add(channel);

	}

	//客户端断开连接
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();

		channelGroup.writeAndFlush("客户端："+channel.remoteAddress()+"离开服务器");
//		channelGroup.remove(channel);  netty会自动调用
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+"上线了");

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println(channel.remoteAddress()+"下线了");

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx ,Throwable cause) throws  Exception{

		cause.printStackTrace();
		ctx.close();

	}
}

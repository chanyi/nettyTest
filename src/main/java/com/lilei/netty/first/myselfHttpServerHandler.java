package com.lilei.netty.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class myselfHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

	@Override
	/**
	 * 读取客户端请求，并给客户端响应
	 */
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

		//获取远程地址
		System.out.print(ctx.channel().remoteAddress());

		if(msg instanceof  HttpRequest){
			ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);

			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);

			response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
			response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

			//返回给客户端
			ctx.writeAndFlush(response);
		}

	}
}

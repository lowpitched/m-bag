package com.mbag.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.mbag.log.LogTools;

public class SocketServer {

	private ServerSocket server;
	
	private String charset;
	
	private DealHandler handler;
	
	public SocketServer(int port,String charset,DealHandler handler){
		try {
			server = new ServerSocket(port);
			this.charset = charset;
			this.handler = handler;
		} catch (IOException e) {
			LogTools.e(this, e.getMessage());
		}
	}
	
	public void startServer(){
		while(true){
			try {
				Socket socket = server.accept();
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				RequestDeal.server(is,os, charset,handler);
			} catch (IOException e) {
				LogTools.e(this, e.getMessage());
			}
		}
	}
	
}

class RequestDeal implements Runnable{

	private String charset;
	
	private InputStream is;
	
	private OutputStream os;
	
	private static String requestMessage;
	
	private static String responseMessage;
	
	private DealHandler handler;
	
	public RequestDeal(InputStream is,OutputStream os,String charset,DealHandler handler){
		this.is = is;
		this.os = os;
		this.charset = charset;
		this.handler = handler;
	}
	
	
	@Override
	public void run() {
		try {
			byte[] buff = new byte[1024];
			int len;
			StringBuilder builder = new StringBuilder();
//			len = is.read(buff);
//			builder.append(new String(buff,0,len,charset));
			while((len=is.read(buff))!=-1){
				builder.append(new String(buff,0,len,charset));
			}
			requestMessage=builder.toString();
			responseMessage = handler.deal(requestMessage);
			os.write(responseMessage.getBytes(charset));
			os.flush();
			LogTools.i(RequestDeal.class, "server request message:"+requestMessage);
			LogTools.i(RequestDeal.class, "server response message:"+responseMessage);
		} catch (IOException e) {
			LogTools.e(this, e.getMessage());
		}finally{
			if(null!=is){
				try {
					is.close();
				} catch (IOException e) {
					LogTools.e(this, e.getMessage());
				}
			}
			if(null!=os){
				try {
					os.close();
				} catch (IOException e) {
					LogTools.e(this, e.getMessage());
				}
			}
		}
		
	}
	/*
	private byte[] formartLen(String requestMessage) {
		int length = requestMessage.getBytes().length;
		return FormatUtils.addZeroFront(10, length).getBytes();
	}
	*/

	public static void server(InputStream is,OutputStream os,String charset,DealHandler handler){
		RequestDeal deal = new RequestDeal(is,os,charset,handler);
		Thread thread = new Thread(deal);
		thread.start();
	}
	
}

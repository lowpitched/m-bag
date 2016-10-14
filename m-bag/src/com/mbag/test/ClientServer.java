package com.mbag.test;

import com.mbag.socket.SocketSender;

public class ClientServer {

	public static void main(String[] args) {
		SocketSender sender = new SocketSender("127.0.0.1",9999,1000*60*3,"gbk");
		sender.request("this is the request message");
	}
	
}

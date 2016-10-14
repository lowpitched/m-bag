package com.mbag.test;

import com.mbag.socket.DealHandler;
import com.mbag.socket.SocketServer;

public class ServerTest {

	public static void main(String[] args) {
		SocketServer server = new SocketServer(9999,"gbk",new DealHandler(){

			@Override
			public String deal(String request) {
				if(request.equals("this is the request message")){
					return "this is the response message";
				}
				return "wrong message";
			}
			
		});
		server.startServer();
	}
	
}

package com.mbag.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.mbag.log.LogTools;
import com.mbag.util.IOUtils;

public class SocketSender {

	private Socket socket;
	
	private String ip;
	
	private int port;
	
	private int outTime;
	
	private String charset;
	
	public SocketSender(String ip,int port,int outTime,String charset){
		this.ip=ip;
		this.port=port;
		this.outTime=outTime;
		this.charset=charset;
		try {
			socket = new Socket(this.ip,this.port);
			socket.setSoTimeout(this.outTime);
		} catch (UnknownHostException e) {
			LogTools.e(this, e.getMessage());
		} catch (IOException e) {
			LogTools.e(this, e.getMessage());
		}
	}
	
	public String request(String message){
		InputStream is = null;
		OutputStream os = null;
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			os.write(message.getBytes(charset));
			os.flush();
			String responseMessage = IOUtils.readInputStream(is, charset);
			LogTools.i(this, "client request message:"+message);
			LogTools.i(this, "client response message:"+responseMessage);
			return responseMessage;
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
			if(null!=socket){
				try {
					socket.close();
				} catch (IOException e) {
					LogTools.e(this, e.getMessage());
				}
			}
		}
		return "";
	}
}

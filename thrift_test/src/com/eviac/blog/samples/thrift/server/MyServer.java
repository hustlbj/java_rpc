package com.eviac.blog.samples.thrift.server;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import com.eviac.blog.samples.thrift.server.AdditionService;
import com.eviac.blog.samples.thrift.server.AdditionService.AsyncProcessor;

public class MyServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyServer.class.getName());
	
	public static void StartSimpleServer(AdditionService.Processor<AdditionServiceHandler> processor) {
		LOGGER.debug("entry");
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(
				new Args(serverTransport).processor(processor));
			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.debug("leave");
	}

	public static void main(String[] args) {
		StartSimpleServer(new AdditionService.Processor<AdditionServiceHandler>(new AdditionServiceHandler()));
	}
}
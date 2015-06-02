package com.lin.common.aio;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

public class SimpleServerTest {

	@Before
	public void testServer() throws IOException, InterruptedException {
		SimpleServer server = new SimpleServer(9021);
		Thread.sleep(10000);// 由于是异步操作，所以睡眠一定时间，以免程序很快结束
	}

	@Test
	public void testClient() throws IOException, InterruptedException,
			ExecutionException {
		SimpleClient client = new SimpleClient("localhost", 9021);
		client.write((byte) 11);
	}

}

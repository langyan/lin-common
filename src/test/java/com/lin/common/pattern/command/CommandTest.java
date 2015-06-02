/**
 * 
 */
package com.lin.common.pattern.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 命令模式：将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；对请求排队或记录请求日志， 以及支持可撤销的操作。
 * @author User
 * 
 */
public class CommandTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.lin.common.pattern.command.Command#execute()}.
	 */
	@Test
	public void testExecute() {
		Receiver re = new Receiver();

		Command c = new ConcreteCommand(re);

		Invoker i = new Invoker();
		i.setCommand(c);
		i.executeCommand();
	}

}

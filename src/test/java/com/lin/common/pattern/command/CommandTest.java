/**
 * 
 */
package com.lin.common.pattern.command;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 命令模式：将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化；对请求排队或记录请求日志， 以及支持可撤销的操作。
 * 
 * @author User
 * 
 */
public class CommandTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void setUp() throws Exception {}

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

    @Test
    public void getNetworkInterface() throws SocketException {

        Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
        while (nis.hasMoreElements()) {
            NetworkInterface ni = nis.nextElement();
            System.out.println(ni);
            System.out.println("displayname: " + ni.getDisplayName());

            System.out.println("name: " + ni.getName());

            System.out.println("MTU: " + ni.getMTU());

            System.out.println("Loopback: " + ni.isLoopback());

            System.out.println("Virtual: " + ni.isVirtual());

            System.out.println("Up: " + ni.isUp());

            System.out.println("PointToPoint: " + ni.isPointToPoint());
            System.out.println("-------------------------");
        }

    }

}

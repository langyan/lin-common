/**
 * 
 */
package com.lin.common.pattern.command;

/**
 * @author User
 * 
 */
public class Invoker {

	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void executeCommand() {
		command.execute();
	}

}

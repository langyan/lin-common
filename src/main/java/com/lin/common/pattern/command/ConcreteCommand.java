/**
 * 
 */
package com.lin.common.pattern.command;

/**
 * @author User
 * 
 */
public class ConcreteCommand extends Command {

	public ConcreteCommand(Receiver receriver) {
		super(receriver);
	}

	@Override
	public void execute() {
		receiver.action();
	}

}

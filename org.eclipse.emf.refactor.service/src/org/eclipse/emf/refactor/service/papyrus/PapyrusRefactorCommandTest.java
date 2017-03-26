package org.eclipse.emf.refactor.service.papyrus;

import org.eclipse.gef.commands.Command;

public class PapyrusRefactorCommandTest extends Command {
	
	private boolean hasCalledFirstUndo = false;
	private Command firstCommand;
	
	public PapyrusRefactorCommandTest(Command firstCommand) {
		this.firstCommand = firstCommand;
	}

	@Override
	public void undo() {
		if (!hasCalledFirstUndo) {
			hasCalledFirstUndo = true;
			firstCommand.undo();
		} else {
			super.undo();
		}
	}
	@Override
	public void execute() {
		System.out.println("Being executed");
		super.execute();
	}
	
	@Override
	public boolean canExecute() {
		System.out.println("test");
		return super.canExecute();
	}
}

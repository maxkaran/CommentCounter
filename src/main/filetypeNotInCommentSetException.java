package main;

public class filetypeNotInCommentSetException extends Exception {
	  public filetypeNotInCommentSetException(String message) { super(message); }
	  public filetypeNotInCommentSetException() { super("Behaviour for this file type has not been set up yet!"); }
}

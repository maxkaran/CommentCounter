package main;

public class notValidFiletypeException extends Exception {
	  public notValidFiletypeException(String message) { super(message); }
	  public notValidFiletypeException() { super("This file either does not have an extension or is a hidden file!"); }
}

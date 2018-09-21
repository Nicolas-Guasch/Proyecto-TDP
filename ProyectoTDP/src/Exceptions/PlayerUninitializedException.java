package Exceptions;

public class PlayerUninitializedException extends RuntimeException
{
    public PlayerUninitializedException(String s){
        super(s);
    }
}

package sivo5555;

public class BadCharException extends Exception
{
    public BadCharException()
    {
        super("Символ не принадлежит алфавиту");
    }
}

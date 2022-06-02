package sivo5555;

public class Chain
{
    private StringBuilder string;

    public Chain(String chain)
    {
        this.string = new StringBuilder(chain);
    }

    public void readMinor()
    {
        string.deleteCharAt(0);
    }

    public char readDigit()
    {
        char digit = string.charAt(0);
        string.deleteCharAt(0);
        return digit;
    }

    public boolean belongs(String pattern)
    {
        String first = string.substring(0,1);
        return pattern.contains(first);
    }
}

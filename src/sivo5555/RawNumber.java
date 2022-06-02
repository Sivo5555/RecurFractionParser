package sivo5555;

public class RawNumber
{
    private StringBuilder digits = new StringBuilder();

    public void append(char digit)
    {
        digits.append(digit);
    }

    public Integer toInt()
    {
        return Integer.parseInt(digits.toString());
    }
}

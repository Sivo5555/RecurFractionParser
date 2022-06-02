package sivo5555;

import java.util.ArrayList;

public class FractionBuilder
{
    private ArrayList<RawNumber> rawFinite;
    private ArrayList<RawNumber> rawPeriod;
    private ArrayList<RawNumber> curPart;

    private Chain chain;
    private RawNumber curNumber;

    private void leftRoundBr() throws BadCharException
    {
        String allowed = "(";
        if(!chain.belongs(allowed)) throw new BadCharException();
        chain.readMinor();
    }

    private void rightRoundBr() throws BadCharException
    {
        String allowed = ")";
        if(!chain.belongs(allowed)) throw new BadCharException();
        chain.readMinor();
    }

    private void leftSquareBr() throws BadCharException
    {
        String allowed = "[";
        if(!chain.belongs(allowed)) throw new BadCharException();
        chain.readMinor();
    }

    private void rightSquareBr() throws BadCharException
    {
        String allowed = "]";
        if(!chain.belongs(allowed)) throw new BadCharException();
        chain.readMinor();
    }

    private void separator1() throws BadCharException
    {
        String allowed = ";";
        if(!chain.belongs(allowed)) throw new BadCharException();
        chain.readMinor();
    }

    private void separator2() throws BadCharException
    {
        String allowed = ",";
        if(!chain.belongs(allowed)) throw new BadCharException();
        chain.readMinor();
    }

    private void digit1() throws BadCharException
    {
        String allowed = "123456789";
        if(!chain.belongs(allowed)) throw new BadCharException();
        curNumber.append(chain.readDigit());
    }

    private void digit() throws BadCharException
    {
        String allowed = "1234567890";
        if(!chain.belongs(allowed)) throw new BadCharException();
        curNumber.append(chain.readDigit());
    }

    private void nextDigit() throws BadCharException
    {
        String allowed = "1234567890";
        if(!chain.belongs(allowed)) throw new BadCharException();
        digit();
        numberEnd();
    }

    private void numberEnd() throws BadCharException
    {
        String allowed = "1234567890";
        if(chain.belongs(allowed))
        {
            nextDigit();
        }
        else
        {
            curPart.add(curNumber);
            curNumber = new RawNumber();
        }
    }

    private void number() throws BadCharException
    {
        String allowed = "123456789";
        if(!chain.belongs(allowed)) throw new BadCharException();
        digit1();
        numberEnd();
    }

    private void number1() throws BadCharException
    {
        String allowed1 = "0";
        String allowed2 = "123456789";
        if(chain.belongs(allowed1))
        {
            curNumber.append(chain.readDigit());
        }
        else if(chain.belongs(allowed2))
        {
            number();
        }
        else throw new BadCharException();
    }

    private void mark2() throws BadCharException
    {
        String allowed1 = ";";
        String allowed2 = ")";
        if(chain.belongs(allowed1))
        {
            separator1();
            period();
        }
        else if(chain.belongs(allowed2))
        {
            rightRoundBr();
        }
        else throw new BadCharException();
    }

    private void period() throws BadCharException
    {
        String allowed = "123456789";
        if(!chain.belongs(allowed)) throw new BadCharException();
        number();
        mark2();
    }

    private void mark1() throws BadCharException
    {
        String allowed1 = ",";
        String allowed2 = "]";
        if(chain.belongs(allowed1))
        {
            separator2();
            finish();
        }
        else if(chain.belongs(allowed2))
        {
            rightSquareBr();
        }
        else throw new BadCharException();
    }

    private void finish() throws BadCharException
    {
        String allowed1 = "(";
        String allowed2 = "123456789";
        if(chain.belongs(allowed1))
        {
            curPart = rawPeriod;
            leftRoundBr();
            period();
            rightSquareBr();
        }
        else if(chain.belongs(allowed2))
        {
            number();
            mark1();
        }
        else throw new BadCharException();
    }

    private void start() throws BadCharException
    {
        String allowed = "[";
        if(!chain.belongs(allowed)) throw new BadCharException();
        leftSquareBr();
        number1();
    }

    private void parse() throws BadCharException
    {
        String allowed = "[";
        if(!chain.belongs(allowed)) throw new BadCharException();
        start();
        separator1();
        finish();
    }

    public Fraction buildFraction(String input) throws BadCharException
    {
        rawFinite = new ArrayList<>();
        rawPeriod = new ArrayList<>();
        curPart = rawFinite;
        curNumber = new RawNumber();
        chain = new Chain(input);
        parse();
        return Fraction.fromRaw(rawFinite, rawPeriod);
    }
}

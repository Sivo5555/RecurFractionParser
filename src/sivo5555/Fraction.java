package sivo5555;

import java.util.ArrayList;

public class Fraction
{
    private ArrayList<Integer> finite;
    private ArrayList<Integer> period;

    public Fraction(ArrayList<Integer> finite)
    {
        this.finite = finite;
        period = new ArrayList<>();
    }

    public Fraction(ArrayList<Integer> finite, ArrayList<Integer> period)
    {
        this.finite = finite;
        this.period = period;
    }

    public static Fraction fromRaw(ArrayList<RawNumber> finite, ArrayList<RawNumber> period)
    {
        ArrayList<Integer> f = new ArrayList<>();
        for(RawNumber term : finite)
        {
            f.add(term.toInt());
        }
        ArrayList<Integer> p = new ArrayList<>();
        for(RawNumber term : period)
        {
            p.add(term.toInt());
        }
        return new Fraction(f, p);
    }

    public void addFiniteTerm(int term)
    {
        finite.add(term);
    }

    public void addPeriodTerm(int term)
    {
        period.add(term);
    }

    @Override
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        string.append("[");
        for(Integer term : finite)
        {
            string.append(term);
            string.append(",");
        }
        if(period.isEmpty())
        {
            string.deleteCharAt(string.lastIndexOf(","));
        }
        else
        {
            string.append("(");
            for (Integer term : period)
            {
                string.append(term);
                string.append(";");
            }
            string.deleteCharAt(string.lastIndexOf(";"));
            string.append(")");
        }
        string.append("]");
        return string.toString();
    }
}

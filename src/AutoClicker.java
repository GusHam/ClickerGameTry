import java.awt.event.ActionListener;

public class AutoClicker
{
    public AutoClicker(){}

    private int count;
    private int baseCost;
    private long cost;
    private double scale;
    private int power;

    public AutoClicker(int baseCost, double scale, int power)
    {
        this.count = 0;
        this.baseCost = baseCost;
        this.cost = baseCost;
        this.scale = scale;
        this.power = power;
    }

    public boolean buy(long points)
    {
        if (ClickerGUI.points >= cost)
        {
            count++;
            cost = Math.round(baseCost * Math.pow(scale, count));
            return true;
        }
        return false;
    }

    public int getPowerPerSecond()
    {
        return count * power;
    }

    public int getCount()
    {
        return count;
    }

    public long getCost()
    {
        return cost;
    }
}

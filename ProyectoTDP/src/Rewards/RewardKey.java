package Rewards;

public class RewardKey
{
    private static int x=0;
    private int ID =x++;
    private RewardKey(){}
    public static RewardKey get(){
        return new RewardKey();
    }
    public int hashCode(){
        return ID;
    }
    public boolean equals(Object obj) {
        return obj == this;
    }
    public String toString() {
        return "RewardKey ID: "+ID;
    }
}

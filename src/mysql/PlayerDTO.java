package mysql;

public class PlayerDTO {
    String name;
    int balance;
    int breweries;
    int shipping;
    int FieldPos;
    int jailcard;
    boolean jailed;
    int Jailturns;
    int TotalAssets;
    // int ExtraTurns; Behøves ikke hvis vi kun gemmer efter hver omgang.

    public PlayerDTO(String name, int balance, int breweries, int shipping, int FieldPos, int jailcard,
                     boolean jailed, int Jailturns,  int TotalAssets)
    {
        this.name = name;
        this.balance = balance;
        this.breweries = breweries;
        this.shipping = shipping;
        this.FieldPos = FieldPos;
        this.jailcard = jailcard;
        this.jailed = jailed;
        this.Jailturns = Jailturns;
        this.TotalAssets = TotalAssets;
    }

    public PlayerDTO(PlayerDTO p)
    {
        this.name = p.getName();
        this.balance = p.getBalance();
        this.breweries = p.getBreweries();
        this.shipping = p.getShipping();
        this.FieldPos = p.getFieldPos();
        this.jailcard = p.getJailcard();
        this.jailed = p.isJailed();
        this.Jailturns = p.getJailturns();
        this.TotalAssets = p.getTotalAssets();
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getBreweries() {
        return breweries;
    }

    public int getShipping() {
        return shipping;
    }

    public int getFieldPos() {
        return FieldPos;
    }

    public int getJailcard() {
        return jailcard;
    }

    public boolean isJailed() {
        return jailed;
    }

    public int getJailturns() {
        return Jailturns;
    }

    public int getTotalAssets() {
        return TotalAssets;
    }
}

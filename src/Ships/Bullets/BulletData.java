package Ships.Bullets;

import Game.Units.Team;

public class BulletData
{
    private Team Team;
    private float Damage;


    public Team getTeam() {
        return Team;
    }

    public void setTeam(Team team) {
        Team = team;
    }

    public float getDamage() {
        return Damage;
    }

    public void setDamage(float damage) {
        Damage = damage;
    }
}

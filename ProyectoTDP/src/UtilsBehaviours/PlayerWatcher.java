package UtilsBehaviours;

import Engine.Component;
import Entities.Ships.Player.PlayerShip;

public class PlayerWatcher extends Component {
    private final PlayerShip playerShip;
    private float last;

    public PlayerWatcher(PlayerShip playerShip) {
        this.playerShip = playerShip;
        last = 10f;
    }

    @Override
    public void update()
    {
        if(playerShip.data() == null) return;
        float recent = playerShip.data().getHealth();
        if(recent != last){
            playerShip.setLife(playerShip.data().getHealth());
            last = recent;
        }
    }
}

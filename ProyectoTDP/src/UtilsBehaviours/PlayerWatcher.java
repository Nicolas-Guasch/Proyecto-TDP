package UtilsBehaviours;

import Engine.Component;
import Entities.Ships.PlayerShip;

public class PlayerWatcher extends Component {
    private final PlayerShip playerShip;
    private float last;

    public PlayerWatcher(PlayerShip playerShip) {
        this.playerShip = playerShip;
        last = 10f;
    }

    @Override
    public void Update()
    {
        if(playerShip.data() == null) return;
        float recent = playerShip.data().getHealth();
        if(recent != last){
            playerShip.setLife(playerShip.data().getHealth());
            last = recent;
        }
    }
}

package EntitiesVisitor;

import ADTs.Vector2;
import Engine.EngineGetter;
import Entities.BarricadeBoth;
import Entities.Ships.Player.PlayerShip;
import Entities.Ships.ShipBoss;

public class BossVisitor extends VisitorEntity {

    private final ShipBoss ship;

    public BossVisitor(ShipBoss shipBoss) {
        this.ship = shipBoss;
    }

    @Override
    public void visit(PlayerShip playerShip) {
        playerShip.data().takeDamage(ship.data().getDamage());
    }


    public void visit(BarricadeBoth barricade){
        var barr_tr = barricade.referenced().transform();
        var repuls = getRepulsion(
                barr_tr.position(),
                ship.referenced().transform().position(),
                barr_tr.top()).withLength(10);

        ship.referenced().transform().moveTowards(repuls);
        Runnable rep = ()->{
            ship.referenced().transform().moveTowards(repuls);
        };
        EngineGetter.Instance().get().waitForFrames(rep,1);
        EngineGetter.Instance().get().waitForFrames(rep,2);
        EngineGetter.Instance().get().waitForFrames(rep,3);



    }

    private Vector2 getRepulsion(Vector2 repulsor, Vector2 repulsado, Vector2 repulsor_top){
        return repulsor_top.prod(repulsado.sub(repulsor).scalarProd(repulsor_top)).prod(-1);
    }

}

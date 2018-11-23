package EntitiesVisitor;

import ADTs.IVector2;
import ADTs.Vector2;
import Engine.Action;
import Engine.Components.Transform;
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
        Transform barr_tr = barricade.referenced().transform();
        IVector2 repuls = getRepulsion(
                barr_tr.position(),
                ship.referenced().transform().position(),
                barr_tr.top()).withLength(10);

        ship.referenced().transform().moveTowards(repuls);
        Action rep = ()->{
            ship.referenced().transform().moveTowards(repuls);
        };
        EngineGetter.Instance().get().waitForFrames(rep,1);
        EngineGetter.Instance().get().waitForFrames(rep,2);
        EngineGetter.Instance().get().waitForFrames(rep,3);



    }

    private IVector2 getRepulsion(IVector2 repulsor, IVector2 repulsado, IVector2 repulsor_top){
        return repulsor_top.prod(repulsado.sub(repulsor).scalarProd(repulsor_top)).prod(-1);
    }

}

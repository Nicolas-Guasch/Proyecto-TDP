package EntitiesVisitor;

import ADTs.Vector2;
import Entities.*;

import Entities.Ships.EnemyShip;


import Scripts.ThePusher;

public class Pusher extends VisitorEntity
{
    private float ratio;
    private Vector2 center;
    public Pusher(float ratio, Vector2 center) {
        this.ratio = ratio;
        this.center = center;
    }



    //@Override
    private void effect(Entity ent) {
        if(ent.referenced() == null || ent.referenced().transform() == null)return;
        var dist = center.distanceTo(ent.referenced().transform().position());
        if(dist<ratio)
        {
            var h = ent.data().getHealth();
            h*=0.5f;
            ent.data().setHealth(h);
            var effect = 1-((ratio-dist)/ratio);
            effect /= 2;
            effect += 0.5f;
            System.out.println("Pusher::effect "+dist+ " --- " + effect);
            // si tiende a 0 mas fuerte el efecto
            // si tiende a 1 dura menos
            int count = ((int)(ratio-dist)+5)*2;
            if(count > 20){
                count = 20;
            }
            Vector2 velocity = ent.referenced().transform().position().sub(center).withLength((ratio-dist)*8).withMaxLength(20);
            ThePusher.getInstance().add(ent.referenced().transform(),count,velocity,effect);
        }
        //TODO: quitar vida si esta en el reduced
    }

    public void visit(EnemyShip enemyShip){effect(enemyShip);}
    public void visit(BarricadeEnem barricade){effect(barricade);}
    public void visit(BarricadeBoth barricade){effect(barricade);}
    public void visit(EnemyBullet bullet){effect(bullet);}




}

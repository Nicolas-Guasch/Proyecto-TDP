package Ships.Bullets;

import Engine.Components.CollisionData;
import Engine.Components.Transform;
import Engine.Vector2;

public class SoloBullet extends AbstractBullet<SoloBullet>
{
    public static final int MaxHardness = 9;
    private Transform target;
    private boolean Hunting;
    private Vector2 currentDirection;
    private int hardness;
    private int counter;


    /**
     *
     * @param target To follow
     * @param hardness Must be in range[0,9]
     */
    public SoloBullet(Transform target, int hardness, BulletData data)
    {
        super(data);
        this.target = target;
        hardness = hardness > MaxHardness ? hardness : MaxHardness;
        this.hardness = (MaxHardness+2-hardness);
        Hunting = false;
    }

    @Override
    public void BeShooted(Vector2 Direction)
    {
        currentDirection = Direction;

        counter = 0;
        Hunting = true;
    }

    @Override
    public void Update()
    {
        if(Hunting && counter >= hardness*10)
        {
            counter = 0;
            float speed = currentDirection.length();
            currentDirection = target.getPosition().minus(transform().getPosition()).versor();
            currentDirection = currentDirection.prod(speed);
        }
        counter++;
    }

    @Override
    public void OnCollisionEnter(CollisionData data)
    {
        Destroy();//TODO: revisar que aun conserve la data del visitor
    }

    @Override
    public void Destroy()
    {
        //Explotar (quiza reemplazar por otro gameobject con un gif de explotar)
    }

    @Override
    public SoloBullet clone() {
        return new SoloBullet(target,hardness,data);
    }


}

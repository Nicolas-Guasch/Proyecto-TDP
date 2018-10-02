package Entities.Builders;

public interface IBulletBuilder<BulletType extends IBullet>
{

    void create();
    BulletType get();
    void assembleSprite();
    void assembleHitBox();
    void assembleBehaviours();
    void assembleData();

}

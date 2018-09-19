package Entities.Builders;

public interface IBulletBuilder<BulletType extends IBullet>
{

    void create();
    BulletType get();
    void assembleSprite();
    void assembleCollider();
    void assembleBehaviours();
    void assembleData();

}

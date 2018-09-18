package Entities.Builders.Directors;

import Entities.Builders.IBullet;
import Entities.Builders.IBulletBuilder;

public interface IBulletDirector<BulletType extends IBullet, BuilderType extends IBulletBuilder<BulletType>> {

    void setBuilder(BuilderType builder);

    void create();

    void assemble();

    BulletType get();
}

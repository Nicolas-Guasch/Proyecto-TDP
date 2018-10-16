package Entities.Builders;


import Engine.Components.Transform;
import Entities.Entity;

public interface IBullet
{
    Transform transform();
    Entity entity();
}

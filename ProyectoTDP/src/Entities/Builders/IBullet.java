package Entities.Builders;


import Engine.Components.ITransform;
import Entities.Entity;

public interface IBullet
{
    ITransform transform();
    Entity entity();
}

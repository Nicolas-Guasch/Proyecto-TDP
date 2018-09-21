package Entities.Builders;

import BufferSystem.IClonable;
import Engine.Components.Transform;
import Entities.Entity;

public interface IBullet
{
    Transform transform();
    Entity entity();
}

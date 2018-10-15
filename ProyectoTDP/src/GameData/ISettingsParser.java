package GameData;

import Entities.EntityData;

import java.awt.*;

public interface ISettingsParser
{
    EntityData getEntityData(EntityEnum ref);
    float getFloat(FloatEnum ref);
    int FPS();
    Dimension sizeWindow();

}


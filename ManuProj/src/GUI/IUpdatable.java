package GUI;

import Map.GraphicObject;
import Map.Map;
import Map.SuperMap;

public interface IUpdatable
{
    void update(Map map);

    void destroyMe(Map map);
}

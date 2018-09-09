package ParaTestear;

import Engine.Component;

public class AlwaysRotate extends Component {
    private float rot;

    public AlwaysRotate(float v) 
    {
        rot = v;        
    }

    @Override
    public void Update() {
        transform().rotate(rot);
    }
}

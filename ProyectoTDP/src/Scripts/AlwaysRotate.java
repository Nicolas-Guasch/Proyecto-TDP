package Scripts;

import Engine.Component;

public class AlwaysRotate extends Component {
    private float rot;

    public AlwaysRotate(float v) 
    {
        rot = v;
    }

    @Override
    public void Update() {
        transform().setTop(transform().top().rotate(rot*100f));
    }
}

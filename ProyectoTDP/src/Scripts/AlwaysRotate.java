package Scripts;

import Engine.Component;

public class AlwaysRotate extends Component {
    private float rot;

    public AlwaysRotate(float v) 
    {
        rot = v;
    }

    @Override
    public void update()
    {
        transform().setTop(transform().top().rot(rot/100f));
    }
}

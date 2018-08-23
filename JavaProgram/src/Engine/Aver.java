package Engine;

public class Aver extends Component
{

    public Aver(){

    }

    int cants = 0;

    public void Awake(){
        System.out.println("awake de a ver");
    }

    public void Start()
    {
        System.out.println("Start de a ver");
        Core.getInstance().waitForFrames(()->{System.out.print("frame2");},2);
    }

    public void Update(){
        if(cants < 10)
            System.out.println("update de A ver");
        cants++;
    }

    int cmcm = 0;
    public void FixedUpdate(float x)
    {
        if(cmcm  < 10){
            Transform t = transform();
            System.out.println("tr "+t);
            t.MoveTowards(Vector2.UP.sum(Vector2.LEFT));
            System.out.println("FU: "+x + " " + transform().getPosition());
        }

        cmcm ++;
    }

}

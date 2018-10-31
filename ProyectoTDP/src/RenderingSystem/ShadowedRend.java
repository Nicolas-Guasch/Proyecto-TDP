package RenderingSystem;

import Engine.GameObject;

public class ShadowedRend extends Renderizable
{


    private Renderizable shadow;
    public ShadowedRend(SpriteData data) {
        super(data);
        var sd = new SpriteData(data.getName()+"_shadow");
        shadow = new Renderizable(sd);
        GameObject.getRoot().addChild().addComponent(shadow);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void OnDestroy() {
        super.OnDestroy();
        if(shadow!=null && shadow.gameObject()!=null)
        shadow.gameObject().destroy();
    }



    @Override
    public void update() {
        super.update();
        if(transform()==null)return;
        if(!Sun.getInstance().daytime() || !visible){
            shadow.hide();
            return;
        }
        if (Sun.getInstance().daytime() && transform() != null) {
            shadow.show();
            shadow.transform().setPosition(transform().position().sum(Sun.getInstance().getLight()));
            shadow.transform().setZcomponent(transform().getZcomponent() - 3);
            shadow.transform().setTop(transform().top());
        }
    }

    @Override
    public void show(){
        super.show();
        if(Sun.getInstance().daytime())
        {
            shadow.show();
        }
    }

    @Override
    public void hide() {
        super.hide();
        shadow.hide();
    }


}

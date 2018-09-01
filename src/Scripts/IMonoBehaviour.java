package Scripts;

public interface IMonoBehaviour<E> {

    void Start();
    void Update();
    void FixedUpdate(float deltaTime);
    void copy(E s);
    E clone();
    String save();
    void load(String data);

}

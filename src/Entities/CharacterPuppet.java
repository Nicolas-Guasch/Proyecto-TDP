package Entities;

import Engine.MonoBehaviour;
import Engine.Vector2;


public abstract class CharacterPuppet<Son extends CharacterPuppet<Son>> extends MonoBehaviour<Son>
{
    public void Move(Vector2 direction)
    {
        //

    }

    public void Shoot(Vector2 direction)
    {

    }

}

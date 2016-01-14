package com.benstone.tankgame.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Ben on 1/10/2016.
 */
public class SpriteComponent implements Component
{
    // Contains image, position, rotation, and scale
    public Sprite sprite;

    public SpriteComponent(Texture inImg, float inPosX, float inPosY)
    {
        sprite = new Sprite(inImg);
        sprite.setPosition(inPosX - inImg.getWidth()/2, inPosY - inImg.getHeight()/2);
    }
}

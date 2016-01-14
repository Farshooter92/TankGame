package com.benstone.tankgame.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ben on 1/10/2016.
 */
public class LookAtTargetComponent implements Component
{
    public Vector2 target = Vector2.Zero;
}

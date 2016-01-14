package com.benstone.tankgame.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Ben on 1/10/2016.
 */
public class MovementComponent implements Component
{
    // Movement States
    public boolean moveRight = false;
    public boolean moveLeft = false;
    public boolean moveUp = false;
    public boolean moveDown = false;

    // Velocity
    public float speedSideToSide = 1f;
    public float speedForwardAndBack = 1f;

    public MovementComponent(float inSpeedSideToSide, float inSpeedForwardAndBack)
    {
        // Instantiate Parameters
        speedSideToSide = inSpeedSideToSide;
        speedForwardAndBack = inSpeedForwardAndBack;
    }
}

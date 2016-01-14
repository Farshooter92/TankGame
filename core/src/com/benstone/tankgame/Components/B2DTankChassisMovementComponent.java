package com.benstone.tankgame.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ben on 1/12/2016.
 */
public class B2DTankChassisMovementComponent implements Component
{
    // Movement States
    public boolean traverseRight = false;
    public boolean traverseLeft = false;
    public boolean moveForward = false;
    public boolean moveBackwards = false;

    // Applied Forces
    public float traverseSpeed = 1f;
    public float forwardAndBackwardSpeed = 1f;

    // Turret Movement Information
    public float turretTraversSpeed = 1f;
    public Vector2 targetDirection;



    public B2DTankChassisMovementComponent(float inTraverseSpeed, float inForwardAndBackWardSpeed)
    {
        traverseSpeed = inTraverseSpeed;
        forwardAndBackwardSpeed = inForwardAndBackWardSpeed;
    }

}

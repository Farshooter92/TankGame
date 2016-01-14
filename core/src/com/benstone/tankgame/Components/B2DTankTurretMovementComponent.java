package com.benstone.tankgame.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;

/**
 * Created by Ben on 1/12/2016.
 */
public class B2DTankTurretMovementComponent implements Component
{
    // Turret Movement Information
    public float turretTraversSpeed = 1f;
    public int targetX = 0;
    public int targetY = 0;

    public B2DTankTurretMovementComponent(float inTurretTraverseSpeed)
    {
        turretTraversSpeed = inTurretTraverseSpeed;
    }
}

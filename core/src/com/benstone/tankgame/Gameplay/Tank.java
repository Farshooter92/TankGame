package com.benstone.tankgame.Gameplay;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.benstone.tankgame.Components.B2DBodyComponent;
import com.benstone.tankgame.Components.B2DTankChassisMovementComponent;
import com.benstone.tankgame.Components.B2DTankTurretMovementComponent;
import com.benstone.tankgame.Components.B2DTurretBodyComponent;
import com.benstone.tankgame.Utils.Constants;
import com.benstone.tankgame.Utils.EntityMapper;

import java.util.ArrayList;

/**
 * Created by Ben on 1/12/2016.
 */
public class Tank
{


    // Entities
    private Entity chassis;
    private Entity turret;

    public Tank(Engine engine, World world, int posX, int posY, int chassisWidth, int chassisHeight, int turretWidth, int turretHieght,
                int barrelWidth, int barrelHeight, float chassisTraverseSpeed, float fowardAndBackwardSpeed, float turretTraverseSpeed)
    {
        // Initialize Entities
        chassis = new Entity();
        turret = new Entity();

        // Hold Components for later use
        B2DBodyComponent chassisB2DBC = new B2DBodyComponent(world, posX, posY, chassisWidth, chassisHeight,
                Constants.BIT_PLAYER, (short)(Constants.BIT_WALL | Constants.BIT_ENEMY), (short) 0);
        B2DTurretBodyComponent turretB2DBC = new B2DTurretBodyComponent(world, chassisB2DBC.body, posX, posY, turretWidth, turretHieght, barrelWidth, barrelHeight,
                Constants.BIT_PLAYER, (short)(Constants.BIT_WALL | Constants.BIT_ENEMY), (short) 0);

        // Add components to chassis entity
        chassis.add(chassisB2DBC);
        chassis.add(new B2DTankChassisMovementComponent(chassisTraverseSpeed, fowardAndBackwardSpeed));

        // Add components to turret entity
        turret.add(turretB2DBC);
        turret.add(new B2DTankTurretMovementComponent(turretTraverseSpeed));

        // Add all entities to engine
        addToEngine(engine);
    }

    public Tank(Engine engine, ArrayList<Component>chassisComponents, ArrayList<Component>turretComponents)
    {
        // Initialize Entities
        chassis = new Entity();
        turret = new Entity();

        // Add components to chassis entity
        for(int i = 0; i < chassisComponents.size(); i++)
        {
            chassis.add(chassisComponents.get(i));
        }

        // Add components to turret entity
        for(int i = 0; i < turretComponents.size(); i++)
        {
            turret.add(turretComponents.get(i));
        }

        // Add all entities to engine
        addToEngine(engine);
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Maintenance Methods								 //
    ///////////////////////////////////////////////////////////////////////////
    private void addToEngine(Engine engine)
    {
        // Add all the entities to the engine
        engine.addEntity(chassis);
        engine.addEntity(turret);
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Getters and Setters								 //
    ///////////////////////////////////////////////////////////////////////////
    public Entity getChassis()
    {
        return chassis;
    }

    public Entity getTurret()
    {
        return turret;
    }
}

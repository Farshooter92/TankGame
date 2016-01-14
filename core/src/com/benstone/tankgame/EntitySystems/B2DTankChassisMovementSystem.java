package com.benstone.tankgame.EntitySystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.benstone.tankgame.Components.*;
import com.benstone.tankgame.Utils.EntityMapper;

/**
 * Created by Ben on 1/12/2016.
 */
public class B2DTankChassisMovementSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities = null;

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        // Get all entities in the engine with a Render and Position Component
        entities = engine.getEntitiesFor(Family.all(B2DBodyComponent.class).all(B2DTankChassisMovementComponent.class).get());
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        if (entities != null)
        {
            for (int i = 0; i < entities.size(); i++)
            {
                // Get the playerEntity at index i
                Entity entity = entities.get(i);

                // Get the Components component from the playerEntity
                B2DTankChassisMovementComponent b2dmc = EntityMapper.B2D_TANK_CHASSIS_MOVEMENT_COMPONENT_MAPPER.get(entity);
                B2DBodyComponent b2dbc = EntityMapper.B2D_BODY_COMPONENT_MAPPER.get(entity);

                // Reset values so we don't move when nothing is pressed
                int traverseDir = 0;
                int forwardAndBackwardDir = 0;

                // Should I move to left
                if(b2dmc.traverseLeft)
                {
                    traverseDir += 1;
                }

                // Should I move to right
                if(b2dmc.traverseRight)
                {
                    traverseDir -= 1;
                }

                // Should I move up
                if(b2dmc.moveForward)
                {
                    forwardAndBackwardDir += 1;
                }

                // Should I move down
                if(b2dmc.moveBackwards)
                {
                    forwardAndBackwardDir -= 1;
                }

                // Get the world coordinates of a vector given the local coordinates.
                Vector2 velocityDirection = b2dbc.body.getWorldVector(new Vector2(0, 1));
                Vector2 linearVelocity = velocityDirection.scl(forwardAndBackwardDir * b2dmc.forwardAndBackwardSpeed);

                // Update Body Position
                b2dbc.body.setLinearVelocity(linearVelocity);

                b2dbc.body.setAngularVelocity(traverseDir * b2dmc.traverseSpeed);
            }
        }
    }
}


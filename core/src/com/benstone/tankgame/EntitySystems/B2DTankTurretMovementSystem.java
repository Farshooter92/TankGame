package com.benstone.tankgame.EntitySystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.benstone.tankgame.Components.B2DBodyComponent;
import com.benstone.tankgame.Components.B2DTankTurretMovementComponent;
import com.benstone.tankgame.Components.B2DTurretBodyComponent;
import com.benstone.tankgame.Utils.EntityMapper;
import static com.benstone.tankgame.Utils.Constants.PPM;

/**
 * Created by Ben on 1/12/2016.
 */
public class B2DTankTurretMovementSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities = null;

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        // Get all entities in the engine with a Render and Position Component
        entities = engine.getEntitiesFor(Family.all(B2DTurretBodyComponent.class).all(B2DTankTurretMovementComponent.class).get());
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
                B2DTankTurretMovementComponent b2dtmc = EntityMapper.B2D_TANK_TURRET_MOVEMENT_COMPONENT_MAPPER.get(entity);
                B2DTurretBodyComponent b2dbc = EntityMapper.B2D_TURRET_BODY_COMPONENT_MAPPER.get(entity);

                Vector2 target = new Vector2(b2dtmc.targetX, b2dtmc.targetY);
                // Scale it the PPM and double because B2D halves width
                Vector2 currPosition = b2dbc.body.getPosition().scl(PPM * 2);

                currPosition.y = Gdx.graphics.getHeight() - currPosition.y;

                float targetDirection = target.sub(currPosition).angle();
                float curDirection = b2dbc.body.getWorldVector(new Vector2(0, 1)).angle();

                float targetDirInRadians = ((targetDirection + 90) * MathUtils.degreesToRadians);

                b2dbc.body.setTransform(b2dbc.anchor.getPosition(), -targetDirInRadians);
                b2dbc.barrel.setTransform(b2dbc.body.getPosition(), -targetDirInRadians);


                //b2dtmc.chassisTurretJoint.setMotorSpeed(500);
                // b2dbc.body.setAngularVelocity(direction * b2dtmc.turretTraversSpeed);

            }
        }
    }
}

package com.benstone.tankgame.EntitySystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.benstone.tankgame.Components.MovementComponent;
import com.benstone.tankgame.Components.SpriteComponent;
import com.benstone.tankgame.Components.VelocityComponent;
import com.benstone.tankgame.Utils.EntityMapper;

/**
 * Created by Ben on 1/10/2016.
 */
public class MovementSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities = null;

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        // Get all entities in the engine with a Render and Position Component
        entities = engine.getEntitiesFor(Family.all(MovementComponent.class).all(SpriteComponent.class).all(VelocityComponent.class).get());
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
                MovementComponent mc = EntityMapper.MOVEMENT_COMPONENT__MAPPER.get(entity);
                SpriteComponent sc = EntityMapper.SPRITE_COMPONENT_MAPPER.get(entity);
                VelocityComponent vc = EntityMapper.VELOCITY_COMPONENT__MAPPER.get(entity);


                // Should I move to the right or the left
                if(mc.moveRight)
                {
                    // Move only if traverseLeft is also not true
                    if(!mc.moveLeft)
                    {
                        vc.x = mc.speedSideToSide;
                    }
                    else
                    {
                        vc.x = 0f;
                    }
                }
                else if(mc.moveLeft)
                {
                    vc.x = -mc.speedSideToSide;
                }
                else
                {
                    vc.x = 0f;
                }

                // Should I move Up or Down
                if(mc.moveUp)
                {
                    // Move only if traverseLeft is also not true
                    if(!mc.moveDown)
                    {
                        vc.y = mc.speedForwardAndBack;
                    }
                    else
                    {
                        vc.y = 0f;
                    }
                }
                else if(mc.moveDown)
                {
                    vc.y = -mc.speedForwardAndBack;
                }
                else
                {
                    vc.y = 0f;
                }

                sc.sprite.setPosition(
                        sc.sprite.getX() + vc.x * deltaTime,
                        sc.sprite.getY() + vc.y * deltaTime
                );
            }
        }
    }
}

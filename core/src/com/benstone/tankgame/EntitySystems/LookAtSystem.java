package com.benstone.tankgame.EntitySystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.benstone.tankgame.Components.LookAtTargetComponent;
import com.benstone.tankgame.Components.SpriteComponent;
import com.benstone.tankgame.Utils.EntityMapper;

/**
 * Created by Ben on 1/10/2016.
 */
public class LookAtSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities = null;

    @Override
    public void addedToEngine(Engine engine)
    {
        super.addedToEngine(engine);

        // Get all entities in the engine with a Render and Position Component
        entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).all(LookAtTargetComponent.class).get());
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

                // Get the render component from the playerEntity
                SpriteComponent sc = EntityMapper.SPRITE_COMPONENT_MAPPER.get(entity);
                LookAtTargetComponent latc = EntityMapper.LOOK_AT_TARGET_COMPONENT_MAPPER.get(entity);

                // Position of the sprite we are looking from
                Vector2 posItem = new Vector2(sc.sprite.getX() + sc.sprite.getOriginX(), sc.sprite.getY() + sc.sprite.getOriginY());

                // Offset it by 90 so it looks from the top of the image
                sc.sprite.setRotation(posItem.sub(latc.target).angle() + 90f);

            }
        }
    }
}

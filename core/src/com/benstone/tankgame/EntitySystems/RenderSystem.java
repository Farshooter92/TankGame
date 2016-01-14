package com.benstone.tankgame.EntitySystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.benstone.tankgame.Components.SpriteComponent;
import com.benstone.tankgame.Utils.EntityMapper;

/**
 * Created by Ben on 1/10/2016.
 */
public class RenderSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities = null;

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        // Get all entities in the engine with a Render and Position Component
        entities = engine.getEntitiesFor(Family.all(SpriteComponent.class).get());
    }

    public void Draw(SpriteBatch sb)
    {
        if (entities != null)
        {
            for (int i = 0; i < entities.size(); i++)
            {
                // Get the playerEntity at index i
                Entity entity = entities.get(i);
                // Get the render component from the playerEntity
                SpriteComponent sc = EntityMapper.SPRITE_COMPONENT_MAPPER.get(entity);

                sb.draw(sc.sprite, sc.sprite.getX(), sc.sprite.getY(),
                        sc.sprite.getWidth()/2, sc.sprite.getHeight()/2,
                        sc.sprite.getWidth(), sc.sprite.getHeight(),
                        sc.sprite.getScaleX(), sc.sprite.getScaleY(),
                        sc.sprite.getRotation());
            }
        }

    }
}

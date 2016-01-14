package com.benstone.tankgame.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.*;

import static com.benstone.tankgame.Utils.Constants.PPM;

/**
 * Created by Ben on 1/12/2016.
 */
public class B2DBodyComponent implements Component
{
    public Body body;

    public B2DBodyComponent()
    {
        body = null;
    }

    /**
     *
     * @param world
     * @param x scaled in method
     * @param y scaled in method
     * @param width scaled in method
     * @param height scaled in method
     * @param isStatic
     * @param canRotate
     */
    public B2DBodyComponent(World world, int x, int y, int width, int height, boolean isStatic, boolean canRotate)
    {
        BodyDef def = new BodyDef();

        // Is the body static
        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        // Set the bodes position
        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = canRotate;
        body = world.createBody(def);

        // Set the shape
        PolygonShape shape = new PolygonShape();
        // Remember that Box2D doubles width
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        body.createFixture(shape, 1.0f);
    }

    public B2DBodyComponent(final World world, int x, int y, int width, int height, boolean isStatic, boolean fixedRotation,
                            short cBits, short mBits, short gIndex)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = fixedRotation;
        bodyDef.position.set(x / PPM, y / PPM);

        if(isStatic) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        } else {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / PPM / 2, height / PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = cBits; // Is a
        fixtureDef.filter.maskBits = mBits; // Collides with
        fixtureDef.filter.groupIndex = gIndex;

        body = world.createBody(bodyDef).createFixture(fixtureDef).getBody();

    }

    public B2DBodyComponent(final World world, int x, int y, int width, int height,
                            short cBits, short mBits, short gIndex)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = false;
        bodyDef.position.set(x / PPM, y / PPM);

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / PPM / 2, height / PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = cBits; // Is a
        fixtureDef.filter.maskBits = mBits; // Collides with
        fixtureDef.filter.groupIndex = gIndex;

        body = world.createBody(bodyDef).createFixture(fixtureDef).getBody();

    }
}

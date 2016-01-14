package com.benstone.tankgame.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;

import static com.benstone.tankgame.Utils.Constants.PPM;

/**
 * Created by Ben on 1/13/2016.
 */
public class B2DTurretBodyComponent extends B2DBodyComponent
{
    public Body barrel;
    public Body anchor;

    public B2DTurretBodyComponent(final World world, Body inAnchor, int x, int y, int width, int height, int barrelWidth, int barrelHeight,
                            short cBits, short mBits, short gIndex)
    {
        super(world, x, y, width, height, cBits, mBits, gIndex);

        anchor = inAnchor;

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = false;
        bodyDef.position.set(x / PPM, y / PPM);

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();

        // Offset the shape for the barrel
        shape.setAsBox(barrelWidth / PPM / 2, barrelHeight / PPM / 2, new Vector2(0, width / PPM), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = cBits; // Is a
        fixtureDef.filter.maskBits = mBits; // Collides with
        fixtureDef.filter.groupIndex = gIndex;

        barrel = world.createBody(bodyDef).createFixture(fixtureDef).getBody();
    }
}

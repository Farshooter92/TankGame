package com.benstone.tankgame.Utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.benstone.tankgame.Components.*;

/**
 * Created by Ben on 1/10/2016.
 */
public class EntityMapper
{
    public static final ComponentMapper<MovementComponent> MOVEMENT_COMPONENT__MAPPER = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<VelocityComponent> VELOCITY_COMPONENT__MAPPER = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<SpriteComponent> SPRITE_COMPONENT_MAPPER = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<LookAtTargetComponent> LOOK_AT_TARGET_COMPONENT_MAPPER = ComponentMapper.getFor(LookAtTargetComponent.class);
    public static final ComponentMapper<B2DTankChassisMovementComponent> B2D_TANK_CHASSIS_MOVEMENT_COMPONENT_MAPPER = ComponentMapper.getFor(B2DTankChassisMovementComponent.class);
    public static final ComponentMapper<B2DTankTurretMovementComponent> B2D_TANK_TURRET_MOVEMENT_COMPONENT_MAPPER = ComponentMapper.getFor(B2DTankTurretMovementComponent.class);
    public static final ComponentMapper<B2DBodyComponent> B2D_BODY_COMPONENT_MAPPER = ComponentMapper.getFor(B2DBodyComponent.class);
    public static final ComponentMapper<B2DTurretBodyComponent> B2D_TURRET_BODY_COMPONENT_MAPPER = ComponentMapper.getFor(B2DTurretBodyComponent.class);
}

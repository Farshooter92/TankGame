package com.benstone.tankgame;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.benstone.tankgame.Components.B2DBodyComponent;
import com.benstone.tankgame.Components.B2DTankChassisMovementComponent;
import com.benstone.tankgame.Components.B2DTankTurretMovementComponent;
import com.benstone.tankgame.EntitySystems.B2DTankChassisMovementSystem;
import com.benstone.tankgame.EntitySystems.B2DTankTurretMovementSystem;
import com.benstone.tankgame.Gameplay.Tank;
import com.benstone.tankgame.Utils.Constants;
import com.benstone.tankgame.Utils.EntityMapper;

import java.util.ArrayList;

import static com.benstone.tankgame.Utils.Constants.PPM;

public class TankGame extends ApplicationAdapter implements InputProcessor{

	/**
	 * TODO
	 * Create a 4 walled arena
	 */

	// Rendering
	private SpriteBatch batch;

	// Engine
	private Engine engine;

	// Box2D
	private Box2DDebugRenderer b2dr;
	private World world;

	// Camera
	private OrthographicCamera camera;

	// Gameplay
	Tank player;

	// Entities

	// Commonly Used Components
	B2DTankChassisMovementComponent playerB2DCMC;
	B2DTankTurretMovementComponent playerB2DTMC;

	// Game Logic Data
	private Vector2 playerSpawn;
	private Vector2 enemySpawn;
	
	@Override
	public void create ()
	{
		// Used later in method
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// Register Listeners
		Gdx.input.setInputProcessor(this);

		// Initialize Engine
		engine = new Engine();

		// Initialize Rendering
		batch = new SpriteBatch();

		// Initialize Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);

		// Initialize Box2D
		world = new World(new Vector2(0, 0), false);
		b2dr = new Box2DDebugRenderer();

		// Initialize Game Data Logic
		playerSpawn = new Vector2(w/8, h/4);
		enemySpawn = new Vector2(w/3, h/4);

		// Initialize Gameplay
		// Player
		player = new Tank(engine, world, (int)playerSpawn.x, (int)playerSpawn.y, 32, 48, 16, 16, 4, 12, 5, 5, 5);
		playerB2DCMC = EntityMapper.B2D_TANK_CHASSIS_MOVEMENT_COMPONENT_MAPPER.get(player.getChassis());
		playerB2DTMC = EntityMapper.B2D_TANK_TURRET_MOVEMENT_COMPONENT_MAPPER.get(player.getTurret());

		ArrayList<Component> playerTanksTurretComponents = new ArrayList<Component>();

		// Initialize Enities
		Entity wallNorth = new Entity();
		Entity wallEast = new Entity();
		Entity wallSouth = new Entity();
		Entity wallWest = new Entity();

		// Add Components to entities
		wallNorth.add(new B2DBodyComponent(world, 7, (Gdx.graphics.getHeight() / 2) - 5, Gdx.graphics.getWidth() - 12, 12,
				true, true, Constants.BIT_WALL, (short)(Constants.BIT_PLAYER | Constants.BIT_ENEMY), (short) 0));

		wallEast.add(new B2DBodyComponent(world, Gdx.graphics.getWidth()/2 - 5, 1, 12, Gdx.graphics.getHeight(),
				true, true, Constants.BIT_WALL, (short)(Constants.BIT_PLAYER | Constants.BIT_ENEMY), (short) 0));

		wallSouth.add(new B2DBodyComponent(world, 7, 6, Gdx.graphics.getWidth() - 12, 12,
				true, true, Constants.BIT_WALL, (short)(Constants.BIT_PLAYER | Constants.BIT_ENEMY), (short) 0));

		wallWest.add(new B2DBodyComponent(world, 6, 1, 12, Gdx.graphics.getHeight(),
				true, true, Constants.BIT_WALL, (short)(Constants.BIT_PLAYER | Constants.BIT_ENEMY), (short) 0));

		// Add Entities to engine
		engine.addEntity(wallNorth);
		engine.addEntity(wallEast);
		engine.addEntity(wallSouth);
		engine.addEntity(wallWest);

		// Initialize Event Systems
		engine.addSystem(new B2DTankChassisMovementSystem());
		engine.addSystem(new B2DTankTurretMovementSystem());
	}

	///////////////////////////////////////////////////////////////////////////
	//						Core Game Loop									 //
	///////////////////////////////////////////////////////////////////////////
	@Override
	public void render ()
	{
		// Update the engine
		engine.update(Gdx.graphics.getDeltaTime());

		// Update Game Logic
		update(Gdx.graphics.getDeltaTime());

		// Double Screen Buffer
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Box2D debug renderer
		b2dr.render(world, camera.combined.scl(PPM));

		// Rendering
		batch.begin();
		batch.end();
	}

	public void update(float delta)
	{
		world.step(1 / 60f, 6, 2);
		cameraUpdate(delta);
	}

	public void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		// Position to track
		// position.x = playerTankChasis.getPosition().x * PPM;
		// position.y = playerTankChasis.getPosition().y * PPM;
		camera.position.set(position);

		camera.update();
	}

	///////////////////////////////////////////////////////////////////////////
	//						Maintenance Methods								 //
	///////////////////////////////////////////////////////////////////////////

	@Override
	public void dispose()
	{
		super.dispose();

		// Free up resources
		world.dispose();
		b2dr.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		camera.setToOrtho(false, width / 2, height / 2);
	}

	///////////////////////////////////////////////////////////////////////////
	//								Input									 //
	///////////////////////////////////////////////////////////////////////////

	@Override
	public boolean keyDown(int keycode)
	{


		// Set values on Key Down
		switch (keycode)
		{
			case Input.Keys.ESCAPE:
				Gdx.app.exit();
				break;
			case Input.Keys.W:
				playerB2DCMC.moveForward = true;
				break;
			case Input.Keys.A:
				playerB2DCMC.traverseLeft = true;
				break;
			case Input.Keys.S:
				playerB2DCMC.moveBackwards = true;
				break;
			case Input.Keys.D:
				playerB2DCMC.traverseRight = true;
				break;
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// Reset values on Key Up
		switch (keycode)
		{
			case Input.Keys.W:
				playerB2DCMC.moveForward = false;
				break;
			case Input.Keys.A:
				playerB2DCMC.traverseLeft = false;
				break;
			case Input.Keys.S:
				playerB2DCMC.moveBackwards = false;
				break;
			case Input.Keys.D:
				playerB2DCMC.traverseRight = false;
				break;
		}

		return true;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		playerB2DTMC.targetX = screenX;
		playerB2DTMC.targetY = screenY;
		return true;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
}

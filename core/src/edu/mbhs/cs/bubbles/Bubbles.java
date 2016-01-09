package edu.mbhs.cs.bubbles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Main Application: Bubbles Game
 */
public class Bubbles extends ApplicationAdapter {
	private static int BUBBLE_NUMBER = 5;
	private Bubble[] b = new Bubble[BUBBLE_NUMBER];
	private Stage stage;
	private World world;
	private OrthographicCamera cam;

	@Override
	public void create () {
		stage = new Stage();
		world = new World(new Vector2(0,0), false);
		Gdx.app.log("AssetPath", Gdx.files.internal("assets/bubble.jpg").file().getAbsolutePath());
		for(int i = 0; i < BUBBLE_NUMBER; i++){
			b[i] = new Bubble(world, stage.getWidth(), stage.getHeight());

			stage.addActor(b[i]);
		}
		//b[0] = new Bubble(world);
		//stage.addActor(b[0]);
		stage.addActor(new Player(world, stage.getWidth(), stage.getHeight()));


		//this edge detection doesn't work somebody fix it
		float with = Gdx.graphics.getWidth()/Bubble.METERS_TO_PIXELS;
        float hite = Gdx.graphics.getHeight()/Bubble.METERS_TO_PIXELS;
	    BodyDef bottom = new BodyDef();
	    bottom.type = BodyDef.BodyType.StaticBody;
	    bottom.position.set(0,0);
	    FixtureDef bot = new FixtureDef();
	    EdgeShape but = new EdgeShape();
	    but.set(new Vector2(-with, 0), new Vector2(with, 0));
	    bot.shape = but;
	    Body botEdge = world.createBody(bottom);
	    botEdge.createFixture(bot);
	    
	    BodyDef top = new BodyDef();
	    top.type = BodyDef.BodyType.StaticBody;
	    top.position.set(0, stage.getHeight() / Bubble.METERS_TO_PIXELS);
	    FixtureDef tip = new FixtureDef();
	    EdgeShape tup = new EdgeShape();
	    tup.set(new Vector2(-with, 0), new Vector2(with, 0));
	    tip.shape = tup;
	    Body topEdge = world.createBody(top);
	    topEdge.createFixture(tip);
	    
	    BodyDef left = new BodyDef();
	    left.type = BodyDef.BodyType.StaticBody;
	    left.position.set(0, 0);
	    FixtureDef lift = new FixtureDef();
	    EdgeShape luft = new EdgeShape();
	    luft.set(new Vector2(0, -hite), new Vector2(0, hite));
	    lift.shape = luft;
	    Body leftEdge = world.createBody(left);
	    leftEdge.createFixture(lift);
	    
	    BodyDef right = new BodyDef();
	    right.type = BodyDef.BodyType.StaticBody;
	    right.position.set(stage.getWidth() / Bubble.METERS_TO_PIXELS,0);
	    FixtureDef rot = new FixtureDef();
	    EdgeShape rut = new EdgeShape();
	    rut.set(new Vector2(0, -hite), new Vector2(0, hite));
	    rot.shape = rut;
	    Body rightEdge = world.createBody(right);
	    rightEdge.createFixture(rot);
	    
	    
	    but.dispose();
	    rut.dispose();
	    luft.dispose();
	    tup.dispose();
	    
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(1f/60f, 6, 2);
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}
}

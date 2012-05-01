package com.morlicando.d;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.ui.livewallpaper.BaseLiveWallpaperService;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WallpaperActivity extends BaseLiveWallpaperService {
	private int CAMERA_WIDTH = 480;
	private int CAMERA_HEIGHT = 720;
	private Camera manokamera ;
	private Scene manoscena;
	private ITextureRegion skyRegion;
	private BitmapTextureAtlas skyAtlas;
	private ITextureRegion cloudRegion;
	private BitmapTextureAtlas cloudAtlas;
	private ITextureRegion palmRegion;
	private BitmapTextureAtlas palmAtlas;
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		manokamera = new Camera(0,0,CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), manokamera);

	}

	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		skyAtlas = new BitmapTextureAtlas(this.getTextureManager(), 240, 320);
		skyRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(skyAtlas,this.getAssets(), "gfx/background.png", 0, 0);
		skyAtlas.load();
		cloudAtlas = new BitmapTextureAtlas(this.getTextureManager(), 240, 320);
		cloudRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(cloudAtlas,this.getAssets(), "gfx/sky.png", 0, 0);
		cloudAtlas.load();
		palmAtlas = new BitmapTextureAtlas(this.getTextureManager(), 240, 320);
		palmRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(palmAtlas,this.getAssets(), "gfx/palm.png", 0, 0);
		palmAtlas.load();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
		
	}

	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		manoscena = new Scene();
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT ,this.skyRegion, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,  this.cloudRegion, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0f, new Sprite(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT,  this.palmRegion, vertexBufferObjectManager)));

		manoscena.setBackground(autoParallaxBackground);
		pOnCreateSceneCallback.onCreateSceneFinished(manoscena);

		
	}

	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		pOnPopulateSceneCallback.onPopulateSceneFinished();
		
	}
    /** Called when the activity is first created. */

}
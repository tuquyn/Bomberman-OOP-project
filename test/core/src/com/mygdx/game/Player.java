package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;

import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;


public class Player extends Image {
    TextureAtlas atlasBlack;
    TextureRegion walk;
    TextureRegion currentFrame;
    Sprite player;
    Image img;
    Animation<TextureAtlas.AtlasRegion> ani;
    PlayerEnum color;
    float elapsedTime = 0;


    public Player(TextureRegion texture) {
//        player = new Sprite(walk);
        super(texture);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
        input();
//        setPosition(96,64);
//        Array<TextureAtlas.AtlasRegion> runningFrames = atlasBlack.findRegions("bomberman_walk");
//        ani = new Animation<>(1f/30f,runningFrames, Animation.PlayMode.NORMAL);



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ((TextureRegionDrawable)getDrawable()).draw(batch, getX(), getY(),
                getOriginX(),getOriginY(),
                getWidth(),getHeight(),
                getScaleX(), getScaleY(),
                getRotation());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        float STEP = 16f;

//        System.out.println(Gdx.graphics.getFramesPerSecond());

//        if(Gdx.input.isKeyPressed(Input.Keys.W));
//            this.setPosition(this.getX(), this.getY() + STEP);
//            player.setPosition(player.getX(), player.getY()+STEP);
////            System.out.println(this.getX());
//
//        if(Gdx.input.isKeyPressed(Input.Keys.S))
//            this.setPosition(this.getX(), this.getY() - STEP);
//            player.setPosition(player.getX(), player.getY() - STEP);
//
//        if(Gdx.input.isKeyPressed(Input.Keys.A))
//            this.setPosition(this.getX() - STEP, this.getY());
//            player.setPosition(player.getX() - STEP, player.getY());
//
//        if(Gdx.input.isKeyPressed(Input.Keys.D))
//            this.setPosition(this.getX() + STEP, this.getY());
//            player.setPosition(player.getX(), player.getY()+STEP);

    }

    public void position(int x, int y){
        setPosition(x, y);
//        player.setPosition(x, y);
    }

    public void input(){
        addListener(new InputListener(){
            public boolean keyDown(InputEvent event, int keycode){
                if(keycode == Input.Keys.D){
                    MoveByAction right = new MoveByAction();
                    right.setAmount(64f, 0f);
                    right.setDuration(1f/2f);
                    Player.this.addAction(right);

//                    Debugging
//                    String text = String.format("%s %f %f","D",Player.this.getX(),Player.this.getY());
//                    System.out.println(text);
                }
                if(keycode ==Input.Keys.W){
                    MoveByAction up = new MoveByAction();
                    up.setAmount(0f,64f);
                    up.setDuration(1f/2f);
                    Player.this.addAction(up);

//                    Debugging
//                    String text = String.format("%s %f %f","W",Player.this.getX(),Player.this.getY());
//                    System.out.println(text);
                }
                if(keycode == Input.Keys.S){
                    MoveByAction down = new MoveByAction();
                    down.setAmount(0f,-64f);
                    down.setDuration(1f/2f);
                    Player.this.addAction(down);

//                    Debugging
//                    String text = String.format("%s %f %f","S",Player.this.getX(),Player.this.getY());
//                    System.out.println(text);
                }
                if(keycode == Input.Keys.A){
                    MoveByAction left = new MoveByAction();
                    left.setAmount(-64f, 0f);
                    left.setDuration(1f/2f);
                    Player.this.addAction(left);

//                    Debugging
//                    String text = String.format("%s %f %f","A",Player.this.getX(),Player.this.getY());
//                    System.out.println(text);
                }

                return true;
            }
        });
    }
    @Override
    protected void positionChanged(){
//        player.setPosition(getX(),getY());
        super.positionChanged();
    }

    protected Sprite switchCharacter(PlayerEnum color) {
        switch (color){
            case BLACK:
                player = new Sprite(new Texture(Gdx.files.internal("sprite/character/bomberman_black/still/bomberman_still_1.png")));
                break;
            case BLUE:
                player = new Sprite(new Texture(Gdx.files.internal("sprite/character/bomberman_blue/still/bomberman_still_1.png")));
                break;
            case GREEN:
                player = new Sprite(new Texture(Gdx.files.internal("sprite/character/bomberman_green/still/bomberman_still_1.png")));
                break;
            case RED:
                player = new Sprite(new Texture(Gdx.files.internal("sprite/character/bomberman_red/still/bomberman_still_1.png")));
                break;
            case WHITE:
                player = new Sprite(new Texture(Gdx.files.internal("sprite/character/bomberman_white/still/bomberman_still_1.png")));
                break;
        }
        return player;
    }
}

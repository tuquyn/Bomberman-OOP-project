package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Items;
import com.mygdx.game.Stage.GameStage;
import com.mygdx.game.Stage.Solid;

//Animation order
//Bomb : 2 3 2 1 2 3 2 1 2
//Explosion: 1 2 3 4 3 2 1
public class Bomb extends Items {
    GameStage gameStage;
    Player player;
    TextureAtlas atlas;
    Animation<TextureAtlas.AtlasRegion> currentAni;
    float elapsedTime;

    int stage = 0;
    int x, y;

    public Bomb(Player player, GameStage gameStage) {

        this.player = player;
        atlas = new TextureAtlas(Gdx.files.internal("sprite_sheet/bomb_explosion/bomb_explosion.txt"));
        Array<TextureAtlas.AtlasRegion> bombNormal = atlas.findRegions("bomb_normal");
        currentAni = new Animation<>(1f / 15f, bombNormal.get(0));
        item = new Sprite(new TextureAtlas.AtlasSprite(bombNormal.get(0)));

        setBounds(item.getRegionX(), item.getRegionY(), item.getRegionWidth(), item.getRegionHeight());


        setTouchable(Touchable.enabled);
//        System.out.printf("%.5f %.5f", player.getX(), player.getY());
        this.x = (int) player.getX();
        this.y = (int) player.getY();
        borderX = this.x;
        borderY = this.y;
        borderWidth = 64;
        borderHeight = 64;

        item.setPosition(player.getX(), player.getY());

        currentAni = new Animation<TextureAtlas.AtlasRegion>(1f / 2f,
                atlas.findRegion("bomb_normal", 2),
                atlas.findRegion("bomb_normal", 3),
                atlas.findRegion("bomb_normal", 2),
                atlas.findRegion("bomb_normal", 1),
                atlas.findRegion("bomb_normal", 2),
                atlas.findRegion("bomb_normal", 3));

        this.gameStage = gameStage;
//        System.out.printf("%3.5f %3.5f", (float) gameStage.getBorderX(), (float) gameStage.getBorderY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime() * 2;
        this.stage += 1;

        batch.draw(currentAni.getKeyFrame(elapsedTime),
                item.getX(), item.getY(), item.getWidth(), item.getHeight());


        if (elapsedTime > 3) { // 3: after 3 second the bomb will explore
            System.out.printf("%d %d\n", x, y);
            addExplosion(this.x + 64, this.y, 1);
            addExplosion(this.x - 64, this.y, 1);
            addExplosion(x, y, 1);
            addExplosion(x, y, 0);
            addExplosion(this.x, this.y + 64, 0);
            addExplosion(this.x, this.y - 64, 0);

            // remove a bomb
            this.gameStage.detachBomb(this);
            this.addAction(Actions.removeActor());

        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private void addExplosion(int x, int y, int direction) {
        if (!checkValid(x, y)) return;

        player.getStage().addActor(new Explosion(x, y, direction, gameStage));
    }

    private boolean checkValid(int x, int y) {
        if (x - this.getHeight() - 3 > (int) (gameStage.getBorderX() + gameStage.getBorderHeight()))
            return false;

        if (x < gameStage.getBorderX() - 1)
            return false;

        if (y + 64 * 2 >= gameStage.getBorderY() + gameStage.getBorderWidth())
            return false;

        if (y < gameStage.getBorderY() - 1)
            return false;

        if (!checkSolid(x, y)) return false;

        return true;
    }

    private boolean checkSolid(int x, int y) {
        for (Items s : gameStage.getListSolid()) {
//            System.out.printf("minX: %d, maxX: %d, minY: %d, maxY: %d\n", s.getBorderX(), s.getBorderX() + s.getBorderWidth(), s.getBorderY(), s.getBorderY() + s.getBorderHeight());
            if (s.getBorderX() - 5 <= x && x <= s.getBorderX() + s.getBorderWidth() - 5
                    && s.getBorderY() - 5 <= y && y <= s.getBorderY() + s.getBorderHeight() - 5)
                return false;
        }

        return true;
    }
}
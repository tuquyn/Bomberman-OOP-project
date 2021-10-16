package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.AbstractScreen;

public class ScreenManager {
//    Singleton: unique instance: to prevent users/ devs to accidentally create many
//    screen managers
    private static ScreenManager instance;

//    Reference to game
    private Game game;

//    Singleton: private constructor
    private ScreenManager(){
        super();
    }

//    Singleton: retrieve instance
    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

//    Initialization with the Game class
    public void initialize(Game game){
        this.game = game;
    }

//    Show in the game the screen which enum type is received
    public void showScreen(ScreenEnum screenEnum, Object...params){
//        Get current screen to dispose it
        Screen currentScreen = game.getScreen();
//        Show new screen
        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);
//        Dispose previous screen
        if(currentScreen != null){
            currentScreen.dispose();
        }
    }
}

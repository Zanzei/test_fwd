/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Constants;

/**
 *
 * @author Andre
 */
public final class BoardConstants {
    //Board Logic
    public static final Integer INITIAL_ROW_VALUE = 99;
    public static final Integer INITIAL_COL_VALUE = 99;
    
    //Messages
    public static final String START_BUTTON_MESSAGE = "PLAY";
    public static final String BOARD_SPINNERS_MESSAGE = "Configuration";
    public static final String BOARD_SIZE_SPINNER_MESSAGE = "Choose the size of the board";
    public static final String PLAYER_COUNT_SPINNER_MESSAGE = "Choose the amount of players";
    public static final String NEW_TURN_MESSAGE = "'s Turn! Your symbol is : ";
    public static final String STARTING_PLAY_MESSAGE = "Click 'PLAY' To Start";
    public static final String USE_DEFAULT_PLAYER_MESSAGE = "Using default player names and symbols";
    public static final String PLAYER_CUSTOMIZATION_MESSAGE = "Please Enter Player Names and Choose Symbol";
    
    //Board UI
    public static final String BOARD_TITLE = "Tic Tac Toe";
    public static final String DEFAULT_PLAYER1_NAME = "Player 1";
    public static final String DEFAULT_PLAYER2_NAME = "Player 2";
    public static final String SYMBOL_FONT_TYPE = "Arial";
    public static final Integer SYMBOL_FONT_SIZE = 12;
    public static final Integer BOARD_SIZE = 400;
    public static final String DEFAULT_PLAYER_NAME_PREFIX = "Player ";
    public static final Integer PLAYER_NAME_MAX_CHARACTER = 10;
    public static final String NAME_CUSTOMIZATION_LABEL_PREFIX = "NAME_";
    public static final String SYMBOL_CUSTOMIZATION_PREFIX = "SYMBOL_";
    
    //Board Panels
    public static final String UPPER_PANEL_NAME = "North";
    public static final String MIDDLE_PANEL_NAME = "Center";
    public static final String BOTTOM_PANEL_NAME = "South";
}

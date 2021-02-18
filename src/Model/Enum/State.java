/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Enum;

/**
 *
 * @author Andre
 */
public enum State {
    DRAW("Draw!"),
    WINNER(" is the winner!");

    private final String message;

    State(String message){
         this.message = message;
    }
     
    public String getMessage(){
        return message;
    }
}

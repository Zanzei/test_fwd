/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Enum.Symbol;

/**
 *
 * @author Andre
 */
public class Player {

    private Integer id;
    private String name;
    private Symbol character;

    public Player() {}
    
    public Player(Integer id, String name, Symbol character) {
        this.id = id;
        this.name = name;
        this.character = character;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getCharacter() {
        return character;
    }

    public void setCharacter(Symbol character) {
        this.character = character;
    }
}

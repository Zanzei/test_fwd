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
public enum Symbol {
    X("X"),
    O("O"),
    Q("Q"),
    M("M"),
    A("A"),
    B("B"),
    P("P"),
    C("C"),
    D("D"),
    E("E"),
    BLANK("");

    private final String symbolString;

    Symbol(String symbolString) {
        this.symbolString = symbolString;
    }

    public String getSymbolString() {
        return symbolString;
    }

    @Override
    public String toString() {
        if (symbolString.equalsIgnoreCase(BLANK.getSymbolString())) {
            return null;
        }
        return symbolString;
    }
}

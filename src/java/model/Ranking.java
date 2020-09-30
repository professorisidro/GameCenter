/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author profpa2
 */
public class Ranking implements java.io.Serializable {
    private int    numSeq;
    private String email;
    private float  score;
    private Game   game;

    /**
     * @return the numSeq
     */
    public int getNumSeq() {
        return numSeq;
    }

    /**
     * @param numSeq the numSeq to set
     */
    public void setNumSeq(int numSeq) {
        this.numSeq = numSeq;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    
}

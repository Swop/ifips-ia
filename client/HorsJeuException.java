/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author swop
 */
public class HorsJeuException extends Exception {

    /**
     * Creates a new instance of <code>HorsJeuException</code> without detail message.
     */
    public HorsJeuException() {}


    /**
     * Constructs an instance of <code>HorsJeuException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public HorsJeuException(String msg) {
        super(msg);
    }
}

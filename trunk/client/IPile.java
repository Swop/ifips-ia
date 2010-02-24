/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.EmptyStackException;

/**
 *
 * @author swop
 */
public interface IPile {
    public Object empiler(Object item);
    public Object depiler() throws EmptyStackException;
    public Object sommet() throws EmptyStackException;
    public boolean estVide();
}
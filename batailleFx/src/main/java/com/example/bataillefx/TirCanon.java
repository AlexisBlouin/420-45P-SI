package com.example.bataillefx;

/**
 * Fonction permettant de faire des tests pour les tirs de missiles.
 */
public class TirCanon {

    /**
     * Fonction modifiant la grille du joueur ou celle de l'ennemi avec la position du tir.
     * @param grille Grille a modifier.
     * @param l Ligne du tir.
     * @param c Colonne du tir.
     */
    public void Tir(int[][] grille, int l, int c){
        grille[l][c] = 6;
    }

    /**
     * Fonction testant si la partie est terminee.
     * @param grille Grille a tester.
     * @return Si la partie est terminee.
     */
    public boolean vainqueur(int[][] grille){
        for(int l = 0; l < 10; l++){
            for(int c = 0; c < 10; c++){
                if(grille[l][c] != 0 && grille[l][c] != 6){
                    return false;
                }
            }
        }
        return true;
    }
}

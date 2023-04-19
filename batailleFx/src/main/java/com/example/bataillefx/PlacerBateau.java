package com.example.bataillefx;

/**
 * Classe contenant les fonctions pour placer les bateaux dans les grilles backends.
 */
public class PlacerBateau {
    /**
     * Vérifie si la position fonctionne pour l'ajout d'un bateau.
     * @param grille La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l Le numéro de la ligne du bateau.
     * @param c Le numéro de la colonne du bateau.
     * @param d La direction du bateau.
     * @param t Le nombre de cases du bateau.
     * @return Le résultat disant si cette position fonctionne.
     */
    public boolean posOk(int [][]grille, int l, int c, int d, int t){
        boolean fonctionne;

        if(d == 1){
            fonctionne = ligneOk(grille, l, c, t);
        }
        else{
            fonctionne = colonneOk(grille, l, c, t);
        }

        return fonctionne;
    }

    /**
     * Teste si le bateau peut se poser sur une ligne.
     * @param grille La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l Le numéro de la ligne du bateau.
     * @param c  Le numéro de la colonne du bateau.
     * @param t Le nombre de cases du bateau.
     * @return Le résultat disant si cette position fonctionne.
     */
    public boolean ligneOk(int [][]grille, int l, int c, int t){
        boolean fonctionne = true;
        int indice = c;

        while(indice < 10 && indice < c + t && grille[l][indice] == 0){
            indice++;
        }

        if(indice < c + t){
            fonctionne = false;
        }

        return fonctionne;
    }

    /**
     * Teste si le bateau peut se poser sur une colonne.
     * @param grille La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l Le numéro de la ligne du bateau.
     * @param c Le numéro de la colonne du bateau.
     * @param t Le nombre de cases du bateau.
     * @return Le résultat disant si cette position fonctionne.
     */
    public boolean colonneOk(int [][]grille, int l, int c, int t){
        boolean fonctionne = true;
        int indice = l;

        while(indice < 10 && indice < l + t && grille[indice][c] == 0){
            indice++;
        }

        if(indice < l + t){
            fonctionne = false;
        }

        return fonctionne;
    }

    /**
     * Écrit dans la grille la position du bateau reçue
     * @param grille La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l Le numéro de la ligne du bateau.
     * @param c Le numéro de la colonne du bateau.
     * @param d La direction du bateau.
     * @param t Le nombre de cases du bateau.
     * @param n Le numéro du bateau à écrire.
     */
    public void ecrireDansGrille(int[][] grille, int l, int c, int d, int t, int n){
        if(d == 1){
            for(int i = c; i < c + t; i++){
                grille[l][i] = n;
            }
        }
        else {
            for(int i = l; i < l + t; i++){
                grille[i][c] = n;
            }
        }
    }
}

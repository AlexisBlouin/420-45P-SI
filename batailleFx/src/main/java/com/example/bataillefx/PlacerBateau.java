package com.example.bataillefx;

public class PlacerBateau {
    public static boolean posOk(int [][]grille, int l, int c, int d, int t){
        boolean fonctionne;

        if(d == 1){
            fonctionne = ligneOk(grille, l, c, t);
        }
        else{
            fonctionne = colonneOk(grille, l, c, t);
        }

        return fonctionne;
    }

    public static boolean ligneOk(int [][]grille, int l, int c, int t){
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

    public static boolean colonneOk(int [][]grille, int l, int c, int t){
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

    public static void ecrireDansGrille(int[][] grille, int l, int c, int d, int[] t, int n){
        if(d == 1){
            for(int i = c; i < c + t[n - 1]; i++){
                grille[l][i] = n;
            }
        }
        else {
            for(int i = l; i < l + t[n - 1]; i++){
                grille[i][c] = n;
            }
        }
    }
}

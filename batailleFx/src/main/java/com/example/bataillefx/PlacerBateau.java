package com.example.bataillefx;

public class PlacerBateau {
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

    public void ecrireDansGrille(int[][] grille, int l, int c, int d, int t, int n){
        if(d == 1){
            System.out.println("Yoy");
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

    public void afficherGrille(int[][] grille){
        System.out.println("  A B C D E F G H I J");

        for(int l = 0; l < 10; l++){
            System.out.print(l);
            for(int c = 0; c < 10; c++){
                System.out.print(" " + grille[l][c]);
            }

            System.out.println();
        }

        System.out.println();
    }
}

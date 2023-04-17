package com.example.bataillefx;

import java.util.Random;

public class TirCanon {

    int[][] grilleJeu;
    int[][] grilleOrdi;

    public int randRange (int a , int b){
        Random rand = new Random();
        return rand.nextInt(b - a)+ a;
    }

    public void tirOrdinateur(){
        int l;
        int c;
        char colonneChar;

        l = randRange(0, 10);
        c = randRange(0, 10);
        colonneChar = (char)(c + 65);

        System.out.println('\n' + "C'est au tour de l'ordinateur." + '\n');
        System.out.println("Tir de l'ordinateur : ");
        System.out.println("Colonne : " + colonneChar + ", Ligne : " + l + '\n');

        mouvement(grilleJeu, l, c, "GrilleJoueur");

        /*if(!partieFinie){
            System.out.println('\n' + "Votre grille apres le tir : ");
            //afficherGrille(grilleJeu);
        }*/
    }

    /*public static void tirJoueur(){
        int c;
        int l;
        char colonneChar;

        System.out.println("C'est votre tour!" + '\n');
        c = demanderColonne(false, 0);
        l = demanderLigne(false, 0);

        colonneChar = (char)(c + 65);

        System.out.println("Votre tir : ");
        System.out.println("Colonne : " + colonneChar + ", Ligne : " + l + '\n');

        mouvement(grilleOrdi, l, c);
    }*/

    public int mouvement(int[][] grille, int l, int c, String typeGrille){

        if(grille[l][c] >= 1 && grille[l][c] <= 5){
            int numeroBateau = grille[l][c];
            boolean bateauCoule = false;
            String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
            String nomBateauVise = nomBateau[grille[l][c] - 1];
            grille[l][c] = 6;

            if(couler(grille, numeroBateau)){
                bateauCoule = true;
            }

            if(!bateauCoule){
                //System.out.println("Touche : " + nomBateauVise);
                return 1;
            }
            else{
                System.out.println("Coule : " + nomBateauVise);

                if(vainqueur(grille)){
                    //if(grille == grilleOrdi){
                    /*if(typeGrille == "GrilleOrdi"){
                        System.out.println('\n' + "Vous avez gagne!" + '\n');
                        System.out.println("Tous les bateaux ennemis sont coules : ");
                        //afficherGrille(grilleOrdi);
                        System.out.println("Voici ce qu'il reste de votre grille : ");
                        //afficherGrille(grilleJeu);
                    }
                    else{
                        System.out.println('\n' + "Vous avez perdu..." + '\n');
                        System.out.println("Tous vos bateaux sont coule : ");
                        //afficherGrille(grilleJeu);
                        System.out.println("Voici ce qu'il reste de la grille ennemie : ");
                        //afficherGrille(grilleOrdi);
                    }*/
                    return 3;
                    //partieFinie = true;
                }
                else{
                    //System.out.println("Tous les bateaux ne sont pas coules." + '\n');
                    return 2;
                }
            }
        }
        else{
            //System.out.println("A l'eau");

            grille[l][c] = 6;
            return 0;
        }

    }

    public static boolean couler(int[][] grille, int n){
        for(int l = 0; l < 10; l++){
            for(int c = 0; c < 10; c++){
                if(grille[l][c] == n){
                    return false;
                }
            }

        }

        return true;
    }

    public static boolean vainqueur(int[][] grille){
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

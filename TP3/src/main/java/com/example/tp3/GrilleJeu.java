package com.example.tp3;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Classe contenant la grille backend du jeu.
 */
public class GrilleJeu {
    /**
     * Grille backend du jeu.
     */
    int[][] grille = new int[3][3];

    /**
     * Variable contenant le nombre de cases du jeu.
     */
    static int nombreCasesJeu = 3;

    /**
     * Cette fonction permet de tester si la position sélectionnée par l'ordi ou par le joueur est valide.
     * @param grille Grille contenant les informations de la partie.
     * @param x Position en X
     * @param y Position en Y
     * @return Si la position est valide ou non.
     */
    public boolean TesterEmplacement(int[][] grille, int x, int y){
        if(grille[x][y] == 0){
            return true;
        }
        return false;
    }

    /**
     * Cette fonction permet de modifier la grille une fois que la position a été acceptée.
     * @param grille Grille contenant les informations de la partie.
     * @param num Numéro à mettre dans la grille (1 ou 2)
     * @param x Position en X
     * @param y Position en Y
     */
    public void ModifierGrille(int[][] grille, int num, int x, int y){
        grille[x][y] = num;
    }

    /**
     * <pre>
     *     Fonction testant si la partie est terminée (ligne de trois signes pareils)
     *     J'ai utiliser la page Algo TicTacToe pour m'aider dans l'élaboration de l'algorithme.
     * </pre>
     * @param grille Grille contenant les informations de la partie.
     * @param num Numéro à mettre dans la grille (1 ou 2)
     * @param x Position en X
     * @param y Position en Y
     * @return Si la partie est terminée ou non.
     * @see <a href="https://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over">Algo TicTacToe</a>
     */
    //https://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over
    public boolean TesterFinDePartie(int[][] grille, int num, int x, int y){
        //Teste les colonnes.
        for(int i = 0; i < nombreCasesJeu; i++){
            if(grille[i][y] != num){
                break;
            }
            if(i == nombreCasesJeu - 1){
                System.out.println("Colonne");
                return true;
            }
        }

        //Teste les lignes.
        for(int i = 0; i < 3; i++){
            if(grille[x][i] != num){
                break;
            }
            if(i == nombreCasesJeu - 1){
                System.out.println("Ligne");
                return true;
            }
        }

        //teste la diagonale 1.
        if(x == y){
            for(int i = 0; i < nombreCasesJeu; i++){
                if(grille[i][i] != num)
                    break;
                if(i == nombreCasesJeu - 1){
                    System.out.println("Diag1");
                    return true;
                }
            }
        }

        //Teste la diagonale 2.
        if(x + y == nombreCasesJeu - 1){
            for(int i = 0; i < nombreCasesJeu; i++){
                if(grille[i][(nombreCasesJeu - 1) - i] != num)
                    break;
                if(i == nombreCasesJeu - 1){
                    System.out.println("Diag2");
                    return true;
                }
            }
        }

        return false;
    }

    public void AfficherGrille(){
        for(int i = 0; i < 3; i++){
            for(int ii = 0; ii < 3; ii++){
                System.out.print(grille[i][ii] + " ");
            }
            System.out.println();
        }
    }
}

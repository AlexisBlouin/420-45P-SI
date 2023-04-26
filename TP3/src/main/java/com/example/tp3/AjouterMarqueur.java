package com.example.tp3;

public class AjouterMarqueur {
    static int largeurJeu = 3;

    public boolean TesterEmplacement(int[][] grille, int x, int y){
        if(grille[x][y] == 0){
            return true;
        }
        return false;
    }

    public void ModifierGrille(int[][] grille, int num, int x, int y){
        grille[x][y] = num;
    }

    //https://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over
    public boolean TesterFinDePartie(int[][] grille, int num, int x, int y){
        for(int i = 0; i < largeurJeu; i++){
            if(grille[i][y] != num){
                break;
            }
            if(i == largeurJeu - 1){
                System.out.println("Colonne");
                return true;
            }
        }

        for(int i = 0; i < 3; i++){
            if(grille[x][i] != num){
                break;
            }
            if(i == largeurJeu - 1){
                System.out.println("Ligne");
                return true;
            }
        }

        if(x == y){
            for(int i = 0; i < largeurJeu; i++){
                if(grille[i][i] != num)
                    break;
                if(i == largeurJeu - 1){
                    System.out.println("Diag1");
                    return true;
                }
            }
        }

        if(x + y == largeurJeu - 1){
            for(int i = 0; i < largeurJeu; i++){
                if(grille[i][(largeurJeu - 1) - i] != num)
                    break;
                if(i == largeurJeu - 1){
                    System.out.println("Diag2");
                    return true;
                }
            }
        }

        return false;
    }
}

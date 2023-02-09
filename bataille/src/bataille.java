import java.util.Random;

/**
 * @author AlexisBlouin
 * @since 5 fevrier 2023
 *
 */
public class bataille {
    public static int [][]grilleOrdi = new int[10][10];
    public static int [][]grilleJeu = new int[10][10];

    public static void main(String[] args){
        initGrilleOrdi();
        afficherGrilleOrdi();
    }

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

        //Teste si les cases nécessaires au bateau sont inoccupées et s'assure de ne pas sortir de la grille.
        while(indice < 10 && indice < c + t && grille[l][indice] == 0){
            indice++;
        }
        //Si l'indice c'est rendu jusqu'à la dernière case du bateau, le positionnement fonctionne.
        if(indice < c + t){
            fonctionne = false;
        }
        return fonctionne;
    }

    public static boolean colonneOk(int [][]grille, int l, int c, int t){
        boolean fonctionne = true;
        int indice = l;

        //Teste si les cases nécessaires au bateau sont inoccupées et s'assure de ne pas sortir de la grille.
        while(indice < 10 && indice < l + t && grille[indice][c] == 0){
            indice++;
        }
        //Si l'indice c'est rendu jusqu'à la dernière case du bateau, le positionnement fonctionne.
        if(indice < l + t){
            fonctionne = false;
        }
        return fonctionne;
    }

    public static Random rand = new Random();
    public static int randRange (int a , int b){
        return rand.nextInt(b - a)+ a;
    }

    public static void initGrilleOrdi(){
        int[] grandeurBateau = new int[] {5, 4, 3, 3, 2};
        int ligne;
        int colonne;
        int direction;
        for(int numeroBateau = 1; numeroBateau <= 5; numeroBateau++){

            //Normallement a 10
            //ligne = randRange(0, 5);
            //colonne = randRange(0, 5);
            //direction = randRange(1, 3);

            do {
                ligne = randRange(0, 10);
                colonne = randRange(0, 10);
                direction = randRange(1, 3);
            }
            while (!posOk(grilleOrdi, ligne, colonne, direction, grandeurBateau[numeroBateau - 1]));

            if(direction == 1){
                for(int i = colonne; i < colonne + grandeurBateau[numeroBateau - 1]; i++){
                    grilleOrdi[ligne][i] = numeroBateau;
                }
                //afficherGrilleOrdi();
            }
            else {
                for(int i = ligne; i < ligne + grandeurBateau[numeroBateau - 1]; i++){
                    grilleOrdi[i][colonne] = numeroBateau;
                }
                //afficherGrilleOrdi();
            }
        }
    }

    public static void afficherGrilleOrdi(){
        System.out.println("  A B C D E F G H I J");
        for(int l = 0; l < 10; l++){
            System.out.print(l);
            for(int c = 0; c < 10; c++){
                System.out.print(" " + grilleOrdi[l][c]);
            }
            System.out.println();
        }
    }
}
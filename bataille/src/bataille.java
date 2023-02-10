import java.util.Random;
import java.util.Scanner;

/**
 * @author AlexisBlouin
 * @since 5 fevrier 2023
 *
 */
public class bataille {
    public static int [][]grilleOrdi = new int[10][10];
    public static int [][]grilleJeu = new int[10][10];

    public static void main(String[] args){
        //initGrilleOrdi();
        //afficherGrilleOrdi();
        initGrilleJeu();
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

            //faire fonction pr position aleatoire
            ligne = randRange(0, 10);
            colonne = randRange(0, 10);
            direction = randRange(1, 3);

            while (!posOk(grilleOrdi, ligne, colonne, direction, grandeurBateau[numeroBateau - 1]))
            {
                ligne = randRange(0, 10);
                colonne = randRange(0, 10);
                direction = randRange(1, 3);
            }

            //Faire fonction (EcrireDansGrille)
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

    public static void initGrilleJeu(){
        int[] grandeurBateau = new int[] {5, 4, 3, 3, 2};
        int ligne;
        int colonneInt;
        char colonneChar;
        int direction;

        //Utilisation du scanner fait avec l'aide de la page : https://www.w3schools.com/java/java_user_input.asp
        Scanner lecture = new Scanner(System.in);

        for(int numeroBateau = 1; numeroBateau <= 5; numeroBateau++){

            //Faire fonction pr demander positionnement
            System.out.println("Donnez la lettre pour le porte avions : ");
            colonneChar = lecture.next().charAt(0);
            colonneChar = Character.toUpperCase(colonneChar);
            while(colonneChar < 'A' || colonneChar > 'J'){
                System.out.println("La lettre entree n'est pas valide.");
                System.out.println("Donnez la lettre pour le porte avions (Entre 'A' et 'J') : ");
                colonneChar = lecture.next().charAt(0);
                colonneChar = Character.toUpperCase(colonneChar);
            }
            colonneInt = colonneChar;
            System.out.println(colonneInt);
            colonneInt -= 65;
            System.out.println(colonneInt);

            System.out.println("Donnez le nombre pour le porte avions : ");
            ligne = lecture.nextInt();
            while(ligne < 0 || ligne > 9){
                System.out.println("Le nombre entree n'est pas valide.");
                System.out.println("Donnez le nombre pour le porte avions (Entre 1 et 9) : ");
                ligne = lecture.nextInt();
            }

            System.out.println("Voulez-vous qu'il soit horizontal (1) ou vertical (2) ?");
            direction = lecture.nextInt();
            while(direction < 1 || direction > 2){
                System.out.println("Le nombre entree n'est pas valide.");
                System.out.println("Voulez-vous qu'il soit horizontal (1) ou vertical (2) ?");
                direction = lecture.nextInt();
            }

            while(!posOk(grilleJeu, ligne, colonneInt, direction, grandeurBateau[numeroBateau - 1])){
                System.out.println("Erreur : Le porte-avions ne rentre pas dans la grille.");
                System.out.println("Donnez la lettre pour le porte avions : ");
                colonneChar = lecture.next().charAt(0);
                colonneChar = Character.toUpperCase(colonneChar);
                while(colonneChar < 'A' || colonneChar > 'J'){
                    System.out.println("La lettre entree n'est pas valide.");
                    System.out.println("Donnez la lettre pour le porte avions (Entre 'A' et 'J') : ");
                    colonneChar = lecture.next().charAt(0);
                    colonneChar = Character.toUpperCase(colonneChar);
                }
                colonneInt = colonneChar;
                System.out.println(colonneInt);
                colonneInt -= 65;
                System.out.println(colonneInt);

                System.out.println("Donnez le nombre pour le porte avions : ");
                ligne = lecture.nextInt();
                while(ligne < 0 || ligne > 9){
                    System.out.println("Le nombre entree n'est pas valide.");
                    System.out.println("Donnez le nombre pour le porte avions (Entre 1 et 9) : ");
                    ligne = lecture.nextInt();
                }

                System.out.println("Voulez-vous qu'il soit horizontal (1) ou vertical (2) ?");
                direction = lecture.nextInt();
                while(direction < 1 || direction > 2){
                    System.out.println("Le nombre entree n'est pas valide.");
                    System.out.println("Voulez-vous qu'il soit horizontal (1) ou vertical (2) ?");
                    direction = lecture.nextInt();
                }
            }

            //Faire fonction (EcrireDansGrille)
            if(direction == 1){
                for(int i = colonneInt; i < colonneInt + grandeurBateau[numeroBateau - 1]; i++){
                    grilleJeu[ligne][i] = numeroBateau;
                }
                afficherGrilleJeu();
            }
            else {
                for(int i = ligne; i < ligne + grandeurBateau[numeroBateau - 1]; i++){
                    grilleJeu[i][colonneInt] = numeroBateau;
                }
                afficherGrilleJeu();
            }
        }
    }

    //appeler sa afficherGrille(Grille ){}
    static void afficherGrilleJeu(){
        System.out.println("  A B C D E F G H I J");
        for(int l = 0; l < 10; l++){
            System.out.print(l);
            for(int c = 0; c < 10; c++){
                System.out.print(" " + grilleJeu[l][c]);
            }
            System.out.println();
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
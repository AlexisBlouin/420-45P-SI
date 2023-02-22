import java.util.Random;
import java.util.Scanner;

/**
 * Programme bataille.java permettant de jouer au jeu Battleship contre un ordinateur.
 * @author AlexisBlouin
 * @since 6 février 2023
 */
public class bataille {
    public static int [][]grilleOrdi = new int[10][10];
    public static int [][]grilleJeu = new int[10][10];

    public static boolean partieFinie = false;

    /**
     * Fonction main appelant la fonction {@link #engagement() engagement} pour démarrer la partie.
     * @param args
     *      Si le programme est lancé en ligne de commande, args contiendra les arguments inscrits dans la ligne de commandes.
     */
    public static void main(String[] args){
        engagement();
    }

    /**
     * <pre>
     *      Initialise les deux grilles et s'occupe du déroulement du jeu.
     *      Utilise les fonctions {@link #initGrilleOrdi() initGrilleOrdi} et {@link #initGrilleJeu() initGrilleJeu} pour initialiser les grilles
     *      et {@link #tirOrdinateur() tirOrdinateur} et {@link #tirJoueur() tirJoueur} pour le déroulement du jeu.
     * </pre>
     */
    public static void engagement(){
        initGrilleOrdi();
        afficherGrille(grilleOrdi);
        initGrilleJeu();
        afficherGrille(grilleJeu);

        while(!partieFinie){
            tirOrdinateur();
            tirJoueur();
        }
    }

    /**
     * <pre>
     *      Initialise la grille de l'ordinateur.
     *      Détermine la position avec la fonction {@link #randRange(int, int)  randRange}.
     *      Appelle ensuite la fonction {@link #posOk(int[][], int, int, int, int)  posOk} pour vérifier si la position reçue aléatoirement fonctionne.
     * </pre>
     */
    public static void initGrilleOrdi(){
        int[] grandeurBateau = new int[] {5, 4, 3, 3, 2};
        int ligne;
        int colonne;
        int direction;
        for(int numeroBateau = 1; numeroBateau <= 5; numeroBateau++){

            //faire fonction pr position aléatoire
            ligne = randRange(0, 10);
            colonne = randRange(0, 10);
            direction = randRange(1, 3);

            while (!posOk(grilleOrdi, ligne, colonne, direction, grandeurBateau[numeroBateau - 1]))
            {
                ligne = randRange(0, 10);
                colonne = randRange(0, 10);
                direction = randRange(1, 3);
            }

            ecrireDansGrille(grilleOrdi, ligne, colonne, direction, grandeurBateau, numeroBateau);
        }
    }

    /**
     * <pre>
     *      Initialisation de la grille du joueur.
     *      La position est déterminée par les entrées du joueur au clavier.
     *      Appelle ensuite la fonction {@link #posOk(int[][], int, int, int, int)  posOk} pour vérifier si la position entrée fonctionne.
     * </pre>
     */
    public static void initGrilleJeu(){
        int[] grandeurBateau = new int[] {5, 4, 3, 3, 2};
        int ligne;
        int colonne;
        int direction;
        String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};

        for(int numeroBateau = 1; numeroBateau <= 5; numeroBateau++){
            colonne = demanderColonne(true,  numeroBateau);
            ligne = demanderLigne(true,  numeroBateau);
            direction = demanderDirection();

            while(!posOk(grilleJeu, ligne, colonne, direction, grandeurBateau[numeroBateau - 1])){
                System.out.println("Erreur : Le " + nomBateau[numeroBateau - 1] + " ne rentre pas dans la grille.");
                colonne = demanderColonne(true,  numeroBateau);
                ligne = demanderLigne(true,  numeroBateau);
                direction = demanderDirection();
            }

            ecrireDansGrille(grilleJeu, ligne, colonne, direction, grandeurBateau, numeroBateau);
        }
    }

    /**
     * <pre>
     *      Vérifie si la position fonctionne pour l'ajout d'un bateau.
     *      Se sert des fonctions {@link #ligneOk(int[][], int, int, int) ligneOk} et
     *      {@link #colonneOk(int[][], int, int, int) colonneOk} pour donner le résultat.
     * </pre>
     * @param grille
     *      La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l
     *      Le numéro de la ligne du bateau.
     * @param c
     *      Le numéro de la colonne du bateau.
     * @param d
     *      La direction du bateau.
     * @param t
     *      Le nombre de cases du bateau.
     * @return fonctionne
     *      Le résultat disant si cette position fonctionne.
     */
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

    /**
     * <pre>
     *      Teste si le bateau peut se poser sur une ligne.
     * </pre>
     * @param grille
     *      La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l
     *      Le numéro de la ligne du bateau.
     * @param c
     *      Le numéro de la colonne du bateau.
     * @param t
     *      Le nombre de cases du bateau.
     * @return fonctionne
     *      Le résultat disant si cette position fonctionne.
     */
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

    /**
     * <pre>
     *      Teste si le bateau peut se poser sur une colonne.
     * </pre>
     * @param grille
     *      La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l
     *      Le numéro de la ligne du bateau.
     * @param c
     *      Le numéro de la colonne du bateau.
     * @param t
     *      Le nombre de cases du bateau.
     * @return fonctionne
     *      Le résultat disant si cette position fonctionne.
     */
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

    //public static Random rand = new Random();

    /**
     * <pre>
     *      Permet de générer un nombre aléatoire.
     *      (Copié du code fourni par le document).
     * </pre>
     * @param a
     *      Borne intérieure du nombre aléatoire (inclus).
     * @param b
     *      Borne extérieure du nombre aléatoire (exclu).
     * @return
     *      Le nombre aléatoire.
     */
    public static int randRange (int a , int b){
        Random rand = new Random();
        return rand.nextInt(b - a)+ a;
    }

    /**
     * <pre>
     *      Affiche une grille de jeu.
     * </pre>
     * @param grille
     *      Grille à afficher (joueur ou ordi).
     */
    public static void afficherGrille(int[][] grille){
        System.out.println("  A B C D E F G H I J");
        for(int l = 0; l < 10; l++){
            System.out.print(l);
            for(int c = 0; c < 10; c++){
                System.out.print(" " + grille[l][c]);
            }
            System.out.println();
        }
    }

    /**
     * <pre>
     *      Écrit dans la grille la position du bateau reçue.
     * </pre>
     * @param grille
     *      La grille utilisée (grilleOrdi ou grilleJeu).
     * @param l
     *      Le numéro de la ligne du bateau.
     * @param c
     *      Le numéro de la colonne du bateau.
     * @param d
     *      La direction du bateau.
     * @param t
     *      Le nombre de cases du bateau.
     * @param n
     *      Le numéro du bateau à écrire.
     */
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

    /**
     * <pre>
     *      Vérifie si un bateau est coulé en regardant s'il reste des cases avec le numéro du bateau dans la grille.
     * </pre>
     * @param grille
     *      La grille à vérifier (grilleOrdi ou grilleJeu).
     * @param n
     *      Le numéro du bateau à vérifier.
     * @return
     *      True or False selon si le bateau est coulé ou non.
     */
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

    /**
     * <pre>
     *      Vérifie si un bateau est touché et donne des informations supplémentaires avec les fonctions
     *      {@link #couler(int[][], int)  couler} et {@link #vainqueur(int[][])}   vainqueur}.
     * </pre>
     * @param grille
     *      La grille à vérifier (grilleOrdi ou grilleJeu).
     * @param l
     *      Le numéro de la ligne à vérifier.
     * @param c
     *      Le numéro de la ligne à vérifier.
     */
    public static void mouvement(int[][] grille, int l, int c){
        if(grille[l][c] >= 1 && grille[l][c] <= 5){
            int numeroBateau = grille[l][c];
            String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
            System.out.println("Touche : " + nomBateau[grille[l][c] - 1]);
            grille[l][c] = 6;
            if(couler(grille, numeroBateau)){
                System.out.println("Coule");
                if(vainqueur(grille)){
                    if(grille == grilleOrdi){
                        System.out.println("Vous avez gagne!");
                    }
                    else{
                        System.out.println("Vous avez perdu...");
                    }
                    partieFinie = true;
                }
                else{
                    System.out.println("Pas Gagne");
                }
            }
        }
        else{
            System.out.println("A l'eau");
        }
    }

    /**
     * <pre>
     *      Détermine la position du tir de l'ordinateur et déterminant aléatoirement la ligne et la colonne
     *      avec la fonction {@link #randRange(int, int)}   randRange}.
     * </pre>
     */
    public static void tirOrdinateur(){
        int l;
        int c;
        l = randRange(0, 10);
        c = randRange(0, 10);
        System.out.println("l : " + l + " c : " + c);
        System.out.println("Tir de l'ordinateur : ");
        System.out.println("C'est au tour de l'ordinateur.");
        mouvement(grilleJeu, l, c);
        afficherGrille(grilleJeu);
    }

    /**
     * <pre>
     *      Vérifie s'il y a un vainqueur après qu'un nouveau bateau a été coulé.
     * </pre>
     * @param grille
     *      La grille à vérifier (grilleOrdi ou grilleJeu).
     * @return
     *      True or False selon le résultat de l'analyse.
     */
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

    /**
     * <pre>
     *      Demande la lettre de la colonne pour la position du bateau ou du tir au joueur.
     * </pre>
     * @param numeroBateau
     *      Numéro servant comme indice au tableau pour avoir le bon nom de bateau.
     * @return
     *      Le numéro de la colonne en integer.
     */
    public static int demanderColonne(boolean initialisation, int numeroBateau){
        //Utilisation du scanner fait avec l'aide de la page : https://www.w3schools.com/java/java_user_input.asp
        Scanner lecture = new Scanner(System.in);
        char colonneChar;
        int colonneInt;
        String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};

        if(initialisation){
            System.out.println("Donnez la lettre pour le " + nomBateau[numeroBateau - 1] + " : ");
        }
        else{
            System.out.println("Donnez la lettre pour la colonne du tir : ");
        }
        colonneChar = lecture.next().charAt(0);
        colonneChar = Character.toUpperCase(colonneChar);
        while(colonneChar < 'A' || colonneChar > 'J'){
            System.out.println("Le caractere entre n'est pas valide.");
            if (initialisation) {
                System.out.println("Donnez la lettre pour le " + nomBateau[numeroBateau - 1] + " (Entre 'A' et 'J') : ");
            }
            else{
                System.out.println("Donnez la lettre pour la colonne du tir (Entre 'A' et 'J') : ");
            }
            colonneChar = lecture.next().charAt(0);
            colonneChar = Character.toUpperCase(colonneChar);
        }
        colonneInt = colonneChar;
        System.out.println(colonneInt);
        colonneInt -= 65;
        System.out.println(colonneInt);
        return colonneInt;
    }

    /**
     * <pre>
     *      Demande le numéro de la ligne pour la position du bateau ou du tir au joueur.
     *      Utilisation du scanner fait avec l'aide de la page Java User Input (Scanner) (L.384, 395, 403 et 405).
     *      Validation de l'entré du joueur faite avec la page Validating input using java.util.Scanner (L.395 à 404).
     * </pre>
     * @param numeroBateau
     *      Numéro servant comme indice au tableau pour avoir le bon nom de bateau.
     * @return
     *      Le numéro de la ligne.
     * @see <a href="https://www.w3schools.com/java/java_user_input.asp">Java User Input (Scanner)</a>
     * @see <a href="https://stackoverflow.com/questions/3059333/validating-input-using-java-util-scanner">Validating input using java.util.Scanner</a>
     */
    public static int demanderLigne(boolean initialisation, int numeroBateau){

        Scanner lecture = new Scanner(System.in);
        int ligne;
        String[] nomBateau = new String[] {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};

        if(initialisation){
            System.out.println("Donnez le nombre pour le " + nomBateau[numeroBateau - 1] + " : ");
        }
        else{
            System.out.println("Donnez le nombre pour la ligne du tir : ");
        }
        do{
            while(!lecture.hasNextInt()){
                System.out.println("Ceci n'est pas un nombre!");
                if(initialisation){
                    System.out.println("Donnez le nombre pour le " + nomBateau[numeroBateau - 1] + " (Entre 1 et 9) : ");
                }
                else{
                    System.out.println("Donnez le nombre pour la ligne du tir (Entre 1 et 9) : ");
                }
                lecture.next();
            }
            ligne = lecture.nextInt();
            if(ligne >= 0 && ligne <= 9){
                return ligne;
            }

            System.out.println("Le nombre entre n'est pas valide.");
            if(initialisation){
                System.out.println("Donnez le nombre pour le " + nomBateau[numeroBateau - 1] + " (Entre 1 et 9) : ");
            }
            else{
                System.out.println("Donnez le nombre pour la ligne du tir (Entre 1 et 9) : ");
            }
        }
        while(true);
    }

    /**
     * <pre>
     *      Demande la direction du bateau lors de l'initialisation de la grille.
     *      Utilisation du scanner fait avec l'aide de la page Java User Input (Scanner) (L.434, 439, 442 et 444).
     * </pre>
     * @return
     *      La direction du bateau.
     * @see <a href="https://www.w3schools.com/java/java_user_input.asp">Java User Input (Scanner)</a>
     */
    public static int demanderDirection(){

        Scanner lecture = new Scanner(System.in);
        int direction;

        System.out.println("Voulez-vous qu'il soit horizontal (1) ou vertical (2) ?");
        do{
            while(!lecture.hasNextInt()){
                System.out.println("Ceci n'est pas un nombre!");
                System.out.println("Voulez-vous qu'il soit horizontal (1) ou vertical (2) ?");
                lecture.next();
            }
            direction = lecture.nextInt();
            if(direction >= 0 && direction <= 9){
                return direction;
            }

            System.out.println("Le nombre entre n'est pas valide.");
            System.out.println("Voulez-vous qu'il soit horizontal (1) ou vertical (2) ?");
        }
        while(true);
    }

    /**
     * <pre>
     *      S'occupe de faire jouer le joueur en lui demandant les paramètres de son tir (colonne et ligne) puis le vérifie.
     *      Utilise les fonctions {@link #demanderColonne(boolean, int)}   demanderColonne} et {@link #demanderLigne(boolean, int)}   demanderLigne},
     *      puis teste le tir avec {@link #mouvement(int[][], int, int)}   mouvement}.
     * </pre>
     */
    public static void tirJoueur(){
        int colonne;
        int ligne;

        System.out.println("C'est votre tour!");
        colonne = demanderColonne(false, 0);
        ligne = demanderLigne(false, 0);

        mouvement(grilleOrdi, ligne, colonne);
        afficherGrille(grilleOrdi);
    }
}
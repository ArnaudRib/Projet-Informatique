import java.util.InputMismatchException;
import java.util.Scanner;

public class Plateau {
	//Attributs
	/**
	 * @param taille  Taille du plateau.
	 */
	static int taille;
	/**
	 * @param compteur  compteur du jeu.
	 */
	static int compteur;
	/**
	 * @param pointJ1  Compte les points du joueur1.
	 */
	static int pointJ1;
	/**
	 * @param pointJ2  Compte les points du joueur2.
	 */
	static int pointJ2;
	/**
	 * @param rejoue  Devient True si le joueur peut rejouer (s'il a complété une case), False sinon.
	 */
	static boolean rejoue;
	
	//Constructeur Plateau de jeu
	public Plateau(int taille, int compteur, String[][] visuel){
	this.taille=taille;
	this.rejoue=false;
	this.compteur=compteur;
	}
	
	
	//Fonctions
		//PARTIE JOUEUR VS JOUEUR.
	/**
	 * Permet au joueur de jouer en console.
	 * @param joueur Le int associé au joueur.
	 * @param visuel Le plateau de jeu
	 * @return Rejoue : True ou False selon que le joueur a rentré des informations erronées ou non.
	 */
	public boolean TourJoueur(int joueur, String visuel[][]){ //Gere le tour de chaque joueur.
		boolean demande=true;
		int colonne=0;
		int ligne=0;
		try{
			Scanner sc = new Scanner(System.in);
			System.out.println("Dans quelle ligne voulez vous jouer ?");
			ligne=sc.nextInt();
			
			System.out.println("Dans quelle colonne voulez vous jouer ?");
			colonne=sc.nextInt();

		} catch(InputMismatchException e){
			System.out.println("Erreur dans la saisie! Veuillez rentrer des nombres.");
			demande = TourJoueur(joueur, visuel); //relance le tour du joueur s'il met quelque chose d'erroné.
			return demande; //evite les problemes avec la suite et que ca reste bloqué sur vérifier.
		}

		
		if (Verifier(ligne, colonne, visuel)){ //si ce qu'il a rentré convient dans le tableau, l'update.
			demande = Update(ligne, colonne, joueur, visuel);
		}
			
		System.out.println("");
		return demande;	 //tant que demande=true, le tour du joueur continue.
	}
	
	
	//PARTIE JOUEUR VS ORDI et ORDI VS ORDI.
/**
 * Joue le même rôle que TourJoueur, mais pour l'ordinateur cette fois. Permet la création de trois niveaux de difficulté :</br>
 * -> Niveau 1 : l'ordinateur joue au hasard, sans aucune logique. Pas très dur de le battre..</br>
 * -> Niveau 2 : L'ordinateur complète les carrés où il y a déjà 3 cases remplies autour, et joue au hasard sinon.</br>
 * -> Niveau 3 : L'ordinateur complète les carrés où il y a déjà 3 cases remplies autour, et ne joue pas là où il y a déjà 2 cases jouées, sauf s'il n'a vraiment pas le choix.
 * @param difficulté La difficulté de l'ordinateur choisie par le joueur au début du jeu.
 * @param visuel Le tableau du plateau de jeu.
 * @return True ou False selon que l'ordinateur peut rejouer ou non, s'il a complété un carré.
 */
public boolean TourIA(int difficulté, int joueur, String[][] visuel) { // Fais marcher l'IA.
	boolean demande=true;
	int tableau[]={0,0};
	int ligne=0, colonne=0;
	//int joueur=2; //l'ordinateur est toujours 2 par défaut en Joueur vs IA
	if (difficulté==1){
		tableau = CalculsIA.CalculIA1(visuel);
		ligne = tableau[0]+1;  //+1 nécessaire car afin de réutiliser les fonction Update du joueur, on doit créer un décalage comme si l'ordi avait choisi où jouer comme le joueur 
		colonne = tableau[1]+1;
	}
	
	if (difficulté==2){
		tableau = CalculsIA.CalculIA2(visuel);
		ligne = tableau[0]+1;//le +1 est nécessaire afin d'éviter les décallages du tableau. La fonction UpdateOrdi est à l'image de Update, l'ordinateur joue à une ligne et colonne décalée de 1 dans le tableau.
		colonne = tableau[1]+1;
	}
	
	if (difficulté==3){
		tableau = CalculsIA.CalculIA3(visuel);
		ligne = tableau[0]+1;
		colonne = tableau[1]+1;
	}
	
	//Pas besoin de vérifier que la demande de l'ordinateur est vrai ou non vu qu'il joue qu'aux cases disponibles.
	System.out.println("L'ordi joue à la ligne : " +ligne + ", et à la colonne : " + colonne);
	demande = UpdateOrdi(ligne, colonne, joueur, visuel);
	
	return demande;
}



	//MECANIQUE DU JEU (Partie Joueur).
	/**
	 * Permet d'update le plateau, c'est à dire rajouter l'endroit (ligne/colonne) dans le visuel où le joueur veut jouer.
	 * @param ligne La ligne demandée par le joueur en plateau
	 * @param colonne La colonne demandée par le joueur en plateau
	 * @param joueur Le numéro du joueur, permet de transférer le joueur aux autres fonctions qui en ont besoin.
	 * @param visuel
	 * @return True ou False selon que le joueur a fini un carré ou non. Si True, alors le joueur ne change pas, et "demande" dans JeuCarre.JoueurvsJoueur() reste true, donc la boucle recommence avec le même joueur.
	 */
	public static boolean Update(int ligne, int colonne, int joueur, String[][] visuel){ // Met à jour le plateau selon que les valeurs rentrées par le joueur sont bonnes ou non.
		if (taille<5){ //Pour le plateau de taille inférieure strictement à 5. Permet de gérer les décallages.
			rejoue = Cocher(ligne, colonne, joueur, visuel);//doit se situer ici et non pas après sinon l'update de la case ne détectera jamais le point.
			if ((ligne)%2==0 && (colonne)%2!=0){
				if (visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne-1]="| ";
				}else{
					System.out.println("Et une case grisée de moins!");
					visuel[ligne-1][colonne-1]="  ";
				}
			}
			if ((ligne)%2!=0 && (colonne)%2==0){
				if (visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne-1][colonne-1]="---";
					}else{
						System.out.println("Et une case grisée de moins!");
						visuel[ligne-1][colonne-1]="   ";
				}	
			} 

		compteur=pointJ1+pointJ2;
		}
		
		if (taille>=5){ // NECESSAIRE POUR LES CAS OU LE PLATEAU A PLUS DE 10 COLONNES
			rejoue = Cocher(ligne, colonne, joueur, visuel); //doit se situer ici et non pas après sinon l'update de la case ne détectera jamais le point.
			if ((ligne)%2==0 && (colonne)%2!=0){
				if (visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne-1]="|  ";
				}else{
					System.out.println("Et une case grisée de moins!");
					visuel[ligne-1][colonne-1]="   ";
				}
			}
			if ((ligne)%2!=0 && (colonne)%2==0){
				if (visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne-1][colonne-1]="-----";
					}else{
						System.out.println("Et une case grisée de moins!");
						visuel[ligne-1][colonne-1]="     ";
				}	
			}
		// demander de vérifier si la case demandée compléte un carré, auquel cas, le joueur peut rejouer
		compteur=pointJ1+pointJ2;
		}
		return rejoue;	//si rejoue est true, cela veut dire que la case demandée a coché une case, d'où le fait de rejouer.
	}

	/**
	 * Permet de vérifier si le joueur a coché, c'est à dire finalisé un carré.
	 * @param ligne la ligne où le joueur a joué.
	 * @param colonne la colonne où le joueur a joué.
	 * @param joueur Le numéro du joueur, permet de rajouter des points au bon joueur.
	 * @param visuel
	 * @return True ou False selon que le joueur a coché une case ou pas.
	 */
	public static boolean Cocher(int ligne, int colonne, int joueur, String[][] visuel) { 	// Vérifie si la position rentrée par le joueur complète une case ou non
		rejoue=false; // false par défaut, car le joueur ne peut pas rejouer tant qu'il ne coche pas une case.
		if (taille<5){
			if (colonne==1){	
				if (visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---" && visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne]=Integer.toString(joueur)+" ";
					if (joueur==1){
						System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez un point!");
						pointJ2++;
					}
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true; // si la case demandée coche une case, return true et permet au joueur de rejouer.
				}
			}
		
			if (colonne==visuel.length){	
				if (visuel[ligne-1][colonne-3]=="| " && visuel[ligne-2][colonne-2]=="---" && visuel[ligne][colonne-2]=="---" && visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne-2]=Integer.toString(joueur)+" ";
					rejoue=true;
					if (joueur==1){
						System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez un point!");
						pointJ2++;
					}
				}	
			}
		
			if (ligne==1){	
				if (visuel[ligne][colonne-2]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne][colonne]=="| "  && visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne][colonne-1]=Integer.toString(joueur)+" ";
					rejoue=true;
					if (joueur==1){
						System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez un point!");
						pointJ2++;
					}
				}
			}
		
			if (ligne==visuel.length){	
				if (visuel[ligne-2][colonne-2]=="| " && visuel[ligne-2][colonne]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne-2][colonne-1]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
					if (joueur==1){
						System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez un point!");
						pointJ2++;
					}
				}
			}
		
			if (ligne!=1 && colonne!=1 && ligne!=visuel.length && colonne!=visuel.length){ //evite les collisions avec les conditions aux bords
				
				if (ligne%2==0 && colonne%2!=0){ // si barre verticale tracée
					if ((visuel[ligne-2][colonne-2]=="---" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="---"  && visuel[ligne-1][colonne-1]!=". ") || (visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---"  && visuel[ligne-1][colonne-1]!=". ")){ 
						if (visuel[ligne-2][colonne-2]=="---" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="---"  && visuel[ligne-1][colonne-1]!=". "){
							visuel[ligne-1][colonne-2]=Integer.toString(joueur)+" ";
						}
						if (visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---"  && visuel[ligne-1][colonne-1]!=". "){
							visuel[ligne-1][colonne]=Integer.toString(joueur)+" ";
						}
						if ((visuel[ligne-2][colonne-2]=="---" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="---"  && visuel[ligne-1][colonne-1]!=". ") && (visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---"  && visuel[ligne-1][colonne-1]!=". ")){
							if (joueur==1){
								System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez deux points!");
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez deux points!");
								pointJ2=pointJ2+2;
							}
						}else{
							if (joueur==1){
								System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez un point!");
								pointJ1++;
							}
							if (joueur==2){
								System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez un point!");
								pointJ2++;
	
							}
						}
					rejoue=true;
					}	
				}
			
				if (ligne%2!=0 && colonne%2==0){ //barre horizontale tracée
					if ((visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . ") || ((visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "))){ 
						if (visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . "){
							visuel[ligne-2][colonne-1]=Integer.toString(joueur)+" ";
						}
						if (visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "){
							visuel[ligne][colonne-1]=Integer.toString(joueur)+" ";
						}
						
						if ((visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . ") && ((visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "))){
							if (joueur==1){
								System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez deux points!");
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez deux points!");
								pointJ2=pointJ2+2;
	
							}
						}else{
						//	rejoue=true;
							if (joueur==1){
								System.out.println("Bravo "+JeuCarre.nomJ1+"! Vous gagnez un point!");
								pointJ1++;
							}
							if (joueur==2){
								System.out.println("Bravo "+JeuCarre.nomJ2+"! Vous gagnez un point!");
								pointJ2++;
	
							}
						}
					rejoue=true;
					}
				}
			}
		}
		
		if (taille>=5){ // NECESSAIRE POUR LES CAS OU LE PLATEAU A PLUS DE 10 COLONNES
			if (colonne==1){	
				if (visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					if (joueur==1){
						pointJ1++;
					}
					if (joueur==2){
						pointJ2++;
					}
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
				}
			}
		
			if (colonne==visuel.length){	
				if (visuel[ligne-1][colonne-3]=="|  " && visuel[ligne-2][colonne-2]=="-----" && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne-2]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					rejoue=true;
					if (joueur==1){
						pointJ1++;
					}
					if (joueur==2){
						pointJ2++;
					}
				}	
			}
		
			if (ligne==1){	
				if (visuel[ligne][colonne-2]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne][colonne-1]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
					if (joueur==1){
						pointJ1++;
					}
					if (joueur==2){
						pointJ2++;
					}
				}
			}
		
			if (ligne==visuel.length){	
				if (visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-2][colonne]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne-2][colonne-1]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
					if (joueur==1){
						pointJ1++;
					}
					if (joueur==2){
						pointJ2++;
					}
				}
			}
		
			if (ligne!=1 && colonne!=1 && ligne!=visuel.length && colonne!=visuel.length){ //evite les collisions avec les conditions aux bords
				
				if (ligne%2==0 && colonne%2!=0){ // si barre verticale tracée
					if ((visuel[ligne-2][colonne-2]=="-----" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  ") || (visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  ")){ // A MODIFIER
						if (visuel[ligne-2][colonne-2]=="-----" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
							visuel[ligne-1][colonne-2]=Integer.toString(joueur)+"  ";
						}
						if (visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
							visuel[ligne-1][colonne]=Integer.toString(joueur)+"  ";
						}
						
						if ((visuel[ligne-2][colonne-2]=="-----" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  ") && (visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  ")){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								pointJ2=pointJ2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejoue=true;
							if (joueur==1){
								pointJ1++;
							}
							if (joueur==2){
								pointJ2++;
	
							}
						}
					rejoue = true;
					}	
				}
			
				if (ligne%2!=0 && colonne%2==0){ //barre horizontale tracée
					if ((visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  ") || ((visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "))){ 
						if (visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  "){
							visuel[ligne-2][colonne-1]=Integer.toString(joueur)+"  ";
						}
						if (visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "){
							visuel[ligne][colonne-1]=Integer.toString(joueur)+"  ";
						}
						if ((visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  ") && ((visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "))){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								pointJ2=pointJ2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejoue=true;
							if (joueur==1){
								pointJ1++;
							}
							if (joueur==2){
								pointJ2++;
	
							}
						}
					rejoue=true;
					}
				}
			}
		}
		return rejoue; 
	}
	
	/**
	 * Permet de vérfier les disponibilités des entrées du joueur : évite qu'il joue sur un point, hors du tableau ou bien sur une case déjà jouée.
	 * @param ligne
	 * @param colonne
	 * @param visuel
	 * @return True ou False selon que le joueur n'a pas essayé de bugger le jeu. Permet de déclencher l'update dans le TourJoueur.
	 */
	public boolean Verifier(int ligne, int colonne, String[][] visuel) {// Vérifie que les lignes et colonnes demandées sont bonnes

		if (ligne>visuel.length || colonne>visuel.length || ligne<1 || colonne<1){
			System.out.println("Les valeurs entrées ne sont pas dans le tableau! Veuillez rejouer.");
			return false;
		}
		
		if (taille<5){			
			if (visuel[ligne-1][colonne-1]=="| " || visuel[ligne-1][colonne-1]=="---"){
				System.out.println("Cette case n'est pas disponible! Elle a déjà été jouée! Rejouez.");
				return false;
			}
		}
		if (taille>=5){			
			if (visuel[ligne-1][colonne-1]=="|  " || visuel[ligne-1][colonne-1]=="-----"){
				System.out.println("Cette case n'est pas disponible! Elle a déjà été jouée! Rejouez.");
				return false;
			}
		}
		
		if (visuel[ligne-1][colonne-1]=="o"){
			System.out.println("Cette case n'est pas disponible! C'est un point! Rejouez.");
			return false;
		}
		if (ligne%2==0 && colonne%2==0){
			System.out.println("Cette case n'est pas disponible! Elle est déstinée à la lettre du gagnant de la case! Rejouez.");
			return false;
		}	
		else{		//if infos rentrées sont disponibles, return true. Else false. 
			return true;
		}
	}
	

	//MECANIQUE DU JEU (Partie Ordi).
	/**
	 * Même fonction que Update, sauf qu'elle fait appel à CocherOrdi() et non Cocher().
	 * @param ligne
	 * @param colonne
	 * @param joueur
	 * @param visuel
	 * @return True ou False selon que l'ordinateur a coché une case ou non.
	 */
	public boolean UpdateOrdi(int ligne, int colonne, int joueur, String[][] visuel){ //Met à jour le plateau selon les valeurs choisies par l'IA
		if (taille<5){		
			rejoue = CocherOrdi(ligne, colonne, joueur, visuel);//doit se situer ici et non pas après sinon l'update de la case ne détectera jamais le point.
			if ((ligne)%2==0 && (colonne)%2!=0){
				if (visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne-1]="| ";//L'espace est nécessaire pour faire des groupes de 2 strings entre les cases
				}else{
					System.out.println("Et une case grisée de moins!");
					visuel[ligne-1][colonne-1]="  ";
				}
			}
			if ((ligne)%2!=0 && (colonne)%2==0){
				if (visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne-1][colonne-1]="---";
					}else{
						System.out.println("Et une case grisée de moins!");
						visuel[ligne-1][colonne-1]="   ";
				}	
			} 
			// demander de vérifier si la case demandée compléte un carré, auquel cas, le joueur peut rejouer
		compteur=pointJ1+pointJ2;
		}
		
		if (taille>=5){ // NECESSAIRE POUR LES CAS OU LE PLATEAU A PLUS DE 10 COLONNES
			rejoue = CocherOrdi(ligne, colonne, joueur, visuel); //doit se situer ici et non pas après sinon l'update de la case ne détectera jamais le point.
			if ((ligne)%2==0 && (colonne)%2!=0){
				if (visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne-1]="|  ";//L'espace est nécessaire pour faire des groupes de 3 strings entre les cases
				}else{
					System.out.println("Et une case grisée de moins!");
					visuel[ligne-1][colonne-1]="   ";
				}
			}
			if ((ligne)%2!=0 && (colonne)%2==0){
				if (visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne-1][colonne-1]="-----";
					}else{
						System.out.println("Et une case grisée de moins!");
						visuel[ligne-1][colonne-1]="     ";
				}	
			}
		compteur=pointJ1+pointJ2;
		}
		return rejoue;	
	}
	
	/**
	 * Même chose que Cocher(). Par soucis d'esthétique, permet de mettre "L'ordinateur n°1/2 marque un point!" au lieu de "le joueur 1/2 marque un point!". </br>
	 * @param ligne la ligne où l'ordinateur souhaite jouer.
	 * @param colonne la colonne où l'ordinateur souhaite jouer.
	 * @param joueur Le numéro de l'ordinateur, permet de rajouter des points à la bonne personne.
	 * @param visuel
	 * @return True ou False selon que l'ordinateur a coché une case ou pas.
	 */
	public boolean CocherOrdi(int ligne, int colonne, int joueur, String[][] visuel) { 	// Vérifie si la position rentrée par l'IA complète une case ou non
		rejoue=false; //intialisation nécessaire sinon ca ne rechange pas après que un carre encadre
		if (taille<5){
			if (colonne==1){	
				if (visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---" && visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne]=Integer.toString(joueur)+" ";
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
				}
			}
		
			if (colonne==visuel.length){	
				if (visuel[ligne-1][colonne-3]=="| " && visuel[ligne-2][colonne-2]=="---" && visuel[ligne][colonne-2]=="---" && visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne-2]=Integer.toString(joueur)+" ";
					rejoue=true;
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
				}	
			}
		
			if (ligne==1){	
				if (visuel[ligne][colonne-2]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne][colonne]=="| "  && visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne][colonne-1]=Integer.toString(joueur)+" ";
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
				}
			}
		
			if (ligne==visuel.length){	
				if (visuel[ligne-2][colonne-2]=="| " && visuel[ligne-2][colonne]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne-2][colonne-1]=Integer.toString(joueur)+" ";
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
				}
			}
		
			if (ligne!=1 && colonne!=1 && ligne!=visuel.length && colonne!=visuel.length){ //evite les collisions avec les conditions aux bords
				
				if (ligne%2==0 && colonne%2!=0){ // si barre verticale tracée
					if ((visuel[ligne-2][colonne-2]=="---" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="---"  && visuel[ligne-1][colonne-1]!=". ") || (visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---"  && visuel[ligne-1][colonne-1]!=". ")){ 
						if (visuel[ligne-2][colonne-2]=="---" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="---"  && visuel[ligne-1][colonne-1]!=". "){
							visuel[ligne-1][colonne-2]=Integer.toString(joueur)+" ";
						}
						if (visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---"  && visuel[ligne-1][colonne-1]!=". "){
							visuel[ligne-1][colonne]=Integer.toString(joueur)+" ";
						}
						if ((visuel[ligne-2][colonne-2]=="---" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="---"  && visuel[ligne-1][colonne-1]!=". ") && (visuel[ligne-2][colonne]=="---" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="---"  && visuel[ligne-1][colonne-1]!=". ")){
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ2=pointJ2+2;
	
							}
						}else{
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque un point!");
								pointJ1++;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque un point!");
								pointJ2++;
	
							}
						}
					rejoue=true;
					}	
				}
			
				if (ligne%2!=0 && colonne%2==0){ //barre horizontale tracée
					if ((visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . ") || ((visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "))){ 
						if (visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . "){
							visuel[ligne-2][colonne-1]=Integer.toString(joueur)+" ";
						}
						if (visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "){
							visuel[ligne][colonne-1]=Integer.toString(joueur)+" ";
						}
						
						if ((visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="---" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . ") && ((visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="---" && visuel[ligne-1][colonne-1]!=" . "))){
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ2=pointJ2+2;
	
							}
						}else{
						//	rejoue=true;
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque un point!");
								pointJ1++;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque un point!");
								pointJ2++;
	
							}
						}
					rejoue=true;
					}
				}
			}
		}
		
		if (taille>=5){ // NECESSAIRE POUR LES CAS OU LE PLATEAU A PLUS DE 10 COLONNES
			if (colonne==1){	
				if (visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne]=Integer.toString(joueur)+"  ";
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
				}
			}
		
			if (colonne==visuel.length){	
				if (visuel[ligne-1][colonne-3]=="|  " && visuel[ligne-2][colonne-2]=="-----" && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne-2]=Integer.toString(joueur)+"  ";
					rejoue=true;
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
				}	
			}
		
			if (ligne==1){	
				if (visuel[ligne][colonne-2]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne][colonne-1]=Integer.toString(joueur)+"  ";
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
				}
			}
		
			if (ligne==visuel.length){	
				if (visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-2][colonne]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne-2][colonne-1]=Integer.toString(joueur)+"  ";
					//	System.out.println("Vous pouvez rejouer!");
					rejoue=true;
					if (joueur==1){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ1++;
					}
					if (joueur==2){
						System.out.println("L'ordinateur n°"+joueur+" marque un point!");
						pointJ2++;
					}
				}
			}
		
			if (ligne!=1 && colonne!=1 && ligne!=visuel.length && colonne!=visuel.length){ //evite les collisions avec les conditions aux bords
				
				if (ligne%2==0 && colonne%2!=0){ // si barre verticale tracée
					if ((visuel[ligne-2][colonne-2]=="-----" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  ") || (visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  ")){ // A MODIFIER
						if (visuel[ligne-2][colonne-2]=="-----" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
							visuel[ligne-1][colonne-2]=Integer.toString(joueur)+"  ";
						}
						if (visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  "){
							visuel[ligne-1][colonne]=Integer.toString(joueur)+"  ";
						}
						
						if ((visuel[ligne-2][colonne-2]=="-----" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="-----" && visuel[ligne-1][colonne-1]!=".  ") && (visuel[ligne-2][colonne]=="-----" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="-----" && visuel[ligne-1][colonne-1]!=".  ")){
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ2=pointJ2+2;
	
							}
						}else{
						//	rejoue=true;
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque un point!");
								pointJ1++;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque un point!");
								pointJ2++;
	
							}
						}
					rejoue = true;
					}	
				}
			
				if (ligne%2!=0 && colonne%2==0){ //barre horizontale tracée
					if ((visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  ") || ((visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "))){ 
						if (visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  "){
							visuel[ligne-2][colonne-1]=Integer.toString(joueur)+"  ";
						}
						if (visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "){
							visuel[ligne][colonne-1]=Integer.toString(joueur)+"  ";
						}
						if ((visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="-----" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  ") && ((visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="-----" && visuel[ligne-1][colonne-1]!="  .  "))){
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ1=pointJ1+2;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ2=pointJ2+2;
	
							}
						}else{
						//	rejoue=true;
							if (joueur==1){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ1++;
							}
							if (joueur==2){
								System.out.println("L'ordinateur n°"+joueur+" marque deux points!");
								pointJ2++;
	
							}
						}
					rejoue=true;
					}
				}
			}
		}
		return rejoue; 
	}

	
	
	// CREATION ET AFFICHAGE DU PLATEAU DE JEU
		//PARTIE CONSOLE MAIS UTILE DANS LA PARTIE GRAPHIQUE
			//AFFICHAGE
	/**
	 * Permet l'affichage propre dans la console du plateau.
	 * @param visuel Le tableau qui représente le plateau.
	 */
	public void Afficher(String[][] visuel) {
		if (taille<5){
			System.out.print(" ");
		}
		if (visuel.length<10){
			for (int i=0;i<visuel.length;i++){
			System.out.print(" "+(i+1)); //pas de décallage tant que taille inférieure à 5.. faudra améliorer en augmentant l'espace selon un log
			}
			for (int i=0;i<visuel.length;i++){ //sur la console.
				System.out.print("\n");
					System.out.print(i+1+" ");
				for (int j=0;j<visuel.length;j++){
					System.out.print(visuel[i][j]);
				}
			}
			System.out.print("\n");
			
		}else{
			System.out.print("     ");
			for (int i=0;i<visuel.length;i++){ //AFFICHE LA LIGNE AU DESSUS DU TABLEAU.
				if (i>7){ //me demande pas pk 7 mais ca marche XD
					System.out.print((i+1)+" ");
				}else{
				System.out.print((i+1)+"  ");//pas de décallage tant que taille inférieure à 5.. faudra améliorer en augmentant l'espace selon un log
				}
			}
			
			for (int i=0;i<visuel.length;i++){ //AFFICHE LES CHIFFRES SUR LES COTES DU TABLEAU.
				System.out.println("");
				if (i>10){
					if (i%2==0){
					System.out.print(i+1+"   ");
					}else{ 
						System.out.print(i+1+"   ");
					}
				}else{
					System.out.print(i+1+"   ");
				}
				if (i<9){ //demande pas pk non plus.. même histoire qu'avant ahha
					System.out.print(" ");
				}
			for (int j=0;j<visuel.length;j++){
				System.out.print(visuel[i][j]);
			}
			}
			System.out.println("");
		}
	}

	/**
	 * Permet l'initialisation du tableau représentatif du plateau, selon la taille demandée par le joueur. Gère les décallages si la taille est supérieure à 5.
	 * @param visuel
	 */
	public void Initialisation(String[][] visuel){
		System.out.println("\n\n");
		if (visuel.length<10){ //traite le cas d'un petit jeu
			for (int i=0;i<visuel.length;i++){ //création du plateau avec des "o"
				if (i%2==0){ // SI LIGNE PAIRE
					for (int j=0;j<visuel.length;j++){
						if(j%2==0){
							visuel[i][j]="o";
						}else{
							visuel[i][j]="   ";
						}
					}
				}else{
					for (int j=0;j<visuel.length;j++){
						visuel[i][j]="  "; //Espace n° joueur gagnant
					}
				}
			}
		}else{ // si taille>10, pour gérer les décallages dans l'affichage.
			for (int i=0;i<visuel.length;i++){ //création du plateau avec des "."
				if (i%2==0){
					for (int j=0;j<visuel.length;j++){
						if(j%2==0){
							visuel[i][j]="o";
						}else{
							visuel[i][j]="     ";
						}
					}
				}else{
					for (int j=0;j<visuel.length;j++){
						visuel[i][j]="   ";
					}
				}
			}

		}
	}
	
		// CREATION DES POINTILLES
	/**
	 * Ajoute un nombre de pointillés demandé par le joueur aléatoirement dans le visuel.
	 * @param pointilles Le nombre de cases grisées demandées par le joueur.
	 * @param visuel
	 */
	public void Pointilles(int pointilles, String[][] visuel) {
		if (pointilles<0){
			System.out.println("Un nombre de pointillés négatif? Sérieusement.. J'en mets aucun pour la peine.\n");
		}
		if (pointilles>((taille*(taille+1))+(taille*(taille+1)))){
			System.out.println("Il y a plus de pointillés que des cases disponibles... Tout le plateau sera donc rempli de points.\n");
			if (taille<5){
				for (int i=0;i<visuel.length;i++){
					for (int j=0;j<visuel.length;j++){
						if ((i%2!=0 && j%2==0)){
							visuel[i][j]=". ";
						}
						if ((i%2==0 && j%2!=0)){
							visuel[i][j]=" . ";
						}

					}
				}
			}
			if (taille>=5){
				for (int i=0;i<visuel.length;i++){
					for (int j=0;j<visuel.length;j++){
						if ((i%2!=0 && j%2==0)){
							visuel[i][j]=".  ";
						}
						if ((i%2==0 && j%2!=0)){
							visuel[i][j]="  .  ";
						}			
					}
				}
			}

		}else{
			for (int k=0; k<pointilles;k++){	
				int ligneAleatoire = 1 + (int)(Math.random() *(visuel[0].length));
				int colonneAleatoire = 1 + (int)(Math.random() *(visuel[0].length));
				if (taille<5){		
					if ((visuel[ligneAleatoire-1][colonneAleatoire-1]!=". ") && (visuel[ligneAleatoire-1][colonneAleatoire-1]!=" . ")){ //Evite de remplacer ". " par ". "
						if ((colonneAleatoire==ligneAleatoire) || (ligneAleatoire%2==0 && colonneAleatoire%2==0) || (ligneAleatoire%2!=0 && colonneAleatoire%2!=0)){
							k--;
						}else{
							if (ligneAleatoire%2==0){
								visuel[ligneAleatoire-1][colonneAleatoire-1]=". ";
							}else{
								visuel[ligneAleatoire-1][colonneAleatoire-1]=" . ";
							}
						}	
					}else{
						k--;
					}
				}

				if (taille>=5){
					if ((visuel[ligneAleatoire-1][colonneAleatoire-1]!=".  ") && (visuel[ligneAleatoire-1][colonneAleatoire-1]!="  .  ")){
						if ((colonneAleatoire==ligneAleatoire) || (ligneAleatoire%2==0 && colonneAleatoire%2==0 || (ligneAleatoire%2!=0 && colonneAleatoire%2!=0))){
							k--;
						}else{
							if (ligneAleatoire%2==0){
								visuel[ligneAleatoire-1][colonneAleatoire-1]=".  ";
							}else{
								visuel[ligneAleatoire-1][colonneAleatoire-1]="  .  ";
							}
						}
					}else{
						k--;
					}
				}
			}
		}
	}
	
	
	// SETTERS AND GETTERS
	public int getCompteur() {
		return compteur;
	}

	public int getPointJ1() {
		return pointJ1;
	}

	public int getPointJ2() {
		return pointJ2;
	}


}

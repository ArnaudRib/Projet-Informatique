import java.util.Scanner;

public class JeuCarre {
	
	public static int console, joueur=1, taille=2, pointilles, ordi=1, difficulté=1, ok, click1=0, click2=0, nop=0, RecommencerJeuInt=1, difficultéOrdi1=1, difficultéOrdi2=1;
	public static String nomJ1="Joueur 1", nomJ2="Joueur 2";
	public static boolean edit1=false, edit2=false, RecommencerJeu=true;

	/**
	 * Permet de faire une partie Joueur vs Joueur.
	 * @param Plateau1 Le plateau de jeu.
	 * @param joueur La variable joueur (int) qui change à chaque tour.
	 * @param compteur Va s'incrémenter à chaque carré complété.
	 * @param visuel Le tableau contenant tous le plateau de jeu.
	 * @return False : le changement de joueur s'effectue hors de cette fonction.
	 * 
	 */
	public static boolean JoueurVsJoueur(Plateau Plateau1, int joueur, int compteur, String[][] visuel){ //déroulement du jeu 1V1 sans ordi

		//Déclaration des variables
		boolean demande = true; //si le joueur ne rentre pas des informations éronnées
		if (console==1){
			while (demande){
				Plateau1.Afficher(visuel);
				System.out.println("\n");
				System.out.println("Tour du joueur "+joueur);
				demande = Plateau1.TourJoueur(joueur, visuel); //Change de joueur si demande=false, car sortie de la boucle.
				if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){ //permet de casser la boucle si le compteur est atteint, joueur fini sur demande=true.
					break; 			
				}// permet de gérer le fait de rejouer le tour.
			}
		}
		if (console==2){
			while (demande){ // permet de gérer le fait de rejouer le tour.
				//Plateau1.Afficher(visuel);
				Graphique.AfficherTextGraphique(joueur,visuel);
				demande=Graphique.Hitbox(joueur,visuel);
				Graphique.AfficherTextGraphique(joueur,visuel);
				StdDraw.show(100);
				StdDraw.clear();
				if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){ //permet de casser la boucle si le compteur est atteint.
					break; 
				}
			}
		}
		return false;
	}

	
	/**
	 * Permet de faire une partie Joueur vs Ordi.
	 * @param Plateau1 Le plateau de jeu.
	 * @param joueur La variable joueur (int) qui change à chaque tour
	 * @param compteur Va s'incrémenter à chaque carré complété
	 * @param difficulté Difficulté de l'ordinateur.
	 * @param visuel Tableau contenant le visuel du plateau.
	 * @return False : Le changement de joueur s'effectue hors de cette fonction.
	 */
	public static boolean JoueurVsOrdi(Plateau Plateau1, int joueur, int compteur, int difficulté, String[][] visuel){ //déroulement du jeu contre un ordi

		//Déclaration des variables
		boolean demande = true; //si le joueur ne rentre pas des informations erronées 

		//Affichage du tableau et du joueur
		if (console==1){
			if (joueur==2){ // pour éviter l'affichage de tous les plateaux a chaque fois que l'ordinateur teste si la position qu'il veut jouer est prise ou non.
				System.out.println("\n");
				Plateau1.Afficher(visuel);
				System.out.println("\n");
				System.out.println("Tour de l'ordi.");
			}

			while (demande){
				if (joueur==1){
					Plateau1.Afficher(visuel);
					System.out.println("\n");
					System.out.println("Tour du joueur 1");
					demande = Plateau1.TourJoueur(joueur, visuel);
					if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){
						break; 
					}
				}

				if (joueur==2){ //si tour ordi. Ordi est le joueur n°2 par défaut.
					demande = Plateau1.TourIA(difficulté, joueur, visuel);
					if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){
						break; 
					}
				}
			}
		}

		if (console==2){
			if (joueur==2){ // pour éviter l'affichage de tous les plateaux a chaque fois que l'ordinateur teste si la position qu'il veut jouer est prise ou non.
				Graphique.AfficherTextGraphique(joueur, visuel);
			}
			while (demande){
				if (joueur==1){
					Graphique.AfficherTextGraphique(joueur, visuel);
					demande=Graphique.Hitbox(joueur,visuel);
					Graphique.AfficherTextGraphique(joueur,visuel);
					StdDraw.show(16);
					if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){ //permet de casser la boucle si le compteur est atteint.
						break; 
					}
				}

				if (joueur==2){ //si tour ordi.
					demande = Plateau1.TourIA(difficulté, joueur, visuel);
					Graphique.AfficherTextGraphique(joueur, visuel);
					StdDraw.show(16);//fais semblant que l'ordi réfléchit.
					if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){
						break; 
					}
				}
			}
			Graphique.AfficherTextGraphique(joueur,visuel);
		}
		return false;
	}

	/**
	 * Permet d'admirer deux Ordis qui s'affrontent.
	 * @param Plateau1 Le plateau de jeu.
	 * @param joueur La variable joueur (int) qui change à chaque tour
	 * @param compteur Va s'incrémenter à chaque carré complété
	 * @param visuel Tableau contenant le visuel du plateau.
	 * @return False : le changement d'ordi s'effectue hors de cette fonction.
	 */
	public static boolean OrdiVSOrdi(Plateau Plateau1, int joueur, int compteur, String[][] visuel){
		boolean demande = true; //si le joueur ne rentre pas des informations erronées

		if (console==1){
			System.out.println("\n");
			Plateau1.Afficher(visuel);
			System.out.println("\n");
			System.out.println("Tour de l'ordi n°"+joueur+".");
			while (demande){
				if (joueur==1){
					demande=Plateau1.TourIA(difficultéOrdi1, joueur, visuel);
					if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){
						break; 
					}
				}
				if (joueur==2){ //obligé afin d'éviter les problèmes de fin de jeu.
					demande=Plateau1.TourIA(difficultéOrdi2, joueur,visuel);
					if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){
						break; 
					}
				}

			}
		}
		if (console==2){
			 // permet de gérer le fait de rejouer le tour.
			while (demande){
				Graphique.AfficherTextGraphique(joueur,visuel);
				if (joueur==1){
					demande=Plateau1.TourIA(difficultéOrdi1, joueur,visuel);
				}
				if (joueur==2){
					demande=Plateau1.TourIA(difficultéOrdi2, joueur,visuel);
				}
				Graphique.AfficherTextGraphique(joueur,visuel);
				StdDraw.show(16);
				StdDraw.clear();
				if (Plateau1.getCompteur()==(((visuel.length-1)/2)*((visuel.length-1)/2))){ //permet de casser la boucle si le compteur est atteint.
					Graphique.AfficherTextGraphique(joueur,visuel);
					break; 
				}
			}
			Graphique.AfficherTextGraphique(joueur,visuel);
		}
		return false;
	}

	/**
	 * Effectue deux actions :</br>
	 * 1°) Essaye de transformer le string en nombre,</br>
	 * 2°) S'il y a un problème de type, renvoie une erreur, et redemande de rentrer un nombre.
	 * @param TestSiNombre Teste la nature du nombre
	 * @return Un integer, directement utilisable par la suite du jeu.
	 */
	public static int TestSiNombre(String TestSiNombre){
		Scanner sc = new Scanner(System.in);
		int TestSiNombre1=0; //permet de transformer le string en int.
		try{
			TestSiNombre1 = Integer.parseInt(TestSiNombre);
		} catch(NumberFormatException e){
			System.out.println("Erreur dans la saisie! Veuillez rentrer un nombre.");
			TestSiNombre1 = TestSiNombre(sc.next());
		}
		return TestSiNombre1;
	}

	/**
	 * Test si le nombre rentré au préalable est dans une fourchette précise.
	 * @param NombreATest 
	 * @param range1
	 * @param range2
	 * @return True ou False selon que le nombre est dans la range spécifiée ou non.
	 */
	public static boolean TestSiRange(int NombreATest, int range1, int range2){
		if (range1<=NombreATest && NombreATest<=range2){
			return true;
		}
		return false;
	}
	
	/**
	 * Permet de changer de joueur.
	 * @param joueur Le joueur qui vient de jouer
	 * @return Le joueur qui va pouvoir jouer ce tour ci.
	 */
	public static int Changer(int joueur){
		if (joueur==1){
			joueur=2;
		}else{
			joueur=1;
		}
		return joueur;
	}

	/**
	 * Fait appel à la classe Menu et aux deux menus qui permettent de set les différentes caractéristiques du plateau.
	 */
	public static void DemandeGraphique(){ //Gère l'interface graphique GG.
		Menu.Menu1(); // lance le menu n°1
		Menu.Menu2(); //lance le menu n°2
	}

	/** 
	 * Permet de set les caractéristiques du plateau à partir de la console.
	 */
	public static void DemandeInformation(){
		Scanner sc = new Scanner(System.in);
		boolean testrangemode=true;
		boolean testrangedifficulté=true;
		boolean testrangedifficultéOrdi1=true;
		boolean testrangedifficultéOrdi2=true;
		boolean testrangetaille=true;
		boolean testjoueurstart=true;
		
		System.out.println("-> Tapez 1 si vous voulez jouer contre l'ordinateur.\n-> Tapez 2 si vous voulez Joueur VS Joueur.\n-> Tapez 3 si vous souhaitez voir deux IA s'affronter.");
		while(true){
			if (testrangemode==false){
				System.out.println("Veuillez rentrer un nombre compris entre 1 et 3.");
			}
			ordi=TestSiNombre(sc.nextLine()); //permet de test si on le joueur n'a pas rentré une lettre ou un symbole au lieu d'un chiffre.
			testrangemode=TestSiRange(ordi, 1, 3); //permet de test si on le joueur n'a pas rentré un nombre hors de la range demandée.
			if (testrangemode) break;
		}
		
		if (ordi==1){
			System.out.println("Quelle difficulté d'ordinateur souhaitez vous?\n   -> 1 pour facile,\n   -> 2 pour moyen,\n   -> 3 pour hardcore.");
			while (true){
				if (testrangedifficulté==false){
					System.out.println("Veuillez rentrer une difficulté comprise entre 1 et 3.");
				}
				difficulté=TestSiNombre(sc.nextLine());
				testrangedifficulté=TestSiRange(difficulté, 1, 3);
				if (testrangedifficulté) break;
			}
		}
		
		if (ordi==3){
			System.out.println("Quelle difficulté pour l'ordinateur 1 souhaitez vous? \n   -> 1 pour facile,\n   -> 2 pour moyen,\n   -> 3 pour hardcore.");
			while (true){
				if (testrangedifficultéOrdi1==false){
					System.out.println("Veuillez rentrer une difficulté pour l'ordi 1 comprise entre 1 et 3.");
				}
				difficultéOrdi1=TestSiNombre(sc.nextLine());
				testrangedifficultéOrdi1=TestSiRange(difficultéOrdi1, 1, 3);
				if (testrangedifficultéOrdi1) break;
			}
			
			System.out.println("Quelle difficulté pour l'ordinateur 2 souhaitez vous?\n   -> 1 pour facile,\n   -> 2 pour moyen,\n   -> 3 pour hardcore.");
			
			while (true){
				if (testrangedifficultéOrdi2==false){
					System.out.println("Veuillez rentrer une difficulté pour l'ordi 2 comprise entre 1 et 3.");
				}
				difficultéOrdi2=TestSiNombre(sc.nextLine());
				testrangedifficultéOrdi2=TestSiRange(difficultéOrdi2, 1, 3);
				if (testrangedifficultéOrdi2) break;
			}
		}
		
		System.out.println("Quel joueur commence? (1 ou 2).");
		while(true){
			if (testjoueurstart==false){
				System.out.println("Veuillez rentrer 1 ou 2.");
			}
			joueur=TestSiNombre(sc.nextLine()); //permet de test si on le joueur n'a pas rentré une lettre ou un symbole au lieu d'un chiffre.
			testjoueurstart=TestSiRange(joueur, 1, 2); //permet de test si on le joueur n'a pas rentré un nombre hors de la range demandée.
			if (testjoueurstart) break;
		}
		
		System.out.println("Combien de case(s) grisées voulez vous ? (celles-ci devront être utilisées deux fois pour tracer le trait).");
		pointilles=TestSiNombre(sc.nextLine());

		System.out.println("Combien de carrés par ligne et par colonne voulez vous?");
		while (true){ //evite de devoir changer l'initialisation.
			if (testrangetaille==false){
				System.out.println("Veuillez rentrer une taille comprise entre 1 et 49.");
			}
			taille=TestSiNombre(sc.nextLine());
			testrangetaille=TestSiRange(taille, 1, 49);
			if (testrangetaille) break;
		}
	}

	/**
	 * Permet de reset les valeurs statiques pour recommencer les parties.
	 */
	public static void Reset(){
		joueur=1; taille=2; pointilles=0; ordi=1; difficulté=1; click1=0; click2=0; nop=0; RecommencerJeuInt=1; difficultéOrdi1=1; difficultéOrdi2=1; Plateau.compteur=0; Plateau.pointJ1=0; Plateau.pointJ2=0;;
		nomJ1="Joueur 1"; nomJ2="Joueur 2";
		edit1=false; edit2=false; RecommencerJeu=true;
	}
	
	/** 
	 * Joue le rôle du main. Est dans une fonction à part afin de pouvoir relancer le jeu facilement.
	 */
	public static void Jeu(){

		while (RecommencerJeu){
			Reset();
			//Déclaration des variables
			boolean rejoue = false;
			boolean testrangeconsoleoupas=true;
			Scanner sc = new Scanner(System.in);
			System.out.println("-> Tapez 1 si vous voulez jouer en console. \n-> Tapez 2 si vous voulez jouer en mode graphique.");
			while(true){
				if (testrangeconsoleoupas==false){
					System.out.println("Veuillez choisir entre 1 et 2.");
				}
				console=TestSiNombre(sc.nextLine()); //permet de test si on le joueur n'a pas rentré une lettre ou un symbole au lieu d'un chiffre.
				testrangeconsoleoupas=TestSiRange(console, 1, 2); //permet de test si on le joueur n'a pas rentré un nombre hors de la range demandée.
				if (testrangeconsoleoupas) break;
			}
	
	
			//DEMANDE DES INFORMATIONS EN CONSOLE
			if (console ==1) {
				DemandeInformation();
			}
	
			//DEMANDE DES INFORMATIONS EN GRAPHIQUE
			if (console ==2) {
				DemandeGraphique();
			}
	
			
			//INITIALISATION DU PLATEAU ET DES TABLEAUX POUR LES DEUX MODES
			
			String[][] visuel = new String [2*taille+1][2*taille+1]; //permet d'obtenir la taille du plateau désiré
			int compteur=0; // permettra de compter le nombre de cases restantes
			Plateau Plateau1= new Plateau(taille, compteur, visuel);
	
			
			Plateau1.Initialisation(visuel); //initialise le plateau de jeu.
			if (console==2){ //initialise le plateau de jeu graphiquement seulement si la demande graphique a été demandée.
				Graphique.InitialisationGraphique(visuel);
			}
			Plateau1.Pointilles(pointilles, visuel); //rajoute les cases grisées au visuel du plateau.
	
	
	
			//Déroulement du jeu en 1v1 contre ordi :
			if (ordi==1){	
				while (Plateau1.getCompteur()!=(((visuel.length-1)/2)*((visuel.length-1)/2))){
					rejoue = JoueurVsOrdi(Plateau1, joueur, compteur, difficulté, visuel);
					if (rejoue == false){
						joueur=Changer(joueur);
					}
				}
			}	
	
	
	
			//Déroulement du jeu avec deux joueurs :
			if (ordi==2){	
				while (Plateau1.getCompteur()!=(((visuel.length-1)/2)*((visuel.length-1)/2))){
					rejoue = JoueurVsJoueur(Plateau1, joueur, compteur, visuel);
					if (rejoue == false){
						joueur=Changer(joueur);
					}
				}
			}
	
	
			//Déroulement du jeu Ordi vs Ordi :
			if (ordi==3){
				while (Plateau1.getCompteur()!=(((visuel.length-1)/2)*((visuel.length-1)/2))){
					rejoue = OrdiVSOrdi(Plateau1, joueur, compteur, visuel);
					if (rejoue == false){
						joueur=Changer(joueur);
					}
				}
			}	
	

			if (console==1)
				Plateau1.Afficher(visuel);
	
			// Fin du jeu, Affichage console.
			if (console==1){
				//Fin du jeu si Homme vs IA
				if (ordi==1){
					if (Plateau1.getPointJ1()>Plateau1.getPointJ2()){
						if (Plateau1.getPointJ1()==1){
							System.out.println("Félicitations joueur 1! Vous avez gagné avec "+Plateau1.getPointJ1()+" point!");
						}else{
							System.out.println("Félicitations joueur 1! Vous avez gagné avec "+Plateau1.getPointJ1()+" points!");
						}
					}
	
					if (Plateau1.getPointJ2()>Plateau1.getPointJ1()){
						if (Plateau1.getPointJ2()==1){
							System.out.println("Désolé! L'ordinateur vous a battu avec "+Plateau1.getPointJ2()+" point!");
						}else{ // pour éviter la faute d'orthographe... 
							System.out.println("Désolé! L'ordinateur vous a battu avec "+Plateau1.getPointJ2()+" points!");
						}
					}
	
					if (Plateau1.getPointJ1()==Plateau1.getPointJ2()){
						System.out.println("Egalité! Personne ne gagne! Ni même l'ordi!");
					}
				}
	
				//Fin du jeu si Homme vs Homme
				if (ordi==2){
					if (Plateau1.getPointJ1()>Plateau1.getPointJ2()){
						if (Plateau1.getPointJ1()==1){
							System.out.println("Félicitations joueur 1! Vous avez gagné avec "+Plateau1.getPointJ1()+" point!");
						}else{
							System.out.println("Félicitations joueur 1! Vous avez gagné avec "+Plateau1.getPointJ1()+" points!");
						}
					}
	
					if (Plateau1.getPointJ2()>Plateau1.getPointJ1()){
						if (Plateau1.getPointJ2()==1){
							System.out.println("Félicitations joueur 2! Vous avez gagné avec "+Plateau1.getPointJ2()+" point!");
						}else{
							System.out.println("Félicitations joueur 2! Vous avez gagné avec "+Plateau1.getPointJ2()+" points!");
						}
					}
	
					if (Plateau1.getPointJ1()==Plateau1.getPointJ2()){
						System.out.println("Egalité! Personne ne gagne!");
					}
				}
	
				if (ordi==3){
					if (Plateau1.getPointJ1()>Plateau1.getPointJ2()){
						if (Plateau1.getPointJ1()==1){
							System.out.println("L'ordi n°1 bat l'autre ordi avec "+Plateau1.getPointJ1()+" point!");
						}else{
							System.out.println("L'ordi n°1 bat l'autre ordi avec "+Plateau1.getPointJ1()+" points!");
						}
					}
					if (Plateau1.getPointJ2()>Plateau1.getPointJ1()){
						if (Plateau1.getPointJ2()==1){
							System.out.println("L'ordi n°2 bat l'autre ordi  "+Plateau1.getPointJ2()+" point!");
						}else{
							System.out.println("L'ordi n°2 bat l'autre ordi avec "+Plateau1.getPointJ2()+" points!");
						}
					}
					if (Plateau1.getPointJ1()==Plateau1.getPointJ2()){
						System.out.println("Egalité! Aucun ordi ne gagne!");
					}
				}
			}	
		

		System.out.println("\n\nVoulez-vous recommencer le jeu? \nTapez 1 si vous voulez recommencer.\nTapez 2 sinon.");
		RecommencerJeuInt=TestSiNombre(sc.nextLine());
		if (RecommencerJeuInt==1){
			RecommencerJeu=true;
		}
		if (RecommencerJeuInt==2){
			System.out.println("Tant pis :(.. Merci de m'avoir utilisé quand même! :)");
			RecommencerJeu=false;
		}
		
		
			
		}//Fin première partie.
	}

	/**
	 * Appelle juste la fonction Jeu().
	 */
	public static void main(String[] args){	
		Jeu(); //permet de faire marcher le bouton rejouer dans la partie graphique. Seul facon de redémarrer le main.
	}// FIN DU MAIN

}

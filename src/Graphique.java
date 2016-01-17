import java.awt.Font;

public class Graphique {

	// PARTIE GRAPHIQUE
/**
 * Permet de transformer le tableau visuel en plateau graphique. Cette fonction va lire à chaque tour le plateau et va l'update.
 * @param visuel
 */
public static void AfficherVisuelGraphique(String[][]visuel){ // Permet de lire le visuel et de le transcrire en graphique.
	Font font1 = new Font("Times", Font.BOLD, 40-(visuel.length/2));
	int a=0;//Bug de java détourné.
	if (JeuCarre.taille<5){
		for (int i=0;i<visuel.length;i++){ //création du plateau avec des "o"
			for (int j=0;j<visuel.length;j++){
				if (visuel[i][j]=="---"){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.line(j-1, i, j+1, i);
				}

				if (visuel[i][j]=="   "){
					if(DetectPos(j-1+0.07,  (1+j), i-0.3, i+0.3-0.07)){
						StdDraw.setPenRadius(0.015);
						if (JeuCarre.joueur==1){
							StdDraw.setPenColor(StdDraw.GREEN);
						}
						if (JeuCarre.joueur==2){
							StdDraw.setPenColor(StdDraw.BLUE);
						}
						StdDraw.line(j-1, i, 1+j, i);	
					}
				}
				
				if (i%2!=0 && j%2==0){
					if (visuel[i][j]=="  "){
						if(DetectPos(j-0.07,  j+0.07, i-1+0.1, i+1-0.1)){
							StdDraw.setPenRadius(0.015);
							if (JeuCarre.joueur==1){
								StdDraw.setPenColor(StdDraw.GREEN);
							}
							if (JeuCarre.joueur==2){
								StdDraw.setPenColor(StdDraw.BLUE);
							}
							StdDraw.line(j, i-1, j, i+1);	
						}
					}
				}
				
				if (visuel[i][j]=="| "){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.line(j, i-1, j, i+1);					
				}
				
				if (visuel[i][j]==" . "){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
					StdDraw.line(j-1, i, j+1, i);					
				}
				
				if (visuel[i][j]==" . "){
					if(DetectPos(j-1+0.1, j+1+0.1,  i-0.1, i+0.1)){
						StdDraw.setPenRadius(0.015);
						if (JeuCarre.joueur==1){
							StdDraw.setPenColor(StdDraw.GREEN);
						}
						if (JeuCarre.joueur==2){
							StdDraw.setPenColor(StdDraw.BLUE);
						}					
						StdDraw.line(j-1, i, j+1, i);	
					}
				}
				
				if (visuel[i][j]==". "){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
					StdDraw.line(j, i-1, j, i+1);					
				}
				
				if (visuel[i][j]==". "){
					if(DetectPos(j-0.1,  j+0.1, i-0.1-1, i+0.1+1)){
						StdDraw.setPenRadius(0.015);
						if (JeuCarre.joueur==1){
							StdDraw.setPenColor(StdDraw.GREEN);
						}
						if (JeuCarre.joueur==2){
							StdDraw.setPenColor(StdDraw.BLUE);
						}
						StdDraw.line(j, i-1, j, i+1);	
					}
				}
				
				if (i%2!=0 && j%2!=0){ // limiter les calculs
					if (visuel[i][j]!="  "){ // pour éviter que ca marque le chiffre partout ou cest vide.
						a=Integer.parseInt(visuel[i][j].substring(0, 1)); //Affiche "2 " or "2 " n'est pas un string donc ca bug .. 
						if (visuel[i][j].equals("1 ")){ // pour éviter que ca marque le chiffre partout ou cest vide.
							StdDraw.setPenColor(StdDraw.GREEN);
							StdDraw.filledRectangle(j, i, 0.90, 0.90);
						}
						if (visuel[i][j].equals("2 ")){ // pour éviter que ca marque le chiffre partout ou cest vide.
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledRectangle(j, i, 0.90, 0.90);
						}
						StdDraw.setFont(font1);
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.text(j, i, Integer.toString(a));
					}
				}
			}
		}
		for (int i=0;i<visuel.length;i++){ //création du plateau avec des "o"
			for (int j=0;j<visuel.length;j++){
				if (visuel[i][j]=="o"){
					StdDraw.setPenRadius(0.03);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.point(i, j);
				}
			}
		}
	}// Fin JeuCarre.taille 5
	
	if (JeuCarre.taille>=5){
		for (int i=0;i<visuel.length;i++){ //création du plateau avec des "o"
			for (int j=0;j<visuel.length;j++){
				if (visuel[i][j]=="-----"){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.line(j-1, i, j+1, i);					
				}
				
				if (visuel[i][j]=="     "){
					if(DetectPos(j-1+0.07,  (1+j), i-0.3, i+0.3-0.07)){
						StdDraw.setPenRadius(0.02);
						if (JeuCarre.joueur==1){
							StdDraw.setPenColor(StdDraw.GREEN);
						}
						if (JeuCarre.joueur==2){
							StdDraw.setPenColor(StdDraw.BLUE);
						}
						StdDraw.line(j-1, i, 1+j, i);	
					}
				}
				
				if (visuel[i][j]=="|  "){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.line(j, i-1, j, i+1);					
				}
				
				if (i%2!=0 && j%2==0){
					if (visuel[i][j]=="   "){
						if(DetectPos(j-0.07,  j+0.07, i-1+0.1, i+1-0.1)){
							StdDraw.setPenRadius(0.02);
							if (JeuCarre.joueur==1){
								StdDraw.setPenColor(StdDraw.GREEN);
							}
							if (JeuCarre.joueur==2){
								StdDraw.setPenColor(StdDraw.BLUE);
							}
							StdDraw.line(j, i-1, j, i+1);	
						}
					}
				}
				
				
				if (visuel[i][j]=="  .  "){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
					StdDraw.line(j-1, i, j+1, i);					
				}
				
				if (visuel[i][j]=="  .  "){
					if(DetectPos(j-0.1-1,  j+0.1+1, i-0.1, i+0.1)){
						StdDraw.setPenRadius(0.015);
						if (JeuCarre.joueur==1){
							StdDraw.setPenColor(StdDraw.GREEN);
						}
						if (JeuCarre.joueur==2){
							StdDraw.setPenColor(StdDraw.BLUE);
						}
						StdDraw.line(j-1, i, j+1, i);	
					}
				}
				
				if (visuel[i][j]==".  "){
					StdDraw.setPenRadius(0.01);
					StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
					StdDraw.line(j, i-1, j, i+1);					
				}
				
				if (visuel[i][j]==".  "){
					if(DetectPos(j-0.1,  j+0.1, i-0.1-1, i+0.1+1)){
						StdDraw.setPenRadius(0.015);
						if (JeuCarre.joueur==1){
							StdDraw.setPenColor(StdDraw.GREEN);
						}
						if (JeuCarre.joueur==2){
							StdDraw.setPenColor(StdDraw.BLUE);
						}
						StdDraw.line(j, i-1, j, i+1);	
					}
				}
				
				if (i%2!=0 && j%2!=0){ // limiter les calculs
					if (visuel[i][j]!="   "){ // pour éviter que ca marque le chiffre partout ou cest vide.
						a=Integer.parseInt(visuel[i][j].substring(0, 1)); //Affiche "2 " or "2 " n'est pas un string donc ca bug .. 
						if (visuel[i][j].equals("1  ")){ // pour éviter que ca marque le chiffre partout ou cest vide.
							StdDraw.setPenColor(StdDraw.GREEN);
							StdDraw.filledRectangle(j, i, 0.90, 0.90);
						}
						if (visuel[i][j].equals("2  ")){ // pour éviter que ca marque le chiffre partout ou cest vide.
							StdDraw.setPenColor(StdDraw.BLUE);
							StdDraw.filledRectangle(j, i, 0.90, 0.90);
						}
						StdDraw.setFont(font1);
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.text(j, i, Integer.toString(a));
					}
				}
			}
		}
		for (int i=0;i<visuel.length;i++){ //création du plateau avec des "o"
			for (int j=0;j<visuel.length;j++){
				if (visuel[i][j]=="o"){
					if (JeuCarre.taille<=8){
						StdDraw.setPenRadius(0.03);
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.point(i, j);
					}
					if (JeuCarre.taille>8){
						StdDraw.setPenRadius(0.03-(0.0007*JeuCarre.taille));
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.point(i, j);
					}
				}
			}
		}
	}// Fin JeuCarre.taille >5
}

/**
 * Gère toute la partie affichage du plateau en graphique, textes et visuel. Cette fonction fait appel à AfficherVisuelGraphique(), qui dessine les traits, les points et les pointillés.
 * @param joueur Le joueur pourra être affiché dans le plateau.
 * @param visuel Nécessaire pour les positions des affichages.
 */
public static void AfficherTextGraphique(int joueur, String[][] visuel) { //Permet de gérer si le jeu est fini ou non en graphique.
	StdDraw.clear();
	Font font = new Font("Papyrus", Font.PLAIN, 30);
	Font font3 = new Font("Papyrus", Font.ITALIC, 2*visuel.length);
	Font font4 = new Font("Papyrus", Font.ITALIC, 3*visuel.length);
	Font font5 = new Font("Papyrus", Font.ITALIC, 26);
	Font font6 = new Font("Papyrus", Font.ITALIC, 39);
	Font font7 = new Font("Papyrus", Font.ITALIC, 25);
	Font font8 = new Font("Papyrus", Font.ITALIC, 20);
	Font font9 = new Font("Papyrus", Font.ITALIC, 7+JeuCarre.taille*2);
	Font font10 = new Font("Papyrus", Font.ITALIC, 22);
	Font font11 = new Font("Papyrus", Font.ITALIC, 12);
	Font font12 = new Font("Papyrus", Font.ITALIC, 35);

	
	if (Plateau.compteur!=(((visuel.length-1)/2)*((visuel.length-1)/2))){ // Tant que le jeu n'est pas fini, afficher le tour du joueur et le score actuel.
		
		StdDraw.setFont(font8);
		StdDraw.setPenColor(StdDraw.BLACK);
		if (JeuCarre.taille==2){
			if (joueur==1)
				StdDraw.text(visuel.length/2, -(visuel.length*0.22),"Tour de "+ JeuCarre.nomJ1 + "("+joueur+")");
			if (joueur==2)
				StdDraw.text(visuel.length/2, -(visuel.length*0.22),"Tour de "+ JeuCarre.nomJ2 + "("+joueur+")");
		}
		
		StdDraw.setFont(font);
		if (JeuCarre.taille<5 &&JeuCarre.taille!=2){
			if (joueur==1)
				StdDraw.text(visuel.length/2, -(visuel.length*0.18),"Tour de "+ JeuCarre.nomJ1 + "("+joueur+")");
			if (joueur==2)
				StdDraw.text(visuel.length/2, -(visuel.length*0.18),"Tour de "+ JeuCarre.nomJ2 + "("+joueur+")");
		}
		if (JeuCarre.taille>=5 && JeuCarre.taille<=10){
			if (joueur==1)
				StdDraw.text(visuel.length/2, -(visuel.length*0.12),"Tour de "+ JeuCarre.nomJ1 + "("+joueur+")");
			if (joueur==2)
				StdDraw.text(visuel.length/2, -(visuel.length*0.12),"Tour de "+ JeuCarre.nomJ2 + "("+joueur+")");
		}
		if (JeuCarre.taille>10 && JeuCarre.taille<20){
			if (joueur==1)
				StdDraw.text(visuel.length/2, -(visuel.length*0.08),"Tour de "+ JeuCarre.nomJ1 + "("+joueur+")");
			if (joueur==2)
				StdDraw.text(visuel.length/2, -(visuel.length*0.08),"Tour de "+ JeuCarre.nomJ2 + "("+joueur+")");
		}
		if (JeuCarre.taille>=20){
			if (joueur==1)
				StdDraw.text(visuel.length/2, -(visuel.length*0.06),"Tour de "+ JeuCarre.nomJ1 + "("+joueur+")");
			if (joueur==2)
				StdDraw.text(visuel.length/2, -(visuel.length*0.06),"Tour de "+ JeuCarre.nomJ2 + "("+joueur+")");
		}
			if (JeuCarre.taille<=4){
				StdDraw.setFont(font4);
				StdDraw.text(visuel.length/2, visuel.length*0.95,"Score :");
				StdDraw.text(0.06*visuel.length, visuel.length*1.1,JeuCarre.nomJ1 + " : " + Plateau.pointJ1);
				StdDraw.text(visuel.length*0.9, visuel.length*1.1,JeuCarre.nomJ2 + " : " + Plateau.pointJ2);
			}
			if (JeuCarre.taille>4 && JeuCarre.taille<7){
				StdDraw.setFont(font4);
				StdDraw.text(visuel.length/2, visuel.length*1.01,"Score :");
				StdDraw.setFont(font3);
				StdDraw.text(0.06*visuel.length, visuel.length*1.1,JeuCarre.nomJ1 + " : " + Plateau.pointJ1);
				StdDraw.text(visuel.length*0.9, visuel.length*1.1,JeuCarre.nomJ2 + " : " + Plateau.pointJ2);
			}
			if (JeuCarre.taille>=7 && JeuCarre.taille<20){
				StdDraw.setFont(font6);
				StdDraw.text(visuel.length/2, visuel.length*1.01,"Score :");
				StdDraw.setFont(font5);
				StdDraw.text(0.06*visuel.length,visuel.length*1.05,JeuCarre.nomJ1 + " : " + Plateau.pointJ1);
				StdDraw.text(visuel.length*0.9, visuel.length*1.05,JeuCarre.nomJ2 + " : " + Plateau.pointJ2);
			}
			if (JeuCarre.taille>=20){
				StdDraw.setFont(font7);
				StdDraw.text(visuel.length/2, visuel.length*1.01,"Score :");
				StdDraw.setFont(font8);
				StdDraw.text(0.06*visuel.length,visuel.length*1.03,JeuCarre.nomJ1 + " : " + Plateau.pointJ1);
				StdDraw.text(visuel.length*0.9, visuel.length*1.03,JeuCarre.nomJ2 + " : " + Plateau.pointJ2);
			}
	}else{
		while (true){
			StdDraw.setFont(font);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(visuel.length/2, -1.4-(JeuCarre.taille*0.03),"Fin du jeu!");
			if (Plateau.pointJ1>Plateau.pointJ2){
				if (JeuCarre.taille>=8){
					StdDraw.setFont(font5);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(visuel.length/9, visuel.length-0.5, "Score Final : ");
				}else if (JeuCarre.taille!=2){
					StdDraw.setFont(font4);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(visuel.length/9, visuel.length-0.5, "Score Final : ");
				}

				if (JeuCarre.taille>=8){
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.setFont(font10);
					StdDraw.text(visuel.length*0.7, visuel.length-0.4, JeuCarre.nomJ1+" (gagnant) : "+Plateau.pointJ1+" -- "+JeuCarre.nomJ2+" : "+Plateau.pointJ2);					
				}else{
					if (JeuCarre.taille==2){
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.setFont(font11);
						StdDraw.text(2, visuel.length-0.4, JeuCarre.nomJ1+" (gagnant) : "+Plateau.pointJ1+" -- "+JeuCarre.nomJ2+" : "+Plateau.pointJ2);
					}else{
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.setFont(font9);
						StdDraw.text(visuel.length*0.7, visuel.length-0.4, JeuCarre.nomJ1+" (gagnant) : "+Plateau.pointJ1+" -- "+JeuCarre.nomJ2+" : "+Plateau.pointJ2);
					}
				}
			}
			
			if (Plateau.pointJ2>Plateau.pointJ1){
				if (JeuCarre.taille>=8){
					StdDraw.setFont(font5);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(visuel.length/9, visuel.length-0.5, "Score Final : ");
				}else if (JeuCarre.taille!=2){
					StdDraw.setFont(font4);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(visuel.length/9, visuel.length-0.5, "Score Final : ");
				}

				if (JeuCarre.taille>=8){
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.setFont(font10);
					StdDraw.text(visuel.length*0.7, visuel.length-0.4, JeuCarre.nomJ1+" : "+Plateau.pointJ1+" -- "+JeuCarre.nomJ2+" (gagnant) : "+Plateau.pointJ2);
				}else{
					if (JeuCarre.taille==2){
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.setFont(font11);
						StdDraw.text(2, visuel.length-0.4, JeuCarre.nomJ1+" : "+Plateau.pointJ1+" -- "+JeuCarre.nomJ2+" (gagnant) : "+Plateau.pointJ2);
					}else{
						StdDraw.setPenColor(StdDraw.BLACK);
						StdDraw.setFont(font9);
						StdDraw.text(visuel.length*0.7, visuel.length-0.4, JeuCarre.nomJ1+" : "+Plateau.pointJ1+" -- "+JeuCarre.nomJ2+" (gagnant) : "+Plateau.pointJ2);
					}
				}
			}
			
			if (Plateau.pointJ1==Plateau.pointJ2){
				if (JeuCarre.taille>=8){
					StdDraw.setFont(font5);
				}else{
					StdDraw.setFont(font4);
				}
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.text(visuel.length/2, visuel.length-0.5,"Egalité! Personne ne gagne");
			}
			
			if (JeuCarre.taille<6){
				StdDraw.setFont(font4);
				Menu.BoutonEntreText(0.005, StdDraw.BLACK, visuel.length/2, 1.09*visuel.length, 0.4*visuel.length, 0.05*visuel.length, StdDraw.RED, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, 0.26*visuel.length, 1.06*visuel.length, "Rejouer");
			}
			if (JeuCarre.taille>=6 && JeuCarre.taille<=10){
				StdDraw.setFont(font6);
				Menu.BoutonEntreText(0.005, StdDraw.BLACK, visuel.length/2, 1.07*visuel.length, 0.4*visuel.length, 0.03*visuel.length, StdDraw.RED, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, 0.35*visuel.length, 1.05*visuel.length, "Rejouer");
			}
			if (JeuCarre.taille>10 && JeuCarre.taille<14){
				StdDraw.setFont(font12);
				Menu.BoutonEntreText(0.005, StdDraw.BLACK, visuel.length/2, 1.07*visuel.length, 0.4*visuel.length, 0.02*visuel.length, StdDraw.RED, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, 0.35*visuel.length, 1.05*visuel.length, "Rejouer");
			}
			if (JeuCarre.taille>=14){
				StdDraw.setFont(font12);
				Menu.BoutonEntreText(0.005, StdDraw.BLACK, visuel.length/2, 1.06*visuel.length, 0.4*visuel.length, 0.02*visuel.length, StdDraw.RED, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, 0.35*visuel.length, 1.04*visuel.length, "Rejouer");
			}
			
			AfficherVisuelGraphique(visuel);
			StdDraw.show(16);
			StdDraw.clear();
			if (Menu.DetectPos(0.1*visuel.length, 0.9*visuel.length, 1.04*visuel.length, 1.14*visuel.length) && StdDraw.mousePressed()){
				JeuCarre.Jeu();
			}
		}
	}
	AfficherVisuelGraphique(visuel);
}

/**
 * Initialise le plateau en graphique.
 * @param visuel
 */
public static void InitialisationGraphique(String[][] visuel){
	if (JeuCarre.taille<7){
		StdDraw.setCanvasSize(110*JeuCarre.taille, 110*JeuCarre.taille);
	}
	if (JeuCarre.taille>=7){
		StdDraw.setCanvasSize(770, 700);
	}
	StdDraw.setXscale(-1.5, visuel.length+1);
	StdDraw.setYscale(visuel.length+1, -1.5);
	StdDraw.clear();
}
	
/**
 * Fonction qui joue le même rôle que TourJoueur() en console : permet au joueur de choisir où il va jouer en cliquant sur le graphique.
 * @param joueur
 * @param visuel
 * @return True ou False selon que le joueur complète un carré ou non.
 */
public static boolean Hitbox(int joueur, String[][] visuel){ //REMPLACE TOURJOUEUR(){...}		
	double largeur=1;
	boolean demande=true;
	if (JeuCarre.taille<5){
		for (int i=0;i<visuel.length;i++){ //création du plateau avec des "o"
			for (int j=0;j<visuel.length;j++){
				if (visuel[i][j]=="---"  && StdDraw.mousePressed()){//evite les reclick sur les cases déjà utilisées!
					if(DetectPos(j-1, j+1, i-(largeur/2), i+(largeur/2))){ //utilise l'autre fonction qui n'update pas, ce qui évite les erreurs de compteurs et de joueurs.
						Plateau.rejoue=false;
					}
				} 

				if (visuel[i][j]=="   "  && StdDraw.mousePressed()){
					demande = detectPos(i+1, j+1, joueur, visuel, j-1,  j+1, i-(largeur/2), i+(largeur/2));// la présence du +1 pour i et j permet de réutiliser Update comme pour le joueur.
					Plateau.rejoue=true;
				}
				
				if (i%2!=0 && j%2==0){ //evite de confondre les cases verticales avec les cases avec les nombres.
					if (visuel[i][j]=="| "  && StdDraw.mousePressed()){
						if(DetectPos(j-(largeur/2), j+(largeur/2), i-1, i+1)){
							Plateau.rejoue=false;
						}
					}
				}
				
				if (i%2!=0 && j%2==0){ 
					if (visuel[i][j]=="  " && StdDraw.mousePressed()){
						demande = detectPos(i+1, j+1, joueur, visuel, j-(largeur/2),  j+(largeur/2), i-1, i+1);
						Plateau.rejoue=true;
					}
				}

				if (visuel[i][j]==" . " && StdDraw.mousePressed()){
					demande = detectPos(i+1, j+1, joueur, visuel, j-1,  j+1, i-(largeur/2), i+(largeur/2));
					Plateau.rejoue=true;		
				}
				
				if (i%2!=0 && j%2==0){
					if (visuel[i][j]==". " && StdDraw.mousePressed()){
						demande = detectPos(i+1, j+1, joueur, visuel, j-(largeur/2), j+(largeur/2), i-1, i+1);
						Plateau.rejoue=true;
					}
				}
			}
		}
	}

	if (JeuCarre.taille>=5){
		for (int i=0;i<visuel.length;i++){ //création du plateau avec des "o"
			for (int j=0;j<visuel.length;j++){
				if (visuel[i][j]=="-----"  && StdDraw.mousePressed()){//evite les reclick sur les cases déjà utilisées!
					if(DetectPos(j-1,  j+1, i-(largeur/2), i+(largeur/2))){ //utilise l'autre fonction qui n'update pas, ce qui évite les erreurs de Plateau.compteurs et de joueurs.
						Plateau.rejoue=false;
					}
				} 
				
				if (visuel[i][j]=="     "  && StdDraw.mousePressed()){
					demande = detectPos(i+1, j+1, joueur, visuel, j-1,  j+1,i-(largeur/2), i+(largeur/2));
					Plateau.rejoue=true;
				}
				
				if (visuel[i][j]=="|  "  && StdDraw.mousePressed()){//evite les reclick sur les cases déjà utilisées!
					if(DetectPos(j-(largeur/2), j+(largeur/2),  i-1, i+1)){ //utilise l'autre fonction qui n'update pas, ce qui évite les erreurs de Plateau.compteurs et de joueurs.
						Plateau.rejoue=false;
					}
				} 
				
				if (i%2!=0 && j%2==0){
					if (visuel[i][j]=="   " && StdDraw.mousePressed()){
						demande = detectPos(i+1, j+1, joueur, visuel, j-(largeur/2),  j+(largeur/2), i-1, i+1);
						Plateau.rejoue=true;
					}
				}
				
				
				if (visuel[i][j]=="  .  " && StdDraw.mousePressed()){
					demande = detectPos(i+1, j+1, joueur, visuel, j-1,  j+1, i-(largeur/2), i+(largeur/2));
					Plateau.rejoue=true;
				}
				if (i%2!=0 && j%2==0){
					if (visuel[i][j]==".  " && StdDraw.mousePressed()){
						demande = detectPos(i+1, j+1, joueur, visuel, j-(largeur/2),  j+(largeur/2), i-1, i+1);
						Plateau.rejoue=true;
					}
				}
			}
		}
	}
//Afficher(visuel);
return demande; // 
}

/**
 * Permet d'update le plateau selon l'endroit où le joueur a clické.
 * @param ligne
 * @param colonne
 * @param joueur
 * @param visuel
 * @param x0 Représente la position en haut à gauche du rectangle de détection de la souris.
 * @param x1 Représente la position en haut à droite du rectangle de détection de la souris.
 * @param y0 Représente la position en bas à gauche du rectangle de détection de la souris.
 * @param y1 Représente la position en haut à droite du rectangle de détection de la souris.
 * @return True ou False selon l'état de la variable globale Plateau.rejoue.
 */
public static boolean detectPos(int ligne, int colonne, int joueur, String[][]visuel, double x0,  double x1, double y0, double y1){ 
	double x=StdDraw.mouseX();
	double y=StdDraw.mouseY(); 
	if (x>x0 && x<x1 && y>y0 && y<y1){
		while(StdDraw.mousePressed()){}	 //evite le fait de laisser appuyer la souris.
		Plateau.Update(ligne, colonne, joueur, visuel); //La fonction update set la valeur de Plateau.rejoue.
	}
	return Plateau.rejoue;
}

/**
 * Permet de faire des effets de surlignement quand on passe la souris sur le trait.</br>
 * Permet aussi d'éviter les changements de Plateau.compteurs et de joueur par rapport à la fonction detectPos().
 * @param x0 Représente la position en haut à gauche du rectangle de détection de la souris.
 * @param x1 Représente la position en haut à droite du rectangle de détection de la souris.
 * @param y0 Représente la position en bas à gauche du rectangle de détection de la souris.
 * @param y1 Représente la position en haut à droite du rectangle de détection de la souris.
 * @return True ou False selon que la position de la souris se trouve dans le rectangle de détection.
 */
public static boolean DetectPos(double x0, double x1, double y0, double y1){ 
	double x=StdDraw.mouseX();
	double y=StdDraw.mouseY();
	if (x0<x && x<x1 && y0<y && y<y1){
		return true;
	}
	return false;
}
}

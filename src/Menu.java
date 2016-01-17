import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;


public class Menu {
	
	public static int ok, click1=0, click2=0, nop=0;
	public static boolean edit1=false, edit2=false;
	
	
	/**
	 * Lance le Menu 1, qui permet de choisir les attributs du plateau de jeu.
	 */
	public static void Menu1(){
		int largeur=500, hauteur=500;
		StdDraw.setCanvasSize(largeur, hauteur); // set la JeuCarre.taille de la fenetre.
		StdDraw.setXscale(-largeur, largeur); // set l'échelle des axes de la fenetre.
		StdDraw.setYscale(-hauteur, hauteur); // set l'échelle des axes de la fenetre.
	
		//Partie Police 
		Font font = new Font("Helvetica", Font.PLAIN, 30);
		Font font1 = new Font("Helvetica", Font.PLAIN, 40);
		Font font2 = new Font("Helvetica", Font.PLAIN, 20);
		Font font3 = new Font("Helvetica", Font.PLAIN, 30);
		
		
		//Partie couleur.
		Color background= new Color(255, 244, 236);
		Color BleuEncreClair = new Color(160,210,255);
		Color BleuClair = new Color(222,255,255);
		
		
		while(true){

			StdDraw.setPenColor(background); //couleur du fond du menu.
			StdDraw.filledRectangle(0, 0, 1000, 1000);
			
			//Affichage du titre du jeu.
			TextEncadre(StdDraw.BLACK, 0, 475, "Dot and Boxes", font1, BleuEncreClair, 0, 480, 300, 50, StdDraw.BLACK, 0.005);

			//demande du mode de jeu.
			TextEncadre(StdDraw.BLACK, 0, 350, "Mode de jeu souhaité", font, BleuClair, 0, 300, 525, 105, StdDraw.BLACK, 0.005);
			
			//J1vsIA
			StdDraw.setFont(font2);
			Bouton(0.004, StdDraw.BLACK, -350, 250, 150, 30, StdDraw.LIGHT_GRAY, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "J1 VS IA");
			if(DetectPos(-500, -200, 220, 280) && StdDraw.mousePressed()){
				JeuCarre.ordi=1;
			}
			if (JeuCarre.ordi==1){
				StdDraw.setPenColor(255,116,0);
				StdDraw.filledRectangle(-350, 250, 150, 30);
				StdDraw.setPenColor(255,255,255);
				StdDraw.text(-355, 245,"J1 VS IA");
			}

			//J1vsJ2
			Bouton(0.004, StdDraw.BLACK, 0, 250, 150, 30, StdDraw.LIGHT_GRAY, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "J1 VS J2");
			if(DetectPos(-150, 150, 220, 280) && StdDraw.mousePressed()){
				JeuCarre.ordi=2;
			}
			if (JeuCarre.ordi==2){
				StdDraw.setPenColor(255,116,0);
				StdDraw.filledRectangle(0, 250, 150, 30);
				StdDraw.setPenColor(255,255,255);
				StdDraw.text(-5, 245,"J1 VS J2");
			}

			//IAvsIA
			Bouton(0.004, StdDraw.BLACK, 350, 250, 150, 30, StdDraw.LIGHT_GRAY, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "IA VS IA");
			if(DetectPos(200, 500, 220, 280) && StdDraw.mousePressed()){
				JeuCarre.ordi=3;
			}
			if (JeuCarre.ordi==3){
				StdDraw.setPenColor(255,116,0);
				StdDraw.filledRectangle(350, 250, 150, 30);
				StdDraw.setPenColor(255,255,255);
				StdDraw.text(345, 245,"IA VS IA");
			}

			//demande de la JeuCarre.taille
			TextEncadre(StdDraw.BLACK, 0, 120, "Taille désirée", font, BleuClair, 0, 80, 260, 92, StdDraw.BLACK, 0.005);

			StdDraw.setFont(font3);
			Bouton(0.008, StdDraw.BLACK, -120, 40, 30, 30, background, StdDraw.RED, StdDraw.YELLOW, StdDraw.RED, "-");
			
			if(DetectPos(-150, -90, 10, 70) && StdDraw.mousePressed()){ //Décrémente la taille si le bouton est cliqué.
				if (JeuCarre.taille>2){
					int temps= JeuCarre.taille-1;
					while(StdDraw.mousePressed()){
						JeuCarre.taille=temps;
					}
				}
			}
			
			StdDraw.setFont(font3);
			BoutonEntreText(0.008, StdDraw.BLACK, 120, 40, 30, 30, background, StdDraw.RED, StdDraw.YELLOW, StdDraw.RED, 103, 38, "+"); 
			if(DetectPos(90, 150, 10, 70) && StdDraw.mousePressed()){ //Incrémente la taille si le bouton est cliqué.
				if (JeuCarre.taille>=2){
					int test= JeuCarre.taille+1;
					while(StdDraw.mousePressed()){
						JeuCarre.taille=test;
					}
				}
			}

			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(0, 35, Integer.toString(JeuCarre.taille));

			//demande du nombre de cases grisées.
			TextEncadre(StdDraw.BLACK, 0, -60, "Nombre de cases grisées voulues", font, BleuClair, 0, -103, 510, 82, StdDraw.BLACK, 0.005);

			int maximum=((JeuCarre.taille*(JeuCarre.taille+1))+(JeuCarre.taille*(JeuCarre.taille+1)));
			Bouton(0.008, StdDraw.BLACK, -120, -140, 30, 30, background, StdDraw.RED, StdDraw.YELLOW, StdDraw.RED, "-");
			if(DetectPos(-150, -90, -170, -110) && StdDraw.mousePressed()){
				if (JeuCarre.pointilles>0 && JeuCarre.pointilles <maximum){
					int test= JeuCarre.pointilles-1;
					while(StdDraw.mousePressed()){
						JeuCarre.pointilles=test;
					}
				}
			}

			BoutonEntreText(0.008, StdDraw.BLACK, 120, -140, 30, 30, background, StdDraw.RED, StdDraw.YELLOW, StdDraw.RED, 103, -140, "+");
			if(DetectPos(90, 150, -170, -110) && StdDraw.mousePressed()){
				if (JeuCarre.pointilles>=0 && JeuCarre.pointilles <maximum){
					int test= JeuCarre.pointilles+1;
					while(StdDraw.mousePressed()){
						JeuCarre.pointilles=test;
					}
				}
			}
			if (JeuCarre.pointilles>maximum) //au cas où la personne diminue la taille alors que le nombre de pointillés choisis précédemment étaient supérieur au maximum possible.
				JeuCarre.pointilles=maximum;
			
			
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.text(0, -145, Integer.toString(JeuCarre.pointilles));

			
			
			
			if (JeuCarre.ordi==1){// Si J1 vs IA demandé. Pour choisir la difficulté de l'ordi.
				TextEncadre(StdDraw.BLACK, 0, -250, "Difficulté voulue", font, BleuClair,0, -300, 320, 95, StdDraw.BLACK, 0.005);

				StdDraw.setFont(font3);	
				Bouton(0.008, StdDraw.BLACK, -160, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "1");
				if(DetectPos(-190, -130, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficulté=1;
				}
				if (JeuCarre.difficulté==1){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(-160, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(-160, -335, "1");
				}

				Bouton(0.008, StdDraw.BLACK, 10, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "2");
				if(DetectPos(-20, 50, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficulté=2;
				}
				if (JeuCarre.difficulté==2){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(10, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(10, -335, "2");
				}
				
				Bouton(0.008, StdDraw.BLACK, 180, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "3");
				if(DetectPos(150, 210, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficulté=3;
				}
				if (JeuCarre.difficulté==3){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(180, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(180, -335, "3");
				}
			}
			
			
		
			
			if (JeuCarre.ordi==3){	//JeuCarre.difficulté si IA vs IA demandé.
				//Demande Difficulté Ordi 1.
				TextEncadre(StdDraw.BLACK, -250, -250, "Difficulté Ordi1", font, BleuClair,-250, -300, 220, 95, StdDraw.BLACK, 0.005);

				StdDraw.setFont(font3);
				Bouton(0.008, StdDraw.BLACK, -390, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "1");
				if(DetectPos(-420, -360, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficultéOrdi1=1;
				}
				if (JeuCarre.difficultéOrdi1==1){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(-390, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(-390, -335, "1");
				}

				Bouton(0.008, StdDraw.BLACK, -241, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "2");//perfection de -241..
				if(DetectPos(-271, -211, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficultéOrdi1=2;
				}
				if (JeuCarre.difficultéOrdi1==2){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(-241, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(-241, -335, "2");
				}
				
				Bouton(0.008, StdDraw.BLACK, -90, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "3");
				if(DetectPos(-120, -60, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficultéOrdi1=3;
				}
				if (JeuCarre.difficultéOrdi1==3){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(-90, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(-90, -335, "3");
				}
				
				//Demande Difficulté Ordi 2.
				TextEncadre(StdDraw.BLACK, 250, -250, "Difficulté Ordi2", font, BleuClair,250, -300, 220, 95, StdDraw.BLACK, 0.005);

				StdDraw.setFont(font3);
				Bouton(0.008, StdDraw.BLACK, 110, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "1");
				if(DetectPos(80, 140, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficultéOrdi2=1;
				}
				if (JeuCarre.difficultéOrdi2==1){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(110, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(110, -335, "1");
				}

				Bouton(0.008, StdDraw.BLACK, 243, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "2");//perfection de -236..
				if(DetectPos(213, 276, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficultéOrdi2=2;
				}
				if (JeuCarre.difficultéOrdi2==2){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(243, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(243, -335, "2");
				}
				
				Bouton(0.008, StdDraw.BLACK, 380, -330, 30, 35, background, StdDraw.BLUE, StdDraw.YELLOW, StdDraw.BLUE, "3");
				if(DetectPos(350, 410, -365, -295) && StdDraw.mousePressed()){
					JeuCarre.difficultéOrdi2=3;
				}
				if (JeuCarre.difficultéOrdi2==3){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.filledRectangle(380, -330, 30, 35);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(380, -335, "3");
				}
			}

			

			
			Color Grisfonce = new Color(134, 128, 125);
			Color OrangeOuPas= new Color(255, 166, 0);
			StdDraw.setFont(font);
			Bouton(0.01, StdDraw.BLACK, 450, -470, 80, 60, Grisfonce, OrangeOuPas, StdDraw.YELLOW, StdDraw.RED, "OK");

			//Casse la boucle si les valeurs entrées sont correctes.
			if (JeuCarre.taille!=0 && JeuCarre.ordi!=0){
				if(DetectPos(370, 530, -530, -410) && StdDraw.mousePressed()){
					break;
				}
			}	
			StdDraw.show(16);
			StdDraw.clear();
		}
	}
	
	/** 
	 * Lance le Menu 2, qui permet de choisir le nom des joueurs et de confirmer les valeurs saisies auparavant.
	 */
	public static void Menu2(){
		int largeur=500, hauteur=500;
		StdDraw.setCanvasSize(largeur, hauteur); // set la JeuCarre.taille de la fenetre.
		StdDraw.setXscale(-largeur, largeur); // set l'échelle des axes de la fenetre.
		StdDraw.setYscale(-hauteur, hauteur); // set l'échelle des axes de la fenetre.
		
		//Partie Police
		Font font1 = new Font("Helvetica", Font.PLAIN, 40);
		Font font5 = new Font("Helvetica", Font.ITALIC, 25);
		Font font6 = new Font("Helvetica", Font.BOLD, 25);
		Font font9 = new Font("Helvetica", Font.BOLD, 15);

		//Partie Couleur
		Color BleuEncreClair = new Color(160,210,255);
		Color BleuClair = new Color(206, 255, 255);
		Color BleuPale = new Color(150, 255, 255);
		Color VertPale = new Color(201,255,197);
		Color GrisClair= new Color(204, 204, 204);
		
		//Partie Menu2.
		while (true) {
			StdDraw.setPenColor(255, 244, 236); // couleur personnalisée.
			StdDraw.filledRectangle(0, 0, 1000, 1000);
			
			
			TextEncadre(StdDraw.BLACK, -80, 475, "Paramètres choisis : ", font1, BleuEncreClair, -100, 480, 425, 50, StdDraw.BLACK, 0.004);
			

			StdDraw.setFont(font5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textLeft(-500, 350, "• Mode de jeu souhaité : ");
			if (JeuCarre.ordi == 1) {
				TextEncadre(StdDraw.RED, 350, 350, "J1 vs Ordi", font6, VertPale, 345, 355, 150, 30, StdDraw.BLACK, 0.002);
				
			}
			if (JeuCarre.ordi == 2) {
				TextEncadre(StdDraw.RED, 332, 350, "J1 vs J2", font6, VertPale, 320, 355, 150, 30, StdDraw.BLACK, 0.002);

			}
			if (JeuCarre.ordi == 3) {
				TextEncadre(StdDraw.RED, 350, 350, "Ordi vs Ordi", font6, VertPale, 350, 355, 180, 30, StdDraw.BLACK, 0.002);
			}

			StdDraw.setFont(font5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textLeft(-500, 270, "• Taille du plateau : ");
			TextEncadre(StdDraw.RED, 340, 270, Integer.toString(JeuCarre.taille), font6, VertPale, 340, 275, 30, 30, StdDraw.BLACK, 0.002);


			StdDraw.setFont(font5);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.textLeft(-500, 190, "• Nombre de pointillés : ");
			TextEncadre(StdDraw.RED, 340, 190, Integer.toString(JeuCarre.pointilles), font6, VertPale, 340, 195, 30, 30, StdDraw.BLACK, 0.002);
			
			if (JeuCarre.ordi == 1){
				Bouton(0.004, StdDraw.BLACK, 340, 30, 150, 35, StdDraw.ORANGE, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, "Modifier");
				if (DetectPos(190, 490, -5, 75) && StdDraw.mousePressed()){
					JeuCarre.DemandeGraphique();
				}
				StdDraw.setFont(font9);
				Bouton(0.004, StdDraw.BLACK, -350, 30, 150, 35, StdDraw.GREEN, StdDraw.BLACK, StdDraw.BLUE, StdDraw.BLACK, "J1 commence");
				if (DetectPos(-500, -200, -5, 65) && StdDraw.mousePressed()){
					JeuCarre.joueur=1;
				}
				Bouton(0.004, StdDraw.BLACK, 0, 30, 150, 35, StdDraw.GREEN, StdDraw.BLACK, StdDraw.BLUE, StdDraw.BLACK, "Ordi commence");
				if (DetectPos(-150, 150, -5, 65) && StdDraw.mousePressed()){
					JeuCarre.joueur=2;
				}
				if (JeuCarre.joueur==1){
					TextEncadre(StdDraw.BLACK, -350, 25, "J1 commence", font9, BleuClair, -350, 30, 150, 35, StdDraw.BLACK, 0.004);
				}
				if (JeuCarre.joueur==2){
					TextEncadre(StdDraw.BLACK, 0, 25, "Ordi commence", font9, BleuClair, 0, 30, 150, 35, StdDraw.BLACK, 0.004);
				}
			}else{ //juste pour l'esthétique
				if (JeuCarre.ordi==2){
					StdDraw.setFont(font9);
					Bouton(0.004, StdDraw.BLACK, -350, 80, 150, 35, StdDraw.GREEN, StdDraw.BLACK, StdDraw.BLUE, StdDraw.BLACK, "J1 commence");
					if (DetectPos(-500, -200, 45, 115) && StdDraw.mousePressed()){
						JeuCarre.joueur=1;
					}
					Bouton(0.004, StdDraw.BLACK, 0, 80, 150, 35, StdDraw.GREEN, StdDraw.BLACK, StdDraw.BLUE, StdDraw.BLACK, "J2 commence");
					if (DetectPos(-150, 150, 45, 115) && StdDraw.mousePressed()){
						JeuCarre.joueur=2;
					}
					if (JeuCarre.joueur==1){
						TextEncadre(StdDraw.BLACK, -350, 75, "J1 commence", font9, BleuClair, -350, 80, 150, 35, StdDraw.BLACK, 0.004);
					}
					if (JeuCarre.joueur==2){
						TextEncadre(StdDraw.BLACK, 0, 75, "J2 commence", font9, BleuClair, 0, 80, 150, 35, StdDraw.BLACK, 0.004);
					}
				}
				if (JeuCarre.ordi==3){
					StdDraw.setFont(font9);
					Bouton(0.004, StdDraw.BLACK, -350, 80, 150, 35, StdDraw.GREEN, StdDraw.BLACK, StdDraw.BLUE, StdDraw.BLACK, "Ordi1 commence");
					if (DetectPos(-500, -200, 45, 115) && StdDraw.mousePressed()){
						JeuCarre.joueur=1;
					}
					Bouton(0.004, StdDraw.BLACK, 0, 80, 150, 35, StdDraw.GREEN, StdDraw.BLACK, StdDraw.BLUE, StdDraw.BLACK, "Ordi2 commence");
					if (DetectPos(-150, 150, 45, 115) && StdDraw.mousePressed()){
						JeuCarre.joueur=2;
					}
					if (JeuCarre.joueur==1){
						TextEncadre(StdDraw.BLACK, -350, 75, "Ordi1 commence", font9, BleuClair, -350, 80, 150, 35, StdDraw.BLACK, 0.004);
					}
					if (JeuCarre.joueur==2){
						TextEncadre(StdDraw.BLACK, 0, 75, "Ordi2 commence", font9, BleuClair, 0, 80, 150, 35, StdDraw.BLACK, 0.004);
					}
				}
				StdDraw.setFont(font5);
				Bouton(0.004, StdDraw.BLACK, 340, 80, 150, 35, StdDraw.ORANGE, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, "Modifier");
				if (DetectPos(190, 490, 45, 115) && StdDraw.mousePressed()){
					JeuCarre.DemandeGraphique();
				}
			}
			
			if (JeuCarre.ordi == 1) {
				// JeuCarre.difficulté
				StdDraw.setFont(font5);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(-500, 110, "• Difficulté : ");
				TextEncadre(StdDraw.RED, 340, 110, Integer.toString(JeuCarre.difficulté), font6, VertPale, 340, 115, 30, 30, StdDraw.BLACK, 0.002);


				// Encadré joueurs.
				Font font7 = new Font("Helvetica", Font.PLAIN, 30);
				Font font8 = new Font("Helvetica", Font.PLAIN, 28);

				TextEncadre(StdDraw.BLACK, -15, -100, "Rentrez les noms des joueurs : ", font7, GrisClair, -30, -220, 480, 180, StdDraw.BLACK, 0.003);

				
				// PARTIE ENTRE JOUEUR ET ORDI.
				StdDraw.setFont(font8);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(-400, -200, "Joueur 1 : ");
				
				if (click1 == 0) {
					BoutonEntreText(0.004, StdDraw.BLACK, 150, -200, 200, 37, BleuClair, StdDraw.BLACK, BleuPale, StdDraw.BLACK, 50, -205, JeuCarre.nomJ1);
				}

				if (StdDraw.mousePressed() && DetectPos(-50, 350, -237, -163)) {// Si le joueur clique sur lacase à editer.
					click1 = 1;
					edit1 = true;
				}

				if (click1 == 1) {
					TextEncadre(StdDraw.BLACK , 150, -205, JeuCarre.nomJ1, font8, BleuPale, 150, -200, 200, 37, StdDraw.BLACK, 0.005);
				}

				if (edit1 == true && click1 == 1) {
					if (StdDraw.hasNextKeyTyped()) {
						if (isKeyReleased(KeyEvent.VK_BACK_SPACE)) { //j'invente des new fonctions parce que stddraw cest nul :(
							if (JeuCarre.nomJ1.length() > 0) {
								JeuCarre.nomJ1 = JeuCarre.nomJ1.substring(0, JeuCarre.nomJ1.length() - 1);
							}
						}else{
							char c=StdDraw.nextKeyTyped();
							if (JeuCarre.nomJ1.length() <= 10){
								if (c!='\b'){
									JeuCarre.nomJ1+=c;
								}
							}			
						}
					}
				}

				StdDraw.setFont(font1);
				Bouton(0.005, StdDraw.BLACK, 0, -465, 250, 50, StdDraw.RED, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, "Valider");

				if (DetectPos(-250, 250, -515, -415) && StdDraw.mousePressed()) {
					if (!JeuCarre.nomJ1.equals("")) {
						break;
					} else {
						if (JeuCarre.nomJ1.equals("")) {
							nop = 1; 
						}
					}
				}

				if (nop == 1) { //Si probleme de validation de la longueur du nom.
					if (JeuCarre.nomJ1.equals("")) {
						StdDraw.setFont(font1);
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.textLeft(-135, -200, "->");
						StdDraw.setPenColor(206, 255, 255);
						StdDraw.filledRectangle(150, -200, 200, 37);
						if (DetectPos(-50, 350, -237, -163)){
							StdDraw.setPenRadius(0.005);
							StdDraw.setPenColor(StdDraw.BLACK);
							StdDraw.rectangle(150, -200, 200, 37);
							StdDraw.setPenColor(150, 255, 255);
							StdDraw.filledRectangle(150, -200, 200, 37);
						}
					}
				}
				JeuCarre.nomJ2="l'ordi";
			}

			// PARTIE ENTRE 2 JOUEURS

			if (JeuCarre.ordi == 2) {

				Font font7 = new Font("Helvetica", Font.PLAIN, 30);
				Font font8 = new Font("Times", Font.PLAIN, 28);

				TextEncadre(StdDraw.BLACK, -15, -100, "Rentrez les noms des joueurs : ", font7, GrisClair, -30, -220, 480, 180, StdDraw.BLACK, 0.005);

				StdDraw.setFont(font8);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(-400, -200, "Joueur 1 : ");

				// click1=0 par défaut au début.

				if (click1 == 0) {
					BoutonEntreText(0.004, StdDraw.BLACK, 150, -200, 200, 37, BleuClair, StdDraw.BLACK, BleuPale, StdDraw.BLACK, 0, -205, JeuCarre.nomJ1);
				}

				if (StdDraw.mousePressed() && DetectPos(-50, 350, -237, -163)) {// Si le joueur clique sur  la case à editer.
					StdDraw.setFont(font8);
					click1 = 1;
					edit1 = true;
					edit2 = false;
				}

				if (click1 == 1) {
					TextEncadre(StdDraw.BLACK , 100, -205, JeuCarre.nomJ1, font8, BleuPale, 150, -200, 200, 37, StdDraw.BLACK, 0.005);

				}

				if (edit1 == true && click1 == 1) {
					if (StdDraw.hasNextKeyTyped()) {
						if (isKeyReleased(KeyEvent.VK_BACK_SPACE)) { //j'invente des new fonctions parce que stddraw cest nul :(
							if (JeuCarre.nomJ1.length() > 0) {
								JeuCarre.nomJ1 = JeuCarre.nomJ1.substring(0, JeuCarre.nomJ1.length() - 1);
							}
						}else{
							char c=StdDraw.nextKeyTyped();
							if (JeuCarre.nomJ1.length() <= 10){
								if (c!='\b'){
									JeuCarre.nomJ1+=c;
								}
							}			
						}
					}
					click2 = 0;
				}

				StdDraw.setFont(font8);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.textLeft(-400, -300, "Joueur 2 : ");

				if (click2 == 0) { // Si le joueur clique sur la case à éditer.
					BoutonEntreText(0.004, StdDraw.BLACK, 150, -300, 200, 37, BleuClair, StdDraw.BLACK, BleuPale, StdDraw.BLACK, 0, -305, JeuCarre.nomJ2);
				}

				if (StdDraw.mousePressed() && DetectPos(-50, 350, -337, -263)) {
					StdDraw.setFont(font8);
					click2 = 1;
					edit1 = false;
					edit2 = true;
				}

				if (click2 == 1) {
					TextEncadre(StdDraw.BLACK , 100, -305, JeuCarre.nomJ2, font8, BleuPale, 150, -300, 200, 37, StdDraw.BLACK, 0.005);
				}

				if (edit2 == true && click2 == 1) {
					if (StdDraw.hasNextKeyTyped()) {
						if (isKeyReleased(KeyEvent.VK_BACK_SPACE)) { //j'invente des new fonctions parce que stddraw cest nul :(
							if (JeuCarre.nomJ2.length() > 0) {
								JeuCarre.nomJ2 = JeuCarre.nomJ2.substring(0, JeuCarre.nomJ2.length() - 1);
							}
						}else{
							char c=StdDraw.nextKeyTyped();
							if (JeuCarre.nomJ2.length() <= 10){
								if (c!='\b'){
									JeuCarre.nomJ2+=c;
								}
							}			
						}
					}
					click1 = 0;
				}
				
				StdDraw.setFont(font1);
				Bouton(0.005, StdDraw.BLACK, 0, -465, 250, 50, StdDraw.RED, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, "Valider");

				if (DetectPos(-250, 250, -515, -415) && StdDraw.mousePressed()) {
					if (!JeuCarre.nomJ1.equals("") && !JeuCarre.nomJ2.equals("")) {
						break;
					} else {
						if (JeuCarre.nomJ1.equals("")) {
							nop = 1;

						}
						if (JeuCarre.nomJ2.equals("")) {
							nop = 1;
						}
					}
				}

				if (nop == 1) {
					if (JeuCarre.nomJ1.equals("")) {
						StdDraw.setFont(font1);
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.textLeft(-135, -200, "->");
						StdDraw.setPenColor(206, 255, 255);
						StdDraw.filledRectangle(150, -200, 200, 37);
						if (DetectPos(-50, 350, -237, -163)){
							StdDraw.setPenColor(150, 255, 255);
							StdDraw.filledRectangle(150, -200, 200, 37);
						}
					}
					if (JeuCarre.nomJ2.equals("")) {
						StdDraw.setFont(font1);
						StdDraw.setPenColor(StdDraw.RED);
						StdDraw.textLeft(-135, -300, "->");
						StdDraw.setPenColor(206, 255, 255);
						StdDraw.filledRectangle(150, -300, 200, 37);
						if (DetectPos(-50, 350, -337, -263)){
							StdDraw.setPenColor(150, 255, 255);
							StdDraw.filledRectangle(150, -300, 200, 37);
						}
					}
				}
			}
			
			// IA VS IA :
			if (JeuCarre.ordi == 3) {
				JeuCarre.nomJ1="l'ordi 1";
				JeuCarre.nomJ2="l'ordi 2";
				
				StdDraw.setFont(font1);
				Bouton(0.005, StdDraw.BLACK, 0, -300, 250, 50, StdDraw.RED, StdDraw.BLACK, StdDraw.YELLOW, StdDraw.BLACK, "Valider");
				
				if (DetectPos(-250, 250, -350, -250) && StdDraw.mousePressed()) {
					break;
				}
			}
			StdDraw.show(16);
			StdDraw.clear();
		}
	}
	
	/**
	 * Créer un rectangle qui sera utile pour la hitbox du click souris.
	 * @param x0 Représente la position en haut à gauche du rectangle de détection de la souris.
	 * @param x1 Représente la position en haut à droite du rectangle de détection de la souris.
	 * @param y0 Représente la position en bas à gauche du rectangle de détection de la souris.
	 * @param y1 Représente la position en haut à droite du rectangle de détection de la souris.
	 * @return true ou false selon que la souris se trouve dans le rectangle dont la position est définie par les 4 entrées.
	 */
	public static boolean DetectPos(double x0, double x1, double y0, double y1){ //pour les clics.
		double x=StdDraw.mouseX();
		double y=StdDraw.mouseY();
		if (x0<x && x<x1 && y0<y && y<y1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Permet de créer un bouton d'une taille précise, avec un texte précis à l'intérieur centré, et qui change de couleur quand on passe la souris dessus.
	 * @param PenRad Taille des bords du rectangle.
	 * @param colorBorder Couleur des bords du rectangle
	 * @param xRec Position du centre du rectangle en X
	 * @param yRec Position du centre du rectangle en Y
	 * @param LargRec Largeur du rectangle
	 * @param HautRec Hauteur du rectangle
	 * @param colorFilled Couleur de remplissage du rectangle
	 * @param colorText Couleur du texte à l'intérieur du rectangle
	 * @param newcolorFilled Couleur du highlight du rectangle quand la souris passe sur le bouton
	 * @param newcolorText Nouvelle couleur du texte à l'intérieur du rectangle quand la souris passe sur le bouton
	 * @param text Le string du texte à l'intérieur du bouton.
	 */
	public static void Bouton(double PenRad, Color colorBorder, double xRec, double yRec, double LargRec, double HautRec, Color colorFilled, Color colorText, Color newcolorFilled, Color newcolorText, String text){
		StdDraw.setPenRadius(PenRad);
		StdDraw.setPenColor(colorBorder);
		StdDraw.rectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setPenColor(colorFilled);
		StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setPenColor(colorText);
		StdDraw.text(xRec, yRec-5, text);
		if(DetectPos(xRec-LargRec, xRec+LargRec, yRec-HautRec, yRec+HautRec)){
			Cover(newcolorFilled, xRec,yRec,LargRec, HautRec, newcolorText, text);
		}
	}
	
	/**
	 * Même fonction que précédement, mais permet de positionner le texte à l'intérieur du rectangle comme on le souhaite.
	 * @param PenRad Taille des bords du rectangle.
	 * @param colorBorder Couleur des bords du rectangle
	 * @param xRec Position du centre du rectangle en X
	 * @param yRec Position du centre du rectangle en Y
	 * @param LargRec Largeur du rectangle
	 * @param HautRec Hauteur du rectangle
	 * @param colorFilled Couleur de remplissage du rectangle
	 * @param colorText Couleur du texte à l'intérieur du rectangle
	 * @param newcolorFilled Couleur du highlight du rectangle quand la souris passe sur le bouton
	 * @param newcolorText Nouvelle couleur du texte à l'intérieur du rectangle quand la souris passe sur le bouton
	 * @param Xtext Position X du texte.
	 * @param Ytext Position Y du texte.
	 * @param text Le string du texte à l'intérieur du bouton.
	 */
	public static void BoutonEntreText(double PenRad, Color colorBorder, double xRec, double yRec, double LargRec, double HautRec, Color colorFilled, Color colorText, Color newcolorFilled, Color newcolorText, double Xtext, double Ytext, String text){ // Presque Pareil que Bouton(..), sauf que l'on peut choisir la position du texte.
		StdDraw.setPenRadius(PenRad);
		StdDraw.setPenColor(colorBorder);
		StdDraw.rectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setPenColor(colorFilled);
		StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setPenColor(colorText);
		StdDraw.textLeft(Xtext, Ytext, text);
		if(DetectPos(xRec-LargRec, xRec+LargRec, yRec-HautRec, yRec+HautRec)){
			CoverText(newcolorFilled, xRec,yRec,LargRec, HautRec, newcolorText, Xtext, Ytext,  text);
		}
	}
	
	/**
	 * Permet de faire des effets de changement de couleur au passage de la souris dans la hitbox du bouton.
	 * @param newcolorFilled Couleur du highlight du rectangle quand la souris passe sur le bouton
	 * @param xRec Position du centre du rectangle en X
	 * @param yRec Position du centre du rectangle en Y
	 * @param LargRec Largeur du rectangle
	 * @param HautRec Hauteur du rectangle
	 * @param newcolorText Nouvelle couleur du texte à l'intérieur du rectangle quand la souris passe sur le bouton
	 * @param text Le string du texte à l'intérieur du bouton.
	 */
	public static void Cover (Color newcolorFilled, double xRec, double yRec, double LargRec, double HautRec, Color newcolorText, String text){ //effet de changement de couleur si souris passe dessus.
		StdDraw.setPenColor(newcolorFilled);
		StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setPenColor(newcolorText);
		StdDraw.text(xRec, yRec-5,text);
	}
	
	/**
	 * Même fonction que précédement, mais permet de positionner le texte à l'intérieur du rectangle comme on le souhaite.
	 * @param newcolorFilled Couleur du highlight du rectangle quand la souris passe sur le bouton
	 * @param xRec Position du centre du rectangle en X
	 * @param yRec Position du centre du rectangle en Y
	 * @param LargRec Largeur du rectangle
	 * @param HautRec Hauteur du rectangle
	 * @param newcolorText Nouvelle couleur du texte à l'intérieur du rectangle quand la souris passe sur le bouton
	 * @param Xtext Position X du texte dans le rectangle
	 * @param Ytext Position Y du texte dans le rectangle
	 * @param text Le string du texte à l'intérieur du bouton.
	 */
	public static void CoverText (Color newcolorFilled, double xRec, double yRec, double LargRec, double HautRec, Color newcolorText, double Xtext, double Ytext, String text){ //effet de changement de couleur si souris passe dessus.
		StdDraw.setPenColor(newcolorFilled);
		StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setPenColor(newcolorText);
		StdDraw.textLeft(Xtext, Ytext,text);
	}
	
	/**
	 * Permet de faire un bouton sans surligner quand la souris passe dessus.
	 * @param colorText Couleur du texte
	 * @param xText Position X du texte
	 * @param yText Position Y du texte
	 * @param text String du texte
	 * @param font Police du texte
	 * @param colorFilledRec Couleur de l'intérieur rectangle
	 * @param xRec Position du centre du rectangle en X
	 * @param yRec Position du centre du rectangle en Y
	 * @param LargRec Largeur du rectangle
	 * @param HautRec Hauteur du rectangle
	 * @param colorRec Couleur des bords du rectangle
	 * @param PenRad Taille des bords du rectangle
	 */
	public static void TextEncadre(Color colorText, int xText, int yText, String text, Font font, Color colorFilledRec, int xRec, int yRec, int LargRec, int HautRec, Color colorRec, double PenRad){ // Pas de Hover si on passe la souris dessus.
		StdDraw.setPenColor(colorFilledRec);
		StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setPenColor(colorRec);
		StdDraw.setPenRadius(PenRad);
		StdDraw.rectangle(xRec, yRec, LargRec, HautRec);
		StdDraw.setFont(font);
		StdDraw.setPenColor(colorText);
		StdDraw.text(xText, yText, text);

	}

	/**
	 * Fonction qui permet d'éviter d'effacer tout le nom du joueur si la personne qui rentre les noms laisse appuyer la touche d'effacement.</br>
	 * Bloque la boucle tant que la touche est appuyée.
	 * @param key La touche pressée.
	 * @return True ou False selon que le joueur relache la touche.
	 */
	public static boolean isKeyReleased(int key){
		if (!StdDraw.isKeyPressed(key)){
			return false;
		}else{
			while (StdDraw.isKeyPressed(key)){
			}
			return true;
		}
	}
}

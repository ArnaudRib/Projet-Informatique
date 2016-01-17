
public class CalculsIA {

	//Les 6 fonctions qui suivent permettent de gérer les trois niveaux de IA.
	/**
	 * Niveau 1 de difficulté de l'ordinateur.
	 * @param visuel Le tableau du plateau de jeu.
	 * @return Un tableau contenant la ligne et la colonne où l'ordinateur veut jouer.
	 */
	public static int[] CalculIA1(String[][] visuel){
		int [] tab= new int [2];
		int[][] CasesVides=CreationCasesVides(visuel); //Crée un tableau avec les cases vides du plateau.
		int hauteuraleatoire=(int) (Math.random()*CasesVides.length);
		tab[0] = CasesVides[hauteuraleatoire][0];
		tab[1] = CasesVides[hauteuraleatoire][1];	
		return tab;
	}
	
	/**
	 * Niveau 2 de difficulté de l'ordinateur.
	 * @param visuel Le tableau du plateau de jeu.
	 * @return Un tableau contenant la ligne et la colonne où l'ordinateur veut jouer.
	 */
	public static int[] CalculIA2(String[][] visuel){
		int [] tab= new int [2]; //tableau où se trouvera ligne et colonne.
		if (JeuCarre.taille<5){
			int[][] CasesVides=CreationCasesVides(visuel); //Crée un tableau avec les cases vides du plateau.
			for (int i=0;i<visuel.length;i++){
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){
						if (visuel[i][j]=="  "){
							if (visuel[i-1][j]=="---" && visuel[i][j-1]=="| " && visuel[i+1][j]=="---" && visuel[i][j+1]=="  "){
								tab[0]=i;
								tab[1]=j+1;
								return tab;
							}
							if (visuel[i-1][j]=="---" && visuel[i][j+1]=="| " && visuel[i+1][j]=="---" && visuel[i][j-1]=="  "){
								tab[0]=i;
								tab[1]=j-1;
								return tab;
							}
							if (visuel[i][j-1]=="| " && visuel[i][j+1]=="| " && visuel[i+1][j]=="---" && visuel[i-1][j]=="   "){
								tab[0]=i-1;
								tab[1]=j;
								return tab;
							}
							if (visuel[i][j-1]=="| " && visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i+1][j]=="   "){
								tab[0]=i+1;
								tab[1]=j;
								return tab;
							}
							else{
								int hauteuraleatoire=(int) (Math.random()*CasesVides.length);
								tab[0] = CasesVides[hauteuraleatoire][0];
								tab[1] = CasesVides[hauteuraleatoire][1];
							}
						}
					}
				}
			}
		}
		
		if (JeuCarre.taille>=5){
			int[][] CasesVides=CreationCasesVides(visuel); //Crée un tableau avec les cases vides du plateau.
			for (int i=0;i<visuel.length;i++){
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){
						if (visuel[i][j]=="   "){
							if (visuel[i-1][j]=="-----" && visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----" && visuel[i][j+1]=="   "){
								tab[0]=i;
								tab[1]=j+1;
								return tab;
							}
							if (visuel[i-1][j]=="-----" && visuel[i][j+1]=="|  " && visuel[i+1][j]=="-----" && visuel[i][j-1]=="   "){
								tab[0]=i;
								tab[1]=j-1;
								return tab;
							}
							if (visuel[i][j-1]=="|  " && visuel[i][j+1]=="|  " && visuel[i+1][j]=="-----" && visuel[i-1][j]=="     "){
								tab[0]=i-1;
								tab[1]=j;
								return tab;
							}
							if (visuel[i][j-1]=="|  " && visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i+1][j]=="     "){
								tab[0]=i+1;
								tab[1]=j;
								return tab;
							}
							else{
								int hauteuraleatoire=(int) (Math.random()*CasesVides.length);
								tab[0] = CasesVides[hauteuraleatoire][0];
								tab[1] = CasesVides[hauteuraleatoire][1];
							}
						}
					}
				}
			}
		}
		return tab;
	}
	
	/**
	 * Niveau 3 de difficulté de l'ordinateur.
	 * @param visuel Le tableau du plateau de jeu.
	 * @return Un tableau contenant la ligne et la colonne où l'ordinateur veut jouer.
	 */
	public static int[] CalculIA3(String[][] visuel){
		int [] tab= new int [2]; //tableau où se trouvera ligne et colonne.
		int y =0;
		
		if (JeuCarre.taille<5){
			int[][] CasesVides=CreationCasesVides(visuel); //Crée un tableau avec les cases vides du plateau.

			for (int i=0;i<visuel.length;i++){ // Calcule la hauteur de la liste de coups interdits.
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){ //limite la recherche au milieu des carrés
						if (visuel[i-1][j]=="---" && visuel[i+1][j]=="---" && visuel[i][j-1]=="  " && visuel[i][j+1]=="  "){
							y++;
						}
						if  (visuel[i][j-1]=="| " && visuel[i][j+1]=="| " && visuel[i-1][j]=="   " && visuel[i+1][j]=="   ") {
							y++;
						}
						if (visuel[i][j-1]=="| " &&  visuel[i-1][j]=="---" && visuel[i+1][j]=="   " && visuel[i][j+1]=="  "){
							y++;
						}
						if (visuel[i][j-1]=="| " && visuel[i+1][j]=="---" && visuel[i][j+1]=="  " && visuel[i-1][j]=="   ") {
							y++;
						}
						if (visuel[i][j+1]=="| " && visuel[i+1][j]=="---"  && visuel[i-1][j]=="   " && visuel[i][j-1]=="  "){
							y++;
						}
						if (visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i][j-1]=="  " && visuel[i+1][j]=="   "){
							y++;
						}
						
						//partie pointillés
					    if (visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i][j-1]==". " && visuel[i+1][j]=="---"){
							y++;
						}
						if (visuel[i][j+1]==". " && visuel[i-1][j]=="---" && visuel[i][j-1]=="| " && visuel[i+1][j]=="---"){
							y++;
						}
						if (visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i][j-1]=="| " && visuel[i+1][j]==" . "){
							y++;
						}
						if (visuel[i][j+1]=="| " && visuel[i-1][j]==" . " && visuel[i][j-1]=="| " && visuel[i+1][j]=="---"){
							y++;
						}
					}
				}
			}
			
			int [][] CoupsInterdits = new  int [y][4];//créé une liste de JeuCarre.taille dynamique. y est la hauteur, 4 représente les deux lignes et les deux hauteurs interdites si deux cases sont remplies.
			//Remplissage de la liste des coups interdits
			int h=0;
			for (int i=0;i<visuel.length;i++){ // Calcul la hauteur de la liste de coups interdits.
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){ //limite la recherche au milieu des carrés
						if (visuel[i-1][j]=="---" && visuel[i+1][j]=="---" && visuel[i][j-1]=="  " && visuel[i][j+1]=="  "){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j-1; // les coordonnées du premier coup interdit (i en ligne et j-1 en colonne)
							CoupsInterdits[h][2]=i;
							CoupsInterdits[h][3]=j+1;// les coordonnées du deuxième coup interdit
							h++; //permet de changer de niveau
						}
						if  (visuel[i][j-1]=="| " && visuel[i][j+1]=="| " && visuel[i-1][j]=="   " && visuel[i+1][j]=="   ") {
							CoupsInterdits[h][0]=i-1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=i+1;
							CoupsInterdits[h][3]=j;
							h++; 
						}
						if (visuel[i][j-1]=="| " && visuel[i+1][j]=="---" && visuel[i][j+1]=="  " && visuel[i-1][j]=="   ") {
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j+1; 
							CoupsInterdits[h][2]=i-1;
							CoupsInterdits[h][3]=j;
							h++;
						}
						if (visuel[i][j-1]=="| " &&  visuel[i-1][j]=="---" && visuel[i+1][j]=="   " && visuel[i][j+1]=="  "){
							CoupsInterdits[h][0]=i+1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=i;
							CoupsInterdits[h][3]=j+1;
							h++;
						}
						if (visuel[i][j+1]=="| " && visuel[i+1][j]=="---"  && visuel[i-1][j]=="   " && visuel[i][j-1]=="  "){
							CoupsInterdits[h][0]=i-1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=i;
							CoupsInterdits[h][3]=j-1;
							h++;
						}
						if (visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i][j-1]=="  " && visuel[i+1][j]=="   "){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j-1; 
							CoupsInterdits[h][2]=i+1;
							CoupsInterdits[h][3]=j;
							h++;
						}
						
						//partie pointillés
						if (visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i][j-1]==". " && visuel[i+1][j]=="---"){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j-1; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
						if (visuel[i][j+1]==". " && visuel[i-1][j]=="---" && visuel[i][j-1]=="| " && visuel[i+1][j]=="---"){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j+1; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
						if (visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i][j-1]=="| " && visuel[i+1][j]==" . "){
							CoupsInterdits[h][0]=i+1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
						if (visuel[i][j+1]=="| " && visuel[i-1][j]==" . " && visuel[i][j-1]=="| " && visuel[i+1][j]=="---"){
							CoupsInterdits[h][0]=i-1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
					}
				}
			}
			
			CoupsInterdits=ClearTableau(CoupsInterdits); //Clear le tableau de facon voulue.
			int[][] CasesVidesCleared=ClearSimilarites(CasesVides,CoupsInterdits); //Créé un tableau des cases vides et jouables par l'IA.
			
			CasesVides=CreationCasesVides(visuel);
						
			for (int i=0;i<visuel.length;i++){
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){
						if (visuel[i][j]=="  "){
							if (visuel[i-1][j]=="---" && visuel[i][j-1]=="| " && visuel[i+1][j]=="---" && visuel[i][j+1]=="  "){
								tab[0]=i;
								tab[1]=j+1;
								return tab;
							}
							if (visuel[i-1][j]=="---" && visuel[i][j+1]=="| " && visuel[i+1][j]=="---" && visuel[i][j-1]=="  "){
								tab[0]=i;
								tab[1]=j-1;
								return tab;
							}
							if (visuel[i][j-1]=="| " && visuel[i][j+1]=="| " && visuel[i+1][j]=="---" && visuel[i-1][j]=="   "){
								tab[0]=i-1;
								tab[1]=j;
								return tab;
							}
							if (visuel[i][j-1]=="| " && visuel[i][j+1]=="| " && visuel[i-1][j]=="---" && visuel[i+1][j]=="   "){
								tab[0]=i+1;
								tab[1]=j;
								return tab;
							}
							else{
								if (CasesVidesCleared.length!=0){
									int hauteuraleatoirecleared=(int) (Math.random()*CasesVidesCleared.length);
									tab[0] = CasesVidesCleared[hauteuraleatoirecleared][0];
									tab[1] = CasesVidesCleared[hauteuraleatoirecleared][1];
								}else{ //S'il n'y a plus de cases vides et disponibles, joue au pif dans les cases vides
									int hauteuraleatoire=(int) (Math.random()*CasesVides.length);
									tab[0] = CasesVides[hauteuraleatoire][0];
									tab[1] = CasesVides[hauteuraleatoire][1];
								}
							}
						}
					}
				}
			}
		}
		



		if (JeuCarre.taille>=5){		
			int[][] CasesVides=CreationCasesVides(visuel);		
			
			for (int i=0;i<visuel.length;i++){ // Calcul la hauteur de la liste de coups interdits.
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){ //limite la recherche au milieu des carrés
						if (visuel[i-1][j]=="-----" && visuel[i+1][j]=="-----" && visuel[i][j-1]=="   " && visuel[i][j+1]=="   "){
							y++;
						}
						if  (visuel[i][j-1]=="|  " && visuel[i][j+1]=="|  " && visuel[i-1][j]=="     " && visuel[i+1][j]=="     ") {
							y++;
						}
						if (visuel[i][j-1]=="|  " &&  visuel[i-1][j]=="-----" && visuel[i+1][j]=="     " && visuel[i][j+1]=="   "){
							y++;
						}
						if (visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----" && visuel[i][j+1]=="   " && visuel[i-1][j]=="     ") {
							y++;
						}
						if ( visuel[i][j+1]=="|  " && visuel[i+1][j]=="-----"  && visuel[i-1][j]=="     " && visuel[i][j-1]=="   "){
							y++;
						}
						if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i][j-1]=="   " && visuel[i+1][j]=="     "){
							y++;
						}		
						//partie pointillés
					    if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i][j-1]==".  " && visuel[i+1][j]=="-----"){
							y++;
						}
						if (visuel[i][j+1]==".  " && visuel[i-1][j]=="-----" && visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----"){
							y++;
						}
						if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i][j-1]=="|  " && visuel[i+1][j]=="  .  "){
							y++;
						}
						if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="  .  " && visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----"){
							y++;
						}
					}
				}
			}
	
			int [][] CoupsInterdits = new  int [y][4];//créé une liste de JeuCarre.taille dynamique. y est la hauteur, 4 représente les deux lignes et les deux hauteurs interdites si deux cases sont remplies.
			int h=0;
			for (int i=0;i<visuel.length;i++){ // Calcul la hauteur de la liste de coups interdits.
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){ //limite la recherche au milieu des carrés
						if (visuel[i-1][j]=="-----" && visuel[i+1][j]=="-----" && visuel[i][j-1]=="   " && visuel[i][j+1]=="   "){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j-1; // les coordonnées du premier coup interdit (i en ligne et j-1 en colonne)
							CoupsInterdits[h][2]=i;
							CoupsInterdits[h][3]=j+1;// les coordonnées du deuxième coup interdit
							h++; //permet de changer de niveau
						}
						if  (visuel[i][j-1]=="|  " && visuel[i][j+1]=="|  " && visuel[i-1][j]=="     " && visuel[i+1][j]=="     ") {
							CoupsInterdits[h][0]=i-1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=i+1;
							CoupsInterdits[h][3]=j;
							h++; 
						}
						if (visuel[i][j-1]=="|  " &&  visuel[i-1][j]=="-----" && visuel[i+1][j]=="     " && visuel[i][j+1]=="   "){
							CoupsInterdits[h][0]=i+1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=i;
							CoupsInterdits[h][3]=j+1;
							h++;
						}
						if (visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----" && visuel[i][j+1]=="   " && visuel[i-1][j]=="     ") {
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j+1; 
							CoupsInterdits[h][2]=i-1;
							CoupsInterdits[h][3]=j;
							h++;
						}
						if ( visuel[i][j+1]=="|  " && visuel[i+1][j]=="-----"  && visuel[i-1][j]=="     " && visuel[i][j-1]=="   "){
							CoupsInterdits[h][0]=i-1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=i;
							CoupsInterdits[h][3]=j-1;
							h++;
						}
						if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i][j-1]=="   " && visuel[i+1][j]=="     "){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j-1; 
							CoupsInterdits[h][2]=i+1;
							CoupsInterdits[h][3]=j;
							h++;
						}
						//partie pointillés
						if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i][j-1]==".  " && visuel[i+1][j]=="-----"){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j-1; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
						if (visuel[i][j+1]==".  " && visuel[i-1][j]=="-----" && visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----"){
							CoupsInterdits[h][0]=i; 
							CoupsInterdits[h][1]=j+1; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
						if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i][j-1]=="|  " && visuel[i+1][j]=="  .  "){
							CoupsInterdits[h][0]=i+1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
						if (visuel[i][j+1]=="|  " && visuel[i-1][j]=="  .  " && visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----"){
							CoupsInterdits[h][0]=i-1; 
							CoupsInterdits[h][1]=j; 
							CoupsInterdits[h][2]=0;
							CoupsInterdits[h][3]=0;
							h++;
						}
					}
				}
			}
			
			CoupsInterdits=ClearTableau(CoupsInterdits); //Clear le tableau de facon voulue.
			int[][] CasesVidesCleared=ClearSimilarites(CasesVides,CoupsInterdits); //Créé un tableau des cases vides et jouables par l'IA.
			
			CasesVides=CreationCasesVides(visuel);

			for (int i=0;i<visuel.length;i++){
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2!=0){
						if (visuel[i][j]=="   "){
							if (visuel[i-1][j]=="-----" && visuel[i][j-1]=="|  " && visuel[i+1][j]=="-----" && visuel[i][j+1]=="   "){
								tab[0]=i;
								tab[1]=j+1;
								return tab;
							}
							if (visuel[i-1][j]=="-----" && visuel[i][j+1]=="|  " && visuel[i+1][j]=="-----" && visuel[i][j-1]=="   "){
								tab[0]=i;
								tab[1]=j-1;
								return tab;
							}
							if (visuel[i][j-1]=="|  " && visuel[i][j+1]=="|  " && visuel[i+1][j]=="-----" && visuel[i-1][j]=="     "){
								tab[0]=i-1;
								tab[1]=j;
								return tab;
							}
							if (visuel[i][j-1]=="|  " && visuel[i][j+1]=="|  " && visuel[i-1][j]=="-----" && visuel[i+1][j]=="     "){
								tab[0]=i+1;
								tab[1]=j;
								return tab;
							}
							else{
								if (CasesVidesCleared.length!=0){
									int hauteuraleatoirecleared=(int) (Math.random()*CasesVidesCleared.length);
									tab[0] = CasesVidesCleared[hauteuraleatoirecleared][0];
									tab[1] = CasesVidesCleared[hauteuraleatoirecleared][1];
								}else{ //S'il n'y a plus de cases vides et disponibles, joue au pif dans les cases vides
									int hauteuraleatoire=(int) (Math.random()*CasesVides.length);
									tab[0] = CasesVides[hauteuraleatoire][0];
									tab[1] = CasesVides[hauteuraleatoire][1];
								}
							}
						}
					}
				}
			}
		}

		return tab;
	}

	/**
	 * Permet de générer un tableau contenant tous les coups encore jouables par le joueur & ordinateur.
	 * @param visuel Le tableau contenant tout le plateau.
	 * @return Le tableau des coups disponibles.
	 */
	public static int[][] CreationCasesVides(String[][] visuel) {
		int[][] CasesVides= new int [0][0];
		if (JeuCarre.taille<5){
			int nblignevide=0;
			for (int i=0;i<visuel.length;i++){ //Calcule le nombre de cases libres autour des carrés non complétés.
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]=="  "){
							nblignevide++;
						}
					}
					if (visuel[i][j]=="   "){
						nblignevide++;
					}
					// partie pointillés
					if (i%2==0 && j%2!=0){
						if (visuel[i][j]==" . ") 
							nblignevide++;
					}
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]==". ") 
							nblignevide++;
					}
				}
			}
			
			CasesVides = new int [nblignevide][2];
			int h1=0;
			//Remplissage de la liste des cases vides. Permet d'optimiser la vitesse de calcul lors des choix random de l'ordinateur.
			for (int i=0;i<visuel.length;i++){ //Calcule le nombre de cases libres autour des carrés non complétés.
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]=="  "){
							CasesVides[h1][0]=i;
							CasesVides[h1][1]=j;
							h1++;
						}
					}
					if (visuel[i][j]=="   "){
						CasesVides[h1][0]=i;
						CasesVides[h1][1]=j;
						h1++;					
					}
					// partie pointillés
					if (i%2==0 && j%2!=0){
						if (visuel[i][j]==" . ") {
							CasesVides[h1][0]=i;
							CasesVides[h1][1]=j;
							h1++;						
						}
					}
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]==". ") {
							CasesVides[h1][0]=i;
							CasesVides[h1][1]=j;
							h1++;						
						}
					}
				}
			}
		}
		
		if (JeuCarre.taille>=5){
			int nblignevide=0; 
			for (int i=0;i<visuel.length;i++){
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]=="   "){
							nblignevide++;
						}
					}
					if (visuel[i][j]=="     "){
						nblignevide++;
					}
					// partie pointillés
					if (i%2==0 && j%2!=0){
						if (visuel[i][j]=="  .  ") 
							nblignevide++;
					}
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]==".  ") 
							nblignevide++;
					}
				}
			}
			
			CasesVides = new int [nblignevide][2];
			int h1=0;
			//Remplissage de la liste des cases vides. Permet d'optimiser la vitesse de calcul lors des choix random de l'ordinateur.
			for (int i=0;i<visuel.length;i++){ //Calcule le nombre de cases libres autour des carrés non complétés.
				for (int j=0;j<visuel.length;j++){
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]=="   "){
							CasesVides[h1][0]=i;
							CasesVides[h1][1]=j;
							h1++;
						}
					}
					if (visuel[i][j]=="     "){
						CasesVides[h1][0]=i;
						CasesVides[h1][1]=j;
						h1++;					
					}
					if (i%2==0 && j%2!=0){
						if (visuel[i][j]=="  .  ") {
							CasesVides[h1][0]=i;
							CasesVides[h1][1]=j;
							h1++;						
						}
					}
					if (i%2!=0 && j%2==0){
						if (visuel[i][j]==".  ") {
							CasesVides[h1][0]=i;
							CasesVides[h1][1]=j;
							h1++;						
						}
					}
				}
				
			}
		}
		return CasesVides;
	}

	/**
	 * Fonction utilisée pour IA3, qui compare les deux tableaux en entrée, et renvoie un tableau (CasesVides - CoupsInterdits).
	 * @param CasesVides Tableau dont on veut supprimer les valeurs communes avec CoupsInterdits
	 * @param CoupsInterdits Tableau comparatif
	 * @return tabcleared : (CasesVides - CoupsInterdits).
	 */
	public static int[][] ClearSimilarites(int[][] CasesVides, int[][] CoupsInterdits) {
		for (int k1=0;k1<CasesVides.length;k1++){
			for (int k2=0;k2<CoupsInterdits.length;k2++){
				if (CasesVides[k1][0]==CoupsInterdits[k2][0] && CasesVides[k1][1]==CoupsInterdits[k2][1]){
					CasesVides[k1][0]=0;
					CasesVides[k1][1]=0;
				}
			}
		}
		
		int taillecleared=0;
		for (int i=0; i<CasesVides.length; i++) { // si [0][0] rajouter +1 à la JeuCarre.taille du futur tableau cleared qui contiendra les coups interdits.
			for (int j=0;j<2;j++){
				if (j%2==0){
					if (CasesVides[i][j]!=0 || CasesVides[i][j+1]!=0){
						taillecleared++;
					}
				}
			}
		}
		int [][]tabcleared = new int[taillecleared][2];
		int i1=0;
		for (int i=0; i<CasesVides.length; i++) { 
			//les deux if qui suivent visent à effacer les [0][0], et à insérer ce qu'il reste dans le tableau cleared.
			if (CasesVides[i][0]!=0 || CasesVides[i][1]!=0 && ((CasesVides[i][0]==0 && CasesVides[i][1]==0)==false)){ //les deux sont différents de 0 en même temps mais peuvent =0 séparement
				int x1=CasesVides[i][0];
				int y1=CasesVides[i][1];
				tabcleared[i1][0]=x1;
				tabcleared[i1][1]=y1;
				i1++;
			}
		}
		return tabcleared;
	}


	/**
	 * Cette fonction remet en forme le tableau afin qu'il soit exploitable par la suite de CalculIA3 :</br>
	 * 1°) Permet d'effacer les doublons du plateau de coups interdits.</br>
	 * 2°) Fait un tableau 2x(nb de coups interdits)
	 * @param tab
	 * @return Le tableau en bonne forme pour la suite de l'IA3
	 */
	public static int[][] ClearTableau(int [][]tab){ // efface les zéros et remet dans une liste de la bonne forme.
		for (int i=0; i<tab.length; i++) { //mets 0 à la place des doublons.
			for (int j=0;j<4;j++){
				for (int k=0; k<tab.length;k++){
					for (int j2=0; j2 < 4; j2++) {
						if (k!=i && j2!=j){
							if (j%2==0 && j2%2==0){ //efface en croisant les lignes.
								if (tab[i][j]==tab[k][j2] && tab[i][j+1]==tab[k][j2+1]){
									tab[k][j2]=0;
									tab[k][j2+1]=0;
								}
							}
						}	
						if (k!=i && j2!=j){ //efface en lignes.
							if (j%2==0 && j2%2==0){
								if (tab[i][j]==tab[k][j] && tab[i][j+1]==tab[k][j+1]){
									tab[k][j]=0;
									tab[k][j+1]=0;
								}
							}
						}	
					}
				}
			}	
		}
		
		int taillecleared=0;
		for (int i=0; i<tab.length; i++) { // si [0][0] rajouter +1 à la JeuCarre.taille du futur tableau cleared qui contiendra les coups interdits.
			for (int j=0;j<4;j++){
				if (j%2==0){
					if (tab[i][j]!=0 || tab[i][j+1]!=0){
						taillecleared++;
					}
				}
			}
		}

		int [][]tabcleared = new int[taillecleared][2];
		int i1=0;
		for (int i=0; i<tab.length; i++) { 
			//les deux if qui suivent visent à effacer les [0][0], et à insérer ce qu'il reste dans le tableau cleared.
			if (tab[i][0]!=0 || tab[i][1]!=0 && ((tab[i][0]==0 && tab[i][1]==0)==false)){ //les deux sont différents de 0 en même temps mais peuvent =0 séparement
				int x1=tab[i][0];
				int y1=tab[i][1];
				tabcleared[i1][0]=x1;
				tabcleared[i1][1]=y1;
				i1++;
			}
			if (tab[i][2]!=0 || tab[i][3]!=0 && ((tab[i][2]==0 && tab[i][3]==0)==false)){ //les deux sont différents de 0 en même temps mais peuvent =0 séparement
				int x2=tab[i][2];
				int y2=tab[i][3];
				tabcleared[i1][0]=x2;
				tabcleared[i1][1]=y2;
				i1++;
			}
		}
		
		return tabcleared;
	}
	
}

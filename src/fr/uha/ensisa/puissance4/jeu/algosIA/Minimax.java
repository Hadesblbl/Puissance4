package fr.uha.ensisa.puissance4.jeu.algosIA;

import fr.uha.ensisa.puissance4.data.Grille;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.util.Constantes;



public class Minimax extends Algorithm {



	public Minimax(int levelIA, Grille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);

	}

	@Override
	public int choisirCoup() {
		double max=Constantes.SCORE_MAX_NON_DEFINI;
		int coupMax=0;
		double result;
		Grille grillenew;
		for(int colonne=0;colonne<Constantes.NB_COLONNES;colonne++) {
			if (grilleDepart.isCoupPossible(colonne)) {
				grillenew = grilleDepart.clone();
				grillenew.ajouterCoup(colonne,this.symboleMax);
				result = minValue(grillenew,tourDepart);
				if(result>max) {
					coupMax=colonne;
					max=result;
				}
			}
		}
		return coupMax;
	}

	public double maxValue(Grille grilleCourante,int tour) {
		int resultat=grilleCourante.getEtatPartie(symboleMin, tour);
		if (resultat!=Constantes.PARTIE_EN_COURS || (tour-tourDepart>=levelIA)) {
			return grilleCourante.evaluer(symboleMax)-grilleCourante.evaluer(symboleMin);
		}
		Grille grillenew;
		double max=Constantes.SCORE_MAX_NON_DEFINI;
		for(int colonne=0;colonne<Constantes.NB_COLONNES;colonne++) {
			if (grilleCourante.isCoupPossible(colonne)) {
				grillenew=grilleCourante.clone();
				grillenew.ajouterCoup(colonne, symboleMax);
				double newValue= minValue(grillenew,tour+1);
				if (newValue>max) {
					max=newValue;
				}
			}
		}
		return max;
	}

	public double minValue(Grille grilleCourante,int tour) {
		int resultat=grilleCourante.getEtatPartie(symboleMax, tour);
		if (resultat!=Constantes.PARTIE_EN_COURS || (tour-tourDepart>=levelIA)) {
			return grilleCourante.evaluer(symboleMax)-grilleCourante.evaluer(symboleMin);
		}
		Grille grillenew;
		double min=Constantes.SCORE_MIN_NON_DEFINI;
		for(int colonne=0;colonne<Constantes.NB_COLONNES;colonne++) {
			if (grilleCourante.isCoupPossible(colonne)) {
				grillenew=grilleCourante.clone();
				grillenew.ajouterCoup(colonne, symboleMin);
				double newValue= maxValue(grillenew,tour+1);
				if (newValue<min) {
					min=newValue;
				}
			}
		}
		return min;
	}
}

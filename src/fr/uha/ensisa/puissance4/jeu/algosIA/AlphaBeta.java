package fr.uha.ensisa.puissance4.jeu.algosIA;

import fr.uha.ensisa.puissance4.data.Grille;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.util.Constantes;


public class AlphaBeta extends Algorithm {

	public AlphaBeta(int levelIA, Grille grilleDepart, Joueur joueurActuel, int tour) {
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
				grillenew.ajouterCoup(colonne,symboleMax);
				result = minValue(grillenew,tourDepart,Constantes.SCORE_MAX_NON_DEFINI,Constantes.SCORE_MIN_NON_DEFINI);
				if(result>max) {
					coupMax=colonne;
					max=result;
				}
			}
		}
		return coupMax;
	}
	
	public double maxValue(Grille grilleCourante,int tour,double alpha,double beta) {
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
				max=Math.max(max, minValue(grillenew,tour+1,alpha,beta));
				if (max>beta) return beta;
				alpha=Math.max(alpha, max);
			}
		}
		return max;
	}

	public double minValue(Grille grilleCourante,int tour,double alpha,double beta) {
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
				min=Math.min(min, maxValue(grillenew,tour+1,alpha,beta));
				if (min<alpha) return alpha;
				beta=Math.min(beta, min);
			}
		}
		return min;
	}
}

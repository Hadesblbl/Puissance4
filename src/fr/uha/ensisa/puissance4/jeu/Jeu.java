package fr.uha.ensisa.puissance4.jeu;

import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.data.Partie;
import fr.uha.ensisa.puissance4.ui.Console;
import fr.uha.ensisa.puissance4.ui.Interface;
import javafx.scene.layout.Pane;

public class Jeu extends Thread{

	private Partie partie;
	private Console console;
	private Interface inter;

	public Jeu(Joueur joueur1, Joueur joueur2, Console console)
	{
		this.partie=new Partie(joueur1, joueur2);
		this.console=console;
	}	

	public Jeu(Joueur joueur1, Joueur joueur2, Interface inter)
	{
		this.partie=new Partie(joueur1, joueur2);
		this.inter=inter;
	}


	public void run()
	{
		if(console!=null) { //Lancement de jeu par console
			console.lancementPartie(partie.getJoueur1(), partie.getJoueur2());
			while(!partie.isPartieFinie())
			{
				console.lancementTour(partie.getTour(), partie.getJoueurCourant(), partie.getGrille());

				long tempsReflexion=System.currentTimeMillis();
				int coup= partie.getJoueurCourant().joue(partie.getGrille(), console, partie.getTour());
				tempsReflexion=System.currentTimeMillis()-tempsReflexion;
				console.afficherCoup(partie.getJoueurCourant(), coup, tempsReflexion);
				if(!partie.jouerCoup(coup, tempsReflexion))
				{
					System.out.println("COUP INVALIDE : Recommencez !");
				}
			}
			console.closeScanner();
			console.afficherFinPartie(partie);
		} else { //lancement de jeu par interface
			inter.lancementPartie(partie.getJoueur1(), partie.getJoueur2());
			while(!partie.isPartieFinie())
			{
				inter.lancementTour(partie.getTour(), partie.getJoueurCourant(), partie.getGrille());

				long tempsReflexion=System.currentTimeMillis();
				int coup= partie.getJoueurCourant().joue(partie.getGrille(), inter, partie.getTour());
				tempsReflexion=System.currentTimeMillis()-tempsReflexion;
				inter.afficherCoup(partie.getJoueurCourant(), coup, tempsReflexion);
				if(!partie.jouerCoup(coup, tempsReflexion))
				{
					System.out.println("COUP INVALIDE : Recommencez !");
				}
			}
			inter.afficherFinPartie(partie);
		}
	}



}

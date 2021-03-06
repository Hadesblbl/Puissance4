package fr.uha.ensisa.puissance4.data;

import fr.uha.ensisa.puissance4.ui.Console;
import fr.uha.ensisa.puissance4.ui.Interface;
import fr.uha.ensisa.puissance4.util.Constantes;
import fr.uha.ensisa.puissance4.util.Constantes.Case;
import javafx.scene.layout.Pane;

public abstract class Joueur {
	/**
	 * Nom du joueur
	 */
	protected String nom;
	/**
	 * Indique si le joueur est le joueur 1 ou 2
	 */
	protected int order;
	
	protected Joueur(String nom, int order)
	{
		this.nom = nom;
		this.order = order;
	}
	
	/**
	 * Indique si le joueur est une IA ou un humain
	 * @return
	 */
	public abstract int getType();
	
	/**
	 * Renvoie "Humain" ou "IA" à des fins d'affichage
	 * @return
	 */
	public abstract String getTypeNom();
	
	/**
	 * Renvoie si le joueur est le joueur 1 ou 2
	 * @return Constantes.JOUEUR_1 ou Constantes.JOUEUR_2
	 */
	public int getOrder()
	{
		return order;
	}
	
	/**
	 * Renvoie le nom du joueur
	 * @return String nom
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Renvoie le symbole utilisé par le joueur (X ou O)
	 * @return Case symbole
	 */
	public Case getSymbole()
	{
		if(getOrder()==Constantes.JOUEUR_1)
		{
			return Constantes.SYMBOLE_J1;
		}
		else
		{
			return Constantes.SYMBOLE_J2;
		}
	}

	/**
	 * Fais jouer un tour au joueur dans la console
	 * @param grille
	 * @param console
	 * @param tour
	 * @return
	 */
	public abstract int joue(Grille grille, Console console, int tour);

	/**
	 * Fais jouer un tour au joueur dans l'interface
	 * @param grille
	 * @param panel
	 * @param tour
	 * @return
	 */
	public abstract int joue(Grille grille, Interface inter, int tour);

}

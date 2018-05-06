package fr.uha.ensisa.puissance4.ui;

import java.util.Scanner;

import fr.uha.ensisa.puissance4.data.Grille;
import fr.uha.ensisa.puissance4.data.Humain;
import fr.uha.ensisa.puissance4.data.IA;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.data.Partie;
import fr.uha.ensisa.puissance4.jeu.Jeu;
import fr.uha.ensisa.puissance4.util.Constantes;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.PopupWindowBuilder;
import javafx.stage.Stage;

public class Interface extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Puissance 4"); 
		final Pane root = new Pane();
		Joueur j1,j2;
		j1 = choixJoueur(Constantes.JOUEUR_1);
		j2 = choixJoueur(Constantes.JOUEUR_2);		//TODO à partir de l'interface
		//Jeu jeu = new Jeu(j1, j2, this);
		//jeu.start();
		final Rectangle rectangle = new Rectangle(50, 50, 150, 100); 
		rectangle.setFill(Color.RED); 
		for(int i=0;i<8;i++) {
			root.getChildren().add(new Line(i*100,0,i*100,600));
		}
		for(int i=0;i<7;i++) {
			root.getChildren().add(new Line(0,i*100,700,i*100));
		}
		final Scene scene = new Scene(root, 700, 600); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}

	private Joueur choixJoueur(int order) {
		Joueur joueur=null;
		int typeJoueur;
		String nomJoueur;
		int typeIA=-1;
		int levelIA=-1;
		Scanner entry=new Scanner(System.in); //à retirer
		do{
			//affichage interface
			creerPopup(); //Experimental, ne sera pas gardé
			System.out.println("Le joueur "+order+" est :");
			System.out.println("1) Humain");
			System.out.println("2) Intelligence Artificielle");
			System.out.print("Votre choix : ");
			typeJoueur = entry.nextInt();
			/////
		}while(typeJoueur!=1&&typeJoueur!=2);
		if(typeJoueur==Constantes.JOUEUR_HUMAN)
		{
			//Interface
			System.out.print("Entrez le nom du joueur "+order+" : ");
			entry.nextLine();
			nomJoueur= entry.nextLine();
			//
			joueur=new Humain(nomJoueur,order);
		}
		else
		{
			nomJoueur=Constantes.IA_NAMES[(int)Math.floor(Math.random()*Constantes.IA_NAMES.length)];
			
			do{
				//TODO
				System.out.println("Quel IA pour joueur "+order+" ("+nomJoueur+") ?");
				for(int j=0; j<Constantes.IA_ALGOS.length; j++)
				{
					//TODO
					System.out.println((j+1)+") "+Constantes.IA_ALGOS[j]);
					
				}
				//TODO
				System.out.print("Votre choix : ");
				typeIA=entry.nextInt()-1;
			}while(typeIA<0||typeIA>=Constantes.IA_ALGOS.length);
			do{
				//TODO
				System.out.println("Niveau de difficulté de l'IA ("+nomJoueur+") [1-42] ?");
				System.out.print("Votre choix : ");
				levelIA=entry.nextInt();
			}while(levelIA<0||levelIA>Constantes.NB_TOUR_MAX);
			joueur= new IA(nomJoueur, order, typeIA, levelIA);
		}
		
		return joueur;
	}
	
	public void creerPopup() {
		Stage popupwindow=new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Start a new game");
		Label label1= new Label("Pop up window now displayed");
		Button button1= new Button("Close this pop up window");
		button1.setOnAction(e -> popupwindow.close());
		VBox layout= new VBox(10);
		layout.getChildren().addAll(label1, button1);
		layout.setAlignment(Pos.CENTER);
		Scene scene1= new Scene(layout, 300, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

	public void lancementPartie(Joueur joueur1, Joueur joueur2) {
		
	}

	public void lancementTour(int tour, Joueur joueurCourant, Grille grille) {
		// TODO Auto-generated method stub
		
	}

	public void afficherCoup(Joueur joueurCourant, int coup, long tempsReflexion) {
		// TODO Auto-generated method stub
		
	}


	public void afficherFinPartie(Partie partie) {
		// TODO Auto-generated method stub
		
	}

}

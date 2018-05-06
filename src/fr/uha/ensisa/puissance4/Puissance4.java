package fr.uha.ensisa.puissance4;

import fr.uha.ensisa.puissance4.ui.Console;
import fr.uha.ensisa.puissance4.ui.Interface;
import fr.uha.ensisa.puissance4.util.Constantes;

public abstract class Puissance4 {

	public static void main(String[] args) {

		int mode = Constantes.MODE_INTERFACE_GRAPHIQUE;
		//Indique la bonne interface et la lance dans un thread différent
		switch(mode)
		{
		case Constantes.MODE_INTERFACE_GRAPHIQUE:
			Interface.launch(Interface.class,args);
			break;
		default :
			Console console = new Console();
			console.start();
			break;
		}

	}

}

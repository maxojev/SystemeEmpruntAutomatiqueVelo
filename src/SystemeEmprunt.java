/* Importation de la classe Lecture. */


import java.util.Random;

class SystemeEmprunt {


	/* Constantes (final indique que la valeur ne peut pas changer) */

	static final int nbSites = 5;
	static final int maxClients = 20;

	/* Ces attributs sont statiques */

	private Site[] sites = new Site[nbSites];
	private Client[] clients = new Client[maxClients];
	private int nbClients = 0;


	/* Cette fonction cr�e un seul client � la fois (� la limite aucun).
	 * Elle renvoie vrai si et seulement si un client a �t� cr��.
	 * Elle renvoie faux d�s que la cr�ation des clients est termin�e. */

	private  boolean nouveauClient() {

		Site depart;
		Site arrivee;

		if(nbClients == maxClients) {
			System.out.println("Le nombre maximum de clients est"
				+ " atteint.");
			return false;
		}

		Random random = new Random();
		int i = random.nextInt(nbSites);
		int j = random.nextInt(nbSites);
		while(i== j) {
			j = random.nextInt(nbSites);
		}

		depart = sites[i];
		arrivee = sites[j];

		clients[nbClients] = new Client(nbClients, depart, arrivee);
		nbClients++;

		return true;

	}




	/* Constructeur. Il est appel� lors de l'instanciation du syst�me d'emprunt. */

	SystemeEmprunt() {

		int i;

		/* Instanciation des sites */
		for(i = 0; i < nbSites; i++) {
			sites[i] = new Site(i);
		}
		/* Instanciation du camion et des clients */
		Camion camion = new Camion(sites);
		camion.setDaemon(true);
		camion.start();



		for(int j=0; j < 20; j++){

			nouveauClient();
		}

		System.out.println("clients.length: " + clients.length);

		for(i = 0; i< clients.length; i++){
			clients[i].start();
		}

		/* ... */
	}


	/* Point d'entr�e du programme */

	public static void main(String[] args){


		new SystemeEmprunt();



	}


}

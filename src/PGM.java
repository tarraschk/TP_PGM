/**
 * 21 sept. 2012 by  maxime
 * TP_PGM
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner; 
/**
 * @author maxime
 *
 */

public class PGM {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Ajout de fancy stuff
		System.out.println("|************TP PGM***********|");
		System.out.println("|EI3 Info - Maxime Alay-Eddine|");
		System.out.println("|*****************************|");
		System.out.println("Nom du fichier PGM à importer ?");
		System.out.println("1: lena512x512.pgm");
		System.out.println("2: peppers512x512.pgm");
		System.out.println("3: test.pgm");
		
		Scanner sc = new Scanner(System.in);
		// La valeur tapée sera stockée dans sc
		String choix = sc.nextLine();
		
		File monFichier = null;
		if (Integer.parseInt(choix) == 1) {
			monFichier = new File("/Users/maxime/workspace/TP_PGM/lena512x512.pgm");
		}
		else if (Integer.parseInt(choix) == 2) {
			monFichier = new File("/Users/maxime/workspace/TP_PGM/peppers512x512.pgm");
		}
		else if (Integer.parseInt(choix) == 3) {
			monFichier = new File("/Users/maxime/workspace/TP_PGM/test.pgm");
		}
		
		// Pour vérification, on affiche le nom du fichier
		System.out.println("Le fichier à importer est : "+monFichier.getName());
		// Scan d'un fichier
		try {
			Scanner parcours = new Scanner(monFichier);
			
			int entierLu, valMax, largeur, hauteur;
			
			try {
				// Chargeons le fichier dans un tableau et calculons son histogramme
				parcours.nextLine(); // On saute la ligne indiquant P2
				parcours.nextLine(); // On saute la ligne de commentaire
				largeur = parcours.nextInt();
				hauteur = parcours.nextInt();
				valMax = parcours.nextInt();
				System.out.println("Largeur : "+largeur+"; Hauteur : "+hauteur+"; Valeur maximale : "+valMax);
				
				int[][] valeurs = new int[hauteur][largeur];
				int[] histogramme = new int[valMax+1] ;
				
				for (int i=0;i<hauteur;i++) {
					for (int j=0;j<largeur;j++) {
						entierLu = parcours.nextInt();
						valeurs[i][j] = entierLu;
						histogramme[entierLu] = histogramme[entierLu] + 1;
					}
				}
				
				
				
				
				parcours.close(); // Fermeture du flux de lecture de fichier
				System.out.println("Fin du programme : SUCCES !");
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				System.out.println("Erreur lors de l'import du fichier...");
				System.out.println("Fin du programme : ERREUR !");
				e.printStackTrace();
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Erreur lors de l'import du fichier");
			e.printStackTrace();
		}
		
	}

}

/**
 * 21 sept. 2012 by  maxime
 * TP_PGM
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * @author maxime
 *
 */

public class PGM {

	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int entierLu, valMax, largeur, hauteur;
		int seuil;
		int[] histogramme;
		// Ajout de fancy stuff
		System.out.println("|************TP PGM***********|");
		System.out.println("|EI3 Info - Maxime Alay-Eddine|");
		System.out.println("|*****************************|");
		System.out.println("Nom du fichier PGM à importer ?");
		System.out.println("1: lena512x512.pgm");
		System.out.println("2: peppers512x512.pgm");
		
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
		
		// Pour vérification, on affiche le nom du fichier
		System.out.println("Le fichier à importer est : "+monFichier.getName());
		// Scan d'un fichier
		try {
			Scanner parcours = new Scanner(monFichier);
			
			try {
				// Chargeons le fichier dans un tableau et calculons son histogramme
				parcours.nextLine(); // On saute la ligne indiquant P2
				parcours.nextLine(); // On saute la ligne de commentaire
				largeur = parcours.nextInt(); // Lecture de la largeur
				hauteur = parcours.nextInt(); // Lecture de la hauteur
				valMax = parcours.nextInt(); // Lecture de la valeur max en niveaux de gris
				
				System.out.println("Saisissez votre valeur seuil :");
				seuil = Integer.parseInt(sc.nextLine());
				
				System.out.println("Largeur : "+largeur+"; Hauteur : "+hauteur+"; Valeur maximale : "+valMax+"; Seuil : "+seuil);
				
				int[][] valeurs = new int[hauteur][largeur];
				histogramme = new int[valMax+1] ;
				
				// On lit tous les pixels
				for (int i=0;i<hauteur;i++) {
					for (int j=0;j<largeur;j++) {
						entierLu = parcours.nextInt();
						//System.out.println(entierLu);
						if (entierLu > seuil) { // Truc bizarre avec le seuil parce que je n'ai pas lu le sujet en entier
							entierLu = seuil;
						}
						//System.out.println("Précédent:"+histogramme[entierLu]);
						histogramme[entierLu] = histogramme[entierLu] + 1;
						//System.out.println("Suivant:"+histogramme[entierLu]);
						valeurs[i][j] = entierLu;
					}
				}
				/*for (int j=0;j<255;j++) {
					System.out.println("Valeur: "+j+" -> "+histogramme[j]);
				}*/

				
				
				// Ecriture de l'histogramme dans un fichier sortie.pgm
				FileWriter fichierSortie = null;
				try {
					fichierSortie = new FileWriter("/Users/maxime/workspace/TP_PGM/sortie.pgm");
					fichierSortie.write("P2\n");
					fichierSortie.write("100 " + Integer.toString(valMax)+"\n");
					fichierSortie.write("255\n");
					int maximum = 0;
					for (int i=0;i<valMax;i++) { // On détermine le maximum du tableau de l'histogramme
						// Ce maximum correspond à la nuance de gris la plus représentée dans l'image
						if (histogramme[i] > maximum) {
							maximum = histogramme[i];
						}
					}
					for (int i=0;i<valMax;i++) {
						// Pour chaque valeur de nuance de gris, on trace une barre plus ou moins longue en fonction du nombre d'occurrences existantes par rapport au maximum de l'histogramme 
						for (int j=1;j<=100;j++) {
							//System.out.println(histogramme[i]*100/maximum);
							//choix = sc.nextLine();
							if(j < (histogramme[i]*100/maximum)) {
								fichierSortie.write("255");
							}
							else {
								fichierSortie.write("0");
							}
							fichierSortie.write("\n");
						}
					}
					fichierSortie.close();
				} catch (IOException e) {
					e.printStackTrace();
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

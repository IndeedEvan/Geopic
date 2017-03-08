# Geopic
Java desktop app for pictures location 

Problèmes à fixer:
  -  Superposition d'images: lorsque 2 photos ont été prises à proximité, elles se superposent --> afficher un indicateur qui montre la présence de plusieurs photos
	--> Impact sur l'affichage des images lors du clic: en afficher plusieurs
  -  Lorsque les images ne sont plus présentes dans leur dossier (renseigné par le path en db), le programme plante
	--> Afficher un message d'erreur précisant "Tels photos on été déplacé dans votre ordinateur, elles n'ont pas pu être affiché"
		- (checkbox) Ne plus afficher ce message | Fermer
					-- Suppresion en BDD			-- Pas de suppresion en BDD
		
Taches en cour:
	- Création de l'interface d'affichage des images
	- Suppresion des images via interface 
	
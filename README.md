# Geopic
Java desktop app for pictures location 

Problèmes à fixer:
  -  Insertion d'image sans coordonnées GPS --> afficher un message d'erreur "tel photo n'a pas pu être ajouté car elle ne possède aucune donnée GPS"
  -  Superposition d'images: lorsque 2 photos ont été prises à proximité, elles se superposent --> afficher un indicateur qui montre la présence de plusieurs photos
      --> Impact sur l'affichage des images lors du clic: en afficher plusieurs
  -  Lorsque les images ne sont plus présentes dans leur dossier (renseigné par le path en db), le programme plante: afficher un message d'erreur précisant "tels photos on été déplacé dans votre ordinateur, elles n'ont pas pu être affiché"
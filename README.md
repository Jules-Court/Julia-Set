# ENSEMBLE DE JULIA

## Présentation
Un ensemble de Julia est une figure mathématique utilisant les nombres complexes. L'ensemble généré avec les complexes est une fractale, on peut donc "zoomé" autant qu'on veut, on tombera toujours sur les mêmes figures.


## Compilation et choix de l'interface

Nous avons décidé d'utiliser gradle pour la gestion du projet.
Pour compiler le projet : **./gradlew build**  
Pour lancer le projet : **./gradlew run**  
Une fois le projet lancé on nous propose deux interfaces. Choisissez 1 ou 2.  
**1** pour la version Terminale, **2** pour la version Graphique

## Version Terminale :
On vous proposera dans un premier temps de choisir la partie réélle de votre Complexe qu'il faudra écrire avec une virgule ! (exemple : -0,3).  

Puis la partie imaginaire qu'il faudra également écrire avec une virgule ! (exemple : 0,6) :  

Pour finir il faudra rentrer le niveau de zoom, pour pouvoir zoomé dans l'image et le nombre d'itération qui lui, ne sera pas un double mais un int et la taille de l'image.
Une fois les 5 nombres entrés, le programme vous génerera une image dans le dossier src/main/ressources. Le nom de l'image sera **f(c)=x+y,_z_w.png** avec x la partie réelle, y la partie imaginaire, z le niveau de zoom et w le nombre d'itération.

## Version Graphique :

Pour la version graphique, il faut taper **2** sur le terminal apres avoir utiliser **./gradlew run**. Il y aura une première interface qui demandera les même choses que dans la partie terminal. Ici il faudra mettre la partie imaginaire et la partie réelle avec des points ! (exemple : -0.3 et 0.6).  
Apres avoir remplit les cases, il faut appuyer sur le bouton **"Envoie"** et il y aura une seconde fenêtre qui s'ouvrira avec la fractale. Dans cette fractale, on pourra se déplacer avec les touches. (Voir partie **Se déplacer dans l'Interface Graphique**)


## Se déplacer dans l'Interface Graphique

Maintenant, pour pouvoir se déplacer dans l'interface, il vous faudra un clavier.  
Il vous faudra utiliser les touches :  
* **k** ou **+** : pour zoomé  
* **l** ou **-** : pour dézoomé
* **z** : pour aller vers le haut
* **s** : pour aller vers le bas
* **q** : pour aller sur la gauche
* **d** : pour aller sur la droite
* **g** : pour enregistrer la photo



TRAN An-Tiêm 21964523
COURTIN Jules 71800254


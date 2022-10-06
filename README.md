# Simulation Bataille en Java

Ce repo git contient mon travail pour le projet de simulation du jeu de carte en Java.

## Structure du code

Comme demandé, il est divisé en 3 classes (*Bataille, Carte & Joueur*), et le point d'entrée principale, autrement dit la fonction main, se trouve dans la classe Bataille.


## Compilation

En se plaçant dans le répertoire contenant le code,
executer la commande:

`javac Bataille.java`

## Utilisation

Pour lancer le programme, après l'avoir compilé:

`java Bataille`

## Déroulement du jeu

Le programme se déroule selon ces étapes:

1. création & mélange d'un paquet de carte
    - *avec la fonction* `shuffleDeck()`
2. distribution des cartes aux deux joueurs
3. début de la boucle de jeu
    1. chaque joueur tire une carte
    2. les deux cartes sont comparées
    3. le joueur ayant la carte la plus forte gagne le tour
    4. si l'un des deux joueurs n'a plus de cartes, l'autre gagne
4. fin

Toutes ces étapes sont indiquées dans les commentaires du code.

# Sudoku Solver - Java POO

Ce projet est une application Java robuste permettant de charger, d'afficher et de résoudre des grilles de Sudoku 9x9. Il a été développé dans le cadre du cours de **Programmation Orientée Objet Avancée** (L3 GLSI - ESP).

---

## Équipe de Développement
Le projet a été développé par 5 étudiants pour garantir une architecture propre et modulaire
- Mohamed BA
- Mouhamadou GUEYE
- Ndeye Tening SENE
- Fatou Kiné DIANKO

##  Fonctionnalités
- **Chargement Multimodal** : Lecture de grilles depuis un fichier texte externe ou via une saisie manuelle sécurisée en console.
- **Moteur de Résolution** : Algorithme de **backtracking** (récursif) capable de résoudre n'importe quelle grille valide en quelques millisecondes.
- **Validation en Temps Réel** : Vérification stricte des règles du Sudoku (ligne, colonne et bloc 3x3) avant chaque placement.
- **Affichage Graphique Console** : Utilisation des caractères Unicode "Box-Drawing" pour un rendu visuel clair et professionnel.

## Structure du Code Source
- `Case.java` : Classe de base représentant une cellule (stocke la valeur et l'état "fixe" ou "modifiable").
- `Grille.java` : Cœur du projet contenant le plateau 9x9, les méthodes de chargement (Fichier/Console) et les règles de validation.
- `Solveur.java` : Contient l'intelligence du programme (backtracking).
- `Afficheur.java` : Gère uniquement le rendu visuel de la grille.
- `SudokuApp.java` : Point d'entrée du programme gérant le flux principal.

---

## 🛠️ Installation et Exécution

### 1. Prérequis
Assurez-vous d'avoir le JDK Java (version 8 ou supérieure) installé sur votre machine.

### 2. Compilation
Ouvrez un terminal dans le dossier du projet et compilez tous les fichiers :
```bash
javac *.java
```


### 3. Lancement
Une fois la compilation réussie, vous pouvez lancer l'application de deux manières :
**Option A : Charger une grille depuis un fichier (Recommandé)** Préparez un fichier `.txt` (ex: `grille.txt`) à la racine du projet et exécutez :
```bash
java SudokuApp grille.txt
```
**Option B : Saisie manuelle via la console**
Si vous lancez le programme sans aucun argument, il basculera automatiquement sur la saisie manuelle :
```bash
java SudokuApp
```

## Tutoriel Vidéo
Vous trouverez une vidéo explicative du projet ici : https://youtu.be/lkWXHsoTTPI?si=M9MF5U80BzWdAyJz

# Calculatrice Graphique

#Installer JavaFX :
https://gluonhq.com/products/javafx/

#Dans le terminal
Sur Linux  :
	export PATH_TO_FX=path/to/javafx-sdk-15.0.1/lib

	Se placer dans le document src

	Compiler le projet : 
	javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml application/*.java
	java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml application.Vue

Sur Windows :
	set PATH_TO_FX="path\to\javafx-sdk-15.0.1\lib"

	Compiler le projet
	javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml application/*.java
	java --module-path  %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml application.Vue


#Guide pour utiliser la calculatrice graphique

- Calculatrice graphique composée de deux parties :
 
-> celle de gauche : elle permet d’entrer les fonctions qu’on souhaite. Au démarrage, un champ avec un TextField et des options est déjà apparent. Pour ajouter d’autres expressions, il faut cliquer sur le bouton menu “+” qui se trouve en haut à gauche de la fenêtre.
En déroulant le menu “Options”, on peut aussi cliquer sur “clavier”, qui crée un nouveau champ et affiche un clavier dans une nouvelle fenêtre. La formule tapée est visible sur le champ de texte dans la partie gauche
Le champ est constitué de : 
-> un label qui indique la couleur qu’aura la courbe, un champ de texte afin d’entrer une fonction, un bouton “x” pour supprimer ce champ et ainsi la courbe qui est associée.
-> Il est possible de choisir un intervalle si l’on souhaite étudier une fonction à un intervalle donné. De plus, avec les radiobutton, on peut choisir les différents types d’intervalle, c’est-à-dire les intervalles fermés, ouverts et demi-fermés.
-> Juste entrer la fonction ne suffit pas, il faut également cocher la case “Tracer”. En effet, comme il est possible d’ajouter un nombre infini de champ d’expression, pour faciliter la visualisation, on peut décider de décocher certains champs pour uniquement faire disparaître la courbe, le champ, lui, sera intact.
-> En cliquant sur le label, une boîte de dialogue apparaît pour choisir une couleur souhaitée pour la courbe associée

->celle de droite : sur le graphique sera affiché les fonctions voulues par l’utilisateur. Au démarrage de la fenêtre, certains paramètres d’affichage ont été mis par défaut.
Grâce au bouton “Réglages”, il est possible de changer ces paramètres.
Réglages : -> L’option “contraste” permet de faire afficher le graphique en noir et les axes et chiffres en blanc. Pour faire apparaître à nouveau le graphique au fond blanc, il suffit de désélectionner le bouton.
-> Le bouton “Axe des nombres” permet de rendre visible ou non l’axe des nombres
-> Le bouton “Axe des x” rend visible ou non l’axe des abscisses. Le champ de texte à côté permet de modifier l’étiquette de l’axe des abscisses
->le champ permet de modifier les bornes de l’axe des abscisses du graphique
-> le champ “Pas” permet de modifier le pas de l’axe des abscisses
-> on peut modifier de la même manière l’axe des ordonnées

- Les fonctions principales possible :
les fonctions avec des opérations élémentaires (addition “+”, soustraction “-”, multiplication “*”, division “/”)
la fonction absolue “abs(x)”
la fonction carrée ainsi que toutes les puissances “x^2” (ou x^n)
la fonction racine carrée “sqrt(x)”
la fonction racine cubique “cbrt(x)”
les formules avec des racines n-ièmes doivent être transformées par la puissance 1/n (racine 5ème de x devient “x^(⅕)”
les fonctions trigonométriques telles que “cos(x)”, “sin(x)”, “tan(x)”, “acos(x)”, “asin(x)”, “atan(x)” (les résultats sont en radians)
la fonction exponentielle “exp(x)”
la fonction logarithme népérien “ln(x)”
la fonction logarithme base 10 “log(x)”
Le nombre pi peut être utilisé “pi”, “Pi”, ou “PI” ex : “cos(Pi*x)” 

Les fonctions composées avec celles ci-dessus peuvent également être visualisées

- Les touches du clavier :
Il y a les chiffres à gauche
Au centre : les symboles des opérations élémentaires, les parenthèses ouvrantes et fermantes, le point pour les chiffres décimaux, le nombre Pi, la variable x, le bouton EFF pour effacer un caractère et le bouton C pour effacer toute la fonction tapée 
A droite : les fonctions utilisables


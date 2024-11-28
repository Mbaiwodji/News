News App
==================================

News est une application (volontairement simple dans le cadre de cet exercice) qui permet d'afficher les actualités à la une dans la langue du téléphone.

Dans cette application, l'utilisateur peut :
- Voir l'ensemble des actualités, avec au minimum le titre de chaque article et l'image associée.
- Cliquer sur une actualité pour voir les détails de celle-ci, comprenant le titre, l'image, la description et un lien vers l'article complet.


Architecture et organisation du projet
-------------------------
J'ai structuré le projet en adoptant une architecture MVVM (Model-View-ViewModel) et une approche modulaire. 
L'architecture MVVM garantit une séparation claire des responsabilités : le modèle gère les données et 
la logique métier, la vue s'occupe de l'affichage et de l'interaction utilisateur, tandis que le ViewModel agit 
comme médiateur entre les deux. Cette approche simplifie la testabilité, j'ai donc pu tester les ViewModels de manière indépendante. 
En plus de l'architecture MVVM l'approche modulaire divise le projet en modules indépendants, 
comme l'UI, les données, etc, ce qui facilite la collaboration, la réutilisabilité.


Le choix des technologies utilisées et pourquoi
-------------------------


Kotlin (avec Coroutines et Flow)
--------
J'ai choisi Kotlin non seulement parce qu'il est requis pour ce test, mais aussi pour ses fonctionnalités avancées comme 
les Coroutines et Flow. Les Flow sont utilisés ici pour gérer les mises à jour continues et les données en temps réel. 
Avec Kotlin, la syntaxe est beaucoup plus concise et sécurisée, ce qui réduit les bugs courants.


Jetpack Compose
--------
J'ai choisi Jetpack Compose pour la conception de l'interface utilisateur, car il offre une approche déclarative 
qui permet de se concentrer sur quoi afficher plutôt que comment afficher. Cela réduit considérablement 
la complexité et le code boilerplate par rapport à XML. Compose est parfaitement intégré à Kotlin et compatible avec 
les tests automatisés grâce à des outils natifs comme ComposeTestRule, ce qui garantit une meilleure qualité du code. 
Il constitue ainsi un toolkit parfaitement adapté aux besoins des applications modernes.


Retrofit
--------
Mon choix s'est porté sur Retrofit pour la consommation des services REST, parce qu'il offre une API intuitive qui simplifie 
l'intégration des services RESTful. Il prend en charge la sérialisation avec Kotlinx Serialization, ce qui facilite 
la gestion des données. Retrofit s'intègre également de manière fluide avec les Coroutines, me permettant donc d'effectuer 
des appels réseau non bloquants et ainsi améliorer les performances de l'application.


Koin
-------

J'ai choisi Koin pour l'injection de dépendances (DI), parce qu'il simplifie la configuration et réduit le temps de mise en place. 
Contrairement à Hilt, Koin ne nécessite pas d'annotations ou de génération de code, ce qui le rend particulièrement adapté aux projets de taille petite ou moyenne.

Tests
------

J'ai opté pour des tests unitaires et d'intégration afin d'assurer la qualité et la fiabilité de l'application.
- Tests unitaires: des tests ont été rédigés pour le ViewModel afin de valider la logique métier.
- Tests d'intégration Compose: J'ai mis en place des tests pour m'assurer que l'interface utilisateur se met à jour correctement en fonction des différents états.

Coil
-------

J'ai opté pour Coil pour le chargement d'images, pour la simple raison qu'il est plus léger et rapide que des alternatives comme Glide ou Picasso. 
De plus, il est conçu spécifiquement pour Kotlin et Jetpack Compose.  Aussi, Coil gère automatiquement les cycles de vie et optimise le chargement, 
ce qui le rend encore plus interessant

Gestion des clés API
-------
Pour des raisons de sécurité, il est déconseillé de coder en dur les clés API dans le code source, comme j'ai fait actuellement dans ce mini-projet. 
Une approche plus sécurisée consiste à utiliser des outils comme Secret Manager ou à stocker les clés dans des variables d'environnement.


Gestion de version avec Git et GitHub
---------------------

J'ai choisi Git et GitHub pour la gestion de version du projet. Git offre un contrôle de version distribué robuste, parfaitement adapté au travail en équipe. 
Il permet une gestion efficace des pull requests, facilite l'intégration continue (CI/CD) pour automatiser les tests et les déploiements, et assure un suivi 
transparent de l'historique des modifications. Cela renforce à la fois la collaboration et la qualité du développement.

Cela dit, je tiens à préciser qu'il aurait été préférable, même dans le cadre de ce mini-projet, de respecter des pratiques de gestion de projet plus rigoureuses. 
Par exemple, j'aurais pu utiliser un outil comme Jira pour créer plusieurs tickets correspondant aux user stories. Ensuite, chaque ticket aurait pu être traité de manière isolée :

1. Créer une branche dédiée pour chaque ticket.
2. Implémenter les changements nécessaires.
3. Tester les modifications.
4. Effectuer des pull requests pour une éventuelle revue de code et validation.
   
Cependant, dans cet exercice, j'ai simplifié le processus en réalisant l'intégralité des tâches sur une seule branche avant de pousser les modifications une seule fois. 


Problèmes identifiés mais non traités :
---------------------

- Gestion avancée des erreurs API
- Mise en œuvre de la pagination
- Mode hors ligne
- Options de recherche
- Stocker API Key dans Secret Manager ou variable d'environnement.


# 420-515-MV-MAINTENANCE-DE-LOGICIEL-LAB1-BACKEND

# Description

Cette application web permet aux utilisateurs de :

se connecter avec un compte sécurisé (JWT),

consulter les personnes enregistrées dans la base de données,

explorer les séries disponibles,

obtenir une playlist personnalisée selon leur profil,

et voir les tendances (top séries) selon la popularité et les notes.

Le backend est développé en Spring Boot (Java),
et le frontend en ReactJS avec gestion du token JWT côté client.

 # Fonctionnalités principales
 Authentification (Login)

L’utilisateur se connecte via son email et mot de passe.

Le serveur génère un token JWT qui est sauvegardé localement (dans localStorage).

Ce token est ensuite utilisé dans toutes les requêtes API pour vérifier l’accès.

# Page des personnes (/persons)

Permet de rechercher des personnes par leur nom de famille.

Affiche : ID, nom, âge et genre.

# Page des séries (/series)

Affiche toutes les séries disponibles dans la base.

Montre : ID, titre, genre, nombre d’épisodes et note moyenne.

 # Page Playlist (/playlist)

L’utilisateur peut cliquer sur un bouton “Avoir ma playlist”.

Le site appelle l’API pour obtenir une recommandation de genre adaptée à son profil.

Les séries correspondant à ce genre sont affichées dans un tableau.

# Page des tendances (/trend)

Affiche les 10 séries les plus populaires selon leur score calculé à partir des votes, du nombre de vues et du nombre d’épisodes.

Utilise la méthode Tendance.trending() du backend.

 Système de vote

Les utilisateurs peuvent attribuer une note (1 à 5) à une série.

Le backend met à jour la table vote et recalcule la note moyenne de la série.

Si un utilisateur modifie son vote, celui-ci est simplement mis à jour.

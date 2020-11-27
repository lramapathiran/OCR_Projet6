# Les Amis de l'escalade
Créer un site communautaire autour de l'escalade.

## Pré-requis

1- Installer le kit de développement Java (JDK 8 minimum).
2- Installer Maven 4
3- Installer mySQL 8

## Contenu du repository

1- Dossier ScriptsSQL avec un fichier Demo.sql qui permet la création de la base de données et insertion de données:
  - Un Utilisateur avec le rôle d'Administrateur: Identifiant = mmariesainte@gmail.com/Mot de passe:mmariesainte
  - Le reste qui sont de simples utilisateurs, ex : Identifiant = vpeireira@gmail.com/Mot de passe:vpeireira

2- Dossier escalade : contient le projet Maven/Spring Boot

## Utilisation de Spring Boot

1- Configuration dans le fichier application.properties dans src/main/resources : personnaliser le host, le username et le mot de passe mySQL.

2- Démarrer l'application avec ``mvn spring-boot:run``.

3- Ouvrir l'application sur localhost:8080 et se connecter avec les utilisateurs mentionnés ci-dessus.

## Déploiement

1- Packager l'application avec ``mvn package``.

2- Exécuter l'application avec ``java -jar -Dserver.port=8080 escalade-0.0.1-SNAPSHOT.jar``.
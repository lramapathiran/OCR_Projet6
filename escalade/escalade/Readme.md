# Les Amis de l'escalade
Créer un site communautaire autour de l'escalade.

## Pré-requis

1- Installer le kit de développement Java (JDK).<br>
2- Installer Spring Boot.<br>
3- Installer Apache Tomcat.<br>
4- Insataller mySQL workbench.<br>
   
## Installation

1- Cloner le projet à partir d'https://github.com/lramapathiran/OCR_Projet6.git.<br>
2- Aller dans le dossier "ScriptsSQL".<br>
3- Dans mySQL workbench avec la commande "run SQL script", générer la base de données mySQL avec ses tables(fichier script_create_db.sql).<br>
4- Insérer les données du script Demo.sql dans les tables avec la commande "data import" et prendre soin de sélectionner "dump structure and data" dans la fenêtre d'import.<br>
Remarque: Un Utilisateur ayant comme Rôle: "Administrateur" (Identifiant: mmariesainte@gmail.com et mot de passe:mmariesainte) a été créé. Les autres utilisateurs ont un simple rôle d'utilisateur (ex: Identifiant:vpeireira@gmail.com et mot de passe:vpeireira).<br>
5- Pour connecter l'application à votre BDD il faudra importer le projet complet dans votre IDE et modifier les données de connexion à la BDD dans le fichier application.properties.<br>
6- Générer un fichier .jar avec Maven et déployer sur le serveur tomcat de votre choix.
# RES - SMTP Pranks

> Par Alec Berney & Nicolas Crausaz

## Introduction

Ce laboratoire fait partie du cours RES de l'HEIG-VD. Il consiste en l'implémentation d'un logiciel d'envoi de "prank" par email.

Les objectifs de ce laboratoire sont de:
- Devenir familier avec le *protocole SMTP*, en travaillant avec les messages SMTP dans utiliser de librairies spécifiques pour SMTP.
- Utiliser des outils de test client-serveur, comme un "mock server".
- Créer et envoyer des _emails forgés_

## Fonctionnalités

Le fonctionnement de l'application se résumer assez simplement:

Le programme réparti un certain nombre de personnes (adresses mail) dans un certain nombre de groupes (tout ceci est configurable).
Chaque groupe contient 1 "pranker" considéré comme l'expéditeur de l'email forgé, les autres membres du groupe seront considérés
comme les victimes, c’est-à-dire les destinataires du mail. Un groupe est donc au minimum composé de deux personnes.

Les pranks sont choisis aléatoirement parmi une liste personnalisable.


## Implémentation

Class Diagram

![diagramme de classe](figures/UML.png)

expliquer les différents aspects 7 particularitées des classes

Example of interactions (client-server level)


Nous avons implémenté une petite série de tests pouvant être executé; *attention*, certains tests envoient des emails.
Il vaut mieux donc utilisé le mock server pour effectuer les tests.

`$ mvn clean test`


## Configuration

Afin d'être prêt à envoyer vous pranks, vous allez d'abord devoir préparer quelques élèments.
Premièrement, remplissez le fichier d'adresses mails [fichier d'adresses mails](configs/emails.utf8) (configs/emails.utf8) avec tous les emails auquel vous souhaitez envoyer et recevoir des pranks.
Ensuite, remplissez le [fichier de pranks](configs/pranks.json) (configs/pranks.json) avec vos meilleures blagues, chaque prank doit avoir un titre (qui sera le sujet du mail) et un contenu (corps du mail).

Vous allez probablement devoir éditer la [configuration](configs/config.properties) (configs/config.properties) pour répondre à vos besoin:

```
SMTP_DEFAULT_PORT= <Port de votre serveur SMTP | Requis>
SMTP_DEFAULT_SERVER= <Adresse IP Port de votre serveur | Requis>
NUMBER_OF_GROUPS= <Nombre de groupes / mail qui seront envoyés | Requis>
CLIENT_NAME= <Nom de l'application | Requis>
MAIL_HIDDEN_SENDER= <Adresse mail cachée de l'expéditeur des pranks | Requis | Le format de l'email doit être valide>
MAIL_CC= <Adresse mail qui sera mise en copie de chaque prank | Optionnel>
MAIL_BCC= <Adresse mail qui sera mise en copie cachée de chaque prank | Optionnel>
```

Notez que les valeurs spécifiés par _Requis_ *doivent être spécifiées*, sinon l'application vous transmettra son mécontentement.

Si vous souhaitez tester l'application sans envoyer de vrai emails, nous avons mis à disposition un _mock server_ qui fera office de serveur SMTP.
Pour travailler avec ce serveur, modifiez les deux premiers champs du fichier de configuration de cette manière:

```
SMTP_DEFAULT_PORT=2525
SMTP_DEFAULT_SERVER=localhost
```

Ceci fait, vous allez pouvoir démarrer le container Docker du _mock server_:

Construire l'image:\
`$ ./docker/build.sh`

Démarrer le container:\
`$ ./docker/run.sh`

Tout est prêt, vous pouvez désormais commencer à envoyer vos pranks en exécutant:

`$ java -jar .\target\smtp-pranks-1.0-SNAPSHOT-launcher.jar`

Si vous avez utilisé notre serveur Mock, les mails sont visible sur [http://localhost:8080](http://localhost:8080).

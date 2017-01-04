Equipe Red Panda:

1)Ouvrir le dossier source, créer un dossier de reception (exemple : Build)
2)Ouvrir une console dans le dossier /src et compiler les fichiers dans le dossier de reception (exemple : javac -d Build *.java)
3)Une fois compiler créer un fichier jar qui s'appelera obligatoirement brainfuckLanguage, avec la commande jar cf brainfuckLanguage *.class
en ayant ouvert un console dans le dossier de reception de la compilation
4)Verifier que le fichier jar contient bien les fichiers compiler et déplacer le fichier jar dans le dossier qui contient le fichier
script bfck.
5)Lancer une console n'importe où et lancer le programme avec ./bfck

# Liste des fichiers sources
SOURCES = SudokuInterface.java GrilleSudoku.java Cellule.java SudokuSolver.java SudokuFileHandler.java Main.java

# Nom du fichier exécutable
EXECUTABLE = SudokuInterface

# Commande pour compiler les fichiers sources en bytecode Java
COMPILE = javac -implicit:none

# Commande pour exécuter le programme
RUN = java $(EXECUTABLE)

# Règle pour la compilation
compile:
    $(COMPILE) $(SOURCES)

# Règle pour l'exécution du programme
run:
	$(RUN)

# Règle par défaut (exécuter après 'make' sans argument)
default: compile

# Nettoyer les fichiers générés
clean:
	rm -f *.class
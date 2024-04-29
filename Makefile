# Liste des fichiers sources
SOURCES = Cellule.java GrilleSudoku.java SudokuInterface.java SudokuFileHandler.java SudokuSolver.java Main.java

# Nom du fichier exécutable
EXECUTABLE = Main

# Liste des fichiers .class
CLASSES = $(SOURCES:.java=.class)

# Commande pour compiler les fichiers sources en bytecode Java
COMPILE = javac -implicit:none

# Commande pour exécuter le programme
RUN = java -classpath . $(EXECUTABLE)

# Règle pour la compilation
compile:
    $(COMPILE) $(SOURCES)

# Règle pour exécuter le programme
run:
	$(RUN)

# Règle par défaut (exécuter après 'make' sans argument)
default: compile

# Nettoyer les fichiers générés
clean:
	rm -f $(CLASSES)

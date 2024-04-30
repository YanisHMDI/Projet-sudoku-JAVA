# Makefile pour le projet Sudoku

# Compiler avec les options de compilation recommandées pour Java
JFLAGS = -g
JC = javac

# Liste des fichiers source Java
SRCS = Main.java SudokuInterface.java Cellule.java GrilleSudoku.java SudokuFileHandler.java 
# Liste des fichiers .class générés
CLASSES = $(SRCS:.java=.class)

# Règle par défaut : construire tous les fichiers .class
all: $(CLASSES)

# Règle pour compiler les fichiers .java en fichiers .class
%.class: %.java
	$(JC) $(JFLAGS) $<

# Nettoyer les fichiers .class générés
clean:
	$(RM) $(CLASSES)

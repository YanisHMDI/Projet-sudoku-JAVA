all: compile

compile:
	javac -implicit:none *.java

run:
	java SudokuInterface

clean:
	rm -f *.class

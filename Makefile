all: compile

compile:
	javac -implicit:none *.java

run:
	java Main

clean:
	rm -f *.class

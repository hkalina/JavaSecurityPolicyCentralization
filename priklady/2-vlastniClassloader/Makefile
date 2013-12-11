
test1: Program.class TestovaciClassLoader.class
	java -Djava.system.class.loader=TestovaciClassLoader Program

Program.class: Program.java
	javac Program.java

TestovaciClassLoader.class: TestovaciClassLoader.java
	javac TestovaciClassLoader.java

clean:
	rm -f Test.class libagent.so *.log

.PHONY: test1 test2 test3 test4 test5 clean


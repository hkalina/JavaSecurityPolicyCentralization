
test1: Program.class
	java Program

test2: Program.class
	java -Djava.security.manager Program

test3: Program.class
	java -Djava.security.manager=java.lang.SecurityManager Program

test4: Program.class
	java -Djava.security.manager=muj.skvely.SecurityManager Program

test5: Program.class
	java -Djava.security.manager=neexistujici.SecurityManager Program

Program.class: Program.java
	javac Program.java

clean:
	rm -f Test.class libagent.so *.log

.PHONY: test1 test2 test3 test4 test5 clean



test1: Program.class
	java Program

test2: Program.class
	java -Djava.security.manager -Djava.security.policy=povolitVse.policy Program

test3: Program.class
	java -Djava.security.manager=java.lang.SecurityManager -Djava.security.policy=povolitVse.policy Program

test4: Program.class TestovaciSM.class
	java -Djava.security.manager=TestovaciSM -Djava.security.policy=povolitVse.policy Program

test5: Program.class
	java -Djava.security.manager=neexistujici.SecurityManager Program

Program.class: Program.java
	javac Program.java

TestovaciSM.class: TestovaciSM.java
	javac TestovaciSM.java

clean:
	rm -f Test.class libagent.so *.log

.PHONY: test1 test2 test3 test4 test5 clean


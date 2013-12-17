
# na Windows classpath oddělit středníkem, na Linuxu dvojtečkou!

run:
	java -cp "program:knihovna" -Djava.security.policy=databaze.policy Program

secured:
	java -cp "program:knihovna" -Djava.security.manager -Djava.security.policy=databaze.policy Program

debug:
	java -cp "program:knihovna" -Djava.security.policy=databaze.policy -Djava.security.debug=all Program

build:
	javac */*.java


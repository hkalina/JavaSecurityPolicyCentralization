Programy v jazyce Java bývají překládány do platformě nezávislého a efektivněji než kód v jazyce Java interpretovatelného mezikódu, takzvaného bytekódu. Bytekód bývá zpravidla interpretován virtuálním strojem Javy (JVM - Java Virtual Machine). JVM je abstraktní výpočetní stroj, podobně jako například Turingův stroj. Stejně jako reálný výpočetní stroj má svoji instrukční sadu a paměť se kterou může manipulovat, ale na rozdíl od něj pro něj neexistuje jeho fyzická implementace, pouze emulovaná implementace softwarová. To znamená že její kód není prováděn nativně hardwarem, ale je interpretován speciálním programem, interpreterem, který sám je v nativním kódu dané platformy. To mimo nezávislosti na platformě přináší také vyšší stupeň abstrakce, díky tomu že programy nepřistupují ani nemohou přistupovat ke zdrojům fyzického stroje přímo, ale pouze zprostředkovaně skrze zdroje virtuální stroje Javy.

Není tedy těžké si představit že by použití takovéhoto virtuálního stroje mohlo mít i významný bezpečnostní efekt. Protože programy v JVM mohou k fyzickým zdrojům počítače (např. k souborům nebo k síti) přistupovat jen skrze JVM, zablokování takového přístupu ze strany JVM není nikterak složité - JVM stačí odmítnout takový požadavek a interpretovaný program nemá možnost JVM obejít.

## Java Security Manager ##
Pro maximální rozšiřitelnost omezení kladených na programy běžících na virtuáních strojích Javy nerozhoduje o povolení nebo zablokování operace samotná JVM, ale dotazuje se speciálního objektu třídy java.lang.SecurityManager, nebo jeho podtřídy. Podtřída je třída dědící atributy a metody (operace, přijímané zprávy) své nadtřídy a reference na její instanci může být vložena do proměnné určené pro referenci na její nadtřídu. Jakákoli podtřída třídy SecurityManager tedy bude vždy přijímat všechny zprávy, které přijimá třída SecurityManager, přičemž ty které nebude sama implementovat, budou přejaty z její nadtřídy SecurityManager.

Security manager který JVM použije při svém startu lze ovlivnit skrze konfigurační proměnnou JVM - `java.security.manager`. Za běhu je možné Security manager vypnout nebo vyměnit voláním System.getSecurityManager(), respektive System.setSecurityManager(). Volání těchto metod ale bývá samo chráněno SecurityManagerem. Aplikace která by se restrikcím daným Security managerem chtěla vzepřít tedy nemůže Security manager vypnout nebo vyměnit jak by jejímu autorovi zlíbilo.

Při startu JVM je možné konfigurační proměnnou `java.security.manager` nastavit za pomoci k tomu určenému parametru `-D`, pro spuštění programu s výchozím Security managerem můžeme tedy
použít příkaz:

`java -Djava.security.manager=default ProgramABC`

Základní možné hodnoty proměnné `java.security.manager` a jim odpovídající třídy objektů security managera popisuje následující tabulka.

+----------------------------------------------------+----------------------------+
| Parametr příkazu `java`                            | Použitý objekt JSM         |
+----------------------------------------------------+----------------------------+
| (nedefinováno)                                     | null                       |
| -Djava.security.manager                            | java.lang.SecurityManager  |
| -Djava.security.manager=default                    | java.lang.SecurityManager  |
| -Djava.security.manager=java.lang.SecurityManager  | java.lang.SecurityManager  |
| -Djava.security.manager=TestovaciSM                | TestovaciSM                |
+----------------------------------------------------+----------------------------+

Poslední řádek demonstruje použití vlastní třídy objektu security managera. Jestliže je uvedena neexistující třída, skončí inicializace JVM vyjímkou a vykonávání programu nebude vůbec zahájeno.

        Error occurred during initialization of VM
        java.lang.InternalError: Could not create SecurityManager: muj.neexistujici.SecurityManager
            at sun.misc.Launcher.<init>(Launcher.java:106)
            at sun.misc.Launcher.<clinit>(Launcher.java:57)
            at java.lang.ClassLoader.initSystemClassLoader(ClassLoader.java:1486)
            at java.lang.ClassLoader.getSystemClassLoader(ClassLoader.java:1468)

Je tedy naplněn bezpečnostní princip, podle kterého musí systém zůstat bezpečný i v případě poruchového stavu, v tomto případě chybné konfigurace security managera, i za cenu nefunkčnosti dané aplikace.

*TODO: příklad vlastního security managera*

## Bezpečnostní politiky ##

Bezpečnostní politika je tedy definována Security managerem. Rozhodování Security manageru ale může být založeno takřka na jakémkoli algoritmu, klidně i v závislosti na konfiguraci načtené ze souboru, čímž se otvírá cesta ke snadnému definování bezpečnostní politiky.

Výchozí Security manager Javy implicitně nepovoluje žádnou operaci, vyjma těch jež jsou v konfiguračním souboru security managera explicitně povoleny. Tento soubor nazýváme souborem bezpečnostní politiky (Policy file).





## Bezpečnost ##

Co je to bezpečnost...

## Java ##

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

Poslední řádek demonstruje použití vlastní třídy objektu security managera. Způsob vytvoření vlastního Security managera bude podrobněji rozebrán v další kapitole. Jestliže je zde uvedena neexistující třída, skončí inicializace JVM vyjímkou a vykonávání programu nebude vůbec zahájeno:

        Error occurred during initialization of VM
        java.lang.InternalError: Could not create SecurityManager: muj.neexistujici.SecurityManager
            at sun.misc.Launcher.<init>(Launcher.java:106)
            at sun.misc.Launcher.<clinit>(Launcher.java:57)
            at java.lang.ClassLoader.initSystemClassLoader(ClassLoader.java:1486)
            at java.lang.ClassLoader.getSystemClassLoader(ClassLoader.java:1468)

Je tedy naplněn bezpečnostní princip, podle kterého musí systém zůstat bezpečný i v případě poruchového stavu, v tomto případě chybné konfigurace security managera, i za cenu nefunkčnosti dané aplikace.

### Implementace vlastního Security managera ###

V této podkapitole bude vysvětlen způsob vytvoření vlastní třídy Security managera za účelem stanovení vlastní bezpečnostní politiky (v širším slova smyslu). Jak se dozvíme později, vytvoření vlastního Security managera není pro stanovení vlastní bezpečnostní politiky nezbytné, umožňuje ale založit rozhodování o povolení nebo nepovolení operace na takřka jakémkoli algoritmu.

`SecurityManager` (resp. `java.lang.SecurityManager`) poskytuje rozhraní skrze které je Java API schopno zjistit, zdali je operace požadovaná uživatelskou aplikací povolena. Algoritmus který Java API používá při provádění potenciálně nebezpečné operace je následující:

1. Uživatelská aplikace žádá Java API o provedení dané operace
2. Java API se dotáže Security manageru zda operaci povolit zavoláním patřičné metody Security manageru.
3. Nemá-li být operace povolena, vyhodí Security manager vyjímku, zpravidla `SecurityException`, která je předána uživatelské aplikaci
4. Není-li vyhozena žádná vyjímka, operace se považuje za povolenou a je provedena.

{Java Security / 4.1.1. Security Managers and the Java API}

Vlastní Security manager tedy vytvoříme rozšířením třídy `SecurityManager`, přičemž přepíšeme metody rozhodující o povolení akce, kterou chceme povolit, nebo pro kterou chceme stanovit vlastní algoritmus určující zda akci povolit.
Protože standardní Security manager implicitně všechny operace zakazuje, stačí nám implementovat metody autorizující provedení operací, které chceme povolit a operacemi které nikdy povolit chtít nebudeme se nebudeme muset vůbec zabývat.

Příklad níže ukazuje jednoduchý Security manager, který o povolení každé akce rozhodne na základě interakce s uživatelem. Při pokusu programu o provedení bezpečnostně-citlivé operace je na standardní výstup tohoto programu zobrazen dotaz, zda tuto operaci uživatel chce povolit. V případě pozitivní odpovědi je operace povolena, jinak je jejímu provedení vyhozením vyjímky `SecurityException` zabráněno.

		public class TestovaciSM extends SecurityManager {
			@Override
			public void checkPermission(Permission perm) {
				
				System.out.println("Povolit "+perm.toString()+" ?");
				if(!askUser()){ // jestliže uživatel nezvolí "ano"
					throw new SecurityException("Operace byla zakázána uživatelem");
				}
				
			}
		}

Tento příklad je samozřejmě pouze demonstrativní, zbytečně obtěžuje uživatele požadováním povolení operací, které jsou nezbytné pro spuštění sebejednodušší aplikace. Po rozšíření o ukládání povolených operací by ale mohl najít praktické uplatnění i podobně jednoduchý Security manager, který by po rozšíření o grafické uživatelské rozhraní mohl fungovat na podobných principech uživatelského rozhraní, na kterých v současnosti fungují interaktivní firewally.

## Zavaděče tříd ##

Zavaděč (classloader) je objekt odpovědný za načítání tříd a rozhraní v Javě. Na základně binárního názvu třídy (tedy názvu používaného v bytekódu, např. `java.lang.String` nebo `java.security.KeyStore$Builder$FileBuilder$1`) se pokusí vyhledat a načíst data třídy daného názvu.

Třída každého zavaděče musí být podtřídou třídy `ClassLoader` a musí implementovat metodu `findClass()`, která provádí právě samotné vyhledání třídy podle názvu. Jejím výstupem je objekt třídy `Class` představující třídu. K vytvoření objektu třídy Class metoda obvykle využívá zděděné metody `defineClass()`, které kromě názvu třídy a bytů samotných dat třídy předává také informace o původu a z něj vyplývajících oprávnění zapouzdřené v objektu tzv. ochrané domény (ProtectionDomain).

Při programování běžných aplikací můžeme na objekty třídy Class narazit například při snaze získat název třídy neznámého objektu jako řetězec za běhu aplikace:

`System.out.println("Proměnná x je typu " + x.getClass().getName());`

Z hlediska bezpečnosti pro nás jsou ale classloadery zajímavé hlavně tím, že právě ony poskytují informaci o původu načtené třídy.

Původ třídy, zmíněný výše jako součást ochranné domény, je stanoven třídou CodeSource, jejíž první částí je URL adresa, ze které byla třída získána, a druhou částí je buď pole certifikátů (Certificate) kterými byla třída podepsána nebo pole podepsaných (CodeSigner).

TODO

{http://docs.oracle.com/javase/7/docs/api/java/lang/ClassLoader.html}
{http://docs.oracle.com/javase/7/docs/api/java/lang/Class.html}
{http://docs.oracle.com/javase/jndi/tutorial/beyond/misc/classloader.html}
{http://www.linuxsoft.cz/article.php?id_article=1123}

## Bezpečnostní kontext ##



## Soubor bezpečnostní politiky ##

Bezpečnostní politika v širším slova smyslu je tedy definována Security managerem a rozhodování Security manageru může být založeno takřka na jakémkoli algoritmu. Tento rozhodovací algoritmus může své rozhodování založit i na datech načtených ze souboru, čímž se otvírá cesta k mnohem snazší způsob definice bezpečnostní politiky, nevyžadující po administrátorech naprogramování vlastního Security managera nebo jiných tříd v Javě.

O standardním Security manageru bylo dosud řečeno jen to, že implicitně nepovoluje žádnou potenciálně nebezpečnou operaci. Přesněji řečeno nepovoluje žádnou operaci, jejíž povolení nebo znemožnění je v kompetenci Security manageru.

Standardní Security manager je ale ve skutečnosti právě takovým Security managerem, jaký jsme si právě popsali. Zdali operaci provést či nikoliv rozhoduje na základě takzvaného souboru bezpečnostní politiky. Tento soubor přiřazuje různým kontextům operace, které je v nich možné provádět. Není-li tedy soubor bezpečnostní politiky nastaven, nepovoluje Security manager žádnou operaci, jak už jsme mohli vidět dříve při jeho použití bez nastavení souboru bezpečnostní politiky. Způsob provedení tohoto nastavení si ukážeme nyní.

Bezpečnostní politika která bude použita může být nastavena podobně jako třída použitého Security manageru, skrze konfigurační proměnnou (property) JVM `java.security.policy`, do které bude uložena adresa souboru s bezpečnostní politikou, kterou by měl standardní Security manager uplatnit.

`java -Djava.security.manager=default -Djava.security.policy=mojePolitika.policy ProgramABC`

{http://docs.oracle.com/javase/7/docs/technotes/guides/security/PolicyFiles.html}







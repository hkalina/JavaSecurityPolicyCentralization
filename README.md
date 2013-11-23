Toto je repozitář pro text a příklady bakalářské práce Java Security Policy Centralization.
Informace o práci naleznete na [Thesis Management System](https://thesis-managementsystem.rhcloud.com/thesis/show/80/java-security-policy-centralization).

## Anotace bakalářské práce ##

Cílem této bakalářské práce je seznámit čtenáře s principy bezpečnostních politik v Javě a možnostmi jejich aplikace, zejména v prostředí aplikačního serveru JBoss, a navrhnout a implementovat systém umožňující centrálně spravovat bezpečnostní politiky nasazené v distribuovaném prostředí JBoss Enterprise Application Platform. Implementovaný systém by měl umožňovat zejména nastavit, zda se má používat správce bezpečnosti (Security Manager) a umožňovat změnu používaných bezpečnostních politik na jednotlivých vituálních strojích Javy (JVM) bez potřeby jejich restartu.

Součástí práce je také návrh a implementace automatizovaných testů umožňujících otestovat funkčnost implementovaného systému a za jejich pomoci porovnat funkčnost na různých implementacích Javy (Oracle Java, IBM Java, OpenJDK).

**Klíčová slova:** JBoss, Java security policy

### Související repozitáře ###
* [honza889 / jsm-policy-subsystem](https://github.com/honza889/jsm-policy-subsystem) - subsystém JBoss/Wildfly
* [honza889 / jsm-policy-console](https://github.com/honza889/jsm-policy-console) - rozšíření JBoss administrační konzole
* [honza889 / jsm-policy-test](https://github.com/honza889/jsm-policy-test) - automatické testy nasazování bezpečnostních polik


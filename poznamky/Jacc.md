https://community.jboss.org/message/809342

In JBoss 7, the service org.jboss.security.jacc.SecurityService (JBoss JACC Integration Service)
installs a java.security.Policy implementation that handles the JACC permission checks. As suggested
in https://community.jboss.org/wiki/JACC, I added a new system property in JBoss command line:
-Djavax.security.jacc.policy.provider=net.jini.security.policy.DynamicPolicyProvider
telling the service org.jboss.security.jacc.SecurityService not to use its default class
org.jboss.security.jacc.DelegatingPolicy.

* [https://docs.jboss.org/author/display/AS7/Class+Loading+in+AS7]
* [https://docs.jboss.org/author/display/MODULES/Module+descriptors]

* [https://community.jboss.org/wiki/ConfiguringAJavaSecurityManager]
* [https://gist.github.com/honza889/8945053]

* [jboss-deployment-structure.xml](https://github.com/honza889/jsm-policy-test/blob/1377fe2d77dc5a70d93cb21df44a2306f4ee8b6e/agent/src/main/webapp/META-INF/jboss-deployment-structure.xml)
* [module.xml](https://github.com/honza889/jsm-policy-subsystem/blob/5530bf4a02373be446e2c014a49986d4349cde86/src/main/resources/module/main/module.xml)

* [Setting of ModulesPolicy (org.jboss.modules.Main)](https://github.com/jboss-modules/jboss-modules/blob/master/src/main/java/org/jboss/modules/Main.java#LC403)

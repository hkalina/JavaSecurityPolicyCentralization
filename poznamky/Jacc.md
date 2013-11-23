https://community.jboss.org/message/809342

In JBoss 7, the service org.jboss.security.jacc.SecurityService (JBoss JACC Integration Service)
installs a java.security.Policy implementation that handles the JACC permission checks. As suggested
in https://community.jboss.org/wiki/JACC, I added a new system property in JBoss command line:
-Djavax.security.jacc.policy.provider=net.jini.security.policy.DynamicPolicyProvider
telling the service org.jboss.security.jacc.SecurityService not to use its default class
org.jboss.security.jacc.DelegatingPolicy.


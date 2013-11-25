
## Konfigurační souboru java.security ##

JVM loads its security configuration, including the JCE providers list, from a master security properties file within the JRE folder (JRE_HOME/lib/security/java.security), the location of that file is fixed in the JVM and cannot be modified.
{http://blog.eyallupu.com/2012/11/how-to-overriding-java-security.html}



security.provider.1=sun.security.provider.Sun # provider kryptografie
policy.provider=java.security.PolicyFile # provider politiky (potomek java.security.Policy)
policy.url.1=file:${java.home}/lib/security/java.policy
policy.url.2=file:${user.home}/.java.policy
policy.expandProperties=true # mohou property mohou obsahovat proměnné ${...}
policy.allowSystemProperty=true # povolit -D ?

cert.provider.x509=sun.security.x509.X509CertImpl
crl.provider.x509=sun.security.x509.X509CRLImpl
keystore.user=${user.home}${/}.keystore
keystore=sun.security.tools.JavaKeyStore

{http://www.javaworld.com/javaworld/jw-08-1998/sandbox/java.security.html}


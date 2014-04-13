
1. Ensure proper installation of WildFly (tested with 8.0.0.Final and version 00ee2871d28548a7bcc2b4e2deb7f0b78ac0007a from repository)

2. Deploy JsmPolicyTestingAgent.war

3. Add following into start script (e.g., standalone.sh)
JAVA_OPTS="$JAVA_OPTS -Djboss.modules.policy-permissions=true"

4. Add WildFly Security Manager extension and subsystem into configuration file of WildFly (e.g., standalone.xml)
        <extension module="org.wildfly.extension.security.manager"/>
        <subsystem xmlns="urn:jboss:domain:security-manager:1.0"/>

5. Install patch of WildFly Security Manager
        make compile install

6. Install JSM Policy Subsystem
        make compile install

7. Add JSM Policy Subsystem into configuration file of WildFly (e.g., standalone.xml)
        <extension module="org.picketbox.jsmpolicy.subsystem"/>
        <subsystem xmlns="urn:org.picketbox.jsmpolicy:1.0"/>


Troubleshooting
===============

* Problem: All permission (or all from policy file) are denied (regardless policy file)
 * Check whether is present param JAVA_OPTS="$JAVA_OPTS -Djboss.modules.policy-permissions=true" in used start script

* Problem: All permission are allowed (regardless policy file)
 * Check if WildFly Security Manager patch is installed correctly (WildFlySecurityManager.CHECKING variable probably is not TRUE)


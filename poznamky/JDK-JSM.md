
## ClassLoader ##

[ClassLoader.java](https://github.com/ReadyTalk/avian/blob/master/classpath/java/lang/ClassLoader.java)



## Default security manager ##

[SecurityManager.java](http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/lang/SecurityManager.java#SecurityManager.checkPermission%28java.security.Permission%29)

* java.lang.SecurityManager().checkPermission(Permission)
* java.security.AccessController.checkPermission(Permission)
* java.security.AccessControlContext.checkPermission(Permission)
* java.security.ProtectionDomain.implies(Permission) (Doména = codesource + principals)
* java.security.Policy.implies(ProtectionDomain, Permission) (abstraktní)
* java.security.PermissionCollection.implies(Permission)

* sun.security.provider.PolicyFile (podtřída Policy)


apacheds-embedded is an embedded LDAP Directory Server using ApacheDS 2. 
It's primary purpose is for integration testing.  As such, it starts up with
a blank slate and cleans up upon shutdown.  This can be disabled, so it's
not limited to the testing use case.

## Usage: Initialization

```
EmbeddedLdapServer embeddedLdapServer = new EmbeddedLdapServer()
embeddedLdapServer.init()
```

## Usage: Shutdown

```
embeddedLdapServer.destroy()
```

## Configuration

The `EmbeddedLdapServer` class has been developed with inheritance in mind
to change behavior.

Common things you may want to change by extending the class and overriding
some methods:

* The base partition name:
  * Override `String getBasePartitionName()`
    * The default is `"mydomain"`
* The base structure (i.e., the base DN of the partition):
  * Override `String getBaseStructure()`
    * The default is `"dc=mydomain,dc=org"`
* The LDAP server port:
  * Override `int getLdapServerPort()`
    * The default is port `10389`.
* The attribute names to index:
  * Override `List<String> getAttrNamesToIndex()`
    * The default is `["uid"]`.
* To disable deleting the ApacheDS working directory upon startup and
shutdown:
  * Override `boolean setDeleteInstanceDirectoryOnStartup()` and `boolean
setDeleteInstanceDirectoryOnShutdown()` to return false
    * The default is true.

ApacheDS also internally utilizes a working directory to build the directory
server.  ApacheDS (as of 2.0.0-M23) uses the `workingDirectory` system
property if it's set and if not, will fall back to using
`System.getProperty( "java.io.tmpdir" ) + "/server-work-" + basePartitionName`.
This code can be seen in
`DefaultDirectoryServiceFactory.buildInstanceDirectory()` in ApacheDS source
code.

To change this location, set the `workingDirectory` system property.

This working directory gets deleted upon startup and shutdown by default to
support testing unless methods in `EmbeddedLdapServer` are overridden, as
described earlier.

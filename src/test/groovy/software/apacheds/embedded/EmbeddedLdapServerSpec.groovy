/*-
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.  The
 * ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package software.apacheds.embedded

import spock.lang.Shared
import spock.lang.Specification

class EmbeddedLdapServerSpec extends Specification {
    @Shared
    EmbeddedLdapServer embeddedLdapServer

    void setupSpec() {
        this.embeddedLdapServer = new EmbeddedLdapServer()
        embeddedLdapServer.init()
    }

    void cleanupSpec() {
        embeddedLdapServer.destroy()
    }

    void "test EmbeddedLdapServer initialization"() {
        expect:
        embeddedLdapServer?.ldapServer
        embeddedLdapServer.directoryService.schemaPartition
        embeddedLdapServer.directoryService.systemPartition
        embeddedLdapServer.directoryService.schemaManager
        embeddedLdapServer.directoryService.dnFactory
        embeddedLdapServer.directoryService.denormalizeOpAttrsEnabled
        !embeddedLdapServer.directoryService.changeLog.enabled
        embeddedLdapServer.directoryService.started
        embeddedLdapServer.ldapServer.started
        embeddedLdapServer.userIndexMap.keySet().sort() == ["uid"].sort()
    }
}

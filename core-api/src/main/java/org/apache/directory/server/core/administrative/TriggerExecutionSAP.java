/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.apache.directory.server.core.administrative;


import org.apache.directory.shared.ldap.name.DN;
import org.apache.directory.shared.ldap.subtree.AdministrativeRole;


/**
 * A class used to create a TriggerExecution SAP
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class TriggerExecutionSAP extends TriggerExecutionAdministrativePoint
{
    /**
     * Create an instance of TriggerExecution SAP
     *
     * @param dn The AdministrativePoint DN
     * @param uuid The AdministrativePoint UUID
     */
    public TriggerExecutionSAP( DN dn, String uuid, long seqNumber )
    {
        super( dn, uuid, AdministrativeRole.TriggerExecutionSpecificArea, seqNumber );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSpecific()
    {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInner()
    {
        return false;
    }


    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        return "TriggerExecution SAP : " + super.toString();
    }
}

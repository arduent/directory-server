/*
 *   Copyright 2004 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.apache.eve.tools.schema;


import org.apache.eve.schema.bootstrap.AbstractBootstrapSchema;


/**
 * A test which tries to generate AttributeType producers for all schemas.
 *
 * @author <a href="mailto:directory-dev@incubator.apache.org">Apache Directory Project</a>
 * @version $Rev$
 */
public class AttributeTypesTemplateTest extends AbstractTestCase
{


    public void testCoreAttributeTypeGeneration() throws Exception
    {
        AbstractBootstrapSchema schema = new AbstractBootstrapSchema(
            "uid=admin,ou=system", "core", "dummy.test",
            new String[] { "dep1", "dep2" }) {};
        generateAttributeTypeProducer( schema );
    }


    public void testJavaAttributeTypeGeneration() throws Exception
    {
        AbstractBootstrapSchema schema = new AbstractBootstrapSchema(
            "uid=admin,ou=system", "java", "dummy.test",
            new String[] { "dep1", "dep2" }) {};
        generateAttributeTypeProducer( schema );
    }


    public void testCorbaAttributeTypeGeneration() throws Exception
    {
        AbstractBootstrapSchema schema = new AbstractBootstrapSchema(
            "uid=admin,ou=system", "corba", "dummy.test",
            new String[] { "dep1", "dep2" }) {};
        generateAttributeTypeProducer( schema );
    }


    public void testCosineAttributeTypeGeneration() throws Exception
    {
        AbstractBootstrapSchema schema = new AbstractBootstrapSchema(
            "uid=admin,ou=system", "cosine", "dummy.test",
            new String[] { "dep1", "dep2" }) {};
        generateAttributeTypeProducer( schema );
    }


    public void testInetorgpersonAttributeTypeGeneration() throws Exception
    {
        AbstractBootstrapSchema schema = new AbstractBootstrapSchema(
            "uid=admin,ou=system", "inetorgperson", "dummy.test",
            new String[] { "dep1", "dep2" }) {};
        generateAttributeTypeProducer( schema );
    }


    public void testMiscAttributeTypeGeneration() throws Exception
    {
        AbstractBootstrapSchema schema = new AbstractBootstrapSchema(
            "uid=admin,ou=system", "misc", "dummy.test",
            new String[] { "dep1", "dep2" }) {};
        generateAttributeTypeProducer( schema );
    }


    public void testNisAttributeTypeGeneration() throws Exception
    {
        AbstractBootstrapSchema schema = new AbstractBootstrapSchema(
            "uid=admin,ou=system", "nis", "dummy.test",
            new String[] { "dep1", "dep2" }) {};
        generateAttributeTypeProducer( schema );
    }
}

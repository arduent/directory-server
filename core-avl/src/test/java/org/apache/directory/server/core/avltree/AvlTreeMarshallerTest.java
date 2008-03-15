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
package org.apache.directory.server.core.avltree;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;


/**
 * TestCase for AvlTreeMarshaller.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 * @version $Rev$, $Date$
 */
public class AvlTreeMarshallerTest
{
    AvlTree<Integer> tree;
    Comparator<Integer> comparator;
    AvlTreeMarshaller<Integer> treeMarshaller;
    
    static AvlTree<Integer> savedTree;
    
    File treeFile = new File( System.getProperty( "java.io.tmpdir" ) + File.separator + "avl.tree");
    
    @Before
    public void createTree()
    {
        comparator = new Comparator<Integer>() 
        {
            public int compare( Integer i1, Integer i2 )
            {
                return i1.compareTo( i2 );
            }
        };
        
      
        tree = new AvlTree<Integer>( comparator );
        treeMarshaller = new AvlTreeMarshaller<Integer>( comparator, new IntegerKeyMarshaller() );
    }


    @Test
    public void testMarshalEmptyTree() throws IOException
    {
        byte[] bites = treeMarshaller.serialize( new AvlTree<Integer>( comparator ) );
        AvlTree<Integer> tree = treeMarshaller.deserialize( bites );
        assertNotNull( tree );
    }


    @Test
    public void testRoundTripEmpty() throws IOException
    {
        AvlTree<Integer> original = new AvlTree<Integer>( comparator );
        byte[] bites = treeMarshaller.serialize( original );
        AvlTree<Integer> deserialized = treeMarshaller.deserialize( bites );
        assertTrue( deserialized.isEmpty() );
    }


    @Test
    public void testRoundTripOneEntry() throws IOException
    {
        AvlTree<Integer> original = new AvlTree<Integer>( comparator );
        original.insert( 0 );
        byte[] bites = treeMarshaller.serialize( original );
        AvlTree<Integer> deserialized = treeMarshaller.deserialize( bites );
        assertFalse( deserialized.isEmpty() );
        assertEquals( 1, deserialized.getSize() );
        assertEquals( 0, ( int ) deserialized.getFirst().getKey() );
    }


    @Test
    public void testRoundTripOneEntryFirstLast() throws IOException
    {
        AvlTree<Integer> original = new AvlTree<Integer>( comparator );
        original.insert( 0 );
        byte[] bites = treeMarshaller.serialize( original );
        AvlTree<Integer> deserialized = treeMarshaller.deserialize( bites );
        assertFalse( deserialized.isEmpty() );
        assertEquals( 1, deserialized.getSize() );
        assertEquals( 0, ( int ) deserialized.getFirst().getKey() );

        assertNotNull( original.getFirst() );
        assertEquals( 0, ( int ) original.getFirst().getKey() );

        assertNotNull( deserialized.getFirst() );
        assertEquals( 0, ( int ) deserialized.getFirst().getKey() );

        assertNotNull( original.getLast() );
        assertEquals( 0, ( int ) original.getLast().getKey() );

        // this marshaller fails to preserve last node reference
        assertNotNull( deserialized.getLast() );
        assertEquals( 0, ( int ) deserialized.getLast().getKey() );
    }


    @Test
    public void testRoundTripTwoEntries() throws IOException
    {
        AvlTree<Integer> original = new AvlTree<Integer>( comparator );
        original.insert( 0 );
        original.insert( 1 );
        byte[] bites = treeMarshaller.serialize( original );
        AvlTree<Integer> deserialized = treeMarshaller.deserialize( bites );
        assertFalse( deserialized.isEmpty() );
        assertEquals( 2, deserialized.getSize() );
        assertEquals( 0, ( int ) deserialized.getFirst().getKey() );
        assertEquals( 1, ( int ) deserialized.getFirst().next.getKey() );
    }


    @Test
    public void testRoundTripTwoEntriesFirstLast() throws IOException
    {
        AvlTree<Integer> original = new AvlTree<Integer>( comparator );
        original.insert( 0 );
        original.insert( 1 );
        byte[] bites = treeMarshaller.serialize( original );
        AvlTree<Integer> deserialized = treeMarshaller.deserialize( bites );
        assertFalse( deserialized.isEmpty() );
        assertEquals( 2, deserialized.getSize() );
        assertEquals( 0, ( int ) deserialized.getFirst().getKey() );
        assertEquals( 1, ( int ) deserialized.getFirst().next.getKey() );

        assertNotNull( original.getFirst() );
        assertEquals( 0, ( int ) original.getFirst().getKey() );

        assertNotNull( deserialized.getFirst() );
        assertEquals( 0, ( int ) deserialized.getFirst().getKey() );

        assertNotNull( original.getLast() );
        assertEquals( 1, ( int ) original.getLast().getKey() );

        // this marshaller fails to preserve last node reference
        assertNotNull( deserialized.getLast() );
        assertEquals( 1, ( int ) deserialized.getLast().getKey() );
    }


    @Test
    public void testRoundTripManyEntries() throws Exception
    {
        AvlTree<Integer> original = new AvlTree<Integer>( comparator );
        for ( int ii = 0; ii < 100; ii++ )
        {
            original.insert( ii );
        }
        byte[] bites = treeMarshaller.serialize( original );
        AvlTree<Integer> deserialized = treeMarshaller.deserialize( bites );
        assertFalse( deserialized.isEmpty() );
        assertEquals( 100, deserialized.getSize() );

        AvlTreeCursor<Integer> cursor = new AvlTreeCursor<Integer>( deserialized );
        cursor.first();
        for ( int ii = 0; ii < 100; ii++ )
        {
            assertEquals( ii, ( int ) cursor.get() );
            cursor.next();
        }
    }


    @Test
    public void testRoundTripManyEntriesFirstLast() throws Exception
    {
        AvlTree<Integer> original = new AvlTree<Integer>( comparator );
        for ( int ii = 0; ii < 100; ii++ )
        {
            original.insert( ii );
        }
        byte[] bites = treeMarshaller.serialize( original );
        AvlTree<Integer> deserialized = treeMarshaller.deserialize( bites );
        assertFalse( deserialized.isEmpty() );
        assertEquals( 100, deserialized.getSize() );

        AvlTreeCursor<Integer> cursor = new AvlTreeCursor<Integer>( deserialized );
        cursor.first();
        for ( int ii = 0; ii < 100; ii++ )
        {
            assertEquals( ii, ( int ) cursor.get() );
            cursor.next();
        }

        assertNotNull( original.getFirst() );
        assertEquals( 0, ( int ) original.getFirst().getKey() );

        assertNotNull( deserialized.getFirst() );
        assertEquals( 0, ( int ) deserialized.getFirst().getKey() );

        assertNotNull( original.getLast() );
        assertEquals( 99, ( int ) original.getLast().getKey() );

        // this marshaller fails to preserve last node reference
        assertNotNull( deserialized.getLast() );
        assertEquals( 99, ( int ) deserialized.getLast().getKey() );
    }


    @Test
    public void testRoundTripManyEntriesDefaultSerialization() throws Exception
    {
        Comparator<Bar> barComparator = new Comparator<Bar>() {
            public int compare( Bar o1, Bar o2 )
            {
                return o1.intValue.compareTo( o2.intValue );
            }
        };

        AvlTree<Bar> original = new AvlTree<Bar>( barComparator );

        for ( int ii = 0; ii < 100; ii++ )
        {
            original.insert( new Bar( ii ) );
        }

        AvlTreeMarshaller<Bar> marshaller = new AvlTreeMarshaller<Bar>( barComparator );
        byte[] bites = marshaller.serialize( original );
        AvlTree<Bar> deserialized = marshaller.deserialize( bites );
        assertFalse( deserialized.isEmpty() );
        assertEquals( 100, deserialized.getSize() );

        AvlTreeCursor<Bar> cursor = new AvlTreeCursor<Bar>( deserialized );
        cursor.first();
        for ( int ii = 0; ii < 100; ii++ )
        {
            assertEquals( ii, ( int ) cursor.get().intValue );
            cursor.next();
        }
    }


    static class Bar implements Serializable
    {
        Integer intValue = 37;
        String stringValue = "bar";
        long longValue = 32L;
        Foo fooValue = new Foo();


        public Bar( int ii )
        {
            intValue = ii;
        }
    }


    static class Foo implements Serializable
    {
        float floatValue = 3;
        String stringValue = "foo";
        double doubleValue = 1.2;
        byte byteValue = 3;
        char charValue = 'a';
    }


    @Test
    public void testMarshal() throws IOException
    {
        tree.insert( 37 );
        tree.insert( 7 );
        tree.insert( 25 );
        tree.insert( 8 );
        tree.insert( 9 );

        FileOutputStream fout = new FileOutputStream( treeFile );
        fout.write( treeMarshaller.serialize( tree ) );
        fout.close();
        
        savedTree = tree; // to reference in other tests
        
        System.out.println("saved tree\n--------");
        tree.printTree();
        
        assertTrue( true );
    }


    @Test
    public void testUnMarshal() throws FileNotFoundException, IOException
    {
        FileInputStream fin = new FileInputStream(treeFile);
        
        byte[] data = new byte[ ( int )treeFile.length() ];
        fin.read( data );
        
        AvlTree<Integer> unmarshalledTree = treeMarshaller.deserialize( data );
        
        System.out.println("\nunmarshalled tree\n---------------");
        unmarshalledTree.printTree();
        
        assertTrue( savedTree.getRoot().getKey() == unmarshalledTree.getRoot().getKey() );

        unmarshalledTree.insert( 6 ); // will change the root as part of balancing
        
        assertTrue( savedTree.getRoot().getKey() == unmarshalledTree.getRoot().getKey() );
        assertTrue( 8 == unmarshalledTree.getRoot().getKey() ); // new root
        
        assertTrue( 37 == unmarshalledTree.getLast().getKey() );
        unmarshalledTree.insert( 99 );
        assertTrue( 99 == unmarshalledTree.getLast().getKey() );

        assertTrue( 6 == unmarshalledTree.getFirst().getKey() );
        
        unmarshalledTree.insert( 0 );
        assertTrue( 0 == unmarshalledTree.getFirst().getKey() );
        
        System.out.println("\nmodified tree after unmarshalling\n---------------");
        unmarshalledTree.printTree();
        
        assertNotNull(unmarshalledTree.getFirst());
        assertNotNull(unmarshalledTree.getLast());
    }
}
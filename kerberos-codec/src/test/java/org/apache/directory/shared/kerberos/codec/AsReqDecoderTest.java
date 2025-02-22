/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.shared.kerberos.codec;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.ByteBuffer;


import org.apache.directory.api.asn1.DecoderException;
import org.apache.directory.api.asn1.EncoderException;
import org.apache.directory.api.asn1.ber.Asn1Container;
import org.apache.directory.api.asn1.ber.Asn1Decoder;
import org.apache.directory.shared.kerberos.codec.asReq.AsReqContainer;
import org.apache.directory.shared.kerberos.messages.AsReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Test the decoder for a AsReq
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class AsReqDecoderTest
{
    /**
     * Test the decoding of a AsReq message
     */
    @Test
    public void testDecodeFullAsReq() throws Exception
    {

        ByteBuffer stream = ByteBuffer.allocate( 0x193 );

        stream.put( new byte[]
            {
                0x6A, ( byte ) 0x82, 0x01, ( byte ) 0x8F,
                0x30, ( byte ) 0x82, 0x01, ( byte ) 0x8B,
                ( byte ) 0xA1, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA2, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA3, 0x20,
                0x30, 0x1E,
                0x30, 0x0D,
                ( byte ) 0xA1, 0x03,
                0x02, 0x01, 01,
                ( byte ) 0xA2, 0x06,
                0x04, 0x04, 'a', 'b', 'c', 'd',
                0x30, 0x0D,
                ( byte ) 0xA1, 0x03,
                0x02, 0x01, 01,
                ( byte ) 0xA2, 0x06,
                0x04, 0x04, 'e', 'f', 'g', 'h',
                ( byte ) 0xA4, ( byte ) 0x82, 0x01, 0x5B,
                0x30, ( byte ) 0x82, 0x01, 0x57,
                ( byte ) 0xA0, 0x07,
                0x03, 0x05,
                0x00, 0x01, 0x04, 0x00, 0x32,
                ( byte ) 0xA1, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                'c', 'l', 'i', 'e', 'n', 't',
                ( byte ) 0xA2, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA3, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                's', 'e', 'r', 'v', 'e', 'r',
                ( byte ) 0xA4, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA5, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA6, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA7, 0x04,
                0x02, 0x02,
                0x30, 0x39,
                ( byte ) 0xA8, 0x0B,
                0x30, 0x09,
                0x02, 0x01, 0x06,
                0x02, 0x01, 0x11,
                0x02, 0x01, 0x12,
                ( byte ) 0xA9, 0x2E,
                0x30, 0x2C,
                0x30, 0x14,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x02,
                ( byte ) 0xA1, 0x0D,
                0x04, 0x0B,
                '1', '9', '2', '.', '1', '6', '8', '.', '0', '.', '1',
                0x30, 0x14,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x02,
                ( byte ) 0xA1, 0x0D,
                0x04, 0x0B,
                '1', '9', '2', '.', '1', '6', '8', '.', '0', '.', '2',
                ( byte ) 0xAA, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f',
                ( byte ) 0xAB, ( byte ) 0x81, ( byte ) 0x83,
                0x30, ( byte ) 0x81, ( byte ) 0x80,
                0x61, 0x3E,
                0x30, 0x3C,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA1, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA2, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x01,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                'c', 'l', 'i', 'e', 'n', 't',
                ( byte ) 0xA3, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f',
                0x61, 0x3E,
                0x30, 0x3C,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA1, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA2, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x01,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                's', 'e', 'r', 'v', 'e', 'r',
                ( byte ) 0xA3, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f'
        } );

        stream.flip();

        // Allocate a AsReq Container
        AsReqContainer asReqContainer = new AsReqContainer( stream );

        // Decode the AsReq PDU
        try
        {
            Asn1Decoder.decode( stream, asReqContainer );
        }
        catch ( DecoderException de )
        {
            fail( de.getMessage() );
        }

        AsReq asReq = asReqContainer.getAsReq();

        assertTrue( asReq instanceof AsReq );

        // Check the encoding
        int length = asReq.computeLength();

        // Check the length
        assertEquals( 0x193, length );

        // Check the encoding
        ByteBuffer encodedPdu = ByteBuffer.allocate( length );

        try
        {
            encodedPdu = asReq.encode( encodedPdu );

            // Check the length
            assertEquals( 0x193, encodedPdu.limit() );
        }
        catch ( EncoderException ee )
        {
            fail();
        }
    }


    /**
     * Test the decoding of a AsReq message with a bad MsgType
     */
    @Test
    public void testDecodeFullAsReqBadMsgType() throws Exception
    {

        ByteBuffer stream = ByteBuffer.allocate( 0x193 );

        stream.put( new byte[]
            {
                0x6A, ( byte ) 0x82, 0x01, ( byte ) 0x8F,
                0x30, ( byte ) 0x82, 0x01, ( byte ) 0x8B,
                ( byte ) 0xA1, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA2, 0x03,
                0x02, 0x01, 0x0C,
                ( byte ) 0xA3, 0x20,
                0x30, 0x1E,
                0x30, 0x0D,
                ( byte ) 0xA1, 0x03,
                0x02, 0x01, 01,
                ( byte ) 0xA2, 0x06,
                0x04, 0x04, 'a', 'b', 'c', 'd',
                0x30, 0x0D,
                ( byte ) 0xA1, 0x03,
                0x02, 0x01, 01,
                ( byte ) 0xA2, 0x06,
                0x04, 0x04, 'e', 'f', 'g', 'h',
                ( byte ) 0xA4, ( byte ) 0x82, 0x01, 0x5B,
                0x30, ( byte ) 0x82, 0x01, 0x57,
                ( byte ) 0xA0, 0x07,
                0x03, 0x05,
                0x00, 0x01, 0x04, 0x00, 0x32,
                ( byte ) 0xA1, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                'c', 'l', 'i', 'e', 'n', 't',
                ( byte ) 0xA2, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA3, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                's', 'e', 'r', 'v', 'e', 'r',
                ( byte ) 0xA4, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA5, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA6, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA7, 0x04,
                0x02, 0x02,
                0x30, 0x39,
                ( byte ) 0xA8, 0x0B,
                0x30, 0x09,
                0x02, 0x01, 0x06,
                0x02, 0x01, 0x11,
                0x02, 0x01, 0x12,
                ( byte ) 0xA9, 0x2E,
                0x30, 0x2C,
                0x30, 0x14,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x02,
                ( byte ) 0xA1, 0x0D,
                0x04, 0x0B,
                '1', '9', '2', '.', '1', '6', '8', '.', '0', '.', '1',
                0x30, 0x14,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x02,
                ( byte ) 0xA1, 0x0D,
                0x04, 0x0B,
                '1', '9', '2', '.', '1', '6', '8', '.', '0', '.', '2',
                ( byte ) 0xAA, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f',
                ( byte ) 0xAB, ( byte ) 0x81, ( byte ) 0x83,
                0x30, ( byte ) 0x81, ( byte ) 0x80,
                0x61, 0x3E,
                0x30, 0x3C,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA1, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA2, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x01,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                'c', 'l', 'i', 'e', 'n', 't',
                ( byte ) 0xA3, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f',
                0x61, 0x3E,
                0x30, 0x3C,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA1, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA2, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x01,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                's', 'e', 'r', 'v', 'e', 'r',
                ( byte ) 0xA3, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f'
        } );

        stream.flip();

        // Allocate a AsReq Container
        AsReqContainer asReqContainer = new AsReqContainer( stream );

        // Decode the AsReq PDU
        Assertions.assertThrows( DecoderException.class, () -> {
            Asn1Decoder.decode(stream, asReqContainer);
        } );
    }


    /**
     * Test the decoding of a AS-REQ with nothing in it
     */
    @Test
    public void testAsReqEmpty() throws DecoderException
    {

        ByteBuffer stream = ByteBuffer.allocate( 0x02 );

        stream.put( new byte[]
            { 0x6A, 0x00 } );

        stream.flip();

        // Allocate a AS-REQ Container
        Asn1Container asReqContainer = new AsReqContainer( stream );

        // Decode the AS-REQ PDU
        Assertions.assertThrows( DecoderException.class, () -> {
            Asn1Decoder.decode(stream, asReqContainer);
        } );
    }


    /**
     * Test the decoding of a AsReq message without PA-DATA
     */
    @Test
    public void testDecodeAsReqNoPaData() throws Exception
    {

        ByteBuffer stream = ByteBuffer.allocate( 0x171 );

        stream.put( new byte[]
            {
                0x6A, ( byte ) 0x82, 0x01, ( byte ) 0x6D,
                0x30, ( byte ) 0x82, 0x01, ( byte ) 0x69,
                ( byte ) 0xA1, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA2, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA4, ( byte ) 0x82, 0x01, 0x5B,
                0x30, ( byte ) 0x82, 0x01, 0x57,
                ( byte ) 0xA0, 0x07,
                0x03, 0x05,
                0x00, 0x01, 0x04, 0x00, 0x32,
                ( byte ) 0xA1, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                'c', 'l', 'i', 'e', 'n', 't',
                ( byte ) 0xA2, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA3, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x0A,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                's', 'e', 'r', 'v', 'e', 'r',
                ( byte ) 0xA4, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA5, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA6, 0x11,
                0x18, 0x0F,
                '2', '0', '1', '0', '1', '1', '1', '0', '1', '5', '4', '5', '2', '5', 'Z',
                ( byte ) 0xA7, 0x04,
                0x02, 0x02,
                0x30, 0x39,
                ( byte ) 0xA8, 0x0B,
                0x30, 0x09,
                0x02, 0x01, 0x06,
                0x02, 0x01, 0x11,
                0x02, 0x01, 0x12,
                ( byte ) 0xA9, 0x2E,
                0x30, 0x2C,
                0x30, 0x14,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x02,
                ( byte ) 0xA1, 0x0D,
                0x04, 0x0B,
                '1', '9', '2', '.', '1', '6', '8', '.', '0', '.', '1',
                0x30, 0x14,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x02,
                ( byte ) 0xA1, 0x0D,
                0x04, 0x0B,
                '1', '9', '2', '.', '1', '6', '8', '.', '0', '.', '2',
                ( byte ) 0xAA, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f',
                ( byte ) 0xAB, ( byte ) 0x81, ( byte ) 0x83,
                0x30, ( byte ) 0x81, ( byte ) 0x80,
                0x61, 0x3E,
                0x30, 0x3C,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA1, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA2, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x01,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                'c', 'l', 'i', 'e', 'n', 't',
                ( byte ) 0xA3, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f',
                0x61, 0x3E,
                0x30, 0x3C,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x05,
                ( byte ) 0xA1, 0x0D,
                0x1B, 0x0B,
                'E', 'X', 'A', 'M', 'P', 'L', 'E', '.', 'C', 'O', 'M',
                ( byte ) 0xA2, 0x13,
                0x30, 0x11,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x01,
                ( byte ) 0xA1, 0x0A,
                0x30, 0x08,
                0x1B, 0x06,
                's', 'e', 'r', 'v', 'e', 'r',
                ( byte ) 0xA3, 0x11,
                0x30, 0x0F,
                ( byte ) 0xA0, 0x03,
                0x02, 0x01, 0x11,
                ( byte ) 0xA2, 0x08,
                0x04, 0x06,
                'a', 'b', 'c', 'd', 'e', 'f'
        } );
        stream.flip();

        // Allocate a AsReq Container
        AsReqContainer asReqContainer = new AsReqContainer( stream );

        // Decode the AsReq PDU
        try
        {
            Asn1Decoder.decode( stream, asReqContainer );
        }
        catch ( DecoderException de )
        {
            fail( de.getMessage() );
        }

        AsReq asReq = asReqContainer.getAsReq();

        assertTrue( asReq instanceof AsReq );

        // Check the encoding
        int length = asReq.computeLength();

        // Check the length
        assertEquals( 0x171, length );

        // Check the encoding
        ByteBuffer encodedPdu = ByteBuffer.allocate( length );

        try
        {
            encodedPdu = asReq.encode( encodedPdu );

            // Check the length
            assertEquals( 0x171, encodedPdu.limit() );

            //assertEquals( decodedPdu, StringTools.dumpBytes( encodedPdu.array() ) );
        }
        catch ( EncoderException ee )
        {
            fail();
        }
    }
}

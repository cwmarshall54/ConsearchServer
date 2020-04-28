package com.consearch.restservice.parsers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.consearch.restservice.parsers.StubHubEventParser;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubHubEventParserTest {

    private StubHubEventParser stubHubEventParser;

    @BeforeAll
    private void setup() {
        stubHubEventParser = new StubHubEventParser();
    }

    @Test
    private void getSampleResponse() throws IOException {
        FileChannel fc = new FileOutputStream("").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(2048);

        stubHubEventParser.parseResponse(buff);
        assertEquals(1, 1);

//        fc.read(buff);
//        buff.flip();
//        System.out.println("lkaghlskdfkahsdlfsk");
//        return buff.asCharBuffer();
    }
}

package com.codzs.utility;

import com.codzs.utility.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {
    @Test
    public void testRenameFile_forNullFileName() {
        String fileName = null;
        String result = FileUtils.renameFile(fileName);
        assertEquals(fileName, result);
    }

    @Test
    public void testRenameFile_forEmptyFileName() {
        String fileName = "";
        String result = FileUtils.renameFile(fileName);
        assertEquals(fileName, result);
    }

    @Test
    public void testRenameFile_forValidFileName() {
        String fileName = "test.txt";
        String result = FileUtils.renameFile(fileName);
        assertTrue(result.contains("test-"));
        assertTrue(result.contains(".txt"));
    }

    @Test
    public void testRenameFile_forValidFileNameWithTwoDots() {
        String fileName = "test.txt.txt";
        String result = FileUtils.renameFile(fileName);
        assertTrue(result.contains("test.txt-"));
        assertTrue(result.contains(".txt"));
    }

    @Test
    public void testEncode_forNullFile() {
        File file = null;
        String result = FileUtils.encode(file);
        assertNull(result);
    }

    @Test
    public void testEncode_forEmptyFile() {
        File file = new File("");
        String result = FileUtils.encode(file);
        assertNull(result);
    }

    @Test
    public void testEncode_forValidFile() {
        File file = new File("src/test/java/com/codzs/utility/sample/User.java");
        String result = FileUtils.encode(file);
        assertEquals("cGFja2FnZSBjb20uY29kenMudXRpbGl0eS5zYW1wbGU7CgppbXBvcnQgamF2YS51dGlsLk1hcDsKCnB1YmxpYyBjbGFzcyBVc2VyIHsKICAgIHB1YmxpYyBTdHJpbmcgbmFtZTsKICAgIHB1YmxpYyBpbnQgYWdlOwogICAgcHVibGljIE1hcDxTdHJpbmcsIE9iamVjdD4gYWRkcmVzczsKfQo=", result);
    }

    @Test
    public void testEecode_forNullEncodedString() {
        String encodedString = null;
        String result = FileUtils.encode(encodedString);
        assertNull(result);
    }

    @Test
    public void testEecode_forEmptyEncodedString() {
        String encodedString = "";
        String result = FileUtils.encode(encodedString);
        assertNull(result);
    }

    @Test
    public void testEecode_forValidEncodedString() {
        String fileName = "src/test/java/com/codzs/utility/sample/User.java";
        String result = FileUtils.encode(fileName);
        assertEquals("cGFja2FnZSBjb20uY29kenMudXRpbGl0eS5zYW1wbGU7CgppbXBvcnQgamF2YS51dGlsLk1hcDsKCnB1YmxpYyBjbGFzcyBVc2VyIHsKICAgIHB1YmxpYyBTdHJpbmcgbmFtZTsKICAgIHB1YmxpYyBpbnQgYWdlOwogICAgcHVibGljIE1hcDxTdHJpbmcsIE9iamVjdD4gYWRkcmVzczsKfQo=", result);
    }

    //test cases for File decode(final String encodedString, final String fileName)
    @Test
    public void testDecode_forNullEncodedString() {
        String encodedString = null;
        String fileName = "test.txt";
        File result = FileUtils.decode(encodedString, fileName);
        assertNotNull(result);
    }

    @Test
    public void testDecode_forEmptyEncodedString() {
        String encodedString = "";
        String fileName = "test.txt";
        File result = FileUtils.decode(encodedString, fileName);
        assertEquals("test.txt", result.toString());
    }

    @Test
    public void testDecode_forNullFileName() {
        String encodedString = "cGFja2FnZSBjb20uY29kenMudXRpbGl0eS5zYW1wbGU7CgppbXBvcnQgamF2YS51dGlsLk1hcDsKCnB1YmxpYyBjbGFzcyBVc2VyIHsKICAgIHB1YmxpYyBTdHJpbmcgbmFtZTsKICAgIHB1YmxpYyBpbnQgYWdlOwogICAgcHVibGljIE1hcDxTdHJpbmcsIE9iamVjdD4gYWRkcmVzczsKfQo=";
        String fileName = null;
        File result = FileUtils.decode(encodedString, fileName);
        assertNull(result);
    }

    @Test
    public void testDecode_forEmptyFileName() {
        String encodedString = "cGFja2FnZSBjb20uY29kenMudXRpbGl0eS5zYW1wbGU7CgppbXBvcnQgamF2YS51dGlsLk1hcDsKCnB1YmxpYyBjbGFzcyBVc2VyIHsKICAgIHB1YmxpYyBTdHJpbmcgbmFtZTsKICAgIHB1YmxpYyBpbnQgYWdlOwogICAgcHVibGljIE1hcDxTdHJpbmcsIE9iamVjdD4gYWRkcmVzczsKfQo=";
        String fileName = "";
        File result = FileUtils.decode(encodedString, fileName);
        assertNull(result);
    }

    @Test
    public void testDecode_forValidEncodedString() {
        String encodedString = "cGFja2FnZSBjb20uY29kenMudXRpbGl0eS5zYW1wbGU7CgppbXBvcnQgamF2YS51dGlsLk1hcDsKCnB1YmxpYyBjbGFzcyBVc2VyIHsKICAgIHB1YmxpYyBTdHJpbmcgbmFtZTsKICAgIHB1YmxpYyBpbnQgYWdlOwogICAgcHVibGljIE1hcDxTdHJpbmcsIE9iamVjdD4gYWRkcmVzczsKfQo=";
        String fileName = "test.txt";
        File result = FileUtils.decode(encodedString, fileName);
        assertEquals("test.txt", result.toString());
    }

    @Test
    public void testDecode_forValidEncodedStringWithTwoDots() {
        String encodedString = "cGFja2FnZSBjb20uY29kenMudXRpbGl0eS5zYW1wbGU7CgppbXBvcnQgamF2YS51dGlsLk1hcDsKCnB1YmxpYyBjbGFzcyBVc2VyIHsKICAgIHB1YmxpYyBTdHJpbmcgbmFtZTsKICAgIHB1YmxpYyBpbnQgYWdlOwogICAgcHVibGljIE1hcDxTdHJpbmcsIE9iamVjdD4gYWRkcmVzczsKfQo=";
        String fileName = "test.txt.txt";
        File result = FileUtils.decode(encodedString, fileName);
        assertEquals("test.txt.txt", result.toString());
    }

    @Test
    public void testGetFileName_forNullFileName() {
        String fileName = null;
        String result = FileUtils.getFileName(fileName);
        assertNull(result);
    }

    @Test
    public void testGetFileName_forEmptyFileName() {
        String fileName = "";
        String result = FileUtils.getFileName(fileName);
        assertEquals("", result);
    }

    @Test
    public void testGetFileName_forValidFileName() {
        String fileName = "test.txt";
        String result = FileUtils.getFileName(fileName);
        assertEquals("test", result);
    }

    @Test
    public void testGetFileName_forValidFileNameWithTwoDots() {
        String fileName = "test.txt.txt";
        String result = FileUtils.getFileName(fileName);
        assertEquals("test.txt", result);
    }

    // test case for String getExtension(String fileName)
    @Test
    public void testGetExtension_forNullFileName() {
        String fileName = null;
        String result = FileUtils.getExtension(fileName);
        assertNull(result);
    }

    @Test
    public void testGetExtension_forEmptyFileName() {
        String fileName = "";
        String result = FileUtils.getExtension(fileName);
        assertEquals("", result);
    }

    @Test
    public void testGetExtension_forValidFileName() {
        String fileName = "test.txt";
        String result = FileUtils.getExtension(fileName);
        assertEquals("txt", result);
    }

    @Test
    public void testGetExtension_forValidFileNameWithTwoDots() {
        String fileName = "test.txt.txt";
        String result = FileUtils.getExtension(fileName);
        assertEquals("txt", result);
    }
}

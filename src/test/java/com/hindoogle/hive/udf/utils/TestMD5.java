package com.hindoogle.hive.udf.utils;

import org.junit.Before;
import org.junit.Test;
import org.apache.hadoop.io.Text;

import static org.junit.Assert.*;
import static com.hindoogle.hive.udf.utils.MD5.*;


public class TestMD5 {
    private MD5 md5 = null;

    @Before
    public void onSetup() {
        md5 = new MD5();
    }

    @Test
    public void testBasic() {
        Text [] args = {new Text("Arg1"), new Text("Arg2"), new Text("Arg3")};
        Text md5 = this.md5.evaluate(args);
        assertNotNull("Null hash was returned", md5);
        assertEquals("hash was not the right length", 30, md5.toString().length());
        //it is a one-way hashing function that should always return the same hash given the same args
        assertEquals("Hash wasn't correct", new Text("b5e35dc0ef9012942809d0906e1efc"), md5);
    }

    @Test
    public void testMoreArgs() {
        Text [] args = {new Text("Arg1"), new Text("Arg2"), new Text("Arg3")};
        Text md5first = this.md5.evaluate(args);

        args[2] = new Text("Arg4");
        Text md5second = this.md5.evaluate(args);
        assertNotSame("changed arguments, but md5 came out the same!", md5first, md5second);
    }

    @Test
    public void testNoArgs() {
        Text [] args = {};
        Text msg = this.md5.evaluate(args);
        assertEquals("should have had an error", NOT_ENOUGH_ARGS_ERROR, msg.toString());
    }
}
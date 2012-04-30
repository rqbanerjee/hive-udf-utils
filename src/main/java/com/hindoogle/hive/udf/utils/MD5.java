package com.hindoogle.hive.udf.utils;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Generates an MD5 Hash from the Text arguments passed in
 *
 * @author rajat
 */
public final class MD5 extends UDF {
    public static final String NOT_ENOUGH_ARGS_ERROR = "Not enough arguments supplied";
    public static final String INVALID_ALGORITHM_ERROR = "Invalid algorithm supplied";

    private final String HASH_ALGORITHM = "MD5";

    public Text evaluate(final org.apache.hadoop.io.Text... args) {
        if (args.length < 1) {
            return new Text(NOT_ENOUGH_ARGS_ERROR);
        }
        Text md5;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.reset();

            for (Text txt : args) {
                messageDigest.update(txt.getBytes());
            }
            md5 = bytesToText(messageDigest.digest());
        }
        catch (NoSuchAlgorithmException nsae) {
            return new Text(INVALID_ALGORITHM_ERROR);
        }
        catch (Exception e) {
            return new Text(String.format("Could not create hash: %s", e.getMessage()));
        }
        return md5;
    }

    private Text bytesToText(byte[] input) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            hexString.append(Integer.toHexString(0xFF & input[i]));
        }

        hexString.append("");
        return new Text(hexString.toString());
    }
}

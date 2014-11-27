package com.dempe.analysis.core.filequeue;

import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Comparator;


public class Bytes {
    protected static final Logger LOG = Logger.getLogger(Bytes.class.getName());

    public static final int SIZEOF_BOOLEAN = 1;
    public static final int SIZEOF_BYTE = 1;
    public static final int SIZEOF_CHAR = 2;
    public static final int SIZEOF_DOUBLE = 8;
    public static final int SIZEOF_FLOAT = 4;
    public static final int SIZEOF_INT = 4;
    public static final int SIZEOF_LONG = 8;
    public static final int SIZEOF_SHORT = 2;
    public static final int ESTIMATED_HEAP_TAX = 16;

    public static byte[] readByteArray(DataInput in, int len) throws IOException {
        if (len < 0) {
            throw new NegativeArraySizeException(Integer.toString(len));
        }
        byte[] result = new byte[len];
        in.readFully(result, 0, len);
        return result;
    }

    public static int putBytes(byte[] tgtBytes, int tgtOffset, byte[] srcBytes, int srcOffset, int srcLength) {
        System.arraycopy(srcBytes, srcOffset, tgtBytes, tgtOffset, srcLength);
        return tgtOffset + srcLength;
    }

    public static int putByte(byte[] bytes, int offset, byte b) {
        bytes[offset] = b;
        return offset + 1;
    }

    public static byte[] toBytes(ByteBuffer bb) {
        int length = bb.limit();
        byte[] result = new byte[length];
        System.arraycopy(bb.array(), bb.arrayOffset(), result, 0, length);
        return result;
    }

    public static String toString(byte[] b) {
        if (b == null) {
            return null;
        }
        return toString(b, 0, b.length);
    }

    public static String toString(byte[] b1, String sep, byte[] b2) {
        return new StringBuilder().append(toString(b1, 0, b1.length)).append(sep).append(toString(b2, 0, b2.length)).toString();
    }

    public static String toString(byte[] b, int off, int len) {
        if (b == null) {
            return null;
        }
        if (len == 0) {
            return "";
        }
        String result = null;
        try {
            result = new String(b, off, len, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String toStringBinary(byte[] b) {
        return toStringBinary(b, 0, b.length);
    }

    public static String toStringBinary(byte[] b, int off, int len) {
        StringBuilder result = new StringBuilder();
        try {
            String first = new String(b, off, len, "ISO-8859-1");
            for (int i = 0; i < first.length(); i++) {
                int ch = first.charAt(i) & 0xFF;
                if (((ch >= 48) && (ch <= 57)) || ((ch >= 65) && (ch <= 90)) || ((ch >= 97) && (ch <= 122)) || (" `~!@#$%^&*()-_=+[]{}\\|;:'\",.<>/?".indexOf(ch) >= 0)) {
                    result.append(first.charAt(i));
                } else
                    result.append(String.format("\\x%02X", new Object[]{Integer.valueOf(ch)}));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private static boolean isHexDigit(char c) {
        return ((c >= 'A') && (c <= 'F')) || ((c >= '0') && (c <= '9'));
    }

    public static byte toBinaryFromHex(byte ch) {
        if ((ch >= 65) && (ch <= 70)) {
            return (byte) (10 + (byte) (ch - 65));
        }
        return (byte) (ch - 48);
    }

    public static byte[] toBytesBinary(String in) {
        byte[] b = new byte[in.length()];
        int size = 0;
        for (int i = 0; i < in.length(); i++) {
            char ch = in.charAt(i);
            if (ch == '\\') {
                char next = in.charAt(i + 1);
                if (next != 'x') {
                    b[(size++)] = (byte) ch;
                } else {
                    char hd1 = in.charAt(i + 2);
                    char hd2 = in.charAt(i + 3);

                    if ((!isHexDigit(hd1)) || (!isHexDigit(hd2))) {
                        continue;
                    }

                    byte d = (byte) ((toBinaryFromHex((byte) hd1) << 4) + toBinaryFromHex((byte) hd2));

                    b[(size++)] = d;
                    i += 3;
                }
            } else {
                b[(size++)] = (byte) ch;
            }
        }

        byte[] b2 = new byte[size];
        System.arraycopy(b, 0, b2, 0, size);
        return b2;
    }

    /**
     * public static int putInt(byte[] bytes, int offset, int val) {
     * if ((bytes == null) || (bytes.length - offset < 4)) {
     * return offset;
     * }
     * for (int i = offset + 3; i > offset; i--) {
     * bytes[i] = (byte) val;
     * val >>>= 8;
     * }
     * bytes[offset] = (byte) val;
     * return offset + 4;
     * } *
     */

    public static int putInt(byte[] buffer, int offset, int value) {
        buffer[offset] = (byte) (value >> 24);
        buffer[offset + 1] = (byte) (value >> 16);
        buffer[offset + 2] = (byte) (value >> 8);
        buffer[offset + 3] = (byte) value;
        return offset + 4;
    }

    public static void putInts(byte[] buffer, int... values) {
        int offset = 0;
        for (int value : values) {
            putInt(buffer, offset, value);
            offset += 4;
        }
    }

    public static int toInt(byte[] buffer, int offset) {
        return ((buffer[offset] & 0xff) << 24) + ((buffer[offset + 1] & 0xff) << 16) + ((buffer[offset + 2] & 0xff) << 8) + (buffer[offset + 3] & 0xff);
    }

    public static byte[] toBytes(String s) {
        if (s == null) {
            throw new IllegalArgumentException("string cannot be null");
        }
        byte[] result = null;
        try {
            result = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] toBytes(boolean b) {
        byte[] bb = new byte[1];
        bb[0] = (byte) (b ? -1 : 0);
        return bb;
    }

    public static boolean toBoolean(byte[] b) {
        if ((b == null) || (b.length > 1)) {
            throw new IllegalArgumentException("Array is wrong size");
        }
        return b[0] != 0;
    }

    public static byte[] toBytes(long val) {
        byte[] b = new byte[8];
        for (int i = 7; i > 0; i--) {
            b[i] = (byte) (int) val;
            val >>>= 8;
        }
        b[0] = (byte) (int) val;
        return b;
    }

    public static long toLong(byte[] bytes) {
        return toLong(bytes, 0);
    }

    public static long toLong(byte[] bytes, int offset) {
        return toLong(bytes, offset, 8);
    }

    public static long toLong(byte[] bytes, int offset, int length) {
        if ((bytes == null) || (length != 8) || (offset + length > bytes.length)) {
            return -1L;
        }
        long l = 0L;
        for (int i = offset; i < offset + length; i++) {
            l <<= 8;
            l ^= bytes[i] & 0xFF;
        }
        return l;
    }

    public static int putLong(byte[] bytes, int offset, long val) {
        if ((bytes == null) || (bytes.length - offset < 8)) {
            return offset;
        }
        for (int i = offset + 7; i > offset; i--) {
            bytes[i] = (byte) (int) val;
            val >>>= 8;
        }
        bytes[offset] = (byte) (int) val;
        return offset + 8;
    }

    public static float toFloat(byte[] bytes) {
        return toFloat(bytes, 0);
    }

    public static float toFloat(byte[] bytes, int offset) {
        int i = toInt(bytes, offset);
        return Float.intBitsToFloat(i);
    }

    public static int putFloat(byte[] bytes, int offset, float f) {
        int i = Float.floatToRawIntBits(f);
        return putInt(bytes, offset, i);
    }

    public static byte[] toBytes(float f) {
        int i = Float.floatToRawIntBits(f);
        return toBytes(i);
    }

    public static double toDouble(byte[] bytes) {
        return toDouble(bytes, 0);
    }

    public static double toDouble(byte[] bytes, int offset) {
        long l = toLong(bytes, offset);
        return Double.longBitsToDouble(l);
    }

    public static int putDouble(byte[] bytes, int offset, double d) {
        long l = Double.doubleToLongBits(d);
        return putLong(bytes, offset, l);
    }

    public static byte[] toBytes(double d) {
        long l = Double.doubleToRawLongBits(d);
        return toBytes(l);
    }

    public static byte[] toBytes(int val) {
        byte[] b = new byte[4];
        for (int i = 3; i > 0; i--) {
            b[i] = (byte) val;
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }

    public static int toInt(byte[] bytes) {
        return toInt(bytes, 0);
    }


    public static int toInt(byte[] bytes, int offset, int length) {
        if ((bytes == null) || (length != 4) || (offset + length > bytes.length)) {
            return -1;
        }
        int n = 0;
        for (int i = offset; i < offset + length; i++) {
            n <<= 8;
            n ^= bytes[i] & 0xFF;
        }
        return n;
    }


    public static byte[] toBytes(short val) {
        byte[] b = new byte[2];
        b[1] = (byte) val;
        val = (short) (val >> 8);
        b[0] = (byte) val;
        return b;
    }

    public static short toShort(byte[] bytes) {
        return toShort(bytes, 0);
    }

    public static short toShort(byte[] bytes, int offset) {
        return toShort(bytes, offset, 2);
    }

    public static short toShort(byte[] bytes, int offset, int length) {
        if ((bytes == null) || (length != 2) || (offset + length > bytes.length)) {
            return -1;
        }
        short n = 0;
        n = (short) (n ^ bytes[offset] & 0xFF);
        n = (short) (n << 8);
        n = (short) (n ^ bytes[(offset + 1)] & 0xFF);
        return n;
    }

    public static int putShort(byte[] bytes, int offset, short val) {
        if ((bytes == null) || (bytes.length - offset < 2)) {
            return offset;
        }
        bytes[(offset + 1)] = (byte) val;
        val = (short) (val >> 8);
        bytes[offset] = (byte) val;
        return offset + 2;
    }

    public static byte[] toBytes(char val) {
        byte[] b = new byte[2];
        b[1] = (byte) val;
        val = (char) (val >> '\b');
        b[0] = (byte) val;
        return b;
    }

    public static char toChar(byte[] bytes) {
        return toChar(bytes, 0);
    }

    public static char toChar(byte[] bytes, int offset) {
        return toChar(bytes, offset, 2);
    }

    public static char toChar(byte[] bytes, int offset, int length) {
        if ((bytes == null) || (length != 2) || (offset + length > bytes.length)) {
            return 65535;
        }
        char n = '\000';
        n = (char) (n ^ bytes[offset] & 0xFF);
        n = (char) (n << '\b');
        n = (char) (n ^ bytes[(offset + 1)] & 0xFF);
        return n;
    }

    public static int putChar(byte[] bytes, int offset, char val) {
        if ((bytes == null) || (bytes.length - offset < 2)) {
            return offset;
        }
        bytes[(offset + 1)] = (byte) val;
        val = (char) (val >> '\b');
        bytes[offset] = (byte) val;
        return offset + 2;
    }

    public static byte[] toBytes(char[] val) {
        byte[] bytes = new byte[val.length * 2];
        putChars(bytes, 0, val);
        return bytes;
    }

    public static char[] toChars(byte[] bytes) {
        return toChars(bytes, 0, bytes.length);
    }

    public static char[] toChars(byte[] bytes, int offset) {
        return toChars(bytes, offset, bytes.length - offset);
    }

    public static char[] toChars(byte[] bytes, int offset, int length) {
        int max = offset + length;
        if ((bytes == null) || (max > bytes.length) || (length % 2 == 1)) {
            return null;
        }

        char[] chars = new char[length / 2];
        int i = 0;
        for (int j = offset; (i < chars.length) && (j < max); j += 2) {
            char c = '\000';
            c = (char) (c ^ bytes[j] & 0xFF);
            c = (char) (c << '\b');
            c = (char) (c ^ bytes[(j + 1)] & 0xFF);
            chars[i] = c;

            i++;
        }

        return chars;
    }

    public static int putChars(byte[] bytes, int offset, char[] val) {
        int max = val.length * 2 + offset;
        if ((bytes == null) || (bytes.length < max)) {
            return offset;
        }
        int i = 0;
        for (int j = offset; (i < val.length) && (j < max); j += 2) {
            char c = val[i];
            bytes[(j + 1)] = (byte) c;
            bytes[j] = (byte) (c >>> '\b');

            i++;
        }

        return offset + 2;
    }

    public static byte[] toBytes(Serializable object) {
        ByteArrayOutputStream byteOut = null;
        ObjectOutputStream objOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(object);
            return byteOut.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("the object not implement Serializable interface.");
        } finally {
            if (byteOut != null)
                try {
                    byteOut.close();
                } catch (IOException e) {
                    LOG.warn("error closing ByteArrayOutputStream", e);
                }

            if (objOut != null)
                try {
                    objOut.close();
                } catch (IOException e) {
                    LOG.warn("error closing ObjectOutputStream", e);
                }
        }
    }

    public static Object toObject(byte[] bytes) throws ClassNotFoundException {
        ByteArrayInputStream byteIn = null;
        ObjectInputStream objIn = null;
        try {
            byteIn = new ByteArrayInputStream(bytes);
            objIn = new ObjectInputStream(byteIn);
            Object result = objIn.readObject();
            return result;
        } catch (IOException e) {
            throw new IllegalArgumentException("the byte array not convert to object");
        } finally {
            if (byteIn != null)
                try {
                    byteIn.close();
                } catch (IOException e) {
                    LOG.warn("error closing ByteArrayInputStream", e);
                }

            if (objIn != null)
                try {
                    objIn.close();
                } catch (IOException e) {
                    LOG.warn("error closing ObjectInputStream", e);
                }
        }
    }

    public static byte[] toBytes(BigDecimal val) {
        byte[] valueBytes = val.unscaledValue().toByteArray();
        byte[] result = new byte[valueBytes.length + 4];
        int offset = putInt(result, 0, val.scale());
        putBytes(result, offset, valueBytes, 0, valueBytes.length);
        return result;
    }

    public static BigDecimal toBigDecimal(byte[] bytes) {
        return toBigDecimal(bytes, 0, bytes.length);
    }

    public static BigDecimal toBigDecimal(byte[] bytes, int offset) {
        return toBigDecimal(bytes, offset, bytes.length);
    }

    public static BigDecimal toBigDecimal(byte[] bytes, int offset, int length) {
        if ((bytes == null) || (length < 5) || (offset + length > bytes.length)) {
            return null;
        }

        int scale = toInt(bytes, 0);
        byte[] tcBytes = new byte[length - 4];
        System.arraycopy(bytes, 4, tcBytes, 0, length - 4);
        return new BigDecimal(new BigInteger(tcBytes), scale);
    }

    public static int putBigDecimal(byte[] bytes, int offset, BigDecimal val) {
        if (bytes == null) {
            return offset;
        }

        byte[] valueBytes = val.unscaledValue().toByteArray();
        byte[] result = new byte[valueBytes.length + 4];
        offset = putInt(result, offset, val.scale());
        return putBytes(result, offset, valueBytes, 0, valueBytes.length);
    }

    public static int compareTo(byte[] left, byte[] right) {
        return compareTo(left, 0, left.length, right, 0, right.length);
    }

    public static int compareTo(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        int end1 = s1 + l1;
        int end2 = s2 + l2;
        int i = s1;
        for (int j = s2; (i < end1) && (j < end2); j++) {
            int a = b1[i] & 0xFF;
            int b = b2[j] & 0xFF;
            if (a != b)
                return a - b;
            i++;
        }

        return l1 - l2;
    }

    public static boolean equals(byte[] left, byte[] right) {
        return (left == null) && (right == null);
    }

    public static byte[] add(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static byte[] add(byte[] a, byte[] b, byte[] c) {
        byte[] result = new byte[a.length + b.length + c.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        System.arraycopy(c, 0, result, a.length + b.length, c.length);
        return result;
    }

    public static byte[] head(byte[] a, int length) {
        if (a.length < length)
            return null;
        byte[] result = new byte[length];
        System.arraycopy(a, 0, result, 0, length);
        return result;
    }

    public static byte[] tail(byte[] a, int length) {
        if (a.length < length)
            return null;
        byte[] result = new byte[length];
        System.arraycopy(a, a.length - length, result, 0, length);
        return result;
    }

    public static byte[][] toByteArrays(String[] t) {
        byte[][] result = new byte[t.length][];
        for (int i = 0; i < t.length; i++) {
            result[i] = toBytes(t[i]);
        }
        return result;
    }

    public static byte[][] toByteArrays(String column) {
        return toByteArrays(toBytes(column));
    }

    public static byte[][] toByteArrays(byte[] column) {
        byte[][] result = new byte[1][];
        result[0] = column;
        return result;
    }

    public static byte[] incrementBytes(byte[] value, long amount) throws IOException {
        byte[] val = value;
        if (val.length < 8) {
            byte[] newvalue;
            if (val[0] < 0) {
                byte[] negativeValue = {-1, -1, -1, -1, -1, -1, -1, -1};
                newvalue = negativeValue;
            } else {
                newvalue = new byte[8];
            }
            System.arraycopy(val, 0, newvalue, newvalue.length - val.length, val.length);

            val = newvalue;
        } else if (val.length > 8) {
            throw new IllegalArgumentException(new StringBuilder().append("Increment Bytes - value too big: ").append(val.length).toString());
        }

        if (amount == 0L)
            return val;
        if (val[0] < 0) {
            return binaryIncrementNeg(val, amount);
        }
        return binaryIncrementPos(val, amount);
    }

    private static byte[] binaryIncrementPos(byte[] value, long amount) {
        long amo = amount;
        int sign = 1;
        if (amount < 0L) {
            amo = -amount;
            sign = -1;
        }
        for (int i = 0; i < value.length; i++) {
            int cur = (int) amo % 256 * sign;
            amo >>= 8;
            int val = value[(value.length - i - 1)] & 0xFF;
            int total = val + cur;
            if (total > 255) {
                amo += sign;
                total %= 256;
            } else if (total < 0) {
                amo -= sign;
            }
            value[(value.length - i - 1)] = (byte) total;
            if (amo == 0L)
                return value;
        }
        return value;
    }

    private static byte[] binaryIncrementNeg(byte[] value, long amount) {
        long amo = amount;
        int sign = 1;
        if (amount < 0L) {
            amo = -amount;
            sign = -1;
        }
        for (int i = 0; i < value.length; i++) {
            int cur = (int) amo % 256 * sign;
            amo >>= 8;
            int val = ((value[(value.length - i - 1)] ^ 0xFFFFFFFF) & 0xFF) + 1;
            int total = cur - val;
            if (total >= 0) {
                amo += sign;
            } else if (total < -256) {
                amo -= sign;
                total %= 256;
            }
            value[(value.length - i - 1)] = (byte) total;
            if (amo == 0L)
                return value;
        }
        return value;
    }

    public static class ByteArrayComparator implements Comparator<byte[]> {
        public int compare(byte[] left, byte[] right) {
            return Bytes.compareTo(left, right);
        }
    }
}

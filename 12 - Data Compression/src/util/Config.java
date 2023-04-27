package src.util;

public class Config
{
    public static final String DATA_PATH = "12 - Data Compression/data/";
    public static final String COMPRESSED_PATH = DATA_PATH + "compressed/";
    public static final String DECOMPRESSED_PATH = DATA_PATH + "decompressed/";
    public static final String ORIGINAL_PATH = DATA_PATH + "original/";

    /**
     * The maximum size of an offset (and hence the maximum size of the buffer)
     */
    public static final int BUFFER_SIZE = Short.MAX_VALUE;
    /**
     * The maximum length of a unmatched sequence.
     * Cannot be longer than this because the length of the sequence is stored in a Short.
     */
    public static final int MAX_NOT_MATCH_LENGTH = Short.MAX_VALUE;
    /**
     * The maximum length of a match. (1 Byte)
     */
    public static final int MAX_MATCH_LENGTH = Byte.MAX_VALUE;
    /**
     * The corresponding number of chars a reference is equal to.
     */
    public static final int LENGTH_OF_REFERENCE = (Short.SIZE + Byte.SIZE) / Byte.SIZE;
}

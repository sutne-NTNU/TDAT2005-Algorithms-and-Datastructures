package src.util;

public class Config
{
    public static final boolean DEBUG = true; // write refs as strings, not bytes

    public static final String DATA_PATH = "12 - Data Compression/data/";
    public static final String COMPRESSED_PATH = DATA_PATH + "compressed/";
    public static final String DECOMPRESSED_PATH = DATA_PATH + "decompressed/";
    public static final String ORIGINAL_PATH = DATA_PATH + "original/";
    public static final String VISUALIZED_PATH = DATA_PATH + "visualized/";

    /**
     * The maximum size of an offset (and hence the maximum size of the buffer)
     * Max Positive Value of a Short.
     */
    public static final int BUFFER_SIZE = 32767;
    /**
     * The corresponding number of chars a reference is equal to.
     *
     * - Reference = 1 Short (16-bit) + 1 Byte (8-bit) = 24bits
     * - 3 Char = 3 * 8 bits = 24bits
     */
    public static final int LENGTH_OF_REFERENCE = 3;
    /**
     * The maximum length of a match. (1 Byte)
     */
    public static final int MAX_MATCH_LENGTH = 127;
    /**
     * The maximum length of a unmatched sequence. (1 Short = 2 Bytes)
     * Cannot be longer than this because the length of the sequence is stored in a Short.
     */
    public static final int MAX_NOT_MATCH_LENGTH = 127;
}

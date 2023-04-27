package src;

import src.util.Config;
import src.util.Reader;
import src.util.Writer;

public class Compressor
{

    private StringBuffer buffer = new StringBuffer(Config.BUFFER_SIZE);
    private Reader reader;
    private Writer writer;
    private Writer vizWriter;

    public Compressor(String name)
    {
        this.reader = new Reader(Config.ORIGINAL_PATH + name);
        this.writer = new Writer(Config.COMPRESSED_PATH + name, false);
        this.vizWriter = new Writer((Config.COMPRESSED_PATH + name).replace(".txt", " - Visualized.txt"), true);

        this.compress();

        this.reader.close();
        this.writer.close();
        this.vizWriter.close();
    }

    private void compress()
    {
        char newCharacter;
        String matched = "";
        String notMatched = "";

        while ((newCharacter = this.reader.readChar()) != 0)
        {
            // Check if new character is part of a match
            boolean isMatch = this.buffer.indexOf(matched + newCharacter) != -1;

            if (isMatch)
            {
                matched += newCharacter;
                if (matched.length() == Config.MAX_MATCH_LENGTH)
                {
                    this.writeReference(matched);
                    matched = "";
                }
            }
            else
            {
                // There is no longer a match, but there might have been a match on the previous iteration
                if (this.shouldReference(matched))
                {
                    if (notMatched.length() > 0)
                    {
                        this.writeUnmatched(notMatched);
                        notMatched = "";
                    }
                    this.writeReference(matched);
                }
                else
                {
                    notMatched += matched;
                }
                matched = "";
                notMatched += newCharacter;
                if (notMatched.length() == Config.MAX_NOT_MATCH_LENGTH)
                {
                    this.writeUnmatched(notMatched);
                    notMatched = "";
                }
            }
            this.addToBuffer(newCharacter);
        }

        // End of file reached, write remaining matches and unmatched
        if (this.shouldReference(matched))
        {
            this.writeReference(matched);
        }
        else
        {
            notMatched += matched;
        }
        if (notMatched.length() > 0)
        {
            this.writeUnmatched(notMatched);
        }
    }

    /**
     * [-offset,length]
     *
     * Offset will be negative, that way decompressor knows it is a reference value.

     Example:

        buffer: "Hello World, World" - length: 18
        str: " World" - length: 6
        matchIndex: 5
        offset: 5 - (18 - 6) = -7
        ref = [-7,6]

     */
    private void writeReference(String str)
    {
        int matchIndex = this.buffer.indexOf(str);
        int offset = matchIndex - (this.buffer.length() - str.length());
        this.writer.writeReference(offset, str.length());
        this.vizWriter.writeReference(offset, str.length());
    }

    /**
     * Write the string to the file, prepended with its length for decompression.
     *
     * [length]str
     */
    private void writeUnmatched(String str)
    {
        this.writer.writeStringWithLength(str);
        this.vizWriter.writeStringWithLength(str);
    }

    /**
     * Add char to buffer and trim buffer to max Config.BUFFER_SIZE
     *
     * Return true if buffer was not full, false if a char was removed
     */
    private void addToBuffer(char c)
    {
        this.buffer.append(c);
        if (this.buffer.length() == Config.BUFFER_SIZE + 1)
        {
            this.buffer.deleteCharAt(0); // remove oldest char
        }
    }

    /**
     * Checks if the string is long enough to be replaced with a reference,
     * if its shorter, the original str takes less space.
     */
    private boolean shouldReference(String str)
    {
        return str.length() > Config.LENGTH_OF_REFERENCE;
    }
}

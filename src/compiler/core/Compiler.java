package compiler.core;

import compiler.interfaces.Consumer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class Compiler implements Consumer<String> {

    /**
     * Represents the file reader of the compiler
     */
    private final ArrayList<String> lines = new ArrayList<>();

    /**
     * Represents all the tags
     */
    private final HashMap<String, Integer> allocatedTags = new HashMap<>();

    /**
     * Represents the remaining instructions to be executed
     */
    private final LinkedList<Integer> remainingInstructions = new LinkedList<>();

    /**
     * Represents the program counter of the compiler
     */
    private int programCounter;

    /**
     * Constructs the compiler based on a directory to a file
     * @param path  the path to the file
     * @throws FileNotFoundException    the exception thrown if the file was never found
     */
    public Compiler(final String path) throws FileNotFoundException {
        FileParser reader = new FileParser(path);
        String line = "";
        while ((line = reader.consume()) != null) {
            lines.add(line);
        }
    }

    @Override
    public String consume() {
        if (programCounter + 1 > lines.size()) {
            return null;
        }
        return lines.get(programCounter ++);
    }

    /**
     * Allocates a tag into the compiler
     * @param tag   the tag
     * @param line  the line
     */
    public void addTag(final String tag, final int line) {
        allocatedTags.put(tag, line);
    }

    /**
     * Gets the tag line based on a tag
     * @param tag   the tag
     * @return  the tag line
     */
    public int getTagLine(final String tag) {
        return allocatedTags.get(tag);
    }

    /**
     * Clears the program counter
     */
    public void clearProgramCounter() {
        programCounter = 0;
    }

    /**
     * Gets the program counter direction
     * @return  the program counter
     */
    public int getProgramCounter() {
        return programCounter;
    }

}

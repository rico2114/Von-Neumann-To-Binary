package compiler.core;


import compiler.interfaces.Consumer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class FileParser implements Consumer<String> {

    /**
     * Represents the source of information
     */
    private final BufferedReader reader;

    /**
     * Constructs the file parser based on a {@oode path} to the file
     * @param path  the path to the file
     * @throws FileNotFoundException    this exception is thrown if the file was never found.
     */
    public FileParser(final String path) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(path));
    }

    @Override
    public String consume() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

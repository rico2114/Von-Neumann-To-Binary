package compiler;

import compiler.core.Compiler;
import compiler.core.Instruction;
import compiler.core.InstructionInterpreter;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import static compiler.core.regex.RegexConstants.NAME;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class App {

    /**
     * Represents the compiler
     */
    private final Compiler compiler;

    /**
     * Constructs the application
     * @param path  the path to the file
     * @throws FileNotFoundException    the file not found exception
     */
    public App(final String path) throws FileNotFoundException {
        this.compiler = new Compiler(path);
    }

    /**
     * A simple main test
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        App app = new App("./code.asm");
        app.convert();
    }

    /**
     * Handles the transformation process
     */
    public void convert() {
        // First the pre processing for the tags
        String line = "";
        while ((line = compiler.consume()) != null) {
            if (line.contains(":")) {
                InstructionInterpreter.interpret(line, compiler);
            }
        }
        compiler.clearProgramCounter();
        // Now the actual conversion
        int lineNumber = 0;
        while ((line = compiler.consume()) != null) {
            if (line.contains(":")) {
                continue;
            }

            final Instruction instruction = InstructionInterpreter.interpret(line, compiler);
            System.out.print(line.replace(";", "") + " ==> ");
            if (instruction != null) {
                System.out.println((lineNumber ++) + ") " + instruction.toString());
            }
        }
    }
}

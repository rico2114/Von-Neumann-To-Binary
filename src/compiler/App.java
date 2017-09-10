package compiler;

import compiler.core.Compiler;
import compiler.core.Instruction;
import compiler.core.InstructionInterpreter;

import java.io.FileNotFoundException;

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
        while ((line = compiler.consume()) != null) {
            if (line.contains(":")) {
                continue;
            }

            final Instruction instruction = InstructionInterpreter.interpret(line, compiler);
            if (instruction != null) {
                System.out.println(instruction.toString());
            }
        }
    }
}

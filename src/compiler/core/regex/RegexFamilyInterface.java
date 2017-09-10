package compiler.core.regex;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public interface RegexFamilyInterface {

    /**
     * This function calculates the opcode based on a line of code
     * @param line  the line of code
     * @param operationName the operation name
     * @return  the opcode of the instruction
     */
    int calculate(final String line, final String operationName);
}

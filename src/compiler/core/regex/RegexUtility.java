package compiler.core.regex;

import java.util.regex.Pattern;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class RegexUtility {

    /**
     * Calculates the opcode of a given line based on their respective parents instruction format and operations.
     * @param line  the line of the code
     * @param operationName the operation name
     * @param instructionFormat the instruction format of the parents
     * @param operations    the operation of the parents
     * @param startingOffset    the starting offset of the parent instruction
     * @return  the opcode or -1 if it does not exists
     */
    public static final int calculateOpcode(final String line, final String operationName, final Pattern[] instructionFormat, final Pattern [] operations, int startingOffset) {
        int formatIndex = -1, operationIndex = -1;
        for (int i = 0; i < instructionFormat.length && formatIndex == -1; i ++) {
            if (instructionFormat[i].matcher(line).matches()) {
                formatIndex = i;
            }
        }
        for (int i = 0; i < operations.length && operationIndex == -1; i ++) {
            if (operations[i].matcher(operationName).matches()) {
                operationIndex = i;
            }
        }

        if (operationIndex == -1 || formatIndex == -1) {
            return -1;
        }

        return startingOffset + (operationIndex * instructionFormat.length) + formatIndex;
    }
}

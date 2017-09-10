package compiler.core;

import compiler.core.regex.RegexFamilyInterface;
import compiler.core.regex.impl.FourthOffsetFamily;
import compiler.core.regex.impl.SevenOffsetFamily;
import compiler.core.regex.impl.SingleOffsetFamily;
import compiler.core.regex.impl.SpecialOperationFamily;

import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class InstructionInterpreter {

    /**
     * Represents the regex family used to interpret all the instructions and their respective opcodes
     */
    private static final LinkedList<RegexFamilyInterface> REGEX_FAMILY = new LinkedList<>();

    /**
     * Static initialization of the family
     */
    static {
        REGEX_FAMILY.add(new SevenOffsetFamily());
        REGEX_FAMILY.add(new SpecialOperationFamily());
        REGEX_FAMILY.add(new FourthOffsetFamily());
        REGEX_FAMILY.add(new SingleOffsetFamily());
    }

    /**
     * This method is used to interpret a line of code
     * @param line  the line to interpret
     * @param context   the {@link Compiler compiler}
     * @return  the {@link Instruction instruction} instance if possible
     */
    public static Instruction interpret(String line, final Compiler context) {
        // If line is empty continue
        if (line.isEmpty()) {
            return null;
        }
        // syntax sugar
        line = line.toUpperCase();
        line = line.replaceAll(";", "");

        // Handles the tag process
        if (line.contains(":")) {
            line = line.replace(":", "");
            context.addTag(line, context.getProgramCounter() - 1);
        } else {
            int whiteSpaceIndex = line.indexOf(" ");
            String operationName = line;
            if (line.contains(" ")) {
                operationName = line.substring(0, whiteSpaceIndex);
            }
            String operationParameters = line.substring(whiteSpaceIndex + 1, line.length()).replaceAll(" ", "");
            line = line.substring(0, line.length());
            for (RegexFamilyInterface family : REGEX_FAMILY) {
                int opcode = family.calculate(line, operationName);
                if (opcode != -1) {
                    if (opcode == 54) { //  NOP
                        return new Instruction(0, 0, 0, 0);
                    }
                    if ((opcode >= 49 && opcode <= 51) || opcode == 55) { // BRM, BRI, BRME, JUMP
                        if (opcode == 55) { // JUMP
                            return new Instruction(opcode, 0, 0, context.getTagLine(operationParameters)); // straight jump
                        } else {
                            int[] parameters = interpretParameters(operationParameters, context);
                            return new Instruction(opcode, parameters[0], parameters[1], parameters[2] - context.getProgramCounter()); // pc modification
                        }
                    } else {
                        int[] parameters = interpretParameters(operationParameters, context);
                        return new Instruction(opcode, parameters[0], parameters[1], parameters[2]); // straight interpretation
                    }
                }
            }
        }
        return null;
    }

    /**
     * Attempts to interpret the parameters of a set of parameters
     * @param operationParameters   the set of parameters
     * @param context   the compiler context
     * @return  the array of parameters in the format {left, right, immediate}
     */
    private static int [] interpretParameters(String operationParameters, final Compiler context) {
        int leftOperand = -1, rightOperand = -1, immediate = -1;
        operationParameters = operationParameters.replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\+", ",").replaceAll(";", "");
        final StringTokenizer tokenizer = new StringTokenizer(operationParameters, ",");
        while (tokenizer.hasMoreElements()) {
            String element = tokenizer.nextToken();
            if (element.contains("R")) {
                element = element.replace("R", "");
                if (leftOperand == -1) {
                    leftOperand = Integer.valueOf(element);
                } else {
                    rightOperand = Integer.valueOf(element);
                }
            } else {
                try {
                    immediate = Integer.valueOf(element);
                } catch (NumberFormatException e) {
                    immediate = context.getTagLine(element);
                }
            }
        }
        return new int[] {Math.max(leftOperand, 0), Math.max(rightOperand, 0), Math.max(immediate, 0)};
    }
}


package compiler.core;

/**
 * Created by Sebastían on 9/09/2017.
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.com
 */
public class Instruction {

    /**
     * Represents the four fundamental attributes of an instruction
     */
    private final int operation, leftOperand, rightOperand, immediate;

    /**
     * Constructs the instruction based on a set of attributes
     * @param operation the operation of the instruction
     * @param leftOperand   the left operand of the instruction
     * @param rightOperand  the right operand of the instruction
     * @param immediate the immediate of the instruction
     */
    public Instruction(final int operation, final int leftOperand, final int rightOperand, final int immediate) {
        this.operation = operation;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.immediate = immediate;
    }

    @Override
    public String toString() {
        String operationBinary = addOrRemove(Integer.toBinaryString(operation), 7);
        String leftOperandBinary = addOrRemove(Integer.toBinaryString(leftOperand), 4);
        String rightOperandBinary = addOrRemove(Integer.toBinaryString(rightOperand), 4);
        String immediateBinary = addOrRemove(Integer.toBinaryString(immediate), 5);

       return operationBinary + '|' + leftOperandBinary +
                '|' + rightOperandBinary + '|' + immediateBinary;
        // todo: remove this whenever we are done with the testing
        //return "" + operation + '|' + leftOperand + '|' + rightOperand + '|' + immediate;
    }

    /**
     * Adds or removes the leading zeroes if the bit is too big
     * @param binary    the binary
     * @param length the desired length of the number
     * @return  the new number in binary
     */
    private String addOrRemove(final String binary, int length) {
        if (binary.length() > length) {
            return binary.substring(binary.length() - length, binary.length());
        } else {
            StringBuilder leadingZeroes = new StringBuilder();
            for (int i = binary.length(); i < length; i++) {
                leadingZeroes.append("0");
            }
            return leadingZeroes.toString() + binary;
        }
    }
}

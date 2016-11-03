package brainfuck.language;

import brainfuck.language.Exceptions.OutOfMemoryException;
import brainfuck.language.Exceptions.ValueOutOfBoundException;

/**
 * This class is where the memory of our file will be stocked. It contains
 * an array of Short with a defined size (you can change it easily). Multiples methods
 * are available here to move in the memory	or change the value of actual cell.
 * 
 * @author Dalla-Nora Enzo and Derringer Guillaume
 */

public class Memory {

	public static final int memorySize = 30000;
	private short[] mArray;
	private short pointer;
	private short maxArray;

	/**
     * Constructor for objects of class Memory.
     */
	
	public Memory() {
		mArray = new short[memorySize];
		pointer = 0;
	}

	/**
	 * Verify if it's possible to increment or not the cell;
	 * Then, add 1 to the actual cell if it's possible.
	 * Print Exit code 1 if it's not.
	 * @throws ValueOutOfBoundException
	 */
	public void incr() throws ValueOutOfBoundException {
		if (mArray[pointer] == 255) throw new ValueOutOfBoundException();
		mArray[pointer]++;
	}

	/**
	 * Verify if it's possible to decrement or not the cell;
	 * Then, subtract 1 to the actual cell if it's possible.
	 * Print Exit code 1 if it's not.
	 * @throws ValueOutOfBoundException
	 */
	public void decr() throws ValueOutOfBoundException {
		if (mArray[pointer] == 0) throw new ValueOutOfBoundException();
		mArray[pointer]--;
	}

	/**
	 * Verify if it's possible to move the pointer by one on the right or not;
	 * Then, add 1 to the pointer's position if it's possible.
	 * Print Exit code 2 if it's not.
	 * @throws OutOfMemoryException
	 */

	public void right() throws OutOfMemoryException {
		if (pointer >= mArray.length - 1) throw new OutOfMemoryException();
		pointer++;
		if (pointer >= maxArray) maxArray = pointer;
	}

	/**
	 * Verify if it's possible to move the pointer by one on the left or not;
	 * Then, subtract 1 to the pointer's position if it's possible.
	 * Print Exit code 2 if it's not.
	 * @throws OutOfMemoryException
	 */

	public void left() throws OutOfMemoryException {
		if (pointer <= 0) throw new OutOfMemoryException();
		pointer--;
	}
	
	public void printMemory(){
		for (int i =  0; i <= maxArray; i++){
			System.out.println("C" + i + ": " + mArray[i] + "\n");
		}
	}
	
	public short[] getmArray() {
		return mArray;
	}
}

package brainfuck.language;

import brainfuck.language.exceptions.OutOfMemoryException;
import brainfuck.language.exceptions.ValueOutOfBoundException;

/**
 * Cette classe permet de gerer la mémoire de notre interpreteur.
 * Elle contient de nombreuses méthodes afin de manipuler la mémoire
 * 
 * @author  Red_Panda
 */

public class Memory {

	public static final int MEMORY_SIZE = 30000;
	private short[] mArray;
	private short pointer;
	private short maxArray;

	/**
     * Le constructeur de la classe mémoire
     */
	
	public Memory() {
		mArray = new short[MEMORY_SIZE];
		pointer = 0;
	}

	/**
	 * Methode permettant d'incrementer la case mémoire actuellement pointée
	 * @throws ValueOutOfBoundException
	 */
	public void incr() throws ValueOutOfBoundException {
		if (mArray[pointer] == 255)
			throw new ValueOutOfBoundException();
		mArray[pointer]++;
	}

	/**
	 * Methode permettant de décrementer la case mémoire actuellement pointée
	 * @throws ValueOutOfBoundException
	 */
	public void decr() throws ValueOutOfBoundException {
		if (mArray[pointer] == 0)
			throw new ValueOutOfBoundException();
		mArray[pointer]--;
	}

	/**
	 * Methode permettant de décaler vers la droite la position du pointeur mémoire
	 * @throws OutOfMemoryException
	 */

	public void right() throws OutOfMemoryException {
		if (pointer >= mArray.length - 1)
			throw new OutOfMemoryException();
		pointer++;
		if (pointer >= maxArray)
			maxArray = pointer;
	}

	/**
	 * Methode permettant de décaler vers la gauche la position du pointeur mémoire
	 * @throws OutOfMemoryException
	 */

	public void left() throws OutOfMemoryException {
		if (pointer <= 0)
			throw new OutOfMemoryException();
		pointer--;
	}

	/**
	 *  Affiche dans la console l'état de la mémoire actuelle
	 */
	public void printMemory(){
		for (int i =  0; i <= maxArray; i++){
			System.out.println("C" + i + ": " + mArray[i]);
		}
		System.out.println();
	}

	/**
	 * Renvoie l'état de la mémoire actuelle
	 * @return l'état de la mémoire actuelle
	 */

	public String writeStateOfMemory(){
		StringBuilder resume = new StringBuilder();
		for (int i =  0; i <= maxArray; i++){
			if(mArray[i] != 0) {
				resume.append('C');
				resume.append(i);
				resume.append(": ");
				resume.append(mArray[i]);
				resume.append(' ');
			}
		}
		return resume.toString();
	}


	/**
	 * Change la valeur de la case mémoire actuellement pointée
	 * @param value la nouvelle valeur de la case mémoire
	 */
	public void updateMemory(short value) throws ValueOutOfBoundException {
		if(value > 255)
			throw new ValueOutOfBoundException();


		mArray[pointer] = value;
		Metrics.DATA_WRITE++;
	}

	/**
	 * Retourne la position du pointeur mémoire
	 * @return La position du pointeur mémoire
	 */

	public int getPointer(){return pointer;}

	/**
	 * Retourne la mémoire entiere
	 * @return La mémoire entiere
	 */

	public short[] getmArray(){
		return mArray;
	}

	/**
	 * Retourne la valeur de la case mémoire actuellement pointée
	 * @return La valeur de la case mémoire actuellement pointée
	 */

	public int getCellValue() {
		return (int) mArray[pointer];
	}
}

package mytest;

public class StringExercise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String testString = "Good 1st 3Morpiofjpg66785884736553  pjrfning! ";
		testString = testString.trim();
		
		String testString2 = "Good morning!";
		System.out.println("'"+testString+"'");
	
		 
		if (testString.equals(testString2)) {
			System.out.println("Yes, they are equal.");
		}
		
		
		System.out.println(testString.toLowerCase());
		System.out.println(testString.length());
		
	
		
		String blankTestString = "  ";
		blankTestString = blankTestString.trim();
		System.out.println("'"+blankTestString+"'");
		
		
		char[] characters = testString.toCharArray();
		for (char character : characters) {
			
			System.out.println(Character.isDigit(character));
		}
		
		
		
	}

}

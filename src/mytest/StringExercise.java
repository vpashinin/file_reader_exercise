package mytest;

public class StringExercise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String testString = "Good Morning! ";
		testString = testString.trim();
		System.out.println("'"+testString+"'");
		if (testString.equals("Good morning!")) {
			System.out.println("Yes, they are equal.");
		}
		
		
		System.out.println(testString.toLowerCase());
		
		
		
		String blankTestString = "  ";
		blankTestString = blankTestString.trim();
		System.out.println("'"+blankTestString+"'");
	}

}

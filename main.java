import java.util.HashMap;

public class main {
	public static void main(String[] args) {
		// creates books list, some starting books, and number of copies of each
		HashMap<String, Integer> bookList = new HashMap<String, Integer>();
		String[] books = { "Forrest Gump", "Gulliver's Travels", "Moulin Rouge", "Intermediate Algebra",
				"Shakespeare: The Complete Works"};
		int[] numBooks = {6, 2, 1, 9, 4};
		
		// adds the initial stock of books to the book list
		for(int i = 0; i < books.length; i++) {
			addBook(bookList, books[i], numBooks[i]);
		}
		
		// checks the number of copies of Gulliver's Travels
		transactionCode(bookList, "Gulliver's Travels", 0, 1);
		
		// adds 3 copies of Holes to the inventory
		transactionCode(bookList, "Holes", 3, 2);
		
		// removes Intermediate Algebra from inventory
		transactionCode(bookList, "Intermediate Algebra", 0, 3);
		
		// tries to check out 6 copies of Shakespeare
		transactionCode(bookList, "Shakespeare: The Complete Works", 6, 4);
		
		// checks out 4 copies of Shakespeare
		transactionCode(bookList, "Shakespeare: The Complete Works", 4, 4);
		
		// feels bad for taking all the copies and returns one copy of Shakespeare and donates 2 copies of Java Programming
		transactionCode(bookList, "Shakespeare: The Complete Works", 1, 5);
		transactionCode(bookList, "Java Programming", 2, 2);
	}

	// checkStatus() - checks if the book is available for checkout ** STATUS 1 **
	public static int checkStatus(HashMap<String, Integer> map, String title) {
		int numAvail = map.get(title);
		return numAvail;
	}
	

	// addBook() - adds a book to inventory ** STATUS 2 **
	public static HashMap<String, Integer> addBook(HashMap<String, Integer> map, String title, int num) {
		map.put(title, num);
		return map;
	}

	// removeBook() - removes a book from inventory ** STATUS 3 **
	public static HashMap<String, Integer> removeBook(HashMap<String, Integer> map, String title) {
		map.remove(title);
		return map;
	}

	// checkOut() - removes a book to the inventory ** STATUS 4 **
	public static HashMap<String, Integer> checkOut(HashMap<String, Integer> map, String title, int num) {
		map.put(title, map.get(title) - num);
		return map;
	}

	// checkIn() - adds a book to the inventory ** STATUS 5 **
	public static void checkIn(HashMap<String, Integer> map, String title, int num) {
		map.put(title, map.get(title) + num);
	}
	
	// transactionCode() - used for each transaction to print to the console
	public static void transactionCode(HashMap<String, Integer> map, String title, int num, int code) {
		// 1 = checkStatus
		// 2 = addBook
		// 3 = removeBook
		// 4 = checkOut
		// 5 = checkIn
		switch(code) {
		case 1: 
			System.out.println("There are " + checkStatus(map, title) + " copies of " + title + " available.");
			break;
		case 2: 
			addBook(map, title, num);
			System.out.println(num + " copies of '" + title + "' successfully added to inventory.");
			break;
		case 3: 
			removeBook(map, title);
			System.out.println(title + " is no longer in inventory.");
			break;
		case 4: 
			int numCopies = checkStatus(map, title);
			if (numCopies >= num) {
				checkOut(map, title, num);
				System.out.println(num + " copies of " + title + " have been checked out. " + checkStatus(map, title) + " copies are left.");
			} else System.out.println("There are only " + numCopies + " of " + title + " available.");
			break;
		case 5: 
			checkIn(map, title, num);
			System.out.println(title + " successfully returned. There are " + checkStatus(map, title) + " copies of " + title + " available.");
			break;
		}
	}

}

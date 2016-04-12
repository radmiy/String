import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		final String FILE = "src/in.txt";
		final String FILE_NOT_FOUND = "Input file not found";
		final String REGULAR_EXPRESSION = "\\W";
		final String SEARCH_WORD = "Enter a search word: ";
		final String WORD_IS_FOUND = "The word \"%s\" is found %d times.";
		final String WORD_IS_NOT_FOUND = "The word \"%s\" is not found.";
		
		Map<String, Integer> allWords = new HashMap<String, Integer>();
		
		@SuppressWarnings("resource")
		Scanner scanner = null;
		try {
			//read file with text
			scanner = new Scanner(new FileReader(FILE));
			while (scanner.hasNextLine()) {
				//read and split ever line
				String segmentString = scanner.nextLine();
				String[] words = segmentString.split(REGULAR_EXPRESSION);
				
				final int NOT_FOUND = 0; 
				//write in the map words with quantity of recurrences
				for(String word : words) {
					if(!word.isEmpty()) {
						String wordLower = word.toLowerCase();
						int count = allWords.get(wordLower) != null ? allWords.get(wordLower) : NOT_FOUND;
						count++;
						allWords.put(wordLower, count);
					}
				}
			}
		}catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_FOUND);
		}finally{
			if(scanner != null)
				scanner.close();
		}
		
		scanner = new Scanner(System.in);
		System.out.print(SEARCH_WORD);
		//ask about a required word
		String findWord = scanner.next().toLowerCase().trim();
		//read quantity of recurrences
		Integer count = allWords.get(findWord); 
		if(count == null) {
			System.out.printf(WORD_IS_NOT_FOUND, findWord);
		}else {
			System.out.printf(WORD_IS_FOUND, findWord, count);
		}
		
		System.out.println();
		System.out.println();
		//print all words
		for(Map.Entry<String, Integer> entry : allWords.entrySet()) {
			System.out.printf("%s - %d", entry.getKey(), entry.getValue());
			System.out.println();
		}
	}

}

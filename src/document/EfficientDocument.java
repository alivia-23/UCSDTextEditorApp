package document;

import java.util.List;

/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class EfficientDocument extends Document {

	private int numWords;  // The number of words in the document
	private int numSentences;  // The number of sentences in the document
	private int numSyllables;  // The number of syllables in the document
	
	public EfficientDocument(String text)
	{
		super(text);
		processText();
	}
	
	
	/** Return true if this string is a word (as opposed to punctuation)
	 * @param tok The string to check
	 * @return true if tok is a word, false otherwise. */
	private boolean isWord(String tok)
	{
	    // Note: This is a fast way of checking whether a string is a word
	    // You probably don't want to change it.
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
	
    /** Passes through the text one time to count the number of words, syllables and 
     * sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below. 
     */
	private void processText()
	{
		// Provide this first line in the starter code.  
		// Words are only strings of letters.  No numbers.
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		int numWords = 0, numSyllables = 0, numSentences = 0;
		for (int i = 0; i < tokens.size(); i++)
		{
			if (isWord(tokens.get(i)))
			{
				if (i == tokens.size() - 1)
					numSentences += 1;
				numWords++;
				numSyllables += countSyllables(tokens.get(i));
			}
			else numSentences += 1;
			
		}
		this.numSentences = numSentences;
		this.numWords = numWords;
		this.numSyllables = numSyllables;

	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
		
	    return this.numWords;
	}

	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
       
        return this.numSentences;
	}


	@Override
	public int getNumSyllables() {
        
        return this.numSyllables;
	}
	
	// Can be used for testing
	// We encourage you to add your own tests here.
	public static void main(String[] args)
	{
	    testCase(new EfficientDocument("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new EfficientDocument(""), 0, 0, 0);
        testCase(new EfficientDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);    
		
	}
	

}
# Setup
Please import the project as Maven with your fav IDE

# Run with File

Change file path in Main.java, else change contents of input.txt
Main class would print the output to console,

# Solution Approach
Without going into AI / ML, which is uncharted territory for me; the only feasible way is to think of the problem as search engine.

You split question into keywords and use a ranked algo to find out best matching sentence.

Once you have the sentence, answer can be mapped to sentence with checking for a substring. :) 

## Things to watch out
The ranking algo is very trivial and would fail for any complex test case (I had just one test case and could not cross check with other cases).

To rank, the solution currently uses length of word, the first version of solution was counting matching filtered keywords with solution. But words like 'a', 'the', 'is' are high frequency and low context words; hence the algorithm favours words with more length.

Ideally solution could have blacklisted more such words, some of them are present in ```KeywordSplitter```, but somehow feels it is not scalable. 

## Tests
You can quickly run all tests via IDE by running suite ```
AllTests.java```

   

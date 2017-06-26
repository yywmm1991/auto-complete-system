# auto-complete-system

build a Query Auto-completion system using MapReduce based on N-Gram Model. 
In the fields of computational linguistics and probability, an n-gram is a contiguous sequence of n items, from a given sequence of text or speech. The items can be phonemes, syllables, letters, words or base pairs according to the application. The n-grams typically are collected from a text or speech corpus. When the items are words, n-grams may also be called shingles.
      The intuition of the N-gram is that instead of computing the probability of a word given its entire history, we can approximate the history by just the last few words. My implementation would mainly focus on 2-gram.

Example: This is my house.  2-gram: This is, is my, my house. 3-gram: This is my, is my house. 4-gram: This is my house.

First, this project generates a 2-gram library from training data and then build a language model based on the 2-gram library, which is a probability distribution over entire sentences or texts. This language model can be used to predict the words after a given word or phrase.

        My Input will be a bunch of text documents, which include different kinds of literatures. Each document has a temporal weight value indicating how recent the doc is. Each time it calculates the frequency of n-gram, it will multiply the temporal weight of the doc to get the final count value. Output will be a n-gram model table, storing all the information needed for type ahead prediction.  

# auto-complete-system

Background and motivations
       Autocomplete or word completion works so that when the writer writes the first letter or letters of a word, the program predicts one or more possible words as choices. If the word he intends to write is included in the list he can select it, for example by using the number keys. If the word that the user wants is not predicted, the writer must enter the next letter of the word. The word choice is altered so that the words provided begin with the same letters as those that have been selected. When the word that the user wants appears it is selected, and the word is inserted into the text. In another form of word prediction, words most likely to follow the just written one are predicted, based on recent word pairs used. Word prediction uses language modeling, where within a set vocabulary the words are most likely to occur are calculated.
      Autocomplete speeds up human-computer interactions when it correctly predicts the word a user intends to enter after only a few characters have been typed into a text input field. It works best in domains with a limited number of possible words (such as in command line interpreters), when some words are much more common (such as when addressing an e-mail), or writing structured and predictable text.
     Query auto completion (QAC) is a prominent feature in modern search engines. High quality QAC substantially improves search experiences by helping users in typing less while submitting the queries.


Related works and differences
Many studies have been proposed to improve quality and relevance of the QAC methods from different perspectives. Although these studies have shown the context, temporal, and user behavior data carry valuable information, most existing QAC approaches do not fully exploit or even completely ignore these information. 
Most of them considered the popularity or frequency of the context. My approach will not only take into account the context but also the temporal information for reliable query completion prediction. 
 

Problem description and proposed methods
      My implementation is to build a Query Auto-completion system using MapReduce based on N-Gram Model. 
      In the fields of computational linguistics and probability, an n-gram is a contiguous sequence of n items, from a given sequence of text or speech. The items can be phonemes, syllables, letters, words or base pairs according to the application. The n-grams typically are collected from a text or speech corpus. When the items are words, n-grams may also be called shingles.
      The intuition of the N-gram is that instead of computing the probability of a word given its entire history, we can approximate the history by just the last few words. My implementation would mainly focus on 2-gram.

Example: This is my house.  2-gram: This is, is my, my house. 3-gram: This is my, is my house. 4-gram: This is my house.

First, this project generates a 2-gram library from training data and then build a language model based on the 2-gram library, which is a probability distribution over entire sentences or texts. This language model can be used to predict the words after a given word or phrase.




figure 1.    process of the MapReduce jobs



Probability of a word apprearing after a phrase:



The way to estimate probability for n-gram is called maximum likelihood estimation, I get the MLE estimate for the parameters of an N-gram model by getting counts from a corpus. However, to make my implementation more efficient, I didn’t normalize the counts so that I can just compare the counts and obtain the results.






        My Input will be a bunch of text documents, which include different kinds of literatures. Each document has a temporal weight value indicating how recent the doc is. Each time it calculates the frequency of n-gram, it will multiply the temporal weight of the doc to get the final count value. Output will be a n-gram model table, storing all the information needed for type ahead prediction.  




figure 2.    language model example



Results
As we can see from the results, the word “office” appears 26 times and the phrase “office is” happens the most time, which is 5. Therefore, our prediction after user typed “office” will be “is”, “of”, “with” and “for”. After I add the temporal information, the results of the prediction are relatively more convincing and reasonable.







Reference 

[1] Liangda Li, Hongbo Deng, Jianhui Chen, Yi Chang. “Learning Parametric Models for Context-Aware Query Auto-Completion via Hawkes Processes”, 2017 ACM. ISBN 978-1-4503-4675-7/17/02. 

[2] Giovanni Di Santo, Richard McCreadie, Craig Macdonald, and Iadh Ounis. “Comparing Approaches for Query Autocompletion”, 2015 ACM. ISBN 978-1-4503-3621-5/15/08. 

[3] Akshay Bhatia , Amit Bharadia, Kunal Sawant, Swapnali Kurhade. “Predictive and Corrective Text Input for desktop editor using n-grams and suffix trees”, 2016 IEEE. ISBN 978-1-4673-8810-8/16. 

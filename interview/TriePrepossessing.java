/*
given Set<String> set, List<Character> chars, return Set<String> which has longest be covered by
the List<Character>

           e.g. dgg cat naioe lot
           1st case: dcnlggatio -> return [dgg,cat,lot]
           2st case: dcnlggatioe -> return [naive]
 */










/*
You are given a dictionary, in the form of a file that contains one word per line. E.g.,
abacus
deltoid
gaff
giraffe
microphone
reef
qar
You are also given a collection of letters. E.g., {a, e, f, f, g, i, r, q}.
The task is to find the longest word in the dictionary that can be spelled with the collection of
letters. For example, the correct answer for the example values above is “giraffe”. (Note that
“reef” is not a possible answer, because the set of letters contains only one “e”.)
 */

/* solution1
* O(M*E), where M is sum of the sizes of all words and E is the number of differents letters.
*
Create a vector V[], where V[e] contains the number of ocorrences letter e in the given set.
For each word s, compute a vector Ws[], where Ws[e] contains the number of repetitions of
letter e in the word (compute size of the word too).

return the max size word s such that Ws[e] <= V[e], for all letters e.
 */
class Solution {

}


















/*
 solution2, but suffer from overflow
 we take an array of size=26 and initialize it by first 26 prime no.
eg: 2 , 3, 5, 7,........
now these prime nos. will represent alphabet
i.e a=2,b=3,c=5......
pro=a* e* f* f* g* r* q=2*11*13*13.....

now we can check with every dictionary word
1) p= product of every letter of a dictionary word
eg: reef=61 * 11 * 11 * 13
2) if pro % p == 0
store l = length(word); and index // if l is greater than previous value
3)finally index will give the result of location of word
 */
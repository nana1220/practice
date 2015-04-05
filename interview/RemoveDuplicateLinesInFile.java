/*
How to remove duplicate lines in a large text file? I think it's easy to find duplicate lines, but
how do we efficiently remove them from the file?
 */

/* BIG FILE
If the file is so large that it can't be held in memory at once, an obvious extension is to break
it into chunks,

maintain global hashmap, line's hash value as key, line's chunk no. and line no. as value

What if we used another file and a hash map? For each line read from the original file,
hash it and check if it exists in the hash map and if it does it means that it's a duplicate so
you just don't write it in the new file. This would require only one pass but it also requires
doubling the storage space and the extra memory for the hash map.


We can use hashing + hashing + hashing approach.
First hash will be based on number of characters in line
Second hash will be number of words in the line
third will be hash of words and now we can store line here.
When a new line comes, it will pass all three hashes and if comes into same bucket then we can compare lines.
using these 3 hash (or filters) we can minimize the chances of clashing lines.

Possibly a more practical approach would be to first construct a bloom filter. This can be made
highly accurate, though it will still produce the occasional false positive. A second pass would
be needed to detect false positives, but by that point the dataset should be very pruned.
 */

/* smail file
put line in hash set to detect duplicates, aslo put line in arraylist to keep order
 */

BufferedReader reader = new BufferedReader(new InputStreamReader(
    new FileInputStream(nwFile)));
    String line;
    while ((line = reader.readLine()) != null) {}
    reader.close();

    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    writer.write(); .write("\n"); write.close();
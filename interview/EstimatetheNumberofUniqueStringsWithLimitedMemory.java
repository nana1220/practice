/*
Estimate the number of unique strings with limited memory
Given a large array of strings S = [s1, s2, … sN], determine Uniq(S) = how many unique strings there are in S.


*/


/*
If it’s only required to estimate the number with limited memory, It’s possible to use a simplified hash table to do this.
We can define a boolean array S
For each iteration we calculate the hash function H of current string, and check if the S[H] is true which means that we
have inserted the same string before current iteration, so we do not count the string.
If the hash function have no collision, the algorithm can always get the accurate result.
To minimize the collision, we can use a group of hash functions like a bloom filter ….(each time we check S1[H1] S2[H2] .. SN[HN],
if all those variable have been set, we assert that the string is duplicated)

Assuming good hashing scheme, this can do 4 billion strings in 512MB memory (ideal case). This means ~64 billion strings in 8GB.
If N becomes two large, a distributed hash table can be used to span the data across multiple servers. Another alternative could
be to store the data on disk and maintain indexes for faster seeks and updates.
 */
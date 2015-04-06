/*int returnRandomNumber(int max, vector<int> &bad_numbers){
    }. from: 1point3acres.com/bbs
    返回0-max之间的随机数，不能包含bad_numbers之中的数。bad_numbers是排好序的。
*/

// 直接看bad numbers里面的数，得出“good numbers” 的个数，然后根据随机数返回。
// 出valid number的个树valid_count,产生从0到valid_count的随机数N，然后用零开始数，
// 如果碰到bad number就跳过，知道碰到第N个valid number为止
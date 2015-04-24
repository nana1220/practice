/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in
binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
 */

public class Solution {
  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    int res=0;
    for(int i=0; i<32;i++){
      res = res<<1;
      int bit = (n>>i) &1;
      res |= bit;
    }
    return res;
  }
}

/*
以4位为单位执行反转，将0x0至0xF的反转结果预存在一个长度为16的数组中，反转时直接查询即可。

C代码：
char tb[16] = {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};

uint32_t reverseBits(uint32_t n) {
        int curr = 0;
        uint32_t ret = 0;
        uint32_t msk = 0xF;
        for(int i = 0; i < 8; i++) {
            ret = ret << 4;
            curr = msk&n;
            ret |= tb[curr];
            n = n >> 4;
        }
        return ret;
}
 */
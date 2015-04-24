/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
 */

// two pointers
public class Solution {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int netGas=gas[0]-cost[0];
    int i=0;
    int j=0;
    while(i!=gas.length){
      if(i>j) { // NOTE:!!!, need to add this condition
        j=i;
        netGas = gas[j] - cost[j];
      }
      while(netGas>=0){
        j = (j+1) % gas.length;
        if(j==i) return i;
        netGas += gas[j]-cost[j];
      }
      netGas -= gas[i] - cost[i];
      i++;
    }
    return -1;
  }
}

// A-->B-->C, A can go to B, so A add positive to B, A cannot go to C means B with positive added by A can not go to C
// So, starting from B, that is, without positive added by A must not be able to go to C
// So every index between old start and current index is bad, and we need to update start to be the current index.
public int canCompleteCircuit(int[] gas, int[] cost) {
  int sumRemaining = 0; // track current remaining
  int total = 0; // track total remaining
  int start = 0;

  for (int i = 0; i < gas.length; i++) {
    int remaining = gas[i] - cost[i];

    //if sum remaining of (i-1) >= 0, continue
    if (sumRemaining >= 0) {
      sumRemaining += remaining;
      //otherwise, reset start index to be current
    } else {
      sumRemaining = remaining;
      start = i;
    }
    total += remaining;
  }

  if (total >= 0){
    return start;
  }else{
    return -1;
  }
}
/*
Do a quick approximation of the number of bytes needed to store a timestamp value in microseconds
that will span until the beginning of 2020?.
Quick approximation: --> come up with an answer without doing complicated operations (i.e. no
calculator, just back of the envelope computations).

1s = 1000*1000ms
1min = 60s
1h = 60 m
1d = 24 h.
1y = 365d
1 timestamp = 6* 365*24*60*60*1000*1000.

Answer:
10^18
2^(3*18)=2^54
54/8 = 6
7 bytes
 */



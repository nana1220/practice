/*
Parse a formula string (only contains “+-()”, no "*" and “/“).
    For example,
    5 + 2x – ( 3y + 2x - ( 7 – 2x) – 9 ) = 3 + 4y
    Parse this string, with a given float of ‘x’ value, output a float for ‘y’ value.

    没有乘除法就没有优先级的问题了，直接一个pass把括号的符号展开就行。下面的代码没有考虑非法情况。
    其实挺简单的。每次遇到括号之前记录下符号，括号里面直接乘以这个括号
比如5 - (2 + 3)
括号之前是-号，记录一个-1，然后括号里面2 和 3直接乘以-1，变成5 + (-2) + (-3)。
 */
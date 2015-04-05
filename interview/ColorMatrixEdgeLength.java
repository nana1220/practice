/*
给你一个matrix，每个cell都有一个collor的标记在里面。给你任何一个点，返回这个点所在的同色的区域块组成的多边形的周长。
 */

// BFS, reach edge if no visited: length+1, total length is bianchang
/*
11X
1XX
XXX
    假如我要求1的那个色块的周长，
    如果用BFS每次遇到一个X就加1的话，这个东西的周长算出来是3.
    就算你把边框算进去再加4，那也是7，但是实际这个L型区域的周长是8
    也就是说那个凹口处的X其实并不只是加1 应该分情况讨论是加1还是加2或者是加3
*/
// 找到一个新的块的时候，把它加到queue里，然后判断下这个新块四边有几个是不同色的， YES!
// 就是这个新块贡献的周长。昨天把这个实现了下，
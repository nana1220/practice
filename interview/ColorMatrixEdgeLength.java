/*
给你一个matrix，每个cell都有一个collor的标记在里面。给你任何一个点，返回这个点所在的同色的区域块组成的多边形的周长。
 */

// BFS,

// 找到一个新的块的时候，把它加到queue里，然后判断下这个新块四边有几个是不同色的， YES!
// 就是这个新块贡献的周长。昨天把这个实现了下，
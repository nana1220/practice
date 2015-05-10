/*
给你一个target number，和一个list，list里面装的都是整数。问是否能用list里面的所有数字，只用四则运算和括号之类的，问能不能得到target number。很像24点，不过是它的扩展。
 */

// modified merge sort

class solu{
  def genNums(A):
      if not A: return A
  if len(A)==1: return  [A[0]]
  res = []
      for i in range(1, len(A)-1):
  left = genNums(A[:i])
  right = genNums(A[i:]). From 1point 3acres bbs
  for x in left:
      for y in right:
      res.extend([x+y, x-y, x*y, x/y])
      return res

  def getNum(target, A):
      return target in genNums(A)
}
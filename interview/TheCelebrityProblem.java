/*
In a party of N people, only one person is known to everyone. Such a person may be present in the party, if yes,
(s)he doesn’t know anyone in the party. We can only ask questions like “does A know B? “. Find the stranger (celebrity) in minimum number of questions.
可以分析几种情况

如果A认识B，那么A不可能是celebrity。去掉A，B则有可能是
如果A不认识B，那么B不可能是celebrity。去掉B，A则有可能是
重复以上两个步骤直到只剩下一个候选人
再次确认是否这最后一个人是否为celebrity
这里用stack来做。

把所有的celebrity压栈
弹出最上面的两个celebrity，根据HaveAcquaintance(A, B)的结果来去掉一个一定不是celebrity的人
将2中剩下的那一位压栈
重复以上两个步骤，直到stack中只剩一个人
确认这个人不认识其他任何人
以上算法需要调用HaveAcquaintance(A,B) 3(N-1)次。
 */

class Solu{
  int CelebrityUsingStack(int size) {
    // Handle trivial case of size = 2

    list<int> stack; // Careful about naming
    int i;
    int C; // Celebrity

    i = 0;
    while( i < size ) {
      stack.push_back(i);
      i = i + 1;
    }

    int A = stack.back();
    stack.pop_back();

    int B = stack.back();
    stack.pop_back();

    while( stack.size() != 1 ) {
      if( HaveAcquiantance(A, B) ) {
        A = stack.back();
        stack.pop_back();
      } else {
        B = stack.back();
        stack.pop_back();
      }
    }

    // Potential candidate?
    C = stack.back();
    stack.pop_back();

    // Last candidate was not examined, it leads one excess comparison (optimise)
    if( HaveAcquiantance(C, B) ) C = B;

    if( HaveAcquiantance(C, A) ) C = A;

    // I know these are redundant,
    // we can simply check i against C
    i = 0;
    while( i < size ) {
      if( C != i )
        stack.push_back(i);
      i = i + 1;
    }

    while( !stack.empty() ) {
      i = stack.back();
      stack.pop_back();

      // C must not know i
      if( HaveAcquiantance(C, i) ) return -1;

      // i must know C
      if( !HaveAcquiantance(i, C) ) return -1;
    }

    return C;
  }
}
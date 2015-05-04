/*
we have a random list of people. each person knows his own height and the number of tall people in front of him. write a code to make the equivalent queue.
for example :
input: <"Height","NumberOfTall","Name">,
<6,2,"A">,<1,4,"B">,<11,0,"C">,<5,1,"D">,<10,0,"E">,<4,0,"F">
output: "F","E","D","C","B","A" --> end of queue


 */


class solu{
//  1.Sort by "NumberOfTall" and "height":
//   (1)those who have fewer people taller in front of them have smaller index;
//  (2)for those having same number of tallers in front, the taller his own height is, the larger index he has.

  struct Person{
    string name;
    int height;
    int frontTaller;
  };
  struct Compare{
    bool operator()(const Person& a, const Person& b){
      if(a.frontTaller != b.frontTaller) return a.frontTaller < b.frontTaller;
      else return a.height < b.height;
    }
  };
//  2.Insert each person into the queue after counting his "NumberOfTaller":

  list<Person> retrieveQueue(vector<Person>& v)
  {
    sort(v.begin(), v.end(), Compare());
    list<Person> q;
    list<Person>::iterator iter;
    for(int i = 0; i < v.size(); ++i){
      iter = q.begin();
      for(int n = v[i].frontTaller; n > 0; ++iter){ // while(cout>0){}
        if(iter->height > v[i].height) --n;
      }
      q.insert(iter, v[i]);
    }
    return q;
  }
}




import java.util.Arrays;
import java.util.Comparator;

class Person {
  public int height, higherBefore;
  public Person(int h, int hb) {
    height = h;
    higherBefore = hb;
  }
};

public class Test {

  public static void swap(Person[] array, int i, int j) {
    if (array == null || array.length == 0 || i == j) return;
    Person tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  /*
   * greedy algorithm
   * sort by height, lineup from highest to lowest
   */
  public static void lineUp(Person[] array) {
    Comparator<Person> personComparator = new Comparator<Person>() {
      public int compare(Person p1, Person p2) {return p2.height - p1.height;}
    };
    Arrays.sort(array, personComparator);
    for (int i = 1; i < array.length; ++i) {
      int higherBefore = 0, shiftLeft = i;
      for (int j = 0; j < i; ++j)
        if (array[j].height > array[i].height) ++higherBefore;
      while (higherBefore > array[shiftLeft].higherBefore && shiftLeft > 0) {
        if (array[shiftLeft - 1].height > array[shiftLeft].height) --higherBefore;
        swap(array, shiftLeft, shiftLeft - 1);
        --shiftLeft;
      }
    }
  }

  public static void print(Person[] array) {
    System.out.print("Height: ");
    for (Person p : array)
      System.out.print(p.height + " ");
    System.out.print("\nHigher: ");
    for (Person p : array)
    System.out.print(p.higherBefore + " ");
    System.out.println("\n");
  }

  public static void main(String[] args) {
    // 8 3 5 6 9 1 2 7 4
    Person[] array = {new Person(5, 1), new Person(2, 1), new Person(9, 0),
        new Person(8, 0), new Person(4, 3), new Person(1, 5),
        new Person(7, 2), new Person(6, 1), new Person(3, 1)};
    print(array);
    lineUp(array);
    print(array);
  }
}
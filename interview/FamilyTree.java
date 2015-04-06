public class FamilyTree {

   /*
    * Given a family tree for a few generations for the entire population and two people write a
    * routine that will find out if they are blood related. Siblings are blood related since they
    * have the same parents. Cousins are blood related since one of their parents have the same
    * parents etc. Design the data structure first and then write the routine.
    */

  // http://www.careercup.com/question?id=4812957531766784

  // This is actually a "connected components" problem. The "family tree" is actually a DAG and If
  // two persons belong to the same connected component they are blood relatives.

  class Person {
    String name;
    ArrayList<Person> children;
    Person father;
    Person mother;

    public Person(String name) {
      this.name = name;
    }

    public ArrayList<Person> getParents(){
      ArrayList<Person> parents = new ArrayList<Person>();
      parents.add(father);
      parents.add(mother);
      return parents;
    }

    // Use BFS to search all parents of this's ancestors and store them
    // in a set. Then BFS search all parents of other's ancestors, once we find
    // that the two share common ancestor, they're blood related
    public boolean isBloodRelative(Person other){
      return false;
    }
  }
}

//solu2
public class Person {
  Person[] parents;
}

  // naming for cousins is: n th cousin m times removed
// where n is the min generations to a common ancestor and m is the number of generations difference between the 2 cousins
// so this is going to be O((2^n+m)+2) which is still more efficient than dfs assuming the num generations in the population is > n+m
  public boolean bloodRelated(Person p1, Person p2) {
    // simple search would go down p1's children/grandchildren/etc and see if we find p2
    // then vice versa
    // then worry about cousin style relationships
    // here we'd go up the parent tree on both until we found a common node (or ran out of data)

    // we could take this last approach anyway and it would get us a parent-child match too
    Set<Person> p1Ancestors = new HashSet<Person>();
    Set<Person> p2Ancestors = new HashSet<Person>();

    // so ideally here we're going to do BFS, but we're going to do 2 at once to try to minimise the depth we have to go
    List<Person> p1Discovered = new LinkedList<Person>();
    p1Discovered.add(p1);
    List<Person> p2Discovered = new LinkedList<Person>();
    p2Discovered.add(p2);

    while (!p1Discovered.isEmpty() || !p2Discovered.isEmpty()) {
      Person nextP1 = p1Discovered.remove(0);
      if (nextP1 != null) {
        if (p2Ancestors.contains(nextP1)) {
          return true;
        }

        for (Person parent : nextP1.parents) {
          p1Discovered.add(parent);
        }
        p1Ancestors.add(nextP1);
      }

      Person nextP2 = p2Discovered.remove(0);
      if (nextP2 != null) {
        if (p1Ancestors.contains(nextP2)) {
          return true;
        }

        for (Person parent : nextP2.parents) {
          p2Discovered.add(parent);
        }
        p2Ancestors.add(nextP2);
      }
    }
    return false;
  }
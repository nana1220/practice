package ch3stacksandqueues;

import java.lang.Object;
import java.util.LinkedList;

public class AnimalShelter {
  LinkedList<Cat> cats = new LinkedList<Cat>();
  LinkedList<Dog> dogs = new LinkedList<Dog>();

  void enqueue(Object animal) {
    if (animal instanceof Cat) {
      // or use add() which is equal to addLast()
      cats.addFirst((Cat) animal); // don't forget to convet type
    } else {
      dogs.addFirst((Dog) animal);
    }
  }

  Object dequeueAny() {
    if (cats.isEmpty() && dogs.isEmpty()) {
      return null;
    } else if (cats.isEmpty()) {
      // or use poll() which is equal to pollFirst()
      return dogs.pollLast();
    } else if (dogs.isEmpty()) {
      return cats.pollLast();
    } else if (cats.getLast().arrivalTime < dogs.getLast().arrivalTime) {
      return cats.pollLast();
    } else {
      return dogs.pollLast();
    }
  }

  Object dequeueDog() {
    if (!dogs.isEmpty()) {
      return dogs.pollLast();
    }
    return null;
  }

  Object dequeueCat() {
    if (!cats.isEmpty()) {
      return cats.pollLast();
    }
    return null;
  }

  static class Cat {
    int arrivalTime;
  }

  static class Dog {
    int arrivalTime;
  }
}

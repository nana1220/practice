package ch8objectorienteddesign;

import java.util.List;
/*
 * Imagine you have a call center with three levels of employees: respondent, manager, and
 * director. An incoming telephone call must be first allocated to a respondent who is free. If
 * the respondent cannot handle the call, he or she must escalate the call to a manager. If the
 * manager is not free or not be able to handle it, then the call should be escalated to
 * director. Design the classes and data structures for this problem. Implement a method
 * dispatchCall() which assigns a call to the first available employee.
 */

class CallHandler { // singleton class

  private static CallHandler handler = null;
  private List<List<Call>> callQueues;
  private List<List<Employee>> employees;
  private CallHandler() {}
  public CallHandler getInstance() {
    if (handler == null) handler = new CallHandler();
    return handler;
  }
  Employee getNextAvailabe() {return employees.get(0).get(0);}
  void dispatchCall(Call call) {}
}

enum Rank {
  RESPONDENT(0), MANAGER(1), DIRECTOR(2);
  private int rank;

  private Rank(int rank) {
    this.rank = rank;
  }

  public int getValue() {
    return rank;
  }
}

class Call {
  Rank rank;
  int phoneNumber;
  Employee handler;
}

abstract class Employee {
  private boolean isAvailable;
  private String name;
  private Rank rank;

  boolean isAvailable() {
    return isAvailable;
  };
  String name() {return name;}
}

class Respondent extends Employee {}
class Manager extends Employee {}
class Director extends Employee {}


package ch8objectorienteddesign;

/*
 * Explain the data structures and algorithms that you would use to design an
 * in-memory file system. Illustrate with an example in code where possible.
 */

import java.util.ArrayList;

abstract class Entry {
  protected Directory parent;
  protected long created;
  protected long lastUpdated;
  protected long lastAccessed;
  protected String name;

  public Entry(String n, Directory p) {
    name = n;
    parent = p;
    created = System.currentTimeMillis();
  }

  public boolean delete() {
    if (parent == null) {
      return false;
    }
    return parent.deleteEntry(this);
  }

  public abstract int size();

  public String getFullPath() {
    if (parent == null) {
      return name;
    } else {
      return parent.getFullPath() + "/" + name;
    }
  }

  public long getCreationTime() {
    return created;
  }

  public long getLastUpdatedTime() {
    return lastUpdated;
  }

  public long getLastAccessedTime() {
    return lastAccessed;
  }

  public void changeName(String n) {
    name = n;
  }

  public String getName() {
    return name;
  }
}

class File extends Entry {
  private String content;
  private int size;

  public File(String n, Directory p, int sz) {
    super(n, p);
    size = sz;
  }

  public int size() {
    return size;
  }

  public String getContents() {
    return content;
  }

  public void setContents(String c) {
    content = c;
  }
}

class Directory extends Entry {
  protected ArrayList<Entry> contents;

  public Directory(String n, Directory p) {
    super(n, p);
    contents = new ArrayList<Entry>();
  }

  protected ArrayList<Entry> getContents() {
    return contents;
  }

  public int size() {
    int size = 0;
    for (Entry e : contents) {
      size += e.size();
    }
    return size;
  }

  public int numberOfFiles() {
    int count = 0;
    for (Entry e : contents) {
      if (e instanceof Directory) {
        count++; // Directory counts as a file
        Directory d = (Directory) e;
        count += d.numberOfFiles();
      } else if (e instanceof File) {
        count++;
      }
    }
    return count;
  }

  public boolean deleteEntry(Entry entry) {
    return contents.remove(entry);
  }

  public void addEntry(Entry entry) {
    contents.add(entry);
  }
}
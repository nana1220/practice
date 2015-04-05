/*
Lookup(key) -> (server, start_key, limit_key) :
 start_key <= key < limit_key, and 'server' can handle all keys key2 where start_key <= key2 < limit_key
server.BatchWrite(sequence of (key, value) pairs) -> sequence of result,
 matching up with the argument (key,value) pairs, where each result is OK or WRONG_SERVER

implement
void ReliableWrite(String key, String value)
void WriteMany(List<KV> pairs)
 */

public class Client {
  void ReliableWrite(String key, String value) {
    server, start_key, limit_key = lookup(key);
    server.BatchWrite((key, value));
  }

  void WriteMany(List<KV> pairs) {
    while(!pairs.Empty()){
      server, start_key, limit_key = lookup(pairs.getFirst());
      for all key in (start_key, limit_key)
          keys, values = pairs.poll()
          server.BatchWriter(keys, values);

    }
  }
}
/*
You have k lists of sorted integers. Find the smallest range that includes at least one number from each of the k lists.

For example,
List 1: [4, 10, 15, 24, 26]
List 2: [0, 9, 12, 20]
List 3: [5, 18, 22, 30]

The smallest range here would be [20, 24] as it contains 24 from list 1, 20 from list 2, and 22 from list 3.
 */

// 实际上就是k路归并的变种题。维护一个长度为n的min heap（n为数组个数），每次找一个最小的，同时保持记录一个最大的
// store value together with array id into heap, so that we know value being poped belong to which array
class Solu{
  pair<int,int> min_range(const vector<vector<int>>& nums) {
    // first: array id, second: element id
    using _pair = pair<int,int>;
    priority_queue<_pair,vector<_pair>,function<bool(_pair&,_pair&)>> heap {
      [&](_pair& a, _pair& b) { return nums[a.first][a.second] >= nums[b.first][b.second]; }
    };

    int cur_max = INT_MIN;
    for(auto i = 0; i < nums.size(); ++i) {
      if(nums[i].empty()) continue;
      heap.emplace(i, 0);
      cur_max = std::max(cur_max, nums[i][0]);
    }
    if(heap.empty()) return {-1, -1};

    int begin = nums[heap.top().first][heap.top().second];
    int min_len = cur_max - begin;

    while (heap.size() == nums.size()) { // stop when one of arrays is empty
      auto top = heap.top();
      heap.pop();
      if(top.second + 1 < nums[top.first].size()) {
        int new_begin = nums[heap.top().first][heap.top().second];
        int new_num = nums[top.first][top.second + 1];
        new_begin = std::min(new_begin, new_num);
        cur_max = std::max(cur_max, new_num);
        if(cur_max - new_begin < min_len) {
          begin = new_begin;
          min_len = cur_max - new_begin;
        }
        heap.emplace(top.first, top.second + 1);
      }
    }
    return {begin, begin + min_len};
  }
}


class Solution{
  public static Result findCoveringRange(List<List<Integer>> lists) {
    Result result = null;

    int start = -1, end = -1;
    int rDiff = Integer.MAX_VALUE;
    int k = lists.size();

    PriorityQueue<Data> pQueue = new PriorityQueue<Data>();
    SortedSet<Data> entries = new TreeSet<Data>();
    Map<Integer, Data> listNoAndEntry = new HashMap<Integer, Data>();

    for (int i = 0; i < k; i++)
      pQueue.add(new Data(lists.get(i).remove(0), i));

    while (!pQueue.isEmpty()) {
      Data minData = pQueue.remove();
      if (lists.get(minData.listNo).size() > 0)
        pQueue.add(new Data(lists.get(minData.listNo).remove(0),
            minData.listNo));

      if (listNoAndEntry.size() == k) {

        Data first = entries.first();
        if ((entries.last().data - first.data) + 1 < rDiff) {
          start = first.data;
          end = entries.last().data;
        }
        entries.remove(first);
        listNoAndEntry.remove(first.listNo);
      }

      if (listNoAndEntry.containsKey(minData.listNo))
        entries.remove(listNoAndEntry.remove(minData.listNo));

      listNoAndEntry.put(minData.listNo, minData);
      entries.add(minData);
    }

    if (listNoAndEntry.size() == k) {

      Data first = entries.first();
      if ((entries.last().data - first.data) + 1 < rDiff) {
        start = first.data;
        end = entries.last().data;
      }
      entries.remove(first);
      listNoAndEntry.remove(first.listNo);
    }

    result = new Result(start, end);
    return result;
  }

}

class Result {
  public final int startRange, endRange;

  public Result(int startRange, int endRange) {
    this.startRange = startRange;
    this.endRange = endRange;
  }
}

class Data implements Comparable<Data> {
  public final int data;
  public final int listNo;

  public Data(int data, int listNo) {
    this.data = data;
    this.listNo = listNo;
  }

  @Override
  public int compareTo(Data o) {
    // TODO Auto-generated method stub
    return data - o.data;
  }
}
}
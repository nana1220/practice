class Solu{
  // get next node whose val is bigger than val
  Node greater=null;
  Node cur=root;
  while(cur!=null) {
    if(cur.val<=target) { cur=cur.right; }
    else { greater=cur; cur=cur.left; }
  }
  if (greater==null) // no result
      else return greater.val;

}
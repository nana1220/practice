//Merge stage of external mergesort
//Read from input file, already sorted into blocks of size blockSize
//Write to output file, sorted into blocks of 2*blockSize
public class ExternalSort {
  public static void merge(String inputFile, String outputFile, long blockSize)
      throws IOException {

    DataInputStream dis1 = new DataInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
    DataInputStream dis2 = new DataInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));

    // merging 2 sub lists
    // go along pairs of blocks in inputFile
    // continue until end of input

    //initialise block2 at right position
    dis2.skipBytes((int) blockSize);

    //while we haven't reached the end of the file
    while (dis1.available() > 0) {
      // if block1 is last block, copy block1 to output
      if (dis2.available() <= 0) {
        while (dis1.available() > 0)
          dos.writeInt(dis1.readInt());
        break;
      }
      // if block1 not last block, merge block1 and block2
      else {
        long block1Pos = 0;
        long block2Pos = 0;
        boolean block1Over = false;
        boolean block2Over = false;

        //data read from each block
        int e1 = dis1.readInt();
        int e2 = dis2.readInt();

        //keep going until fully examined both blocks
        while (!block1Over | !block2Over) {
          //copy from block 1 if:
          //  block1 hasnt been fully examined AND
          //  block1 element less than block2s OR block2 has been fully examined
          while (!block1Over & ((e1 <= e2) | block2Over)) {
            dos.writeInt(e1);
            block1Pos += 4;
            if (block1Pos < blockSize & dis1.available() > 0)
              e1 = dis1.readInt();
            else
              block1Over = true;
          }
          //same for block2
          while (!block2Over & ((e2 < e1) | block1Over)) {
            dos.writeInt(e2);
            block2Pos += 4;
            if (block2Pos < blockSize & dis2.available() > 0)
              e2 = dis2.readInt();
            else
              block2Over = true;
          }
        }
      }
      // skip to next blocks
      dis1.skipBytes((int) blockSize);
      dis2.skipBytes((int) blockSize);
    }
    dis1.close();
    dis2.close();
    dos.close();
    fos.close();
  }
}
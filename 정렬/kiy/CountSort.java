public class CountSort {

  public static void main(String[] args) {
    int[] array = {7,5,9,0,3,1,6,2,9,1,4,8,0,5,2};
    int maxValue = 0;
    for(int a: array) {
      if(a > maxValue) maxValue = a;
    }
    int[] count = new int[maxValue + 1];

    for(int i=0; i<array.length; i++)
      count[array[i]]++;

    for(int i=0; i<count.length; i++) {
      for(int j=0; j<count[i]; j++){
        System.out.print(i + " ");
      }
    }
  }
}

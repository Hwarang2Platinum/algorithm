import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr;
    static int r,c,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());
        arr = new int[101][101];
        for(int i=0;i<3;i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int length_r = 3; int length_c = 3;
        int t;
        for(t=0;t<100;t++){
            if(arr[r][c]==k) break;

            if(length_r>=length_c){
                for(int i=0;i<length_r;i++){
                    PriorityQueue<Pair> pq = new PriorityQueue<>();
                    Pair[] tmp = new Pair[100];
                    for (int j = 0; j < 100; j++) {
                        tmp[j] = new Pair(j,0);
                    }
                    for (int j = 0; j < 100; j++) {
                        tmp[arr[i][j]].cnt+=1;
                    }
                    Arrays.sort(tmp);

                    for(int j=0;j<100;j++){
                        arr[i][j] = 0;
                    }
                    int j=0;
                    for(Pair p:tmp){
                        if(j>=100) break;
                        if(p.num==0 || p.cnt == 0) continue;
                        arr[i][j++] = p.num;
                        arr[i][j++] = p.cnt;
                    }
                    length_c = Math.max(j,length_c);
                }
            }else{
                for(int i=0;i<length_c;i++){
                    Pair[] tmp = new Pair[100];
                    for (int j = 0; j < 100; j++) {
                        tmp[j] = new Pair(j,0);
                    }
                    for (int j = 0; j < 100; j++) {
                        tmp[arr[j][i]].cnt+=1;
                    }
                    Arrays.sort(tmp);

                    for(int j=0;j<100;j++){
                        arr[j][i] = 0;
                    }
                    int j=0;
                    for(Pair p:tmp){
                        if(j>=100) break;
                        if(p.num==0 || p.cnt ==0) continue;
                        arr[j++][i] = p.num;
                        arr[j++][i] = p.cnt;
                    }
                    length_r = Math.max(j,length_r);
                }
            }
        }

        System.out.println(t==100?-1:t);
    }

    static class Pair implements Comparable<Pair>{
        int num, cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            return this.cnt == o.cnt?this.num-o.num:this.cnt-o.cnt;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "num=" + num +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
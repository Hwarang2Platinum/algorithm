import java.io.*;
import java.util.*;

public class BOJ_BST_2805_HR {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int numTree = Integer.parseInt(st.nextToken());
        long wantTree = Long.parseLong(st.nextToken());

        long [] treeHeight = new long [numTree];
        st = new StringTokenizer(input.readLine());
        long maxHeight = 0;
        for (int i = 0 ; i < numTree ; i++) {
            treeHeight[i] = Long.parseLong(st.nextToken());
            if (treeHeight[i] > maxHeight)
                maxHeight = treeHeight[i];
        }

        long start = 0;
        long end = maxHeight;
        long mid = 0;
        long result = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (getTree(treeHeight,mid) > wantTree)
                start = mid + 1;
            else if (getTree(treeHeight,mid) < wantTree)
                end = mid - 1;
            else {
                result = mid;
                break;
            }
            result = end;
        }
        System.out.println(result);
    }
    public static long getTree(long[] treeHeight, long cutHeight) {
        long getTree = 0;
        for (int i = 0 ; i < treeHeight.length ; i++){
            if (treeHeight[i] - cutHeight > 0)
                getTree += (treeHeight[i] - cutHeight);
        }
        return getTree;
    }
}

// 나무의 높이가 영향을 줄 수 있는 코드로 구성? => Problem이 발생할 수 있음.
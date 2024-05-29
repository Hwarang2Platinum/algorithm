import java.util.*;

class P_정수삼각형_이찬민 {
    static int[][] dist;

    public int solution(int[][] triangle) {
        int len = triangle.length;
        dist = new int[len][len];

        for(int i=0; i<len; i++){
            for(int j=0; j<=i; j++){
                dist[i][j] = triangle[i][j];

                if(j==0 && i!=0){
                    dist[i][j] = dist[i-1][j]+triangle[i][j];
                }else if(j==i && i!=0){
                    dist[i][j] = dist[i-1][j-1]+triangle[i][j];
                }
            }
        }

        for(int i=2; i<len; i++){
            for(int j=1; j<i; j++){
                dist[i][j] = Math.max(dist[i-1][j-1],dist[i-1][j]) + dist[i][j];
            }
        }
        int result =0;
        for(int i=0; i<len; i++){
            result = Math.max(result,dist[len-1][i]);
        }
        return result;

        // for(int i=0; i<len; i++){
        //     for(int j=0; j<len; j++){
        //         System.out.print(dist[i][j]+" ");
        //     }
        //     System.out.println();
        // }
    }
}
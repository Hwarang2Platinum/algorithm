import java.util.*;
import java.io.*;

public class B12865_평범한배낭_HR {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(input.readLine());
        int num = Integer.parseInt(st.nextToken());
        int bagMax = Integer.parseInt(st.nextToken());
        int [][] wvList = new int[num + 1][2];
        int [][] madeList = new int [num + 1][bagMax + 1];

        for (int i = 1 ; i <= num ; i++) {
            st = new StringTokenizer(input.readLine());
            wvList[i][0] = Integer.parseInt(st.nextToken()); // weight
            wvList[i][1] = Integer.parseInt(st.nextToken()); // value
        }
        int temp;
        for (int i = 1 ; i <= num ; i++) {
            for (int w = 1 ; w <= bagMax ; w++) {
                if (wvList[i][0] <= w) { // 怨좊Ⅸ 臾쇨굔�쓽 臾닿쾶媛� �쁽�옱 媛�諛� �슜�웾蹂대떎 minSame => �꽑�깮 or Not
                    temp = wvList[i][1] + madeList[i - 1][w - wvList[i][0]]; // 怨좊Ⅸ 臾쇨굔 �꽑�깮
                    if (temp > madeList[i -1][w]) // �꽑�깮 > �꽑�깮X
                        madeList[i][w] = temp;
                    else
                        madeList[i][w] = madeList[i -1][w];
                }
                else
                    madeList[i][w] = madeList[i-1][w];
            }
        }
        System.out.println(madeList[num][bagMax]);
    }
}

// �뱾�뼱�삩 Input
// 6 13
// 4 8
// 3 6
// 5 12

// Weight 7�쓽 MindSet
// 7 -> f(4, 7) : 4媛쒖쓽 臾쇨굔, 7kg �궡�쇅�쓽 maxValue
// 6 13 �꽑�깮 -> f(3,1) : 3媛쒖쓽 臾쇨굔, 1kg �궡�쇅�쓽 maxValue Problem
// 6 13 �꽑�깮X -> f(3,7) : 3媛쒖쓽 臾쇨굔, 7kg �궡�쇅�쓽 maxValue Problem
// 4 8 �꽑�깮 -> f(3, 3) : 3媛쒖쓽 臾쇨굔, 3kg �궡�쇅�쓽 maxValue Problem
// 4 8 �꽑�깮X -> f(3, 7) : 3媛쒖쓽 臾쇨굔, 7kg �궡�쇅�쓽 maxValue Problem

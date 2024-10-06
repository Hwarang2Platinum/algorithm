import java.util.*;
import java.io.*;

public class B3107_IPv6_조승기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayList<String> li = new ArrayList<>();
        int idx = -1;
        li.add("");

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ':' && line.charAt(i+1) == ':') {
                idx = li.size();
                i += 1;
                if (!li.get(li.size() - 1).isEmpty()) {
                    li.add("");
                    idx += 1;
                }
                continue;
            }

            if (c == ':') {
                li.add("");
                continue;
            }

            li.set(li.size() - 1, li.get(li.size() - 1) + c);
        }

        while (li.size() < 8) {
            li.add(idx-1, "0000");
        }

        for (int i = 0; i < li.size(); i++) {
            while (li.get(i).length() < 4) {
                li.set(i, "0" + li.get(i));
            }
        }

        System.out.print(li.get(0));
        for (int i = 1; i < li.size(); i++) {
            System.out.print(":" + li.get(i));
        }

    }
}

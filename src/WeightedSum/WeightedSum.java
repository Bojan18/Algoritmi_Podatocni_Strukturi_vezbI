package WeightedSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WeightedSum {

    //Sample input
    //10 3; 3 = k, 10 = n
    //1 9 2 3 6 1 3 2 1 3
    //Sample output
    //37
    //
    //1*1 + 2*9 + 3*2 + 4*3 = 1+18+6+12 = 37

    static int solve(int numbers[], int N, int K) {

        int sum = 0;
        int a = 1;
        //1 2 3 4 5
        for (int i = 0; i < K; i++) {
            sum += a * numbers[i];
            a++;
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }
}

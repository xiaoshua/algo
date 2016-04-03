import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author xiaoshua
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            final int N = 109;
            int[][] a = new int[N][N];
            int[][] dp = new int[N][N];
            int re = in.nextInt();
            for (int ri = 1; ri <= re; ++ri) {
                int n = in.nextInt();
                for (int i = 0; i < n; ++i) {
                    for (int j = 0; j <= i; ++j) {
                        a[i][j] = in.nextInt();
                    }
                }
                System.arraycopy(a[n - 1], 0, dp[n - 1], 0, n);
                for (int i = n - 2; i >= 0; --i) {
                    for (int j = 0; j <= i; ++j) {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + a[i][j];
                    }
                }
                out.println(dp[0][0]);
            }
        }

    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

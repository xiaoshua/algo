import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
            while (true) {
                int n = in.nextInt();
                if (n == 0) {
                    break;
                }
                List<Integer> fixedBaseList = new ArrayList<>();
                for (int radix = 2; radix <= 16; ++radix) {
                    String str = Integer.toString(n, radix);
                    String revStr = new StringBuilder(str).reverse().toString();
                    if (str.equals(revStr)) {
                        fixedBaseList.add(radix);
                    }
                }
                if (fixedBaseList.size() == 0) {
                    out.printf("Number %d is not a palindrom", n);
                } else {
                    out.printf("Number %d is palindrom in basis", n);
                    for (Integer base : fixedBaseList) {
                        out.printf(" %d", base);
                    }
                }
                out.println();
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

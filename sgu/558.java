import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
            int n = in.nextInt();
            Queue<Dragon> dragons = new PriorityQueue<>(n);
            int coinsGet = 0;
            boolean getLastPrincess = false;
            for (int i = 2; i <= n; ++i) {
                String cmd = in.next();
                int curCoins = in.nextInt();
                if (cmd.equals("d")) {
                    dragons.add(new Dragon(i, curCoins));
                    coinsGet += curCoins;
                } else {
                    if (i < n) {
                        while (!dragons.isEmpty() && dragons.size() >= curCoins) {
                            Dragon dragon = dragons.poll();
                            coinsGet -= dragon.getCoins();
                        }
                    } else {
                        getLastPrincess = dragons.size() >= curCoins;
                    }
                }
            }
            if (getLastPrincess) {
                List<Integer> positionList = new ArrayList<>(dragons.size());
                for (Dragon dragon : dragons) {
                    positionList.add(dragon.getPosition());
                }
                Collections.sort(positionList);
                out.println(coinsGet);
                out.println(positionList.size());
                boolean first = true;
                for (Integer position : positionList) {
                    if (!first) {
                        out.print(" ");
                    }
                    first = false;
                    out.print(position);
                }
                out.println();
            } else {
                out.println("-1");
            }
        }

    }

    static class Dragon implements Comparable<Dragon> {
        private final int position;
        private final int coins;

        public int getPosition() {
            return position;
        }

        public int getCoins() {
            return coins;
        }

        public Dragon(int position, int coins) {
            this.position = position;
            this.coins = coins;
        }

        @Override
        public int compareTo(Dragon another) {
            return Integer.compare(this.coins, another.coins);
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

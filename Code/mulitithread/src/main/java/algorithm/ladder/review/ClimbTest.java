package algorithm.ladder.review;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ClimbTest {

    public static void main(String[] args) {
        ClimbTest test = new ClimbTest();
        List<String> words = new ArrayList<>();
        words.add("dog");
        words.add("dad");
        words.add("dgdg");
        words.add("can");
        char[][] board = new char[3][4];
        board[0] = "doaf".toCharArray();
        board[1] = "agai".toCharArray();
        board[2] = "dcan".toCharArray();

        int res = test.wordSearchIII(board, words);
        log.info("结果为：{}", res);

    }

    int count = 0;

    public int wordSearchIII(char[][] board, List<String> words) {
        if (words == null || words.size() == 0 || board == null
                || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int m = board.length;
        int n = board[0].length;

        TrieTree tree = new TrieTree();
        for (String word : words) {
            tree.insert(word);
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<String> resultSet = new HashSet<>();
                dfsSearch(i, j, board, resultSet, tree.root);
            }
        }

        return count;
    }

    private void dfsSearch(int x, int y, char[][] board, Set<String> resultSet,
                           TreeNode node) {
        char curChar = board[x][y];
        board[x][y] = 0;
        if (!node.childern.containsKey(curChar)) {
            return;
        }
        TreeNode curNode = node.childern.get(curChar);
        if (curNode.word != null) {
            resultSet.add(curNode.word);
            if (resultSet.size() > count) {
                count = resultSet.size();
            }
        }
        for (int i = 0; i < 4; i++) {
            int nextx = x + dx[i];
            int nexty = y + dy[i];
            if (!inBound(nextx, nexty, board) || board[nextx][nexty] == 0) {
                continue;
            }
            dfsSearch(nextx, nexty, board, resultSet, curNode);
        }
        board[x][y] = curChar;
    }

    private static int[] dx = new int[]{1, 0, -1, 0};
    private static int[] dy = new int[]{0, 1, 0, -1};

    private boolean inBound(int x, int y, char[][] board) {
        int m = board.length;
        int n = board[0].length;
        if (x >= 0 && y >= 0 && x < m && y < n) {
            return true;
        }
        return false;
    }

    class TrieTree {

        private TreeNode root = new TreeNode();

        private void insert(String word) {
            TreeNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char wc = word.charAt(i);
                if (cur.childern.containsKey(wc)) {
                    cur = cur.childern.get(wc);
                } else {
                    TreeNode child = new TreeNode();
                    cur.childern.put(wc, child);
                    cur = child;
                }
            }
            cur.word = word;
        }

    }


    class TreeNode {
        private String word;
        private Map<Character, TreeNode> childern = new HashMap<>();
    }
}

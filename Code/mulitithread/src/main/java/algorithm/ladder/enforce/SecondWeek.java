package algorithm.ladder.enforce;

import java.util.*;

public class SecondWeek {


    public static void main(String[] args) {
        SecondWeek secondWeek = new SecondWeek();
        char[][] board = new char[3][4];
        board[0][0] = 'd';
        board[0][1] = 'o';
        board[0][2] = 'a';
        board[0][3] = 'f';

        board[1][0] = 'a';
        board[1][1] = 'g';
        board[1][2] = 'a';
        board[1][3] = 'i';

        board[2][0] = 'd';
        board[2][1] = 'c';
        board[2][2] = 'a';
        board[2][3] = 'n';
        List<String> words = new ArrayList<>();
        words.add("dog");
        List<String> res = secondWeek.wordSearchII(board, words);
        System.out.println(Arrays.toString(res.toArray()));
    }

    //son_id,father_id
//    private Map<Integer, Integer> father = new HashMap<>();

//    public List<List<String>> accountsMerge(List<List<String>> accounts) {
//        //init email-id relation
//        Map<String, List<Integer>> emailIdMap = getEmailToID(accounts);
//        System.out.println(Arrays.toString(emailIdMap.keySet().toArray()));
//        //union the id with same email
//        for (Map.Entry<String, List<Integer>> emailIdEntry : emailIdMap.entrySet()) {
//            List<Integer> idSet = emailIdEntry.getValue();
//            int fatherid = idSet.get(0);
//            for (int i = 1; i < idSet.size(); i++) {
//                int sonid = idSet.get(i);
//                union(sonid, fatherid);
//            }
//        }
//
//        //merge different id with the same email(the same father id)
//        //fatherid , email list
//        Map<Integer, Set<String>> idToEmailMap = getIdToEmailMap(emailIdMap);
//
//        List<List<String>> ans = new ArrayList<>();
//        for (Map.Entry<Integer, Set<String>> faheridEmailMap : idToEmailMap.entrySet()) {
//            int fatherid = faheridEmailMap.getKey();
//            List<String> emailList = new ArrayList<>(faheridEmailMap.getValue());
//            Collections.sort(emailList);
//            emailList.add(0, accounts.get(fatherid).get(0));
//            ans.add(emailList);
//        }
//        return ans;
//    }

//    private Map<Integer, Set<String>> getIdToEmailMap(Map<String, List<Integer>> emailIdMap) {
//        Map<Integer, Set<String>> ans = new HashMap<>();
//        for (Map.Entry<String, List<Integer>> emailIdEntry : emailIdMap.entrySet()) {
//            String email = emailIdEntry.getKey();
//            List<Integer> idList = emailIdEntry.getValue();
//            for (Integer id : idList) {
//                //将email对应的所有账户id集中到fatherid上
//                int fatherid = find(id);
//                Set<String> emailList = ans.getOrDefault(fatherid,  new HashSet<>());
//                emailList.add(email);
//                ans.put(fatherid,emailList);
//            }
//        }
//        return ans;
//    }


//    private Map<String, List<Integer>> getEmailToID(List<List<String>> accounts) {
//        Map<String, List<Integer>> emailIdMap = new HashMap<>();
//        int i = 0;
//        for (List<String> account : accounts) {
//            for (int j = 1; j < account.size(); j++) {
//                String emailId = account.get(j);
//                List<Integer> idSet = emailIdMap.getOrDefault(emailId, new ArrayList<>());
//                idSet.add(i);
//                emailIdMap.put(emailId, idSet);
//            }
//            i++;
//        }
//        return emailIdMap;
//    }

    //find root father id
//    private int find(int id) {
//        List<Integer> path = new ArrayList<>();
//        while (father.containsKey(id)) {
//            path.add(id);
//            id = father.get(id);
//        }
//        //path condense
//        for (Integer sonid : path) {
//            father.put(sonid, id);
//        }
//        return id;
//    }

//    private void union(int aid, int bid) {
////        int arootid = find(aid);
////        int brootid = find(bid);
////        if (arootid != brootid) {
////            father.put(arootid, brootid);
////        }
//    }


    class TrieNode {
        private char aChar;
        private Map<Character, TrieNode> childern = new HashMap<>();
        private boolean isword;
    }

    private TrieNode root;

//    public Trie() {
//        // do intialization if necessary
//        root = new TrieNode();
//    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        // write your code here
        char[] wordCharArr = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < wordCharArr.length; i++) {
            char c = wordCharArr[i];
            if (cur.childern.containsKey(c)) {
                cur = cur.childern.get(c);
            } else {
                TrieNode newNode = new TrieNode();
                newNode.aChar = c;
                cur.childern.put(c, newNode);
                cur = newNode;
            }
            if (i == wordCharArr.length - 1) {
                cur.isword = true;
            }
        }
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
//    public boolean search(String word) {
//        // write your code here
//        char[] wordCharArr = word.toCharArray();
//        TrieNode cur = root;
//        for (char c : wordCharArr) {
//            if (cur.childern.containsKey(c)) {
//                cur = cur.childern.get(c);
//            } else {
//                return false;
//            }
//        }
//        return cur.isword;
//    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // write your code here
        char[] preCharArr = prefix.toCharArray();
        TrieNode cur = root;
        for (char c : preCharArr) {
            if (!cur.childern.containsKey(c)) {
                return false;
            } else {
                cur = cur.childern.get(c);
            }
        }
        return true;
    }


    public class Node {

        private char aChar;
        private Map<Character, Node> children = new HashMap<>();
        private boolean isword;

    }

    private Node rootChar = new Node();

    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    public void addWord(String word) {
        // write your code here
        char[] chars = word.toCharArray();
        Node cur = rootChar;
        for (char c : chars) {
            if (cur.children.containsKey(c)) {
                cur = cur.children.get(c);
            } else {
                Node cNode = new Node();
                cNode.aChar = c;
                cur.children.put(c, cNode);
                cur = cNode;
            }
        }
        cur.isword = true;
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        // write your code here
        char[] chars = word.toCharArray();
        return search(chars, 0, rootChar);
    }

    private boolean search(char[] chars, int index, Node rootChar) {
        Node cur = rootChar;
        for (int i = index; i < chars.length; i++) {
            char ac = chars[i];
            if (cur.children.containsKey(ac)) {
                cur = cur.children.get(ac);
                index++;
            } else if (ac == '.') {
                Collection<Node> values = cur.children.values();
                boolean res = false;
                ++i;
                for (Node node : values) {
                    res = res || search(chars, i, node);
                }
                return res;
            } else {
                return false;
            }

        }
        return index == chars.length && cur.isword;
    }


    public class Connection {

        public String city1, city2;
        public int cost;
        public Connection(String city1, String city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }

    }

    private int[] father;

    private Map<String, Integer> cityMap = new HashMap<>();

    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        // Write your code here
        List<Connection> res = new ArrayList<>();
        //按照cost -> city1 ->city2排序
        Collections.sort(connections, (o1, o2) -> {
            if (o1.cost == o2.cost) {
                if (o1.city1.equals(o2.city1)) {
                    return o1.city2.compareTo(o2.city2);
                }
                return o1.city1.compareTo(o2.city1);
            }
            return o1.cost - o2.cost;
        });
        int n = 0;
        //维护city名称与自增id的关系
        for (Connection connection : connections) {
            if (!cityMap.containsKey(connection.city1)) {
                cityMap.put(connection.city1, ++n);
            }
            if (!cityMap.containsKey(connection.city2)) {
                cityMap.put(connection.city2, ++n);
            }
        }
        father = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            father[i] = i;
        }

        for (Connection connection : connections) {
            int city1root = findFather(cityMap.get(connection.city1));
            int city2root = findFather(cityMap.get(connection.city2));
            if (city1root != city2root) {
                father[city1root] = city2root;
                res.add(connection);
            }
        }
        if (res.size() != n - 1) {
            return new ArrayList<>();
        }
        return res;
    }

    private int findFather(int a) {
        if (father[a] == a) {
            return a;
        }
        return father[a] = findFather(father[a]);
    }


    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
        TrieTree tree = new TrieTree();
        for (String word : words) {
            //维护字典树
            tree.insert(word);
        }
        int n = board.length;
        int m = board[0].length;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //dfs遍历矩阵搜索单词，将其与单词树中的单词进行匹配
                dfsSearch(i, j, board, res, tree.root);
            }
        }
        return res;

    }

    public void dfsSearch(int x, int y, char[][] board, List<String> res, TreeNode cur) {
        char temp = board[x][y];
        if (!cur.children.containsKey(temp)) {
            return;
        }
        TreeNode child = cur.children.get(temp);
        if (child.word != null) {
            res.add(child.word);
        }
        for (int i = 0; i < 4; i++) {
            int nextx = x + dx[i];
            int nexty = y + dy[i];
            if (inside(nextx, nexty, board)) {
                board[x][y] = 0;
                dfsSearch(nextx, nexty, board, res, child);
                board[x][y] = temp;
            }
        }


    }

    private boolean inside(int nextx, int nexty, char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if (nextx >= 0 && nexty >= 0 && nextx < n && nexty < m && board[nextx][nexty] != 0) {
            return true;
        }
        return false;
    }

    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};

    public class TrieTree {
        private TreeNode root = new TreeNode();

        public void insert(String word) {
            TreeNode cur = root;
            char[] chars = word.toCharArray();
            for (char aChar : chars) {
                if (cur.children.containsKey(aChar)) {
                    cur = cur.children.get(aChar);
                } else {
                    TreeNode node = new TreeNode();
                    cur.children.put(aChar, node);
                    cur = node;
                }
            }
            cur.word = word;
        }


    }

    public class TreeNode {

        private String word;

        private Map<Character, TreeNode> children = new HashMap<>();

    }

}

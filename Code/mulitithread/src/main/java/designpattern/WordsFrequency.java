package designpattern;

import java.util.Map;

public class WordsFrequency {

    private Map<String, Integer> cache;

    public WordsFrequency() {
//        cache = new HashMap<>();
//        for (String s : book) {
//            Integer count = cache.get(s);
//            cache.put(s, count == null ? 1 : ++count);
//        }
    }

    public int get(String word) {

        return cache.get(word) == null ? 0 : cache.get(word);
    }

    public static void main(String[] args) {
        String[] arr = new String[3];
        arr[0] = "OX ";
        arr[1] = "OO ";
        arr[2] = "XXO";
        WordsFrequency wordsFrequency = new WordsFrequency();
        wordsFrequency.tictactoe(arr);
    }

    public String tictactoe(String[] board) {
        //判断是否有玩家获胜
        boolean hasEmpty = false;
        boolean hasWon = false;
        String res = null;
        for (int i = 0; i < board.length; i++) {
            boolean rowCheck = true;
            char rowChar = board[i].charAt(0);
            res = String.valueOf(rowChar);
            if (rowChar == ' ') {
                hasEmpty = true;
                rowCheck = false;
                continue;
            }
            for (int j = 0; j < board.length; j++) {
                if (board[i].charAt(j) == ' ') {
                    hasEmpty = true;
                    rowCheck = false;
                    break;
                }
                if (rowChar != board[i].charAt(j)) {
                    rowCheck = false;//横向检查失败
                    break;
                }
            }
            if (rowCheck) {
                hasWon = true;
                break;
            }
        }
        if (!hasWon) {
            //纵向检查
            String fRow = board[0];
            for (int i = 0; i < fRow.length(); i++) {
                boolean cloumnCheck = true;
                char charCon = fRow.charAt(i);
                res = String.valueOf(charCon);
                if (charCon == ' ') {
                    hasEmpty = true;
                    cloumnCheck = false;
                    continue;
                }
                //纵向检查
                for (int j = 1; j < board.length; j++) {
                    if (board[j].charAt(i) == ' ') {
                        cloumnCheck = false;
                        hasEmpty = true;
                        break;
                    }
                    if (charCon != board[j].charAt(i)) {
                        cloumnCheck = false;
                        break;
                    }
                }
                if (cloumnCheck) {
                    hasWon = true;
                    break;
                }
            }
        }

        if (!hasWon) {
            boolean diaglonCheck = true;
            char charConStart = board[0].charAt(0);
            char charConEnd = board[0].charAt(board.length - 1);
            res = String.valueOf(charConStart);
            if (charConStart == ' ') {
                hasEmpty = true;
                diaglonCheck = false;
            }
            for (int i = 1; i < board.length; i++) {
                //对角线检查
                if (board[i].charAt(i) == ' ') {
                    diaglonCheck = false;
                    hasEmpty = true;
                    break;
                }
                if (charConStart != board[i].charAt(i)) {
                    diaglonCheck = false;
                    break;
                }
            }
            if (diaglonCheck) return res;
            boolean rightDiaglonCheck = true;
            res = String.valueOf(charConEnd);
            if (charConEnd == ' ') {
                hasEmpty = true;
                rightDiaglonCheck = false;
            }
            for (int i = 1; i < board.length; i++) {
                //对角线检查
                if (board[i].charAt(board.length - 1 - i) == ' ') {
                    rightDiaglonCheck = false;
                    hasEmpty = true;
                    break;
                }
                if (charConEnd != board[i].charAt(board.length - 1 - i)) {
                    rightDiaglonCheck = false;
                    break;
                }
            }
            if (rightDiaglonCheck) return res;
        }

        if (hasWon) {
            return res;
        } else {
            if (hasEmpty) {
                return "Pending";
            } else {
                return "Draw";
            }
        }
        //判断是否有空位
    }

}

package com.arch.ccc.storminaction;

import org.apache.logging.log4j.core.util.ArrayUtils;

import java.util.*;

public class WordCountTopology {

    private static final String SENTENCE_SPOUT_ID = "sentence-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String REPORT_BOLT_ID = "report-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";

//    public static void main(String[] args) {
//        int[] res = new int[5];
//        res[0] = 1;
//        res[1] = 2;
//        res[2] = 3;
//        res[3] = 4;
//        res[4] = 5;
//        Combination combination = new Combination();
//        combination.setComb(res);
//        int[] oth2 = new int[5];
//        oth2[0] = 2;
//        oth2[1] = 3;
//        oth2[2] = 5;
//        oth2[3] = 4;
//        oth2[4] = 1;
//        Combination othcombination = new Combination();
//        othcombination.setComb(oth2);
//        Set<Combination> combinationSet = new HashSet<>();
//        combinationSet.add(combination);
//        combinationSet.add(othcombination);
//
//    }


    public static void main(String[] args) {
        WordCountTopology wordCountTopology = new WordCountTopology();
        List<Integer> boxData = new ArrayList<>();
        boxData.add(1);
        boxData.add(2);
        boxData.add(3);
        boxData.add(4);
        boxData.add(5);
        boxData.add(6);
        boxData.add(7);
        boxData.add(8);
        boxData.add(9);
        boxData.add(10);
        Set<Combination> combinations = wordCountTopology.getCombinations(boxData);
        System.out.println(combinations.size());
        for (Combination combination : combinations) {
            System.out.println(Arrays.toString(combination.getComb()));
        }
    }

    private Set<Combination> getCombinations(List<Integer> boxData) {
        Set<Combination> combinations = new HashSet<>();
        for (int i = 0; i < boxData.size(); i++) {
            int fir = boxData.get(i);
            Set<Integer> temp1 = new HashSet<>(boxData);
            temp1.remove(fir);
            List<Integer> temp = new ArrayList<>(temp1);
            for (int j = 0; j < temp.size(); j++) {
                int second = temp.get(j);
                Set<Integer> thrSet = new HashSet<>(temp);
                thrSet.remove(second);
                List<Integer> thrList = new ArrayList<>(thrSet);
                for (int k = 0; k < thrList.size(); k++) {
                    int thr = thrList.get(k);
                    Set<Integer> forSet = new HashSet<>(thrList);
                    forSet.remove(thr);
                    List<Integer> forList = new ArrayList<>(forSet);
                    for (int l = 0; l < forList.size(); l++) {
                        int forth =forList.get(l);
                        Set<Integer> fiveSet = new HashSet<>(forList);
                        fiveSet.remove(forth);
                        List<Integer> fiveList = new ArrayList<>(fiveSet);
                        for (int m = 0; m < fiveList.size(); m++) {
                            int fiveth = fiveList.get(m);
                            Combination combination = new Combination();
                            int[] res = new int[5];
                            res[0] = fir;
                            res[1] = second;
                            res[2] = thr;
                            res[3] = forth;
                            res[4] = fiveth;
                            combination.setComb(res);
                            combinations.add(combination);
                        }
                    }
                }

            }
        }
        return combinations;
    }


    static class Combination {
        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            Arrays.sort(comb);
            for (int i = 0; i < comb.length; i++) {
                sb.append(comb[i]);
            }
            return Integer.valueOf(sb.toString());
        }

        private int[] comb;

        public int[] getComb() {
            return comb;
        }

        public void setComb(int[] comb) {
            this.comb = comb;
        }

        @Override
        public boolean equals(Object obj) {
            Combination oth = (Combination) obj;
            int[] othArray = oth.getComb();
            Arrays.sort(othArray);
            Arrays.sort(comb);
            if (isEqual(comb, othArray)) {
                return true;
            }
            return false;
        }

        private boolean isEqual(int[] thisArray, int[] othArray) {
            for (int i = 0; i < 5; i++) {
                if (thisArray[i] != othArray[i]) {
                    return false;
                }
            }
            return true;
        }
    }

}


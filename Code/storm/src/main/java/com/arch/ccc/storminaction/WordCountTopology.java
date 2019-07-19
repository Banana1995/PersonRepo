package com.arch.ccc.storminaction;

import java.util.Arrays;
import java.util.Comparator;

public class WordCountTopology {

    private static final String SENTENCE_SPOUT_ID = "sentence-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String REPORT_BOLT_ID = "report-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";

    public static void main(String[] args) {
        WordCountTopology wordCountTopology = new WordCountTopology();
        int[] h = {1};
//        wordCountTopology.hIndex(h);
    }

}


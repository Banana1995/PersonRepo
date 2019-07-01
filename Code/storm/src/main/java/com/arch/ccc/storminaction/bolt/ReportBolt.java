package com.arch.ccc.storminaction.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReportBolt extends BaseRichBolt {
    private HashMap<String, Long> counts = null;

    /**
     * Called when a task for this component is initialized within a worker on the cluster. It provides the bolt with the environment in
     * which the bolt executes.
     * <p>
     * This includes the:
     *
     * @param topoConf  The Storm configuration for this bolt. This is the configuration provided to the topology merged in with cluster
     *                  configuration on this machine.
     * @param context   This object can be used to get information about this task's place within the topology, including the task id and
     *                  component id of this task, input and output information, etc.
     * @param collector The collector is used to emit tuples from this bolt. Tuples can be emitted at any time, including the prepare and
     */
    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.counts = new HashMap<>();
    }

    /**
     * Process a single tuple of input. The Tuple object contains metadata on it about which component/stream/task it came from. The values
     * of the Tuple can be accessed using Tuple#getValue. The IBolt does not have to process the Tuple immediately. It is perfectly fine to
     * hang onto a tuple and process it later (for instance, to do an aggregation or join).
     * <p>
     * Tuples should be emitted using the OutputCollector provided through the prepare method. It is required that all input tuples are
     * acked or failed at some point using the OutputCollector. Otherwise, Storm will be unable to determine when tuples coming off the
     * spouts have been completed.
     * <p>
     * For the common case of acking an input tuple at the end of the execute method, see IBasicBolt which automates this.
     *
     * @param input The input tuple to be processed.
     */
    @Override
    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Long count = input.getLongByField("count");
        this.counts.put(word, count);
    }

    /**
     * Declare the output schema for all the streams of this topology.
     *
     * @param declarer this is used to declare output stream ids, output fields, and whether or not each output stream is a direct stream
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int[] res = Arrays.copyOf(nums, nums.length);
        Arrays.sort(res);
        int j = 0;
        int e = res.length - 1;
        for (int i = 0; i < res.length; i++) {
            if (i % 2 > 0) {
                nums[i] = res[e];
                e--;
            } else {
                nums[i] = res[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {

    }
}

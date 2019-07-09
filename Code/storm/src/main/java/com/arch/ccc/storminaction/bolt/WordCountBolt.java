package com.arch.ccc.storminaction.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

public class WordCountBolt extends BaseRichBolt {

    private OutputCollector collector;

    private HashMap<String, Long> counts;

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
        this.collector = collector;
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
        Long count = this.counts.get(word);
        if (count == null) {
            count = 0L;
        }
        count++;
        this.counts.put(word, count);
        this.collector.emit(new Values(word, count));

    }

    /**
     * Declare the output schema for all the streams of this topology.
     *
     * @param declarer this is used to declare output stream ids, output fields, and whether or not each output stream is a direct stream
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }


    public ListNode sortList(ListNode head) {
        if (head.next == null) {
            return head;
        }
        int i = 0;
        int j = 0;
        ListNode twoStep = head;
        ListNode oneStep = head;
        ListNode privoit = head;
        while (twoStep.next != null) {
            oneStep = oneStep.next;
            privoit = oneStep;
            twoStep = twoStep.next.next;
        }
        //断开链表
        ListNode ta = privoit.next
        privoit.next = null;

        merge(sortList(head), sortList(ta))
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode head = new ListNode(0);
        ListNode point = head;
        while (a.next != null && b.next != null) {
            if (a.val < b.val) {
                point.next = a;
                a = a.next;
            } else {
                point.next = b;
                b = b.next;
            }
            point = point.next;
        }
        point.next = a.next == null ? b : a;
        return head.next;
    }


}

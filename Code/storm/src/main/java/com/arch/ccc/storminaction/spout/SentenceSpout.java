package com.arch.ccc.storminaction.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;

public class SentenceSpout extends BaseRichSpout {

    private SpoutOutputCollector spoutOutputCollector;

    private String[] sentence = {"hao hao xue xi",
            "tian tian xiang shang",
            "wei da ling qiu mao zhu xi",
            "zhi yin wo men xiang qian jin"};

    private int index = 0;

    @Override
    public void open(Map<String, Object> map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.spoutOutputCollector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        this.spoutOutputCollector.emit(new Values(sentence[index]));
        index = index >= sentence.length ? 0 : index++;
        Utils.sleep(10);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }
}

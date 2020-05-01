package com.archforce.arc.pullpressurerulev2;

import com.archforce.arc.framework.common.api.Rule;
import com.archforce.arc.framework.common.param.RuleParameterConfig;
import com.archforce.arc.framework.common.util.AbstractRuleInitBean;
import com.archforce.arc.pullpressurerulev2.config.PullPressureParameterConfig;
import com.archforce.arc.pullpressurerulev2.service.util.PullPressureParameterUtil;
import com.archforce.framework.arc.base.ruleparam.BaseParameterData;
import com.archforce.framework.arc.base.ruleparam.Parameter;
import com.archforce.framework.arc.base.ruleparam.RuleParameterData;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

public class PullPressureRuleInitBean
        extends AbstractRuleInitBean
{
    @Resource(name="pullPressureRuleProcess")
    private PullPressureRuleProcess pullPressureRuleProcess;
    @Resource(name="ruleParameterConfig")
    private RuleParameterConfig ruleParameterConfig;
    @Resource(name="pullPressureParameterConfig")
    private PullPressureParameterConfig pullPressureParameterConfig;
    @Resource(name="pullPressureParameterUtil")
    private PullPressureParameterUtil pullPressureParameterUtil;

    public List<String> submitRule()
    {
        List<String> ruleList = new ArrayList();
        ruleList.add("101101");
        ruleList.add("101102");
        ruleList.add("101105");
        ruleList.add("101106");
        ruleList.add("101103");
        ruleList.add("101104");
        return ruleList;
    }

    public BaseParameterData<? extends Parameter> submitBaseParameterData(String ruleCode)
    {
        RuleParameterData pressureRuleParam = this.ruleParameterConfig.getParamByRuleCode(ruleCode);
        return this.pullPressureParameterUtil.toListParameterEntity(pressureRuleParam, ruleCode);
    }

    public void initBean()
    {
        Rule rule = new Rule();
        rule.setRuleCode("101101");
        rule.setRoleName("????????????????");
        rule.setRuleProcess(this.pullPressureRuleProcess);
        rule.submit();

        Rule tailRule = new Rule();
        tailRule.setRuleCode("101103");
        tailRule.setRoleName("????????????");
        tailRule.setRuleProcess(this.pullPressureRuleProcess);
        tailRule.submit();

        Rule pressureRule = new Rule();
        pressureRule.setRuleCode("101102");
        pressureRule.setRoleName("????????????????");
        pressureRule.setRuleProcess(this.pullPressureRuleProcess);
        pressureRule.submit();

        Rule tailPressureRule = new Rule();
        tailPressureRule.setRuleCode("101104");
        tailPressureRule.setRoleName("????????????");
        tailPressureRule.setRuleProcess(this.pullPressureRuleProcess);
        tailPressureRule.submit();
        Rule headPressureRule = new Rule();
        headPressureRule.setRuleCode("101106");
        headPressureRule.setRoleName("????????????????");
        headPressureRule.setRuleProcess(this.pullPressureRuleProcess);
        headPressureRule.submit();
        Rule headRule = new Rule();
        headRule.setRuleCode("101105");
        headRule.setRoleName("????????????????");
        headRule.setRuleProcess(this.pullPressureRuleProcess);
        headRule.submit();
    }
}

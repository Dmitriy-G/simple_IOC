package rules;

public class Rule {

    private RuleTypes type;
    private String value;

    public Rule(RuleTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    public RuleTypes getType() {
        return type;
    }

    public void setType(RuleTypes type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

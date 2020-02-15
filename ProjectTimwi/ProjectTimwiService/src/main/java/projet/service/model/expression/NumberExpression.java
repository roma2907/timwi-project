package projet.service.model.expression;

import projet.service.model.Wire;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberExpression extends Expression  {

    private Integer value;

    public NumberExpression(Integer value){
        this.value = value;
    }

    @Override
    public Set<String> collectDependency() {
        return new HashSet<>();
    }

    @Override
    public Integer evaluate(Map<String, Wire> dictionnary) {
        return value;
    }
}

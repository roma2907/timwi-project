package projet.service.model.expression;

import projet.service.model.Wire;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VariableExpression extends Expression {

    private String name;

    public VariableExpression(String name){
        this.name = name;
    }

    @Override
   public Set<String> collectDependency() {
        Set<String> list = new HashSet<>();
        list.add(name);
        return list;
    }

    @Override
    public Integer evaluate(Map<String, Wire> dictionnary) {
        return dictionnary.get(name).getValue();
    }
}

package projet.service.model.expression;

import projet.service.model.Wire;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LShiftExpression  extends Expression {

    private Expression expression1;
    private Expression expression2;

    public LShiftExpression(Expression expression1,Expression expression2){
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Set<String> collectDependency() {
        Set<String> list = new HashSet<>(expression1.collectDependency());
        list.addAll(expression2.collectDependency());
        return list;
    }

    @Override
    public Integer evaluate(Map<String, Wire> dictionnary) {
        return this.expression1.evaluate(dictionnary) << this.expression2.evaluate(dictionnary);
    }
}

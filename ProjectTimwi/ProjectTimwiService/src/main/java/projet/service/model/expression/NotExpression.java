package projet.service.model.expression;

import projet.service.model.Wire;

import java.util.Map;
import java.util.Set;

public class NotExpression  extends Expression {

    private Expression expression;

    public NotExpression(Expression expression){
        this.expression = expression;
    }

    @Override
    public Set<String> collectDependency() {
        return expression.collectDependency();
    }

    @Override
    public Integer evaluate(Map<String, Wire> dictionnary) {

        return (int)Integer.toUnsignedLong(~ this.expression.evaluate(dictionnary)) & 0x0000FFFF;
    }
}

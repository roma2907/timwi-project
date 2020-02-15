package projet.service.model.expression;

import projet.service.model.Wire;

import java.util.Map;
import java.util.Set;

public abstract class Expression {

    public abstract Set<String> collectDependency();
    public abstract Integer evaluate(Map<String, Wire> dictionnary);
}

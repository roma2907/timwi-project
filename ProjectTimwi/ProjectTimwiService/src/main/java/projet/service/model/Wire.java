package projet.service.model;

import projet.service.model.expression.Expression;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Wire {
    private String name;
    private Expression expression;
    private Set<String> dependencies;
    private Set<Wire> listDepending;
    private Integer value;

    public Wire(String name, Expression expression){
        this.expression = expression;
        this.name = name;
        this.listDepending = new HashSet<>();
        dependencies = expression.collectDependency();
    }

    public void evaluate(Map<String,Wire> dictionnary){
        //si toutes les dépendances ont été résolu
        if(dependencies.isEmpty()){

            //on calcule la valeur
            this.value = expression.evaluate(dictionnary);

            //on évalue les wires qui dépendaient de ce wire
            listDepending.stream().forEach(wire -> {
                //suppresseion de la dependance
                wire.removeDependency(name);
                //évaluation du nouveau wire
                wire.evaluate(dictionnary);
            });
        }
    }

    public Integer getValue(){
        return value;
    }

    public void removeDependency(String wire){
        dependencies.remove(wire);
    }

    public Set<String> getDependencies(){
        return dependencies;
    }

    public void addDepending(Wire wire){
        listDepending.add(wire);
    }

    public String getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }
}

package projet.service.impl;

import org.springframework.stereotype.Service;
import projet.service.IWireService;
import projet.service.model.Wire;
import projet.service.model.expression.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class WireServiceImpl implements IWireService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Wire> evaluateWires(String wireName) throws IOException {
        String fileName = "assembly-instructions.txt";

        //lecture du fichier
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));

        //récupération des lignes dans un tableau
        String[] lines = content.split("\\r\\n");

        //création d'objet à partir du texte
        //création d'un dictionnaire qui permet de lié un Wire à son nom
        Map<String,Wire> dictionnary = new HashMap<>();
        for(String line : lines){
            Wire wire = buildWire(line);
            dictionnary.put(wire.getName(), wire);
        }

        dictionnary.values().stream().forEach(wire -> {
            //pour chaque dépendance d'un wire on va aller indiquer sur la dépendance que le wire peut être évaluer une fois la dépendance résolu
            for(String dependency : wire.getDependencies()){
                dictionnary.get(dependency).addDepending(wire);
            }
        });
        //on démarre par les wires qui sont déjà valorisé
        dictionnary.values().stream().filter(wire -> wire.getExpression() instanceof  NumberExpression).forEach(wire -> {
            wire.evaluate(dictionnary);
        });
        if(wireName != null){
            return Arrays.asList(dictionnary.get(wireName));
        }
        return dictionnary.values();
    }

    /**
     * Construction de la wire à partir d"une ligne
     * @param line
     * @return la wire contruite
     */
    private Wire buildWire(String line){
        List<String> words = Arrays.asList(line.split(" "));
        return new Wire(words.get(words.size() - 1), buildExpression(words));
    }

    /**
     * Construction de l'expression à partir de la ligne sur lequel on travaille
     * @param words
     * @return l'instance de l'expression
     */
    private Expression buildExpression(List<String> words){
        Expression expression = null;
        if(words.contains("AND")){
            expression = new AndExpression(
                    buildExpression(Arrays.asList(words.get(0))),
                            buildExpression(Arrays.asList(words.get(2))));
        }else if(words.contains("OR")){
            expression = new OrExpression(
                    buildExpression(Arrays.asList(words.get(0))),
                    buildExpression(Arrays.asList(words.get(2))));
        }else if(words.contains("RSHIFT")){
            expression = new RShiftExpression(
                    buildExpression(Arrays.asList(words.get(0))),
                    buildExpression(Arrays.asList(words.get(2))));
        }else if(words.contains("LSHIFT")){
            expression = new LShiftExpression(
                    buildExpression(Arrays.asList(words.get(0))),
                    buildExpression(Arrays.asList(words.get(2))));
        }else if(words.contains("NOT")){
            expression = new NotExpression(
                    buildExpression(Arrays.asList(words.get(1))));
        }else if(!isNumeric(words.get(0))){
            expression = new VariableExpression(words.get(0));
        }else{
            expression = new NumberExpression(Integer.valueOf(words.get(0)));
        }
        return expression;
    }

    /**
     * Indique si str est un nombre
     * @param str
     * @return true si le string est un nombre sinon false
     */
    private boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
}

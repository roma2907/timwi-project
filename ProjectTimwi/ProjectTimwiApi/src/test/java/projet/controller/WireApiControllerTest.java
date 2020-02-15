package projet.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import projet.service.IWireService;
import projet.service.model.Wire;
import projet.service.model.expression.Expression;
import projet.service.model.expression.VariableExpression;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WireApiController.class)
public class WireApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IWireService wireService;

    @Test
    public void testAssemblyWireNull() throws Exception {
        String wireNull = null;
        List<Wire> allWire = new ArrayList<>();
        String name1 = "test";
        String name2 = "test2";
        Expression expression1 = new VariableExpression("a");
        Expression expression2 = new VariableExpression("B");
        allWire.add(new Wire(name1, expression1));
        allWire.add(new Wire(name2, expression2));

        //mock appel à la méthode
        given(wireService.evaluateWires(wireNull)).willReturn(allWire);

        //appel
        mvc.perform(post("/api/assembly")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wires", hasSize(2)))
                .andExpect(jsonPath("$.wires[0].wire-name", is(name1)))
                .andExpect(jsonPath("$.wires[1].wire-name", is(name2)));
    }

    @Test
    public void testAssemblyWireName() throws Exception {
        String wireName= "test";
        List<Wire> allWire = new ArrayList<>();
        String name1 = "test";
        Expression expression1 = new VariableExpression("a");
        allWire.add(new Wire(name1, expression1));

        //mock appel à la méthode
        given(wireService.evaluateWires(wireName)).willReturn(allWire);

        //appel
        mvc.perform(post("/api/assembly?wire="+wireName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wires", hasSize(1)))
                .andExpect(jsonPath("$.wires[0].wire-name", is(name1)));
    }
}

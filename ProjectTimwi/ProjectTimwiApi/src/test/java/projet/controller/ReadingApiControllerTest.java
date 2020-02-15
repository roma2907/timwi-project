package projet.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import projet.service.IReadingDigitService;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReadingApiController.class)
public class ReadingApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IReadingDigitService readingDigitService;

    @Test
    public void testLookAndSay() throws Exception {
        String input = "11";
        int times = 678;

        String result = "1234";

        //mock appel à la méthode
        given(readingDigitService.lookAndSay(input,times)).willReturn(result);

        //appel
        mvc.perform(get("/api/lookandsay/"+input+"?times="+times)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.next_string", is(result)));
    }
}

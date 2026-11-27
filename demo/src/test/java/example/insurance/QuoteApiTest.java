package example.insurance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class QuoteApiTest {
    @Autowired MockMvc mvc;
    @Test void carAndTravelReturnMockResult() throws Exception {
        for (String kind : new String[]{"car", "travel"}) {
            mvc.perform(post("/demo/quotes/" + kind).contentType(MediaType.APPLICATION_JSON).content("{\"reference\":\"demo\"}"))
               .andExpect(status().isOk()).andExpect(jsonPath("$.data.category").value(kind));
        }
    }
    @Test void blankInputIsRejected() throws Exception {
        mvc.perform(post("/demo/quotes/car").contentType(MediaType.APPLICATION_JSON).content("{\"reference\":\"\"}"))
           .andExpect(status().isBadRequest());
    }
}

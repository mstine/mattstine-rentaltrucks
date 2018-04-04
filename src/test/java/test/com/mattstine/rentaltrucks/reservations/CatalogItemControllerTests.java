package test.com.mattstine.rentaltrucks.reservations;

import com.mattstine.rentaltrucks.reservations.CatalogItem;
import com.mattstine.rentaltrucks.reservations.CatalogItemController;
import com.mattstine.rentaltrucks.reservations.CatalogItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CatalogItemControllerTests {

    @Mock
    private CatalogItemRepository mockRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        CatalogItemController controller = new CatalogItemController(mockRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listCatalogItems() throws Exception {
        CatalogItem catalogItem1 = new CatalogItem("some-id-1", "some-truck-type-1");
        CatalogItem catalogItem2 = new CatalogItem("some-id-2", "some-truck-type-2");
        doReturn(asList(catalogItem1, catalogItem2))
                .when(mockRepository)
                .findAll();

        mockMvc
                .perform(
                        get("/catalog-items")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        verify(mockRepository).findAll();
    }

}

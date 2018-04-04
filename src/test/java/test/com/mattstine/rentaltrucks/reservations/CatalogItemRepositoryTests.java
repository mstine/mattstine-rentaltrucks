package test.com.mattstine.rentaltrucks.reservations;

import com.mattstine.rentaltrucks.RentalTrucksApplication;
import com.mattstine.rentaltrucks.reservations.CatalogItem;
import com.mattstine.rentaltrucks.reservations.CatalogItemRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newHashSet;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RentalTrucksApplication.class)
public class CatalogItemRepositoryTests {

    @Autowired
    private CatalogItemRepository repository;

    private String id;

    @Before
    public void setUp() {
        id = "some-id";
    }

    @After
    public void tearDown() {
        repository.delete(id);
    }

    @Test
    public void testRepository() {
        CatalogItem catalogItem = new CatalogItem(id, "some-truck-type");

        CatalogItem savedCatalogItem = repository.save(catalogItem);

        assertThat(savedCatalogItem).isNotNull();
        assertThat(savedCatalogItem.getId()).isNotNull();

        Iterable<CatalogItem> catalogItems = repository.findAll(newHashSet(singletonList(id)));

        assertThat(catalogItems)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(catalogItem);
    }
}

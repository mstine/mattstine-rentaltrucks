package test.com.mattstine.rentaltrucks.reservations;

import com.mattstine.rentaltrucks.events.EventLog;
import com.mattstine.rentaltrucks.reservations.Catalog;
import com.mattstine.rentaltrucks.reservations.CatalogItem;
import com.mattstine.rentaltrucks.reservations.CatalogItemAddedEvent;
import org.junit.Before;
import org.junit.Test;
import test.com.mattstine.rentaltrucks.support.VerifiableEventHandler;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Matt Stine
 */
public class CatalogTests {

    private Catalog catalog;
    private EventLog eventLog;

    @Before
    public void setUp() {
        eventLog = new EventLog();
        catalog = new Catalog(eventLog);
    }

    @Test
    public void canCreateCatalogItem() {
        catalog.add(new CatalogItem("15 Foot Truck"));

        assertThat(catalog.size()).isEqualTo(1);
    }

    @Test
    public void addingCatalogItemFiresEvent() {
        VerifiableEventHandler handler = VerifiableEventHandler
                .of(e -> assertThat(e).isExactlyInstanceOf(CatalogItemAddedEvent.class));

        eventLog.subscribe("catalog", handler);
        catalog.add(new CatalogItem("15 Foot Truck"));

        assertThat(handler.isInvoked()).isTrue();
    }

    @Test
    public void canFindAllCatalogItems() {
        CatalogItem item = new CatalogItem("15 Foot Truck");
        catalog.add(item);

        assertThat(catalog.findAll()).contains(item);
    }

}

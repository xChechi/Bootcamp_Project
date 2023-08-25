package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.table.TableResponse;
import chechi.nino.bootcamp.entity.restaurant.TableZone;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import org.springframework.stereotype.Component;

@Component
public class TableConverter {

    public TableResponse totableResponse (RestaurantTable table) {

        TableResponse response = new TableResponse();
        response.setId(table.getId());
        response.setTableNumber(table.getTableNumber());
        response.setTableZone(table.getTableZone());
        response.setSmoke(table.getTableZone().isSmoke());
        response.setTableCapacity(table.getTableZone().getTableCapacity());

        TableZone tableZone = table.getTableZone();
        response.setSmoke(tableZone.isSmoke());
        response.setTableCapacity(tableZone.getTableCapacity());

        return response;
    }
}

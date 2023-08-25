package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.TableConverter;
import chechi.nino.bootcamp.dto.table.TableResponse;
import chechi.nino.bootcamp.entity.room.RestaurantTable;
import chechi.nino.bootcamp.exception.TableNotFoundException;
import chechi.nino.bootcamp.repository.RestaurantTableRepository;
import chechi.nino.bootcamp.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private final RestaurantTableRepository tableRepository;
    private final TableConverter tableConverter;

    @Override
    public List<TableResponse> getAllTableResponses() {

        List<RestaurantTable> tables = tableRepository.findAll();
        return tables.stream()
                .map(tableConverter::totableResponse)
                .toList();
    }

    @Override
    public TableResponse getTableResponsesById(Integer id) {

        RestaurantTable table = tableRepository.findById(id).orElseThrow(() -> new TableNotFoundException("Table not found"));

        return tableConverter.totableResponse(table) ;
    }
}

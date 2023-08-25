package chechi.nino.bootcamp.controller;

import chechi.nino.bootcamp.dto.table.TableResponse;
import chechi.nino.bootcamp.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tables")
public class TableController {

    private final TableService tableService;

    @GetMapping
    public ResponseEntity<List<TableResponse>> findAllTables () {

        return ResponseEntity.status(HttpStatus.FOUND).body(tableService.getAllTableResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableResponse> findTableById (@PathVariable Integer id) {

        return ResponseEntity.status(HttpStatus.FOUND).body(tableService.getTableResponsesById(id));
    }
}

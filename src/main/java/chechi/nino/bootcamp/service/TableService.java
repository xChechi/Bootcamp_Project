package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.table.TableResponse;

import java.util.List;

public interface TableService {

    List<TableResponse> getAllTableResponses ();

    TableResponse getTableResponsesById (Integer id);
 }

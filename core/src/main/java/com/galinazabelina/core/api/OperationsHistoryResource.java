package com.galinazabelina.core.api;

import com.galinazabelina.core.api.dto.OperationDto;
import com.galinazabelina.core.core.OperationsHistoryService;
import com.galinazabelina.core.core.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping(Paths.OPERATIONS + Paths.HISTORY)
public class OperationsHistoryResource {

    private final OperationsHistoryService operationsHistoryService;

    @Autowired
    public OperationsHistoryResource(OperationsHistoryService operationsHistoryService) {
        this.operationsHistoryService = operationsHistoryService;
    }

    @GetMapping("/{id}")
    public List<OperationDto> get(@PathVariable String id) {
        return operationsHistoryService.get((long) Integer.parseInt(id));
    }

}

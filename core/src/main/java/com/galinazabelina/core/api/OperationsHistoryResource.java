package com.galinazabelina.core.api;

import com.galinazabelina.core.api.dto.OperationDto;
import com.galinazabelina.core.core.OperationsHistoryService;
import com.galinazabelina.core.core.Parameters;
import com.galinazabelina.core.model.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path(Paths.OPERATIONS + Paths.HISTORY)
public class OperationsHistoryResource {

    private final OperationsHistoryService operationsHistoryService;

    @Autowired
    public OperationsHistoryResource(OperationsHistoryService operationsHistoryService) {
        this.operationsHistoryService = operationsHistoryService;
    }

    @GET
    public List<OperationDto> get(@PathParam(Parameters.id) Long accountId) {
        return operationsHistoryService.get(accountId);
    }

}

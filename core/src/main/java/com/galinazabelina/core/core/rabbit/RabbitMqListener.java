package com.galinazabelina.core.core.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.galinazabelina.core.core.repository.OperationsHistoryRepository;
import com.galinazabelina.core.model.entity.Operation;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@AllArgsConstructor
public class RabbitMqListener {

    private OperationsHistoryRepository operationsHistoryRepository;

    @RabbitListener(queues = "operationQueue")
    public void saveOperation(String operationString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Operation operation = objectMapper.readValue(operationString, Operation.class);
        operationsHistoryRepository.save(operation);
    }
}

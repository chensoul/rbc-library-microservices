package com.productdock.library.rental.adapter.out.kafka.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.productdock.library.rental.data.provider.out.kafka.RentalRecordsMessageMother.defaultRentalRecordsMessageBuilder;
import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KafkaMessageProducerShould {

    @InjectMocks
    private KafkaMessageProducer kafkaMessageProducer;

    private static final String BOOK_STATUS_TOPIC = "test-book-status";

    @Test
    void produceMessage() throws JsonProcessingException {
        var rentalRecordsMessage = defaultRentalRecordsMessageBuilder().rentalRecords(Collections.emptyList()).build();

        var producerRecord = kafkaMessageProducer.createKafkaRecord(BOOK_STATUS_TOPIC, rentalRecordsMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String desiredValue = "{\"bookId\":\"" + rentalRecordsMessage.getBookId() +
                "\",\"rentalRecords\":" + objectMapper.writeValueAsString(rentalRecordsMessage.getRentalRecords()) + "}";

        assertThat(producerRecord.value()).isEqualTo(desiredValue);
    }
}

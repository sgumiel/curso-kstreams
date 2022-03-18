package com.gumi.cursos.kstream.randomdata.person.infrastructure.kafka;

import com.gumi.cursos.kstream.randomdata.person.application.producer.PersonProducer;
import com.gumi.cursos.kstream.randomdata.person.avro.PersonDTO;
import com.gumi.cursos.kstream.randomdata.person.domain.Person;
import com.gumi.cursos.kstream.randomdata.person.infrastructure.rest.mapper.PersonRestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonProducerImpl implements PersonProducer {

    private final KafkaTemplate<String, PersonDTO> kafkaTemplate;
    private final PersonRestMapper person2DTOMapper;

    @Value("${topics.person}")
    private String personTopic;

    @Override
    public void publish(Person person) {

        log.debug("Publish person to topic: {}. Person: {}", this.personTopic, person);

        final var personDTO = this.person2DTOMapper.toPersonDTO(person);
        log.debug("Person mapped to personDTO: {}", personDTO);

        final ListenableFuture<SendResult<String, PersonDTO>> lf = this.kafkaTemplate.send(personTopic, person.getDni().getCompleto(), personDTO);
        lf.addCallback(
                (success) -> { log.debug("PersonDTO published: {}", success.getProducerRecord().value());},
                (failure) -> { log.debug("PersonDTO NOT published: {}", failure); }
        );
    }
}

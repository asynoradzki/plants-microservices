package com.x250.notificationservice.message_queue;

import com.x250.notificationservice.event.PlantWateringEvent;
import com.x250.notificationservice.model.UsersPlantToWater;
import com.x250.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageQueueService {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(PlantWateringEvent plantWateringEvent) {
        UsersPlantToWater usersPlantToWater = mapToUsersPlantToWater(plantWateringEvent);

        notificationRepository.save(usersPlantToWater);

        log.info("Received Notification for Plant: {}", plantWateringEvent.usersPlantId());
    }

    private static UsersPlantToWater mapToUsersPlantToWater(PlantWateringEvent plantWateringEvent) {
        return UsersPlantToWater.builder()
                .usersPlantId(plantWateringEvent.usersPlantId())
                .plantName(plantWateringEvent.plantName())
                .appUserId(plantWateringEvent.uppUserId())
                .appUserEmail(plantWateringEvent.uppUserEmail())
                .notificationDate(plantWateringEvent.notificationDate())
                .emailSent(false)
                .receivedByUser(false)
                .build();
    }


}

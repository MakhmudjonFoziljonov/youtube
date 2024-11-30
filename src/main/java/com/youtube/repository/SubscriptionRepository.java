package com.youtube.repository;

import com.youtube.entity.SubscriptionEntity;
import com.youtube.enums.ProfileStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    Optional<SubscriptionEntity> findByProfileIdAndChannelId(String profileId, String channelId);
    List<SubscriptionEntity> findByProfileIdAndStatus(String profileId, ProfileStatus status);

}

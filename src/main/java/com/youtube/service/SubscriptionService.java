package com.youtube.service;

import com.youtube.dto.ChannelDTO;
import com.youtube.dto.SubscriptionInfo;
import com.youtube.entity.ChannelEntity;
import com.youtube.entity.SubscriptionEntity;
import com.youtube.enums.ChannelStatus;
import com.youtube.enums.ProfileStatus;
import com.youtube.repository.ChannelRepository;
import com.youtube.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final ChannelRepository channelRepository;
    public SubscriptionService(SubscriptionRepository subscriptionRepository, ChannelRepository channelRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.channelRepository = channelRepository;
    }

    public SubscriptionInfo createUserSubscription(String channelId) {
        String userId= String.valueOf(getId(channelId));
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setProfileId(userId);
        subscription.setChannelId(channelId);
        subscription.setStatus(ChannelStatus.ACTIVE);
        subscription.setCreatedDate(LocalDateTime.now());

        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscription);

        return toSubscriptionInfo(savedSubscription);
    }

    public void changeSubscriptionStatus(String channelId, ChannelStatus status) {
        String userId= String.valueOf(getId(channelId));
        SubscriptionEntity subscription = subscriptionRepository.findByProfileIdAndChannelId(userId, channelId)
                .orElseThrow(() -> new RuntimeException("Subscription not found for channelId: " + channelId));
        subscription.setStatus(status);
        subscriptionRepository.save(subscription);
    }

    public void changeNotificationType(String channelId) {
        String userId= String.valueOf(getId(channelId));
        SubscriptionEntity subscription = subscriptionRepository.findByProfileIdAndChannelId(userId, channelId)
                .orElseThrow(() -> new RuntimeException("Subscription not found for channelId: " + channelId));
        subscriptionRepository.save(subscription);
    }

    public List<SubscriptionInfo> getUserSubscriptions() {

        List < SubscriptionEntity > subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream().map(this::toSubscriptionInfo).toList();
    }

    public List<SubscriptionInfo> getSubscriptionsByUserId(String userId) {
        List<SubscriptionEntity> subscriptions = subscriptionRepository.findByProfileIdAndStatus(userId, ProfileStatus.ACTIVE);
        return subscriptions.stream().map(this::toSubscriptionInfoWithDate).toList();
    }

    private SubscriptionInfo toSubscriptionInfo(SubscriptionEntity subscription) {
        SubscriptionInfo info = new SubscriptionInfo();
        info.setId(subscription.getId());
        return info;
    }

    private SubscriptionInfo toSubscriptionInfoWithDate(SubscriptionEntity subscription) {
        SubscriptionInfo info = new SubscriptionInfo();
        info.setCreatedDate(subscription.getCreatedDate());
        return info;
    }

    public ChannelDTO getId(String ChannelId) {
        Optional<ChannelEntity> optional = channelRepository.findById(ChannelId);
        if (optional.isPresent()) {
            ChannelEntity channel = optional.get();

            ChannelDTO channelDTO = new ChannelDTO();
            channelDTO.setProfileId(channel.getProfileId());

            return channelDTO;
        }
        return null;

    }

    }

package com.youtube.controller;

import com.youtube.dto.SubscriptionInfo;
import com.youtube.enums.ChannelStatus;
import com.youtube.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    @PostMapping("/")
    public SubscriptionInfo createUserSubscription(@RequestParam String channelId) {
        return subscriptionService.createUserSubscription(channelId);
    }
    @PatchMapping("/user/subscription/status")
    public String changeSubscriptionStatus(@RequestParam String channelId, @RequestParam ChannelStatus status) {
        subscriptionService.changeSubscriptionStatus(channelId, status);
        return "Subscription status updated successfully.";
    }
    @PatchMapping("/user/subscription/notification")
    public String changeSubscriptionNotificationType(@RequestParam String channelId) {
        subscriptionService.changeNotificationType(channelId);
        return "Notification type updated successfully.";
    }
    @GetMapping("/user/subscriptions")
    public List<SubscriptionInfo> getUserSubscriptions() {
        return subscriptionService.getUserSubscriptions();
    }
    @GetMapping("/admin/subscriptions")
    public List<SubscriptionInfo> getSubscriptionsByUserId(@RequestParam String userId) {
        return subscriptionService.getSubscriptionsByUserId(userId);
    }

}

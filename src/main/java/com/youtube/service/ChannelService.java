package com.youtube.service;

import com.youtube.dto.ChannelDTO;
import com.youtube.dto.ChannelPhotoDTO;
import com.youtube.entity.ChannelEntity;
import com.youtube.enums.ChannelStatus;
import com.youtube.repository.ChannelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
     public void createChannel(ChannelDTO channelDTO) {

         ChannelEntity channelEntity = new ChannelEntity();
         channelEntity.setName(channelDTO.getName());
         channelEntity.setDescription(channelDTO.getDescription());
         channelEntity.setPhotoId(channelDTO.getPhoto());
         channelEntity.setBannerID(channelDTO.getBanner());
         channelEntity.setStatus(ChannelStatus.ACTIVE);
         channelEntity.setProfile_id(channelDTO.getProfile_id());
         channelRepository.save(channelEntity);

     }
     public void updateChannel(ChannelDTO channelDTO) {

        ChannelEntity channel=new ChannelEntity();
        channel.setName(channelDTO.getName());
        channel.setDescription(channelDTO.getDescription());
        channel.setPhotoId(channelDTO.getPhoto());
        channel.setBannerID(channelDTO.getBanner());
        channel.setStatus(ChannelStatus.ACTIVE);
        channel.setProfile_id(channelDTO.getProfile_id());
        channelRepository.save(channel);

     }

    public Optional<ChannelEntity> updateChannelPhoto(String channelId, ChannelPhotoDTO updateChannelDTO, String profileId, String role) {
        Optional<ChannelEntity> channel = channelRepository.findById(channelId);

        if (channel == null) {
            throw new IllegalArgumentException("Channel not found");
        }
        if (!channel.get().getProfile_id().equals(profileId) && !role.equals("OWNER")) {
            throw new SecurityException("You are not authorized to update this channel's photo");
        }
        channel.get().setPhotoId(updateChannelDTO.getPhoto());

       return Optional.of(channelRepository.save(channel.get()));
    }

    public Optional<ChannelEntity> updateChannelBanner(String channelId, ChannelPhotoDTO updateChannelDTO, String profileId, String role) {
        Optional<ChannelEntity> channel = channelRepository.findById(channelId);
        if (channel == null) {
            throw new IllegalArgumentException("Channel not found");
        }if (!channel.get().getProfile_id().equals(profileId) && !role.equals("OWNER")) {
            throw new SecurityException("You are not authorized to update this channel's photo");
        }
        channel.get().setBannerID(updateChannelDTO.getBanner());

        return Optional.of(channelRepository.save(channel.get()));

    }
}

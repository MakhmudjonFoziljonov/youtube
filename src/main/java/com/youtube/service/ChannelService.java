package com.youtube.service;

import com.youtube.dto.ChangeChannelStatusDTO;
import com.youtube.dto.ChannelDTO;
import com.youtube.dto.ChannelPhotoDTO;
import com.youtube.entity.ChannelEntity;
import com.youtube.enums.ChannelStatus;
import com.youtube.exp.AppBadRequestException;
import com.youtube.repository.ChannelRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        channelEntity.setBannerId(channelDTO.getBannerId());
        channelEntity.setStatus(ChannelStatus.ACTIVE);
        channelEntity.setProfileId(channelDTO.getProfileId());
        channelRepository.save(channelEntity);

    }

    public void updateChannel(ChannelDTO channelDTO) {

        ChannelEntity channel = new ChannelEntity();
        channel.setName(channelDTO.getName());
        channel.setDescription(channelDTO.getDescription());
        channel.setPhotoId(channelDTO.getPhoto());
        channel.setBannerId(channelDTO.getBannerId());
        channel.setStatus(ChannelStatus.ACTIVE);
        channel.setProfileId(channelDTO.getProfileId());
        channelRepository.save(channel);

    }

    public Optional<ChannelEntity> updateChannelPhoto(String channelId, ChannelPhotoDTO updateChannelDTO, String profileId, String role) {
        Optional<ChannelEntity> channel = channelRepository.findById(channelId);

        if (channel == null) {
            throw new IllegalArgumentException("Channel not found");
        }
        if (!channel.get().getProfileId().equals(profileId) && !role.equals("OWNER")) {
            throw new SecurityException("You are not authorized to update this channel's photo");
        }
        channel.get().setPhotoId(updateChannelDTO.getPhoto());

        return Optional.of(channelRepository.save(channel.get()));
    }

    public Optional<ChannelEntity> updateChannelBanner(String channelId, ChannelPhotoDTO updateChannelDTO, String profileId, String role) {
        Optional<ChannelEntity> channel = channelRepository.findById(channelId);

        if (channel == null) {
            throw new IllegalArgumentException("Channel not found");
        }
        if (!channel.get().getProfileId().equals(profileId) && !role.equals("OWNER")) {
            throw new SecurityException("You are not authorized to update this channel's photo");
        }
        channel.get().setBannerId(updateChannelDTO.getBannerId());

        return Optional.of(channelRepository.save(channel.get()));

    }

    public Page<ChannelEntity> getChannels(int page, int size, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(sortDirection));
        return channelRepository.findAll(pageable);
    }

    public Optional<ChannelEntity> getById(String id) {
        return channelRepository.findById(id);
    }

    public String changeChannelStatus(Long userId, String role, ChangeChannelStatusDTO dto) throws ChangeSetPersister.NotFoundException {

        ChannelEntity channel = channelRepository.findById(String.valueOf(dto.getChannelId()))
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        if (role.equals("ADMIN")) {
            channel.setStatus(ChannelStatus.ACTIVE);
            channelRepository.save(channel);
            return "Channel status updated successfully by ADMIN.";
        }

        if (role.equals("OWNER")) {
            if (!channel.getProfileId().equals(userId)) {
                throw new AppBadRequestException("You are not authorized to update this channel.");
            }
            channel.setStatus(ChannelStatus.ACTIVE);
            channelRepository.save(channel);
            return "Channel status updated successfully by OWNER.";
        }

        throw new AppBadRequestException("Users are not allowed to update channel status.");
    }

}

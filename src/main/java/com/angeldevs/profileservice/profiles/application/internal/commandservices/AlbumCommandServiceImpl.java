package com.angeldevs.profileservice.profiles.application.internal.commandservices;

import com.angeldevs.profileservice.profiles.domain.model.aggregates.Album;
import com.angeldevs.profileservice.profiles.domain.model.aggregates.Profile;
import com.angeldevs.profileservice.profiles.domain.model.commands.CreateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.CreatePhotoInAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.DeletePhotoFromAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.commands.UpdateAlbumCommand;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.Photo;
import com.angeldevs.profileservice.profiles.domain.model.valueobjects.ProfileType;
import com.angeldevs.profileservice.profiles.domain.services.AlbumCommandService;
import com.angeldevs.profileservice.profiles.infrastructure.persistence.jpa.repositories.AlbumRepository;
import com.angeldevs.profileservice.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of album command service.
 */
@Service
public class AlbumCommandServiceImpl implements AlbumCommandService {

    private final AlbumRepository albumRepository;
    private final ProfileRepository profileRepository;

    public AlbumCommandServiceImpl(AlbumRepository albumRepository, ProfileRepository profileRepository) {
        this.albumRepository = albumRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Long> handle(CreateAlbumCommand command) {
        var profileOpt = profileRepository.findById(command.profileId());
        if (profileOpt.isEmpty()) return Optional.empty();
        Profile profile = profileOpt.get();
        if (profile.getType() != ProfileType.ORGANIZER) return Optional.empty();
        var album = new Album(profile, command.title(), command.description(), command.photos());
        albumRepository.save(album);
        return Optional.of(album.getId());
    }

    @Override
    public void handle(UpdateAlbumCommand command) {
        var albumOpt = albumRepository.findById(command.albumId());
        if (albumOpt.isEmpty()) return;
        var album = albumOpt.get();
        album.update(command.title(), command.description(), command.photos());
        albumRepository.save(album);
    }

    @Override
    public void deleteById(Long albumId) {
        albumRepository.deleteById(albumId);
    }


    @Override
    public void handle(CreatePhotoInAlbumCommand command) {
        var albumOpt = albumRepository.findById(command.albumId());
        if (albumOpt.isEmpty()) return;
        var album = albumOpt.get();
        album.addPhoto(command.photoUrl(),command.publicId());
        albumRepository.save(album);
    }

    @Override
    public void handle(DeletePhotoFromAlbumCommand command) {
        var albumOpt = albumRepository.findById(command.albumId());
        if (albumOpt.isEmpty()) return;
        var album = albumOpt.get();
        album.removePhoto(command.publicId());
        albumRepository.save(album);
    }
}
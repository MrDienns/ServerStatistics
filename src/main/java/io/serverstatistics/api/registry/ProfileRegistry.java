package io.serverstatistics.api.registry;

import io.serverstatistics.api.Profile;

import java.util.Collection;

public interface ProfileRegistry {

    void addProfile(Profile...statistics);
    void removeProfile(Profile...statistics);
    Collection<Profile> getProfiles();
}

package io.serverstatistics.api.registry;

import io.serverstatistics.api.Profile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class BasicProfileRegistry implements ProfileRegistry {

    private static final Logger LOGGER = Logger.getLogger("ServerStatistics");

    private static final BasicProfileRegistry i = new BasicProfileRegistry();
    public static BasicProfileRegistry get() { return i; }

    private final Set<Profile> profiles = new HashSet<>();

    private BasicProfileRegistry() {

    }

    public void addProfile(Profile ...profiles) {
        for (Profile profile : profiles) {
            LOGGER.info(String.format("Registered profile '%s'", sanitizeProfileName(profile)));
            this.profiles.add(profile);
        }
    }

    public void removeProfile(Profile ...profiles) {
        for (Profile profile : profiles) {
            LOGGER.info(String.format("Unregistered profile '%s'", sanitizeProfileName(profile)));
            this.profiles.remove(profile);
        }
    }

    public Collection<Profile> getProfiles() {
        return this.profiles;
    }

    private String sanitizeProfileName(Profile profile) {
        return profile.getName()
                .replace(" ", "-")
                .replace(".", "-")
                .toLowerCase();
    }
}

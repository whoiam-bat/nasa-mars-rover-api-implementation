package ua.com.demo.spacey.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.demo.spacey.model.Preference;
import ua.com.demo.spacey.repository.PreferenceRepository;

import java.util.Optional;

@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    @Autowired
    public PreferenceService(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public Preference findOne(int preferenceId) {
        Optional<Preference> preference = preferenceRepository.findById(preferenceId);

        return preference.orElse(null);
    }

    @Transactional
    public Preference save(Preference preference) {
        return preferenceRepository.save(preference);
    }
}

package it.cgmconsulting.capriello.service;

import it.cgmconsulting.capriello.entity.Language;
import it.cgmconsulting.capriello.exception.ResourceNotFoundException;
import it.cgmconsulting.capriello.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public boolean existsBy(Language language){
        return languageRepository.existsByLanguageIdAndLanguageName(language.getLanguageId(), language.getLanguageName());
    }

}

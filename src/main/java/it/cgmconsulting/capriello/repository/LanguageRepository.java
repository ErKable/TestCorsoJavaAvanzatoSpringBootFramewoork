package it.cgmconsulting.capriello.repository;

import it.cgmconsulting.capriello.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByLanguageIdAndLanguageName(long languageId, String languageName);
}

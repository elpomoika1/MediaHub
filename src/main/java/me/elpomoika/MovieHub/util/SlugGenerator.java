package me.elpomoika.MovieHub.util;

import lombok.experimental.UtilityClass;
import ru.homyakin.iuliia.Schemas;
import ru.homyakin.iuliia.Translator;

@UtilityClass
public class SlugGenerator {
    private final Translator translator = new Translator(Schemas.BGN_PCGN_ALT);

    public String generateSlug(String rawTitle) {
        String title = translator.translate(rawTitle);
        return title.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
    }
}

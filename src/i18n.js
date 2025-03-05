import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import LanguageDetector from "i18next-browser-languagedetector";
import Backend from "i18next-http-backend";
import translationEN from "./locales/en.json";
import translationES from "./locales/es.json";

i18n
    .use(Backend)
    .use(LanguageDetector)
    .use(initReactI18next)
    .init({
        lng: "es",
        fallbackLng: "es",
        debug: true,
        interpolation: {
            escapeValue: false
        },
        resources: {
            es: { translation: translationES },
            en: { translation: translationEN }
        },
    });

export default i18n;
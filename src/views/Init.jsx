import { useState, useEffect } from "react";
import ProfileImage from "../components/init/ProfileImage.jsx";
import InfoSection from "../components/init/InfoSection.jsx";
import SocialLinks from "../components/init/SocialLinks.jsx";
import cv from '../data/cv.js';
import { useTranslation } from 'react-i18next';
import Flag from 'react-world-flags';
import { skillEmojis } from "../data/constants.js";

const socialLinks = [
    { url: cv.personal.linkedin, icon: "linkedin" },
    { url: cv.personal.github, icon: "github" }
];

const Init = () => {
    const { t } = useTranslation();
    const [isMobile, setIsMobile] = useState(window.innerWidth <= 768);

    useEffect(() => {
        const handleResize = () => setIsMobile(window.innerWidth <= 768);
        window.addEventListener("resize", handleResize);
        return () => window.removeEventListener("resize", handleResize);
    }, []);

    const languages = Object.entries(t("Init.languages", { returnObjects: true })).map(
        ([languageKey, languageName]) => ({
            languageKey,
            languageName,
            emoji: languageKey === "spanish" ? "🇪🇸" : "🇬🇧",
            levelKey: languageKey === "spanish" ? "native" : "intermediate"
        })
    );

    const skills = Object.entries(t("Init.skills", { returnObjects: true })).map(([skillKey, skillName]) => {
        const emoji = skillEmojis[skillKey] || "🔧";
        return { skillKey, skillName, emoji };
    });

    return (
        <section
            style={{
                display: "flex",
                justifyContent: "center",
                backgroundColor: "#f5f5f5",
                padding: "20px",
                minHeight: "100vh"
            }}
        >
            <div
                style={{
                    display: "flex",
                    flexDirection: isMobile ? "column" : "row",
                    alignItems: "center",
                    backgroundColor: "#fff",
                    padding: "30px",
                    borderRadius: "10px",
                    boxShadow: "0 6px 12px rgba(0, 0, 0, 0.1)",
                    width: "90vw",
                    maxWidth: "900px",
                    gap: "20px",
                }}
            >
                <ProfileImage src={cv.personal.profileImage} alt={t('Init.photo') + cv.personal.name} />

                <div style={{ flex: 1, textAlign: "center" }}>
                    <h1 style={{ fontSize: "28px", fontWeight: "bold" }}>{cv.personal.name}</h1>
                    <h2 style={{ fontSize: "20px", color: "#555" }}>{t(`Init.engineer`)}</h2>
                    <p style={{ fontSize: "18px", color: "#777" }}>
                        📍 {cv.personal.location}
                        <Flag code={t('LanguageSelector.spanish')} style={{ width: "20px", height: "15px", marginLeft: "8px" }} />
                    </p>
                    <div style={{ display: "flex", justifyContent: "center", marginTop: "10px" }}>
                        <SocialLinks links={socialLinks} />
                    </div>
                    <InfoSection title={t('Init.languagesTitle')}>
                        <ul style={{ listStyle: "none", padding: 0 }}>
                            {languages.map(({ emoji, languageKey, languageName, levelKey }) => (
                                <li key={languageKey} style={{ marginBottom: "8px", fontSize: "18px" }}>
                                    {emoji} {languageName} ({t(`Init.levels.${levelKey}`)})
                                </li>
                            ))}
                        </ul>
                    </InfoSection>

                    <InfoSection title={t('Init.skillsTitle')}>
                        <ul style={{ listStyle: "none", padding: 0 }}>
                            {skills.map(({ emoji, skillKey, skillName }) => (
                                <li key={skillKey} style={{ marginBottom: "8px", fontSize: "18px" }}>
                                    {emoji} {skillName}
                                </li>
                            ))}
                        </ul>
                    </InfoSection>
                </div>
            </div>
        </section>
    );
};

export default Init;
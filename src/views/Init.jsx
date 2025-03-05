import ProfileImage from "../components/init/ProfileImage.jsx";
import InfoSection from "../components/init/InfoSection.jsx";
import SocialLinks from "../components/init/SocialLinks.jsx";
import data from '../data/data.js';
import { useTranslation } from 'react-i18next';
import Flag from 'react-world-flags';
import {skillEmojis} from "../data/constants.js";

const socialLinks = [
    { url: data.personal.linkedin, icon: "linkedin" },
    { url: data.personal.github, icon: "github" }
];

const Init = () => {
    const { t } = useTranslation();

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

        return {
            skillKey,
            skillName,
            emoji,
        };
    });

    return (
        <section
            style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                minHeight: "100vh",
                backgroundColor: "#f5f5f5",
                padding: "20px",
            }}
        >
            <ProfileImage src={data.personal.profileImage} alt={t('Init.photo') + data.personal.name} />
            <div
                style={{
                    backgroundColor: "#fff",
                    padding: "30px",
                    borderRadius: "10px",
                    boxShadow: "0 6px 12px rgba(0, 0, 0, 0.1)",
                    width: "50%",
                    minHeight: "60vh",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                }}
            >
                <h1 style={{ fontSize: "28px", fontWeight: "bold", marginBottom: "10px" }}>
                    {data.personal.name}
                </h1>
                <h2 style={{ fontSize: "20px", color: "#555", marginBottom: "15px" }}>
                    {t(`Init.engineer`)}
                </h2>
                <p style={{fontSize: "18px", color: "#777", marginBottom: "25px"}}>
                    📍 {data.personal.location}
                    <Flag code={t('LanguageSelector.spanish')} style={{ width: "20px", height: "15px", marginLeft: "8px" }} />
                </p>
                <SocialLinks links={socialLinks}/>

                <InfoSection title={t('Init.languagesTitle')}>
                    {languages.map(({ emoji, languageKey, languageName, levelKey }) => (
                        <li key={languageKey} style={{ marginBottom: "8px", fontSize: "18px" }}>
                            {emoji} {languageName} ({t(`Init.levels.${levelKey}`)})
                        </li>
                    ))}
                </InfoSection>

                <InfoSection title={t('Init.skillsTitle')}>
                    <ul style={{listStyle: "none", padding: 0, margin: 0}}>
                        {skills.map(({emoji, skillKey, skillName}) => (
                            <li key={skillKey} style={{marginBottom: "8px", fontSize: "18px"}}>
                                {emoji} {skillName}
                            </li>
                        ))}
                    </ul>
                </InfoSection>
            </div>
        </section>
    );
};

export default Init;
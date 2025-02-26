import ProfileImage from "../components/init/ProfileImage.jsx";
import InfoSection from "../components/init/InfoSection.jsx";
import SocialLinks from "../components/init/SocialLinks.jsx";
import cvData from '../data/cvData.js';

const socialLinks = [
    { url: cvData.personal.linkedin, icon: "linkedin" },
    { url: cvData.personal.github, icon: "github" }
];

const Init = () => (
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
        <ProfileImage src={cvData.personal.profileImage} alt={"Foto de " + cvData.personal.name} />
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
            <h1 style={{ fontSize: "28px", fontWeight: "bold", marginBottom: "10px" }}>{cvData.personal.name}</h1>
            <h2 style={{ fontSize: "20px", color: "#555", marginBottom: "15px" }}>{cvData.personal.job}</h2>
            <p style={{ fontSize: "18px", color: "#777", marginBottom: "25px" }}>📍 {cvData.personal.location} ({cvData.personal.country})</p>
            <SocialLinks links={socialLinks} />
            <InfoSection title="Idiomas">
                <ul style={{ listStyle: "none", padding: 0, margin: 0 }}>
                    {cvData.personal.languages.map((lang, index) => (
                        <li key={index} style={{ marginBottom: "8px", fontSize: "18px" }}>
                            {lang.emoji} {lang.language} ({lang.level})
                        </li>
                    ))}
                </ul>
            </InfoSection>
            <InfoSection title="Principales Aptitudes">
                <ul style={{ listStyle: "none", padding: 0, margin: 0 }}>
                    {cvData.personal.skills.map((skill, index) => (
                        <li key={index} style={{ marginBottom: "8px", fontSize: "18px" }}>
                            {skill.emoji} {skill.skill}
                        </li>
                    ))}
                </ul>
            </InfoSection>
        </div>
    </section>
);

export default Init;
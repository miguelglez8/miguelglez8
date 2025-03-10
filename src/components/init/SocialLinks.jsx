import { useState } from "react";
import GitHubIcon from "../../icons/GitHubIcon.jsx";
import LinkedInIcon from "../../icons/LinkedInIcon.jsx";

const SocialLinks = ({ links }) => {
    const [hoveredIcon, setHoveredIcon] = useState(null);

    const iconMapping = {
        github: (isHovered) => (
            <GitHubIcon
                size={isHovered ? 55 : 50}
                color="#000000"
                style={{
                    transition: "all 0.3s ease-in-out",
                    transform: isHovered ? "scale(1.2)" : "scale(1)",
                    boxShadow: isHovered ? "0 4px 12px rgba(0, 0, 0, 0.2)" : "none"
                }}
            />
        ),
        linkedin: (isHovered) => (
            <LinkedInIcon
                size={isHovered ? 55 : 50}
                color="#0077B5"
                style={{
                    transition: "all 0.3s ease-in-out",
                    transform: isHovered ? "scale(1.2)" : "scale(1)",
                    boxShadow: isHovered ? "0 4px 12px rgba(0, 0, 0, 0.2)" : "none"
                }}
            />
        )
    };

    return (
        <div style={{ display: "flex", gap: "20px", marginBottom: "15px", alignItems: "center" }}>
            {links.map((link, index) => (
                <a
                    key={index}
                    href={link.url}
                    style={{
                        marginLeft: "5px",
                        display: "flex",
                        alignItems: "center",
                    }}
                    onMouseEnter={() => setHoveredIcon(index)}
                    onMouseLeave={() => setHoveredIcon(null)}
                >
                    {iconMapping[link.icon](hoveredIcon === index)}
                </a>
            ))}
        </div>
    );
};

export default SocialLinks;

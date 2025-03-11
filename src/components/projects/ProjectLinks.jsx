import { useState } from "react";
import { FaGithub, FaLink, FaFileAlt, FaYoutube } from 'react-icons/fa';
import { linkColors } from "../../data/colors.js";

const iconMapping = {
    github: <FaGithub size={20} />,
    web: <FaLink size={20} />,
    doc: <FaFileAlt size={20} />,
    demo: <FaYoutube size={20} />
};

const ProjectLinks = ({ links }) => {
    const [hoveredIndex, setHoveredIndex] = useState(null);

    return (
        <div style={{ display: "flex", gap: "15px", marginTop: "15px" }}>
            {links.map(({ href, type }, index) => {
                const icon = iconMapping[type];

                return (
                    <a
                        key={index}
                        href={href}
                        target="_blank"
                        rel="noopener noreferrer"
                        style={{
                            backgroundColor: hoveredIndex === index ? "#FFFFFF" : linkColors[type],
                            color: hoveredIndex === index ? linkColors[type] : "#fff",
                            padding: "10px 20px",
                            borderRadius: "5px",
                            textDecoration: "none",
                            fontWeight: "bold",
                            transition: "background-color 0.3s, transform 0.3s, color 0.3s",
                            display: "flex",
                            alignItems: "center",
                            justifyContent: "center",
                            transform: hoveredIndex === index ? "scale(1.2)" : "scale(1)",
                        }}
                        onMouseEnter={() => setHoveredIndex(index)}
                        onMouseLeave={() => setHoveredIndex(null)}
                    >
                        {icon}
                    </a>
                );
            })}
        </div>
    );
};

export default ProjectLinks;

import { FaGithub, FaLink, FaFileAlt, FaYoutube } from 'react-icons/fa';
import {linkColors} from "../../data/colors.js";

const iconMapping = {
    github: <FaGithub size={20} />,
    web: <FaLink size={20} />,
    doc: <FaFileAlt size={20} />,
    demo: <FaYoutube size={20} />
};

const ProjectLinks = ({ links }) => (
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
                        backgroundColor: linkColors[type],
                        color: "#fff",
                        padding: "10px 20px",
                        borderRadius: "5px",
                        textDecoration: "none",
                        fontWeight: "bold",
                        transition: "background-color 0.3s ease",
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                    }}
                >
                    {icon}
                </a>
            );
        })}
    </div>
);

export default ProjectLinks;
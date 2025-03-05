import GitHubIcon from "../../icons/GitHubIcon.jsx";
import LinkedInIcon from "../../icons/LinkedInIcon.jsx";

const iconMapping = {
    github: <GitHubIcon size={50} color="#000000" />,
    linkedin: <LinkedInIcon size={50} color="#0077B5" />
};

const SocialLinks = ({ links }) => (
    <div style={{ display: "flex", gap: "20px", marginBottom: "15px" }}>
        {links.map((link, index) => (
            <a key={index} href={link.url} style={{ marginLeft: "5px" }}>
                {iconMapping[link.icon]}
            </a>
        ))}
    </div>
);

export default SocialLinks;
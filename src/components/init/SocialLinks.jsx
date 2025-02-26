import GitHubIcon from "../../icons/GitHubIcon.jsx";
import LinkedInIcon from "../../icons/LinkedInIcon.jsx";

const SocialLinks = ({ links }) => (
    <div style={{ display: "flex", gap: "20px", marginBottom: "15px" }}>
        {links.map((link, index) => (
            <a key={index} href={link.url} style={{ marginLeft: "5px" }}>
                {link.icon === 'github' && <GitHubIcon size={50} color="#000000" />}
                {link.icon === 'linkedin' && <LinkedInIcon size={50} color="#0077B5" />}
            </a>
        ))}
    </div>
);

export default SocialLinks;
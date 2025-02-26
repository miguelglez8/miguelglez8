const ProjectLinks = ({ links }) => (
    <div style={{ display: "flex", gap: "15px", marginTop: "15px" }}>
        {links.map(({ href, text, bgColor }, index) => (
            <a
                key={index}
                href={href}
                target="_blank"
                rel="noopener noreferrer"
                style={{
                    backgroundColor: bgColor,
                    color: "#fff",
                    padding: "10px 20px",
                    borderRadius: "5px",
                    textDecoration: "none",
                    fontWeight: "bold",
                    transition: "background-color 0.3s ease",
                }}
            >
                {text}
            </a>
        ))}
    </div>
);

export default ProjectLinks;
const CourseItem = ({ logo, alt, title, organization, date, link, linkText, text, skills }) => {
    return (
        <li style={{ display: "flex", alignItems: "center", marginBottom: "20px" }}>
            <img
                src={logo}
                alt={alt}
                style={{ width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px" }}
            />
            <div style={{ flex: 1 }}>
                <p style={{ fontSize: "18px", fontWeight: "bold" }}>{title}</p>
                <p style={{ fontSize: "16px", fontWeight: "bold", color: "#333" }}>{organization}</p>
                <p style={{ fontSize: "16px", color: "#555" }}>
                    📅 <strong>{text}:</strong> {date}
                </p>
                {link && (
                    <p style={{ fontSize: "16px", color: "#555" }}>
                        🔗 <a
                        href={link}
                        target="_blank"
                        style={{
                            color: "#2563eb",
                            textDecoration: "none",
                            transition: "text-decoration 0.3s ease"
                        }}
                        onMouseEnter={(e) => e.target.style.textDecoration = "underline"}
                        onMouseLeave={(e) => e.target.style.textDecoration = "none"}
                    >
                        {linkText}
                    </a>
                    </p>
                )}
                {skills && (
                    <p style={{ fontSize: "14px", color: "#777" }}>
                        🛠 <strong>Aptitudes:</strong> {skills}
                    </p>
                )}
            </div>
        </li>
    );
};

export default CourseItem;

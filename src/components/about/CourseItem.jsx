const CourseItem = ({ logo, alt, title, date, link, linkText }) => {
    return (
        <li style={{ display: "flex", alignItems: "center", marginBottom: "20px" }}>
            <img
                src={logo}
                alt={alt}
                style={{ width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px" }}
            />
            <div style={{ flex: 1 }}>
                <p style={{ fontSize: "18px", fontWeight: "bold" }}>{title}</p>
                <p style={{ fontSize: "16px", color: "#555" }}>
                    📅 <strong>Expedición:</strong> {date}
                </p>
                {link && (
                    <p style={{ fontSize: "16px", color: "#555" }}>
                        🔗 <a href={link} target="_blank" style={{ color: "#2563eb" }}>
                        {linkText || "Ver curso"}
                    </a>
                    </p>
                )}
            </div>
        </li>
    );
};

export default CourseItem;
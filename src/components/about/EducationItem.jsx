const EducationItem = ({ logo, alt, title, institution, location, period, details, link, text }) => (
    <li style={{ display: "flex", alignItems: "flex-start", marginBottom: "20px" }}>
        <img
            src={logo}
            alt={alt}
            style={{ width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px" }}
        />
        <div style={{ flex: 1 }}>
            <p style={{ fontSize: "18px", fontWeight: "bold" }}>{title}</p>
            <p style={{ fontSize: "16px", color: "#555", marginBottom: "10px" }}>
                {institution}, {location}
            </p>
            <ul style={{ paddingLeft: "20px", fontSize: "16px", color: "#555" }}>
                {details.map((item, index) => (
                    <li key={index}>
                        {item}{" "}
                        {index === details.length - 1 && link && (
                            <span>
                                (
                                <a
                                    href={link}
                                    target="_blank"
                                    style={{
                                        color: "#2563eb",
                                        fontWeight: "bold",
                                        textDecoration: "none",
                                        transition: "text-decoration 0.3s ease"
                                    }}
                                    onMouseEnter={(e) => e.target.style.textDecoration = "underline"}
                                    onMouseLeave={(e) => e.target.style.textDecoration = "none"}
                                >
                                    {text}
                                </a>
                                )
                            </span>
                        )}
                    </li>
                ))}
            </ul>
            <div style={{ display: "flex", justifyContent: "space-between", marginTop: "10px" }}>
                <span style={{ fontSize: "14px", color: "#888" }}>{period}</span>
            </div>
        </div>
    </li>
);

export default EducationItem;
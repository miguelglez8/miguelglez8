const ExperienceItem = ({ logo, alt, title, company, location, period, responsibilities, stack, textStack, emoji, workMode }) => (
    <li style={{ display: "flex", alignItems: "flex-start", marginBottom: "20px" }}>
        <img src={logo} alt={alt} style={{ width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px" }} />
        <div style={{flex: 1}}>
            <p
                style={{
                    fontSize: "20px",
                    fontWeight: "bold",
                    margin: 0
                }}
            >
                {company}
            </p>

            <p
                style={{
                    fontSize: "17px",
                    fontWeight: "600",
                    color: "#444",
                    margin: "4px 0"
                }}
            >
                {title}
            </p>

            <p
                style={{
                    fontSize: "15px",
                    color: "#666",
                    margin: "4px 0"
                }}
            >
                📍 {location}
                {emoji && ` · ${emoji} ${workMode}`}
            </p>

            {responsibilities?.length > 0 && (
                <ul style={{paddingLeft: "20px", fontSize: "16px", color: "#555"}}>
                    {responsibilities.map((item, index) => (
                        <li key={index}>{item}</li>
                    ))}
                </ul>
            )}

            {stack && (
                <p style={{fontSize: "14px", color: "#777", marginTop: "10px"}}>
                    🛠 <strong>{textStack}:</strong> {stack}
                </p>
            )}

            <p
                style={{
                    fontSize: "14px",
                    color: "#888",
                    margin: "4px 0 12px 0"
                }}
            >
                {period}
            </p>
        </div>
    </li>
);

export default ExperienceItem;

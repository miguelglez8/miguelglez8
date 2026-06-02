const ExperienceItem = ({ logo, alt, title, company, location, period, responsibilities, stack, textStack, workMode, emoji }) => (

    <li style={{ display: "flex", alignItems: "flex-start", marginBottom: "20px" }}>
        <img src={logo} alt={alt} style={{ width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px" }} />
        <div style={{ flex: 1 }}>
            <p style={{ fontSize: "18px", fontWeight: "bold" }}>{title}</p>
            <p style={{ fontSize: "16px", color: "#555", marginBottom: "10px" }}>
                {emoji && <span style={{ marginLeft: "6px" }}>{emoji}</span>}{" "}
                {company}, {location}
            </p>
            {responsibilities && (
                <ul style={{ paddingLeft: "20px", fontSize: "16px", color: "#555" }}>{responsibilities.map((item, index) => <li key={index}>{item}</li>)}</ul>
            )}
            {stack && (
                <p style={{ fontSize: "14px", color: "#777", marginTop: "10px" }}>
                    🛠 <strong>{textStack}:</strong> {stack}
                </p>
            )}
            <div style={{ display: "flex", justifyContent: "space-between", marginTop: "10px" }}>
                <span style={{ fontSize: "14px", color: "#888" }}>{period}</span>
            </div>
        </div>
    </li>
);

export default ExperienceItem;

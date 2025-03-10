const ExperienceItem = ({ logo, alt, title, company, location, period, responsibilities }) => (
    <li style={{ display: "flex", alignItems: "flex-start", marginBottom: "20px" }}>
        <img src={logo} alt={alt} style={{ width: "80px", height: "80px", borderRadius: "10px", marginRight: "20px" }} />
        <div style={{ flex: 1 }}>
            <p style={{ fontSize: "18px", fontWeight: "bold" }}>{title}</p>
            <p style={{ fontSize: "16px", color: "#555", marginBottom: "10px" }}>{company}, {location}</p>
            <ul style={{ paddingLeft: "20px", fontSize: "16px", color: "#555" }}>{responsibilities.map((item, index) => <li key={index}>{item}</li>)}</ul>
            <div style={{ display: "flex", justifyContent: "space-between", marginTop: "10px" }}>
                <span style={{ fontSize: "14px", color: "#888" }}>{period}</span>
            </div>
        </div>
    </li>
);

export default ExperienceItem;
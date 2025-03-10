const InfoSection = ({ title, children }) => (
    <div style={{ marginBottom: "20px" }}>
        <h3 style={{ fontSize: "20px", fontWeight: "bold", marginBottom: "8px" }}>{title}</h3>
        {children}
    </div>
);

export default InfoSection;
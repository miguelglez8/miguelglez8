const SectionItem = ({ title, children }) => (
    <section style={{ padding: "20px", backgroundColor: "#f9f9f9" }}>
        {title && <h2 style={{ fontSize: "24px", fontWeight: "bold", marginBottom: "20px", textAlign: "center" }}>{title}</h2>}
        <div style={{ display: "flex", flexDirection: "column", gap: "20px", padding: "20px" }}>{children}</div>
    </section>
);

export default SectionItem;
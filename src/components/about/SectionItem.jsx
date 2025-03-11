const SectionItem = ({ title, children }) => (
    <section
        style={{
            display: "flex",
            justifyContent: "center",
            padding: "20px",
            backgroundColor: "#f5f5f5",
        }}
    >
        <div
            style={{
                backgroundColor: "#fff",
                padding: "25px",
                borderRadius: "10px",
                boxShadow: "0 6px 12px rgba(0, 0, 0, 0.1)",
                width: "90vw"
            }}
        >
            {title && (
                <h2
                    style={{
                        fontSize: "24px",
                        fontWeight: "bold",
                        marginBottom: "15px",
                        color: "#333",
                        textAlign: "center"
                    }}
                >
                    {title}
                </h2>
            )}
            <div style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
                {children}
            </div>
        </div>
    </section>
);

export default SectionItem;

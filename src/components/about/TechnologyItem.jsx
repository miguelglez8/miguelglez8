const TechnologyItem = ({ name, backgroundColor }) => (
    <span
        style={{
            backgroundColor: backgroundColor || "#ccc",
            color: "#fff",
            padding: "8px 16px",
            borderRadius: "20px",
            fontSize: "14px",
            fontWeight: "bold",
        }}
    >
        {name}
    </span>
);

export default TechnologyItem;
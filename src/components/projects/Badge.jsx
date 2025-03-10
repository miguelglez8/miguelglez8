const Badge = ({ text, color }) => (
    <span
        style={{
            backgroundColor: color,
            color: "#fff",
            padding: "5px 10px",
            borderRadius: "20px",
            fontSize: "14px",
        }}
    >
    {text}
  </span>
);

export default Badge;
import {technologiesColors} from "../../data/colors.js";

const TechnologyItem = ({ name }) => (
    <span
        style={{
            backgroundColor: technologiesColors[name] || "#ccc",
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
import { technologiesColors } from "../../data/colors.js";
import { useTranslation } from "react-i18next";

import { FaDocker, FaJava, FaAws, FaGit } from "react-icons/fa";
import { SiSpringboot, SiMysql } from "react-icons/si";

const logoMapping = {
    java: <FaJava />,
    spring_boot: <SiSpringboot />,
    rest_apis: "🌐",
    microservices: "🧩",
    docker: <FaDocker />,
    git: <FaGit />,
    sql: <SiMysql />,
    aws: <FaAws />,
};

const TechnologyItem = ({ id }) => {
    const { t } = useTranslation();

    return (
        <span
            style={{
                backgroundColor: technologiesColors[id] || "#ccc",
                color: "#fff",
                padding: "8px 16px",
                borderRadius: "20px",
                fontSize: "14px",
                fontWeight: "bold",
                display: "flex",
                alignItems: "center",
                gap: "8px",
            }}
        >
      {logoMapping[id]}
            <span>{t(`About.technologies.${id}`)}</span>
    </span>
    );
};

export default TechnologyItem;
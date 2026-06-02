import { technologiesColors } from "../../data/colors.js";
import { useTranslation } from "react-i18next";

import { FaDocker, FaJava, FaGit } from "react-icons/fa";
import {
    SiSpringboot,
    SiMysql,
    SiReact,
    SiJavascript,
    SiApachekafka,
    SiJunit5,
} from "react-icons/si";
import { TbApi } from "react-icons/tb";
import { FaFileCode } from "react-icons/fa";

const logoMapping = {
    java: <FaJava />,
    spring_boot: <SiSpringboot />,
    react: <SiReact />,
    javascript: <SiJavascript />,
    rest_apis: <TbApi />,
    microservices: "🧩",
    kafka: <SiApachekafka />,
    sql: <SiMysql />,
    git: <FaGit />,
    docker: <FaDocker />,
    junit: <SiJunit5 />,
    jsp: <FaFileCode />,
    liferay: "🏢"
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

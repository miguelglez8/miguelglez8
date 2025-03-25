import { technologiesColors } from "../../data/colors.js";

import { FaDocker, FaJava, FaAws, FaReact, FaGit, FaGithub, FaRegCheckCircle, FaCogs } from 'react-icons/fa';
import { SiSpringboot, SiFirebase, SiKotlin, SiAndroid, SiHtml5, SiJavascript, SiMysql, SiMongodb } from 'react-icons/si';

const logoMapping = {
    JavaScript: <SiJavascript />,
    React: <FaReact />,
    HTML: <SiHtml5 />,
    Java: <FaJava />,
    "Spring Boot": <SiSpringboot />,
    Docker: <FaDocker />,
    AWS: <FaAws />,
    Firebase: <SiFirebase />,
    MySQL: <SiMysql />,
    MongoDB: <SiMongodb />,
    Git: <FaGit />,
    GitHub: <FaGithub />,
    Android: <SiAndroid />,
    Kotlin: <SiKotlin />,
    JUnit: <FaRegCheckCircle />,
    JMeter: <FaCogs />,
};

const TechnologyItem = ({ name }) => {
    return (
        <span
            style={{
                backgroundColor: technologiesColors[name] || "#ccc",
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
            {logoMapping[name]}
            <span>{name}</span>
    </span>
    );
};

export default TechnologyItem;

import { technologiesColors } from "../../data/colors.js";

import { FaDocker, FaJava, FaAws, FaReact, FaGit, FaGithub, FaRegCheckCircle, FaCogs } from 'react-icons/fa';
import { SiSpringboot, SiFirebase, SiHtml5, SiJavascript, SiMysql, SiMongodb, SiCss3, SiLiferay, SiApachejsp, SiElasticsearch } from "react-icons/si";

const logoMapping = {
  Java: <FaJava />,
  Liferay: <SiLiferay />,
  JSP: <SiApachejsp />,
  React: <FaReact />,
  JavaScript: <SiJavascript />,
  HTML: <SiHtml5 />,
  CSS: <SiCss3 />,
  Docker: <FaDocker />,
  Elasticsearch: <SiElasticsearch />,
  Git: <FaGit />,
  GitHub: <FaGithub />,
  MySQL: <SiMysql />,
  MongoDB: <SiMongodb />,
  AWS: <FaAws />,
  Firebase: <SiFirebase />,
  "Spring Boot": <SiSpringboot />
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


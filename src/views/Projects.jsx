import { useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import ProjectCard from "../components/projects/ProjectCard.jsx";
import cv from "../data/cv.js";
import { Calendar, CheckCircle, Wrench } from "lucide-react";
import { statusColors, techColors } from "../data/colors.js";
import { JOB_STATUS } from "../data/constants.js";

const statusIconMapping = {
    [JOB_STATUS.in_progress]: <Calendar size={18} color={statusColors.in_progress} />,
    [JOB_STATUS.finished]: <CheckCircle size={18} color={statusColors.finished} />,
    [JOB_STATUS.current]: <Wrench size={18} color={statusColors.current} />,
};

const Projects = () => {
    const { t } = useTranslation();
    const [isMobile, setIsMobile] = useState(window.innerWidth <= 768);

    useEffect(() => {
        const handleResize = () => setIsMobile(window.innerWidth <= 768);
        window.addEventListener("resize", handleResize);
        return () => window.removeEventListener("resize", handleResize);
    }, []);

    return (
        <section
            style={{
                display: "flex",
                flexDirection: "column",
                gap: "20px",
                padding: "20px",
                alignItems: "center",
                justifyContent: "center",
            }}
        >
            <h2
                style={{
                    fontSize: "24px",
                    fontWeight: "bold",
                    marginBottom: "20px",
                    textAlign: "center",
                    width: "100%",
                }}
            >
                {t("Projects.title")}
            </h2>

            <div
                style={{
                    display: "flex",
                    flexDirection: isMobile ? "column" : "row",
                    gap: "20px",
                    justifyContent: "center",
                    width: "100%",
                    flexWrap: "wrap"
                }}
            >
                {cv.projects.map((project, index) => (
                    <ProjectCard
                        key={index}
                        image={project.image}
                        title={t(`Projects.projects.${project.id}.title`)}
                        role={t(`Projects.projects.${project.id}.role`)}
                        date={t(`Projects.projects.${project.id}.date`)}
                        status={t(`Projects.status.${project.status}`)}
                        statusColor={statusColors[project.status]}
                        technologies={project.technologies.map((tech) => ({
                            text: tech.text,
                            color: techColors[tech.text] || "#000",
                        }))}
                        links={project.links}
                        statusIcon={statusIconMapping[project.status]}
                        isMobile={isMobile}
                    />
                ))}
            </div>
        </section>
    );
};

export default Projects;
